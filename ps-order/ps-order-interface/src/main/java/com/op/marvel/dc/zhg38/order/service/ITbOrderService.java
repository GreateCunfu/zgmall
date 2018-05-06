package com.op.marvel.dc.zhg38.order.service;

import com.op.marvel.dc.zhg38.common.source.bean.vo.OrderInfo;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 13:57 on 2018/5/4.
 */
public interface ITbOrderService {

    ResultInfo createOrder(OrderInfo orderInfo)throws Exception;
}
