package com.op.marvel.dc.zhg38.cart.controller;

import com.op.marvel.dc.zhg38.cart.service.ITbCartService;
import com.op.marvel.dc.zhg38.common.pojo.TbCart;
import com.op.marvel.dc.zhg38.common.pojo.TbUser;
import com.op.marvel.dc.zhg38.common.source.threadlocal.UserThreadLoacal;
import com.op.marvel.dc.zhg38.common.source.utils.CookieUtils;
import com.op.marvel.dc.zhg38.common.source.utils.JsonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 11:34 on 2018/5/4.
 */
@Controller
@RequestMapping("cart")
public class CartController {

    @Value("${COOKIE_CART}")
    private  String COOKIE_CART;

    @Value("${COOKIE_EXPIRE_TIME}")
    private  Integer COOKIE_EXPIRE_TIME;

    @Autowired
    private ITbCartService cartService;

    /**
     * 加入商品到购物车
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "{itemId}",method = RequestMethod.GET)
    public String addItemToCart(@PathVariable("itemId") Long itemId, HttpServletRequest request, HttpServletResponse response)throws  Exception{
        TbUser user = UserThreadLoacal.get();

        if(null==user){
            //未登录
            //未登录 从cookie中获取商品列表
            String cart_in_cookie_value = CookieUtils.getCookieValue(request, COOKIE_CART, true);
            List<TbCart> carts=this.cartService.addItemToCart(itemId,cart_in_cookie_value);
            if(CollectionUtils.isNotEmpty(carts)){
                saveCartsToCookie(request,response,carts);
            }
        }else {
            //登录状态
            this.cartService.addItemToCart(user.getId(),itemId);
        }

        return "redirect:/cart/list.html";
    }

    private void saveCartsToCookie(HttpServletRequest request, HttpServletResponse response, List<TbCart> carts) throws  Exception{
        String jsoncart=JsonUtil.obj2Json(carts);
        //将购物车数据写入cookie中
        CookieUtils.setCookie(request,response,COOKIE_CART,jsoncart,COOKIE_EXPIRE_TIME,true);
    }

    /**
     * 获取购物车列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String showCartList(HttpServletRequest request, Model model)throws  Exception{
        TbUser user = UserThreadLoacal.get();
        List<TbCart> cartList=null;
        if (null==user){
            //未登录 从cookie中获取商品列表
            String cart_in_cookie_value = CookieUtils.getCookieValue(request, COOKIE_CART, true);
            cartList=this.cartService.queryCartList(cart_in_cookie_value);
        }else {
            //登录状态
            cartList=this.cartService.queryCartList(user.getId());
        }
        model.addAttribute("cartList",cartList);
        return "cart";
    }
}
