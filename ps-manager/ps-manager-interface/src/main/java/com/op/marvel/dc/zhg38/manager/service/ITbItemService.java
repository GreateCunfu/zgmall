package com.op.marvel.dc.zhg38.manager.service;

import com.op.marvel.dc.zhg38.common.pojo.TbItem;
import com.op.marvel.dc.zhg38.common.pojo.TbItemDesc;
import com.op.marvel.dc.zhg38.common.source.bean.dto.PageBeanDto;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 18:20 on 2018/5/2.
 */
public interface ITbItemService {
    /**
     * 分页查询商品列表
     * @param page
     * @param rows
     * @return
     */
    PageBeanDto<TbItem> queryItemPageList(Integer page, Integer rows)throws  Exception;

    /**
     * 根据itemId 查询商品信息
     * @param itemId
     * @return
     */
    TbItem queryItemById(Long itemId)throws  Exception;

    /**
     * 根据itemId 查询商品描述
     * @param itemId
     * @return
     */
    TbItemDesc queryItemDescByItemId(Long itemId)throws  Exception;


}
