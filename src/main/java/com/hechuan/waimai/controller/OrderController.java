package com.hechuan.waimai.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.*;
import com.hechuan.waimai.service.OrderItemService;
import com.hechuan.waimai.service.OrderService;
import com.hechuan.waimai.service.impl.ProductServiceImpl;
import com.hechuan.waimai.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * 查询每月用户数
     * @return
     */
    @PostMapping("queryOrderByMonth")
    public ResultVO queryOrderByMonth(){
        log.info("【查询每月订单数开始】");
        List<OrderCountDTO> orderCountDTOList = orderService.queryOrderByMonth();
        log.info("【查询每月订单数，查询结果】 = {}",JSON.toJSONString(orderCountDTOList));
        return ResultVO.success(orderCountDTOList);
    }

    @RequestMapping(value = "/getOrderExport")
    public ResponseEntity<byte[]> getOrderExport() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String date = sdf.format(new Date());
        String fileName = "Order" + date + ".xlsx";
        responseHeaders.set("Content-Disposition", "attachment; filename=" + fileName);
        List<OrderCountDTO> orderCountDTOList = orderService.queryOrderByMonth();
        //转换数据
        List<OrderReportDTO> orderReportDTOS = new ArrayList();
        for (OrderCountDTO orderCountDTO : orderCountDTOList) {
            OrderReportDTO orderReportDTO = new OrderReportDTO();
            BeanUtils.copyProperties(orderCountDTO,orderReportDTO);
            orderReportDTOS.add(orderReportDTO);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, OrderReportDTO.class).sheet("每月订单数").doWrite(orderReportDTOS);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(outputStream.toByteArray());
    }

}
