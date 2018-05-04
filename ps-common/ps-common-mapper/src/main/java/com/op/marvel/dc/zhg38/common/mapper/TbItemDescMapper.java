package com.op.marvel.dc.zhg38.common.mapper;


import com.op.marvel.dc.zhg38.common.annotion.Myppers;
import com.op.marvel.dc.zhg38.common.pojo.TbItemDesc;

@Myppers
public interface TbItemDescMapper {
    int insert(TbItemDesc record)throws  Exception;

    int insertSelective(TbItemDesc record)throws  Exception;

    TbItemDesc selectItemDescByItemId(Long itemId)throws  Exception;
}