package com.op.marvel.dc.zhg38.order.service.impl;

import com.op.marvel.dc.zhg38.common.mapper.TbOrderItemMapper;
import com.op.marvel.dc.zhg38.common.mapper.TbOrderMapper;
import com.op.marvel.dc.zhg38.common.mapper.TbOrderShippingMapper;
import com.op.marvel.dc.zhg38.common.pojo.TbOrderItem;
import com.op.marvel.dc.zhg38.common.pojo.TbOrderShipping;
import com.op.marvel.dc.zhg38.order.service.ITbOrderService;
import com.op.marvel.dc.zhg38.common.source.bean.vo.OrderInfo;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;
import com.op.marvel.dc.zhg38.common.source.jedis.JedisClientPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 13:58 on 2018/5/4.
 */
@Service
public class TbOrderServiceImpl implements ITbOrderService {

    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;
    @Autowired
    private JedisClientPool jedisClient;

    @Value("${ORDER_ID_GEN_KEY}")
    private String ORDER_ID_GEN_KEY;
    @Value("${ORDER_ID_BEGIN_VALUE}")
    private String ORDER_ID_BEGIN_VALUE;
    @Value("${ORDER_ITEM_ID_GEN_KEY}")
    private String ORDER_ITEM_ID_GEN_KEY;

    @Override
    public ResultInfo createOrder(OrderInfo orderInfo) throws Exception {
        //生成订单号,可以使用redis的incr生成
        if (!jedisClient.exists(ORDER_ID_GEN_KEY)) {
            //设置初始值
            jedisClient.set(ORDER_ID_GEN_KEY, ORDER_ID_BEGIN_VALUE);
        }
        String orderId = jedisClient.incr(ORDER_ID_GEN_KEY).toString();
        //向订单表插入数据，需要补全pojo的属性
        orderInfo.setOrderId(orderId);
        //免邮费
        orderInfo.setPostFee("0");
        //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setStatus(1);
        //订单创建时间
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        //向订单 表插入数据
        orderMapper.insert(orderInfo);
        //向订单明细表插入数据。
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem tbOrderItem : orderItems) {
            //获得明细主键
            String oid = jedisClient.incr(ORDER_ITEM_ID_GEN_KEY).toString();
            tbOrderItem.setItemId(oid);
            tbOrderItem.setOrderId(orderId);
            //插入明细数据
            orderItemMapper.insert(tbOrderItem);
        }
        //向订单物流表插入数据
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        orderShippingMapper.insert(orderShipping);
        //返回订单号
        return ResultInfo.ok(orderId);
    }
}
