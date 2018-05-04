package com.op.marvel.dc.zhg38.common.mapper;


import com.op.marvel.dc.zhg38.common.annotion.Myppers;
import com.op.marvel.dc.zhg38.common.pojo.TbOrderItem;

@Myppers
public interface TbOrderItemMapper {

    int insert(TbOrderItem record)throws  Exception;

    int insertSelective(TbOrderItem record)throws  Exception;
}