package com.op.marvel.dc.zhg38.common.source.bean.vo;

import com.op.marvel.dc.zhg38.common.pojo.SearchItem;

import java.io.Serializable;
import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 13:43 on 2018/5/3.
 */
public class SearchResult implements Serializable {

    private  Long totalPages;
    private  Long recordCount;
    private List<SearchItem> itemList;

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }
}
