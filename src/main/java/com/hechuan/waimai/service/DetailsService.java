package com.hechuan.waimai.service;

import com.hechuan.waimai.entity.Details;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("detailsService")
public interface DetailsService {
	// 插入数据 调用detailsDAO里的insertDetails配置
	public int insertDetails(Details details);

	// 按照Details类里面的字段名称精确查询 调用detailsDAO里的getDetailsByCond配置
	public List<Details> getDetailsByCond(Details details);



}
