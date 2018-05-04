package com.op.marvel.dc.zhg38.common.mapper;




import com.op.marvel.dc.zhg38.common.annotion.Myppers;
import com.op.marvel.dc.zhg38.common.pojo.TbItemCat;

import java.util.List;

@Myppers
public interface TbItemCatMapper {

    int deleteByPrimaryKey(Long id)throws  Exception;

    int insert(TbItemCat record)throws  Exception;

    int insertSelective(TbItemCat record)throws  Exception;

    TbItemCat selectByPrimaryKey(Long id)throws  Exception;

    int updateByPrimaryKeySelective(TbItemCat record)throws  Exception;

    int updateByPrimaryKey(TbItemCat record)throws  Exception;

    /**
     * 根据parentId 获取Itemcat 列表
     * @param parentId
     * @return
     */
    List<TbItemCat> selectItemCatByParentId(Long parentId)throws  Exception;
}