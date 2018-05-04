package com.op.marvel.dc.zhg38.common.mapper;

import com.op.marvel.dc.zhg38.common.annotion.Myppers;
import com.op.marvel.dc.zhg38.common.pojo.TbContent;

import java.util.List;

@Myppers
public interface TbContentMapper {

    int deleteByPrimaryKey(Long id)throws  Exception;

    int insert(TbContent record)throws  Exception;

    int insertSelective(TbContent record)throws  Exception;

    TbContent selectByPrimaryKey(Long id)throws  Exception;

    int updateByPrimaryKeySelective(TbContent record)throws  Exception;

    int updateByPrimaryKeyWithBLOBs(TbContent record)throws  Exception;

    int updateByPrimaryKey(TbContent record)throws  Exception;

    List<TbContent> selectontentByCid(long cid)throws  Exception;
}