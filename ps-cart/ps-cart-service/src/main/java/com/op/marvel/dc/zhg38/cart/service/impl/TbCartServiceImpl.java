package com.op.marvel.dc.zhg38.cart.service.impl;

import com.op.marvel.dc.zhg38.cart.service.ITbCartService;
import com.op.marvel.dc.zhg38.common.mapper.TbCartMapper;
import com.op.marvel.dc.zhg38.common.mapper.TbItemMapper;
import com.op.marvel.dc.zhg38.common.pojo.TbCart;
import com.op.marvel.dc.zhg38.common.pojo.TbItem;
import com.op.marvel.dc.zhg38.common.source.utils.JsonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 10:47 on 2018/5/4.
 */
@Service
public class TbCartServiceImpl implements ITbCartService {
    @Autowired
    private TbCartMapper cartMapper;
    @Autowired
    private TbItemMapper itemMapper;
    /**
     * 将cookie中商品列表转换成购物车
     *
     * @param cart_in_cookie_value
     * @return
     */
    @Override
    public List<TbCart> queryCartList(String cart_in_cookie_value) throws  Exception{
        List<TbCart> returnList=null;
        if (StringUtils.isEmpty(cart_in_cookie_value)){
            returnList= new ArrayList<>();
        }else {
            returnList = JsonUtil.json2List(cart_in_cookie_value, TbCart.class);
        }
        return returnList;
    }

    /**
     * 根据用户id获取用户购物车
     *
     * @param userId
     * @return
     */
    @Override
    public List<TbCart> queryCartList(Long userId)throws  Exception {
        return this.cartMapper.selectCartsByUserId(userId);
    }

    /**
     * 将选择的商品添加离线购物车中
     *
     * @param itemId
     * @param cart_in_cookie_value
     * @return
     */
    @Override
    public List<TbCart> addItemToCart(Long itemId, String cart_in_cookie_value)throws  Exception {
        List<TbCart> carts = this.queryCartList(cart_in_cookie_value);
        TbCart cart=null;
        if (CollectionUtils.isNotEmpty(carts)){
            for (TbCart c : carts) {
                if (c.getItemId().longValue()==itemId.longValue()){
                    cart=c;
                }
            }

        }
        Date current=new Date();
        if (null==cart){
            //获取商品信息
            TbItem tbItem = this.itemMapper.selectByPrimaryKey(itemId);
            if (tbItem!=null){
                cart=new TbCart();

                cart.setCreated(current);
                cart.setUpdated(current);
                cart.setItemId(itemId);
                cart.setNum(1);

                cart.setItemTitle(tbItem.getTitle());
                cart.setItemPrice(tbItem.getPrice());
                String str_image = tbItem.getImage();
                if(StringUtils.isNotEmpty(str_image)){
                    String[] images = StringUtils.split(str_image, ",");
                    if(images!=null&&images.length!=0){
                        cart.setItemImage(images[0]);
                    }
                }
                carts.add(cart);

            }



        }else{//用户还没有购物车
            cart.setNum(cart.getNum()+1);
            cart.setUpdated(current);
        }


        return carts;
    }

    /**
     * 将选择的商品添加到购物中,并持久化
     *
     * @param itemId
     * @return
     */
    @Override
    public void addItemToCart(Long userId,Long itemId)throws  Exception {
        Date current=new Date();
        //根据用户id和商品id 获取对应的购物车
        TbCart cart= this.cartMapper.selectCartByUserIdAndItemId(userId,itemId);

        if (null==cart){
            //获取商品信息
            TbItem tbItem = this.itemMapper.selectByPrimaryKey(itemId);
            if (tbItem!=null){
                cart=new TbCart();

                cart.setCreated(current);
                cart.setUpdated(current);
                cart.setItemId(itemId);
                cart.setUserId(userId);
                cart.setNum(1);

                cart.setItemTitle(tbItem.getTitle());
                cart.setItemPrice(tbItem.getPrice());
                String str_image = tbItem.getImage();
                if(StringUtils.isNotEmpty(str_image)){
                    String[] images = StringUtils.split(str_image, ",");
                    if(images!=null&&images.length!=0){
                        cart.setItemImage(images[0]);
                    }
                }
                this.cartMapper.insertSelective(cart);

            }



        }else{//用户还没有购物车
            cart.setNum(cart.getNum()+1);
            cart.setUpdated(current);
            this.cartMapper.updateByPrimaryKeySelective(cart);
        }

    }
}
