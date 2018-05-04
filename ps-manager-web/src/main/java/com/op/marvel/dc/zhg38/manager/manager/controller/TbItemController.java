package com.op.marvel.dc.zhg38.manager.manager.controller;

import com.op.marvel.dc.zhg38.common.pojo.TbItem;
import com.op.marvel.dc.zhg38.common.source.bean.dto.PageBeanDto;
import com.op.marvel.dc.zhg38.common.source.bean.vo.EasyUIGridData;
import com.op.marvel.dc.zhg38.manager.service.ITbItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 17:18 on 27/03/2018.
 */
@Controller
@RequestMapping("item")
public class TbItemController {
    private static final Logger LOGGER= LoggerFactory.getLogger(TbItemController.class);
    @Autowired
    private ITbItemService iTbItemService;

    /**
     * 获取商品分页列表数据
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("list")
    public @ResponseBody
    EasyUIGridData queryItemPageList(@RequestParam("page") Integer page,
                                     @RequestParam("rows") Integer rows) throws Exception{

        PageBeanDto<TbItem> result = this.iTbItemService.queryItemPageList(page, rows);
        EasyUIGridData returnData = new EasyUIGridData(result.getTotal(), result.getRows());

        return returnData;

    }
    @RequestMapping("{itemId}")
    public ResponseEntity<TbItem> queryItem(@PathVariable("itemId") Long itemId)throws Exception{

        try {
            TbItem item=this.iTbItemService.queryItemById(itemId);
            if (item==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
