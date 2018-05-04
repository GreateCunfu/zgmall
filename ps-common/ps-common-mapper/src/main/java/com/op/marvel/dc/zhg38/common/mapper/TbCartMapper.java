package com.op.marvel.dc.zhg38.common.mapper;


import com.op.marvel.dc.zhg38.common.annotion.Myppers;
import com.op.marvel.dc.zhg38.common.pojo.TbCart;

import java.util.List;

@Myppers
public interface TbCartMapper {

    int deleteByPrimaryKey(Long id) throws  Exception;

    int insert(TbCart record)throws  Exception;

    int insertSelective(TbCart record)throws  Exception;

    TbCart selectByPrimaryKey(Long id)throws  Exception;

    int updateByPrimaryKeySelective(TbCart record)throws  Exception;

    int updateByPrimaryKey(TbCart record)throws  Exception;

    /**
     * 根据用户id获取用户的购物车
     * @param userId
     * @return
     */
    List<TbCart> selectCartsByUserId(Long userId)throws  Exception;

    /**
     * 根据用户id和itemId 获取对应的购物车
     * @param userId
     * @param itemId
     * @return
     */
    TbCart selectCartByUserIdAndItemId(Long userId, Long itemId)throws  Exception;
}