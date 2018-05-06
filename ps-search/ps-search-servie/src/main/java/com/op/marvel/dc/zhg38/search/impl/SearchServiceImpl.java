package com.op.marvel.dc.zhg38.search.impl;

import com.op.marvel.dc.zhg38.common.pojo.SearchItem;
import com.op.marvel.dc.zhg38.search.service.ISearchService;
import com.op.marvel.dc.zhg38.common.source.bean.vo.SearchResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 13:51 on 2018/5/3.
 */
@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private CloudSolrClient cloudSolrClient;
    /**
     * 分页查询索引库
     *
     * @param searchKey
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @Override
    public SearchResult search(String searchKey, Integer page, Integer rows) throws Exception {
        //根据查询条件拼装查询对象
        //创建一个SolrQuery对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery(searchKey);
        //设置分页条件
        if (page < 1) page =1;
        query.setStart((page - 1) * rows);
        if (rows < 1) rows = 10;
        query.setRows(rows);
        //设置默认搜索域
        query.set("df", "item_title");
        //设置高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
        //调用dao执行查询
        SearchResult searchResult = this.search(query);
        //计算查询结果的总页数
        long recordCount = searchResult.getRecordCount();
        long pages =  recordCount / rows;
        if (recordCount % rows > 0) {
            pages++;
        }
        searchResult.setTotalPages(pages);
        //返回结果
        return searchResult;
    }

    private SearchResult search( SolrQuery query) throws Exception{
        //根据query对象进行查询
        QueryResponse response = cloudSolrClient.query(query);
        //取查询结果
        SolrDocumentList solrDocumentList = response.getResults();
        //取查询结果总记录数
        long numFound = solrDocumentList.getNumFound();
        SearchResult result = new SearchResult();
        result.setRecordCount(numFound);
        List<SearchItem> itemList = new ArrayList<>();
        //把查询结果封装到SearchItem对象中
        for (SolrDocument solrDocument : solrDocumentList) {
            SearchItem item = new SearchItem();
            item.setCategoryName((String) solrDocument.get("item_category_name"));
            item.setId((String) solrDocument.get("id"));
            //取一张图片
            String image = (String) solrDocument.get("item_image");
            if (StringUtils.isNotBlank(image)) {
                image = image.split(",")[0];
            }
            item.setImage(image);
            item.setPrice((long) solrDocument.get("item_price"));
            item.setSellPoint((String) solrDocument.get("item_sell_point"));
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String title = "";
            if (list != null && list.size() > 0) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);
            //添加到商品列表
            itemList.add(item);
        }
        //把结果添加到SearchResult中
        result.setItemList(itemList);
        //返回
        return result;
    }
}
