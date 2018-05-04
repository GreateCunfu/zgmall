package com.op.marvel.dc.zhg38.common.mapper;


import com.op.marvel.dc.zhg38.common.annotion.Myppers;
import com.op.marvel.dc.zhg38.common.pojo.TbItemParam;

@Myppers
public interface TbItemParamMapper {
    int deleteByPrimaryKey(Long id)throws  Exception;

    int insert(TbItemParam record)throws  Exception;

    int insertSelective(TbItemParam record)throws  Exception;

    TbItemParam selectByPrimaryKey(Long id)throws  Exception;

    int updateByPrimaryKeySelective(TbItemParam record)throws  Exception;

    int updateByPrimaryKeyWithBLOBs(TbItemParam record)throws  Exception;

    int updateByPrimaryKey(TbItemParam record)throws  Exception;
}