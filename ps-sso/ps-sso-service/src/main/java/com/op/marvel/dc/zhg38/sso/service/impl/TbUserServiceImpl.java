package com.op.marvel.dc.zhg38.sso.service.impl;

import com.op.marvel.dc.zhg38.common.mapper.TbUserMapper;
import com.op.marvel.dc.zhg38.common.pojo.TbUser;
import com.op.marvel.dc.zhg38.common.pojo.TbUserExample;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;
import com.op.marvel.dc.zhg38.common.source.context.Constants;
import com.op.marvel.dc.zhg38.common.source.jedis.JedisClientPool;
import com.op.marvel.dc.zhg38.common.source.utils.JsonUtil;
import com.op.marvel.dc.zhg38.sso.service.ITbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 17:32 on 2018/5/3.
 */
@Service
public class TbUserServiceImpl implements ITbUserService {

    @Value("${USER_SESSION}")
    private String USER_SESSION;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private JedisClientPool jedisClient;
    /**
     * 检查数据是否可用
     *
     * @param data
     * @param type
     * @return
     */
    @Override
    public ResultInfo checkData(String data, int type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        //1.判断用户名是否可用
        if (type == 1) {
            criteria.andUsernameEqualTo(data);
            //2判断手机号是否可以使用
        } else if (type == 2) {
            criteria.andPhoneEqualTo(data);
            //3判断邮箱是否可以使用
        } else if (type == 3) {
            criteria.andEmailEqualTo(data);
        } else {
            return ResultInfo.build(Constants.BAD_PARAM, "参数中包含非法数据");
        }
        //执行查询
        List<TbUser> list = userMapper.selectByExample(example);
        if (list !=null && list.size() > 0) {
            //查询到数据，返回false
            return ResultInfo.ok(false);
        }
        //数据可以使用
        return ResultInfo.ok(true);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public ResultInfo register(TbUser user) {
        //检查数据的有效性
        if (StringUtils.isBlank(user.getUsername())) {
            return ResultInfo.build(Constants.BAD_PARAM, "用户名不能为空");
        }
        //判断用户名是否重复
        ResultInfo taotaoResult = checkData(user.getUsername(), 1);
        if (!(boolean) taotaoResult.getData()) {
            return ResultInfo.build(Constants.BAD_PARAM, "用户名重复");
        }
        //判断密码是否为空
        if (StringUtils.isBlank(user.getPassword())) {
            return ResultInfo.build(Constants.BAD_PARAM, "密码不能为空");
        }
        if (StringUtils.isNotBlank(user.getPhone())) {
            //是否重复校验
            taotaoResult = checkData(user.getPhone(), 2);
            if (!(boolean) taotaoResult.getData()) {
                return ResultInfo.build(Constants.BAD_PARAM, "电话号码重复");
            }
        }
        //如果email不为空的话进行是否重复校验
        if (StringUtils.isNotBlank(user.getEmail())) {
            //是否重复校验
            taotaoResult = checkData(user.getEmail(), 3);
            if (!(boolean) taotaoResult.getData()) {
                return ResultInfo.build(Constants.BAD_PARAM, "email重复");
            }
        }
        //补全pojo的属性
        user.setCreated(new Date());
        user.setUpdated(new Date());
        //密码要进行md5加密
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        //插入数据
        userMapper.insert(user);
        //返回注册成功
        return ResultInfo.ok();
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResultInfo login(String username, String password) {
        //判断用户名和密码是否正确
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            //返回登录失败
            return ResultInfo.build(400, "用户名或密码不正确");
        }
        TbUser user = list.get(0);
        //密码要进行md5加密然后再校验
        if (!DigestUtils.md5DigestAsHex(password.getBytes())
                .equals(user.getPassword())) {
            //返回登录失败
            return ResultInfo.build(400, "用户名或密码不正确");
        }
        //生成token，使用uuid
        String token = UUID.randomUUID().toString();
        //清空密码
        user.setPassword(null);
        //把用户信息保存到redis，key就是token，value就是用户信息
        jedisClient.set(USER_SESSION + ":" + token, JsonUtil.obj2Json(user));
        //设置key的过期时间
        jedisClient.expire(USER_SESSION + ":" + token, SESSION_EXPIRE);
        //返回登录成功，其中要把token返回。
        return ResultInfo.ok(token);
    }

    /**
     * 通过token查询用户信息
     *
     * @param token
     * @return
     */
    @Override
    public ResultInfo getUserByToken(String token) {
        String json = jedisClient.get(USER_SESSION + ":" + token);
        if (StringUtils.isBlank(json)) {
            return ResultInfo.build(400, "用户登录已经过期");
        }
        //重置Session的过期时间
        jedisClient.expire(USER_SESSION + ":" + token, SESSION_EXPIRE);
        //把json转换成User对象
        TbUser user = JsonUtil.json2Object(json, TbUser.class);
        return ResultInfo.ok(user);
        //return ResultInfo.ok(json);
    }
}
