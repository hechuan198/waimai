package com.hechuan.waimai.service.impl;

import com.hechuan.waimai.dao.OrdersDAO;
import com.hechuan.waimai.entity.Orders;
import com.hechuan.waimai.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	@Resource
	private OrdersDAO ordersDAO;

	@Override // 继承接口的新增 返回值0(失败),1(成功)
	public int insertOrders(Orders orders) {
		return this.ordersDAO.insertOrders(orders);
	}

	@Override // 继承接口的更新 返回值0(失败),1(成功)
	public int updateOrders(Orders orders) {
		return this.ordersDAO.updateOrders(orders);
	}

	@Override // 继承接口的按条件精确查询
	public List<Orders> getOrdersByCond(Orders orders) {
		return this.ordersDAO.getOrdersByCond(orders);
	}

	@Override // 继承接口的按主键查询 返回Entity实例
	public Orders getOrdersById(String ordersid) {
		return this.ordersDAO.getOrdersById(ordersid);
	}

}
