package com.op.marvel.dc.zhg38.cart.service;

import com.op.marvel.dc.zhg38.common.pojo.TbCart;

import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 10:46 on 2018/5/4.
 */
public interface ITbCartService {
    /**
     * 将cookie中商品列表转换成购物车
     * @param cart_in_cookie_value
     * @return
     */
    List<TbCart> queryCartList(String cart_in_cookie_value) throws  Exception;

    /**
     * 根据用户id获取用户购物车
     * @param userId
     * @return
     */
    List<TbCart> queryCartList(Long userId)throws  Exception;

    /**
     * 将选择的商品添加离线购物车中
     * @param itemId
     * @param cart_in_cookie_value
     * @return
     */
    List<TbCart> addItemToCart(Long itemId, String cart_in_cookie_value)throws  Exception;

    /**
     * 将选择的商品添加到购物中,并持久化
     * @param userId
     * @param itemId
     * @return
     */
    void addItemToCart(Long userId, Long itemId)throws  Exception;
}
