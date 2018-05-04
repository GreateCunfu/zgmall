package com.op.marvel.dc.zhg38.content.service.impl;


import com.op.marvel.dc.zhg38.common.mapper.TbContentCategoryMapper;
import com.op.marvel.dc.zhg38.common.pojo.TbContentCategory;
import com.op.marvel.dc.zhg38.common.source.bean.vo.EasyUITreeNode;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;
import com.op.marvel.dc.zhg38.content.service.ITbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 17:51 on 28/03/2018.
 */
@Service
public class TbContentCategoryServiceImpl implements ITbContentCategoryService {


    @Autowired
    private TbContentCategoryMapper tbcontentCategoryMapper;
    /**
     * 根据 parentId 获取ContentCategory 列表
     *
     * @param parentId
     * @return
     */
    @Override
    public List<EasyUITreeNode> getContentCategoryList(long parentId)throws Exception {
        //执行查询
        List<TbContentCategory> list = tbcontentCategoryMapper.selectContentCategoryListByParentId(parentId);
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            //添加到结果列表
            resultList.add(node);
        }
        return resultList;
    }

    /**
     * 根据 parentId 和name 获取 获取ContentCategory对象
     *
     * @param parentId
     * @param name
     * @return
     */
    @Override
    public ResultInfo addContentCategory(Long parentId, String name) throws Exception {
        //创建一个pojo对象
        TbContentCategory contentCategory = new TbContentCategory();
        //补全对象的属性
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        //状态。可选值:1(正常),2(删除)
        contentCategory.setStatus(1);
        //排序，默认为1
        contentCategory.setSortOrder(1);
        contentCategory.setIsParent(false);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //插入到数据库
        tbcontentCategoryMapper.insert(contentCategory);
        //判断父节点的状态
        TbContentCategory parent = tbcontentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()) {
            //如果父节点为叶子节点应该改为父节点
            parent.setIsParent(true);
            //更新父节点
            tbcontentCategoryMapper.updateByPrimaryKey(parent);
        }

        //返回结果
        return ResultInfo.ok(contentCategory);
    }
}
