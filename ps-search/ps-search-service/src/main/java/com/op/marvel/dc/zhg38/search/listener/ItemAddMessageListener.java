package com.op.marvel.dc.zhg38.search.listener;

import com.op.marvel.dc.zhg38.common.mapper.SearchItemMapper;
import com.op.marvel.dc.zhg38.common.pojo.SearchItem;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 15:34 on 2018/5/3.
 */
public class ItemAddMessageListener implements MessageListener {
    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private CloudSolrClient cloudSolrClient;
    @Override
    public void onMessage(Message message)  {
        try {
            //从消息中获取商品id
            TextMessage textMessage=  (TextMessage)message;
            String text = textMessage.getText();
            long itemId=Long.parseLong(text);
            //根据商品id查询数据，取商品信息
            //等待事务提交
            Thread.sleep(1000);
            SearchItem searchItem = searchItemMapper.getItemById(itemId);
            //创建文档对象
            SolrInputDocument document = new SolrInputDocument();
            //向文档对象中添加域
            document.addField("id", searchItem.getId());
            document.addField("item_title", searchItem.getTitle());
            document.addField("item_sell_point", searchItem.getSellPoint());
            document.addField("item_price", searchItem.getPrice());
            document.addField("item_image", searchItem.getImage());
            document.addField("item_category_name", searchItem.getCategoryName());
            document.addField("item_desc", searchItem.getItemDesc());
            //把文档对象写入索引库
            cloudSolrClient.add(document);
            //提交
            cloudSolrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
