package com.op.marvel.dc.zhg38.manager.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 17:18 on 27/03/2018.
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String toIndex() throws  Exception{
       int q=  1/0;
        return "index";
    }
    @RequestMapping("{pageName}")
    public String toPage(@PathVariable("pageName") String pageName){
        return  pageName;
    }

}
