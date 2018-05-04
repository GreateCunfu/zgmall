package com.op.marvel.dc.zhg38.common.mapper;


import com.op.marvel.dc.zhg38.common.annotion.Myppers;
import com.op.marvel.dc.zhg38.common.pojo.TbItem;

import java.util.List;

@Myppers
public interface TbItemMapper {
    int deleteByPrimaryKey(Long id)throws  Exception;

    int insert(TbItem record)throws  Exception;

    int insertSelective(TbItem record)throws  Exception;

    TbItem selectByPrimaryKey(Long id)throws  Exception;

    int updateByPrimaryKeySelective(TbItem record)throws  Exception;

    int updateByPrimaryKey(TbItem record)throws  Exception;

    /**
     * 查询商品列表
     * @return
     */
    List<TbItem> selectItemList()throws  Exception;
}