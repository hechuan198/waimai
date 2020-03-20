package com.hechuan.waimai.service;

import com.hechuan.waimai.entity.Cate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cateService")
public interface CateService {

	// 查询全部数据 调用cateDAO里的getAllCate配置
	public List<Cate> getAllCate();

	public List<Cate> getCateFront();



}
