package com.op.marvel.dc.zhg38.common.mapper;

import com.op.marvel.dc.zhg38.common.annotion.Myppers;
import com.op.marvel.dc.zhg38.common.pojo.TbContentCategory;

import java.util.List;

@Myppers
public interface TbContentCategoryMapper {
    int deleteByPrimaryKey(Long id)throws  Exception;

    int insert(TbContentCategory record)throws  Exception;

    int insertSelective(TbContentCategory record)throws  Exception;

    TbContentCategory selectByPrimaryKey(Long id)throws  Exception;

    int updateByPrimaryKeySelective(TbContentCategory record)throws  Exception;

    int updateByPrimaryKey(TbContentCategory record)throws  Exception;

    /**
     * 根据 parentId 获取ContentCategory 列表
     * @param parentId
     * @return
     */
    List<TbContentCategory> selectContentCategoryListByParentId(long parentId)throws  Exception;
}