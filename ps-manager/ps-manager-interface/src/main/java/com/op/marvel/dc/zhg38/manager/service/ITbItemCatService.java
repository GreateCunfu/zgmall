package com.op.marvel.dc.zhg38.manager.service;


import com.op.marvel.dc.zhg38.common.source.bean.vo.EasyUITreeNode;

import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 18:23 on 2018/5/2.
 */
public interface ITbItemCatService {
    /**
     * 根据parentid 获取itemCat 列表信息
     * @param parentId
     * @return
     */
    List<EasyUITreeNode> getItemCatList(Long parentId)throws  Exception;


}
