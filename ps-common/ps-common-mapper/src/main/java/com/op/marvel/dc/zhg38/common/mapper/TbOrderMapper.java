package com.op.marvel.dc.zhg38.common.mapper;


import com.op.marvel.dc.zhg38.common.annotion.Myppers;
import com.op.marvel.dc.zhg38.common.pojo.TbOrder;

@Myppers
public interface TbOrderMapper {

    int insert(TbOrder record)throws  Exception;

    int insertSelective(TbOrder record)throws  Exception;
}