package com.op.marvel.dc.zhg38.content.service.impl;

import com.op.marvel.dc.zhg38.common.mapper.TbContentMapper;
import com.op.marvel.dc.zhg38.common.pojo.TbContent;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;
import com.op.marvel.dc.zhg38.common.source.jedis.JedisClientPool;
import com.op.marvel.dc.zhg38.common.source.utils.JsonUtil;
import com.op.marvel.dc.zhg38.content.service.ITbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 18:04 on 28/03/2018.
 */
@Service
public class TbContentServiceImpl implements ITbContentService {

    @Autowired
    private TbContentMapper tbcontentMapper;
    @Autowired
    private JedisClientPool jedisClient;

    @Value("INDEX_CONTENT")
    private  String  INDEX_CONTENT;

    @Override
    public ResultInfo addContent(TbContent content) throws  Exception {
        //补全pojo的属性
        content.setCreated( new Date());
        content.setUpdated(new Date());
        //插入到内容表
        tbcontentMapper.insert(content);
        jedisClient.hdel(INDEX_CONTENT,content.getCategoryId().toString());
        return ResultInfo.ok();
    }

    @Override
    public List<TbContent> getContentByCid(long cid) throws  Exception{
        String filed=String.valueOf(cid);
        try {
            String json = jedisClient.hget(INDEX_CONTENT, filed);
            if (StringUtils.isNotEmpty(json)){
                List<TbContent>   list = JsonUtil.json2List(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<TbContent> list = tbcontentMapper.selectontentByCid(cid);

        try {
            this.jedisClient.hset(INDEX_CONTENT,filed,JsonUtil.obj2Json(list));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
