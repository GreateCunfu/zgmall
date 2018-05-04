package com.op.marvel.dc.zhg38.manager.service.impl;

import com.op.marvel.dc.zhg38.common.mapper.TbItemCatMapper;
import com.op.marvel.dc.zhg38.common.pojo.TbItemCat;
import com.op.marvel.dc.zhg38.common.source.bean.vo.EasyUITreeNode;
import com.op.marvel.dc.zhg38.manager.service.ITbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 18:25 on 2018/5/2.
 */
@Service
public class TbItemCatServiceImpl implements ITbItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * 根据parentid 获取itemCat 列表信息
     *
     * @param parentId
     * @return
     */
    @Override
    public List<EasyUITreeNode> getItemCatList(Long parentId) throws  Exception{


        //执行查询
        List<TbItemCat> list = itemCatMapper.selectItemCatByParentId(parentId);
        //转换成EasyUITreeNode列表
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            //如果节点下有子节点“closed”，如果没有子节点“open”
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            //添加到节点列表
            resultList.add(node);
        }
        return resultList;
    }
}
