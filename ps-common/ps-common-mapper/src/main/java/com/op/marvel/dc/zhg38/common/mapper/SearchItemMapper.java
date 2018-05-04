package com.op.marvel.dc.zhg38.common.mapper;

import com.op.marvel.dc.zhg38.common.annotion.Myppers;
import com.op.marvel.dc.zhg38.common.pojo.SearchItem;

import java.util.List;

@Myppers
public interface SearchItemMapper {

	List<SearchItem> getItemList();

	SearchItem getItemById(long itemId);
}
