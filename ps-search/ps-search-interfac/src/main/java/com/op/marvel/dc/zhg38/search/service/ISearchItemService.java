package com.op.marvel.dc.zhg38.search.service;

import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 11:44 on 2018/5/3.
 */
public interface ISearchItemService {
    /**
     * 将商品信息导入索引库中
     * @return
     */
    ResultInfo importItemsToIndex() throws  Exception;
}
