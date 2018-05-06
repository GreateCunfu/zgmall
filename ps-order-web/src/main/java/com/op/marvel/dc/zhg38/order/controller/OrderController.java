package com.op.marvel.dc.zhg38.order.controller;

import com.op.marvel.dc.zhg38.order.service.ITbOrderService;
import com.op.marvel.dc.zhg38.common.source.bean.vo.OrderInfo;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 14:25 on 2018/5/4.
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Value("${CART_KEY}")
    private String CART_KEY;

    @Autowired
    private ITbOrderService orderService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createOder(OrderInfo orderInfo, Model model) throws Exception{
        //生成订单
        ResultInfo result = orderService.createOrder(orderInfo);
        //返回逻辑视图
        model.addAttribute("orderId", result.getData().toString());
        model.addAttribute("payment", orderInfo.getPayment());
        //预计送达时间，预计三天后送达
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusDays(3);
        model.addAttribute("date", dateTime.toString("yyyy-MM-dd"));

        return "success";
    }
}