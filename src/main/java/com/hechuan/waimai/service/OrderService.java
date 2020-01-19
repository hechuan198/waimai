package com.hechuan.waimai.service;

import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.Order;
import com.hechuan.waimai.dto.OrderRequest;

public interface OrderService {
    PageInfo<Order> queryOrderList(OrderRequest order);


}
