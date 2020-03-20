package com.hechuan.waimai.service.impl;

import com.hechuan.waimai.dao.CateDAO;
import com.hechuan.waimai.entity.Cate;
import com.hechuan.waimai.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cateService")
public class CateServiceImpl implements CateService {
	@Autowired
	@Resource
	private CateDAO cateDAO;

	@Override // 继承接口的查询全部
	public List<Cate> getAllCate() {
		return this.cateDAO.getAllCate();
	}

	@Override // 继承接口的查询全部
	public List<Cate> getCateFront() {
		return this.cateDAO.getCateFront();
	}

}
