package com.op.marvel.dc.zhg38.search.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description: 接收Activemq 发送的消息
 * @Date Created in 15:31 on 2018/5/3.
 */
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

        try {
            TextMessage textMessage=  (TextMessage)message;
            String text = textMessage.getText();
            System.out.println(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
