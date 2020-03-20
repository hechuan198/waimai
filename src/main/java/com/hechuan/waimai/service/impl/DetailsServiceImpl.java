package com.hechuan.waimai.service.impl;

import com.hechuan.waimai.dao.DetailsDAO;
import com.hechuan.waimai.entity.Details;
import com.hechuan.waimai.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("detailsService")
public class DetailsServiceImpl implements DetailsService {
	@Autowired
	@Resource
	private DetailsDAO detailsDAO;

	@Override // 继承接口的新增 返回值0(失败),1(成功)
	public int insertDetails(Details details) {
		return this.detailsDAO.insertDetails(details);
	}


	@Override // 继承接口的按条件精确查询
	public List<Details> getDetailsByCond(Details details) {
		return this.detailsDAO.getDetailsByCond(details);
	}

}
