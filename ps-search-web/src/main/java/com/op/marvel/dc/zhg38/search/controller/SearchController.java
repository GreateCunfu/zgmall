package com.op.marvel.dc.zhg38.search.controller;

import com.op.marvel.dc.zhg38.search.service.ISearchService;
import com.op.marvel.dc.zhg38.common.source.bean.vo.SearchResult;
import com.op.marvel.dc.zhg38.common.source.context.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 15:57 on 2018/5/3.
 */
@Controller
public class SearchController {
    @Autowired
    private ISearchService searchService;

    @Value("${SEARCH_RESULT_ROWS}")
    private Integer SEARCH_RESULT_ROWS;

    @RequestMapping()
    public String search(@RequestParam("q") String  searchKey,
                         @RequestParam(value = "page",defaultValue = "1") Integer page, Model model)throws Exception{
        //将查询条件进行解码
        searchKey=new String(searchKey.getBytes(Config.CHARSET_8859),Config.CHARSET_UTF_8);
        //查询
        SearchResult searchResult = this.searchService.search(searchKey, page, SEARCH_RESULT_ROWS);
        // 设置模型
        model.addAttribute(Config.QUERY_KEY,searchKey);
        model.addAttribute(Config.TOTAL_PAGES_KEY,searchResult.getTotalPages());
        model.addAttribute(Config.ITEM_LIST_KEY,searchResult.getItemList());
        model.addAttribute(Config.PAGE_KEY,page);

        return Config.VIEW_SEARCH;
    }
}
