package com.op.marvel.dc.zhg38.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.op.marvel.dc.zhg38.common.mapper.TbItemDescMapper;
import com.op.marvel.dc.zhg38.common.mapper.TbItemMapper;
import com.op.marvel.dc.zhg38.common.pojo.TbItem;
import com.op.marvel.dc.zhg38.common.pojo.TbItemDesc;
import com.op.marvel.dc.zhg38.common.source.bean.dto.PageBeanDto;
import com.op.marvel.dc.zhg38.manager.service.ITbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 18:26 on 2018/5/2.
 */
@Service
public class TbItemServiceImpl implements ITbItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;

    /**
     * 分页查询商品列表
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageBeanDto<TbItem> queryItemPageList(Integer page, Integer rows) throws  Exception{
        PageHelper.startPage(page, rows, true);
        List<TbItem> resList = this.itemMapper.selectItemList();
        PageInfo<TbItem> pageInfo = new PageInfo<>(resList);
        List<TbItem> list = pageInfo.getList();
        PageBeanDto<TbItem> returnList = new PageBeanDto<>(pageInfo.getTotal(), pageInfo.getList());
        return returnList;
    }

    /**
     * 根据itemId 查询商品信息
     *
     * @param itemId
     * @return
     */
    @Override
    public TbItem queryItemById(Long itemId) throws  Exception{

        return this.itemMapper.selectByPrimaryKey(itemId);
    }

    /**
     * 根据itemId 查询商品描述
     *
     * @param itemId
     * @return
     */
    @Override
    public TbItemDesc queryItemDescByItemId(Long itemId)throws  Exception {
        return this.itemDescMapper.selectItemDescByItemId(itemId);
    }
}

