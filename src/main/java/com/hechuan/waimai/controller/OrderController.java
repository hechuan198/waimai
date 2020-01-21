package com.hechuan.waimai.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.Order;
import com.hechuan.waimai.dto.OrderCountDTO;
import com.hechuan.waimai.dto.OrderItem;
import com.hechuan.waimai.dto.OrderRequest;
import com.hechuan.waimai.service.OrderItemService;
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

    @Autowired
    private OrderItemService orderItemService;

    /**
     * 查询订单分页信息
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

    /**
     * 分页查询订单详情
     * @param orderItem
     * @return
     */
    @PostMapping("queryOrderDetailList")
    public ResultVO queryOrderDetailList(OrderItem orderItem){
        log.info("【分页查询订单详情，请求参数】 = {}",JSON.toJSONString(orderItem) );
        PageInfo<Order> pageInfo = orderItemService.queryOrderDetailList(orderItem);
        log.info("【返回分页结果】 = {}",JSON.toJSONString(pageInfo));
        return ResultVO.success(pageInfo);
    }

    /**
     * 修改订单状态
     * @param orderRequest
     * @return
     */
    @PostMapping("updateOrderStatus")
    public ResultVO updateOrderStatus(OrderRequest orderRequest){

        log.info("【修改订单状态，请求参数】 = {}", JSON.toJSONString(orderRequest));
        orderService.updateOrderStatus(orderRequest);
        return ResultVO.success("修改状态成功");
    }

    /**
     * 查询订单数量
     * @return
     */
    @PostMapping("queryOrderCount")
    public ResultVO queryOrderCount(){
        log.info("【查询订单数量开始】");
        OrderCountDTO orderCountDTO = orderService.queryOrderCount();
        log.info("【查询订单数量结果】 = {}",JSON.toJSONString(orderCountDTO));
        return ResultVO.success(orderCountDTO);
    }



}
