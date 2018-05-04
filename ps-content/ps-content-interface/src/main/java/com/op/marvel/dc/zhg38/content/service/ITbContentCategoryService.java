package com.op.marvel.dc.zhg38.content.service;


import com.op.marvel.dc.zhg38.common.source.bean.vo.EasyUITreeNode;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;

import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 17:12 on 28/03/2018.
 */
public interface ITbContentCategoryService {

    /**
     * 根据 parentId 获取ContentCategory 列表
     * @param parentId
     * @return
     */
    List<EasyUITreeNode> getContentCategoryList(long parentId) throws  Exception;

    /**
     * 根据 parentId 和name 获取 获取ContentCategory对象
     * @param parentId
     * @param name
     * @return
     */
    ResultInfo addContentCategory(Long parentId, String name) throws Exception;
}
