package com.op.marvel.dc.zhg38.content.service;


import com.op.marvel.dc.zhg38.common.pojo.TbContent;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;

import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 18:02 on 28/03/2018.
 */
public interface  ITbContentService {


    ResultInfo addContent(TbContent content)throws  Exception;

    List<TbContent> getContentByCid(long cid) throws Exception;
}
