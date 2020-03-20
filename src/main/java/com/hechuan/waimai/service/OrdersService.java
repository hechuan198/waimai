package com.hechuan.waimai.service;

import com.hechuan.waimai.entity.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ordersService")
public interface OrdersService {
	// 插入数据 调用ordersDAO里的insertOrders配置
	public int insertOrders(Orders orders);

	// 更新数据 调用ordersDAO里的updateOrders配置
	public int updateOrders(Orders orders);

	// 按照Orders类里面的字段名称精确查询 调用ordersDAO里的getOrdersByCond配置
	public List<Orders> getOrdersByCond(Orders orders);

	// 按主键查询表返回单一的Orders实例 调用ordersDAO里的getOrdersById配置
	public Orders getOrdersById(String ordersid);

}
