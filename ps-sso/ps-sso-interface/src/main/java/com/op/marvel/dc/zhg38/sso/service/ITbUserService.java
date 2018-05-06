package com.op.marvel.dc.zhg38.sso.service;

import com.op.marvel.dc.zhg38.common.pojo.TbUser;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 17:27 on 2018/5/3.
 */
public interface ITbUserService {
    /**
     *检查数据是否可用
     * @param data
     * @param type
     * @return
     */
    ResultInfo checkData(String data, int type);

    /**
     * 用户注册
     * @param user
     * @return
     */
    ResultInfo register(TbUser user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    ResultInfo login(String username, String password);

    /**
     * 通过token查询用户信息
     * @param token
     * @return
     */
    ResultInfo getUserByToken(String token);
}
