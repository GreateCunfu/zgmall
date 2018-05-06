package com.op.marvel.dc.zhg38.search.service;

import com.op.marvel.dc.zhg38.common.source.bean.vo.SearchResult;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 13:49 on 2018/5/3.
 */
public interface ISearchService {
    /**
     * 分页查询索引库
     * @param searchKey
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    SearchResult search(String searchKey, Integer page, Integer rows)throws  Exception;
}
