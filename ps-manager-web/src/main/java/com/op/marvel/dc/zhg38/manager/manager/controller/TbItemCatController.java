package com.op.marvel.dc.zhg38.manager.manager.controller;


import com.op.marvel.dc.zhg38.common.source.bean.vo.EasyUITreeNode;
import com.op.marvel.dc.zhg38.manager.service.ITbItemCatService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 14:10 on 28/03/2018.
 */
@Controller
@RequestMapping("item/cat")
public class TbItemCatController {
    @Autowired
    private ITbItemCatService iTbItemCatService;

    @RequestMapping("list")
    public ResponseEntity<List<EasyUITreeNode>> getItemCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) throws Exception {
        try {
            List<EasyUITreeNode> list = this.iTbItemCatService.getItemCatList(parentId);
            if (CollectionUtils.isEmpty(list)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
