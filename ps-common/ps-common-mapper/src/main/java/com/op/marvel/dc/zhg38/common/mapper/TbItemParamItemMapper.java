package com.op.marvel.dc.zhg38.common.mapper;


import com.op.marvel.dc.zhg38.common.annotion.Myppers;
import com.op.marvel.dc.zhg38.common.pojo.TbItemParamItem;

@Myppers
public interface TbItemParamItemMapper {
    int deleteByPrimaryKey(Long id)throws  Exception;

    int insert(TbItemParamItem record)throws  Exception;

    int insertSelective(TbItemParamItem record)throws  Exception;

    TbItemParamItem selectByPrimaryKey(Long id)throws  Exception;

    int updateByPrimaryKeySelective(TbItemParamItem record)throws  Exception;

    int updateByPrimaryKeyWithBLOBs(TbItemParamItem record)throws  Exception;

    int updateByPrimaryKey(TbItemParamItem record)throws  Exception;
}