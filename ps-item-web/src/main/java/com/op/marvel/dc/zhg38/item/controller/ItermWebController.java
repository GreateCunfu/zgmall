package com.op.marvel.dc.zhg38.item.controller;

import com.op.marvel.dc.zhg38.common.pojo.TbItem;
import com.op.marvel.dc.zhg38.common.pojo.TbItemDesc;
import com.op.marvel.dc.zhg38.manager.service.ITbItemService;
import com.op.marvel.dc.zhg38.source.bean.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 17:14 on 2018/5/3.
 */
@Controller
public class ItermWebController {
    @Autowired
    private ITbItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model) throws Exception {
        //取商品基本信息
        TbItem tbItem = itemService.queryItemById(itemId);
        Item item = new Item(tbItem);
        //取商品详情
        TbItemDesc tbItemDesc = itemService.queryItemDescByItemId(itemId);
        //把数据传递给页面
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", tbItemDesc);
        //返回逻辑视图
        return "item";
    }
}

