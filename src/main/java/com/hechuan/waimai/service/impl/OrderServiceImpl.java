package com.hechuan.waimai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dao.OrderMapper;
import com.hechuan.waimai.dto.Order;
import com.hechuan.waimai.dto.OrderCountDTO;
import com.hechuan.waimai.dto.OrderItem;
import com.hechuan.waimai.dto.OrderRequest;
import com.hechuan.waimai.service.OrderService;
import com.hechuan.waimai.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageInfo<Order> queryOrderList(OrderRequest order) {

        return PageHelper.startPage(order.getPageNum(),order.getPageSize()).doSelectPageInfo(() -> orderMapper.queryOrderList(order) );
    }
    /**
     * 修改订单状态
     * @param orderRequest
     * @return
     */
    @Override
    public void updateOrderStatus(OrderRequest orderRequest) {
        orderMapper.updateByPrimaryKeySelective(orderRequest);
    }

    @Override
    public OrderCountDTO queryOrderCount() {
         Integer countTodo = orderMapper.queryOrderCountStatus(Constant.OrderStatus.PAY);
         Integer countDoing = orderMapper.queryOrderCountStatus(Constant.OrderStatus.HANDLE);
         Integer countDone = orderMapper.queryOrderCountStatus(Constant.OrderStatus.SHIPPED);

        OrderCountDTO orderCountDTO = new OrderCountDTO();
        orderCountDTO.setNewOrderCount(countDoing);
        orderCountDTO.setDoingOrderCount(countDoing);
        orderCountDTO.setDoneOrderCount(countDone);

        return orderCountDTO;
    }


}
