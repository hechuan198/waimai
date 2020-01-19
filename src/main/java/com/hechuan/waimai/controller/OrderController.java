package com.hechuan.waimai.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.Order;
import com.hechuan.waimai.dto.OrderRequest;
import com.hechuan.waimai.service.OrderService;
import com.hechuan.waimai.service.impl.ProductServiceImpl;
import com.hechuan.waimai.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("order/")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private OrderService orderService;

    /**
     * 查询订单分信息
     * @param order
     * @return
     */
    @PostMapping("queryOrderList")
    public ResultVO queryOrderList(OrderRequest order){
        log.info("【查询订单分页信息，请求参数】 = {}", JSON.toJSONString(order));
        PageInfo<Order> orderPageInfo = orderService.queryOrderList(order);
        log.info("【查询订单分页结果，orderPageInfo】 = {}", JSON.toJSONString(orderPageInfo));
        return ResultVO.success(orderPageInfo);
    }


}
