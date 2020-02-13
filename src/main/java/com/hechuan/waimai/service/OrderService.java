package com.hechuan.waimai.service;

import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.Order;
import com.hechuan.waimai.dto.OrderCountDTO;
import com.hechuan.waimai.dto.OrderItem;
import com.hechuan.waimai.dto.OrderRequest;

public interface OrderService {
    /**
     * 分页查询订单
     * @param order
     * @return
     */
    PageInfo<Order> queryOrderList(OrderRequest order);

    /**
     * 修改订单状态
     * @param orderRequest
     * @return
     */
    void updateOrderStatus(OrderRequest orderRequest);

    OrderCountDTO queryOrderCount();

}
