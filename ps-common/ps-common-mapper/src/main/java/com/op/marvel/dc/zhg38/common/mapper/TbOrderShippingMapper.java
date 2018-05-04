package com.op.marvel.dc.zhg38.common.mapper;


import com.op.marvel.dc.zhg38.common.annotion.Myppers;
import com.op.marvel.dc.zhg38.common.pojo.TbOrderShipping;

@Myppers
public interface TbOrderShippingMapper {

    int deleteByPrimaryKey(String orderId)throws  Exception;

    int insert(TbOrderShipping record)throws  Exception;

    int insertSelective(TbOrderShipping record)throws  Exception;

    TbOrderShipping selectByPrimaryKey(String orderId)throws  Exception;

    int updateByPrimaryKeySelective(TbOrderShipping record)throws  Exception;

    int updateByPrimaryKey(TbOrderShipping record)throws  Exception;
}