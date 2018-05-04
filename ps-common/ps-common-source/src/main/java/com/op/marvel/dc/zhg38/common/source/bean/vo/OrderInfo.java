package com.op.marvel.dc.zhg38.common.source.bean.vo;

import com.op.marvel.dc.zhg38.common.pojo.TbOrder;
import com.op.marvel.dc.zhg38.common.pojo.TbOrderItem;
import com.op.marvel.dc.zhg38.common.pojo.TbOrderShipping;

import java.io.Serializable;
import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 13:54 on 2018/5/4.
 */
public class OrderInfo extends TbOrder implements Serializable {

    private List<TbOrderItem> orderItems;

    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
