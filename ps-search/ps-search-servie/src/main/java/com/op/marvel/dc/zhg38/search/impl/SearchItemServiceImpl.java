package com.op.marvel.dc.zhg38.search.impl;

import com.op.marvel.dc.zhg38.common.mapper.SearchItemMapper;
import com.op.marvel.dc.zhg38.common.pojo.SearchItem;
import com.op.marvel.dc.zhg38.search.service.ISearchItemService;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;
import com.op.marvel.dc.zhg38.common.source.context.Constants;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 13:53 on 2018/5/3.
 */
@Service
public class SearchItemServiceImpl implements ISearchItemService {

    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private CloudSolrClient cloudSolrClient;
    /**
     * 将商品信息导入索引库中
     *
     * @return
     */
    @Override
    public ResultInfo importItemsToIndex() throws Exception {

        try {
            //1.先查询所有的商品数据
            List<SearchItem> itemList = this.searchItemMapper.getItemList();
            //2.遍历商品数据添加到索引库中
            List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
            for (SearchItem searchItem : itemList) {
                //创建文档对象
                SolrInputDocument document = new SolrInputDocument();
                //向文档中添加域
                document.addField("id", searchItem.getId());
                document.addField("itemTitle", searchItem.getTitle());
                document.addField("itemSellPoint", searchItem.getSellPoint());
                document.addField("itemPrice", searchItem.getPrice());
                document.addField("itemImage", searchItem.getImage());
                document.addField("itemCategoryName", searchItem.getCategoryName());
                document.addField("itemDesc", searchItem.getItemDesc());
                //把文档写入文档库中
                docs.add(document);

            }
            cloudSolrClient.add(docs);
            cloudSolrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultInfo.build(Constants.ERROR,Constants.ERROR_INDEX_MSG);
        }
        return ResultInfo.ok();
    }
}
