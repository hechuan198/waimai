package com.hechuan.waimai.util;

/**
 * 常量类
 */
public class Constant {
    /**
     * 订单状态的内部接口
     */
    public interface OrderStatus{
        // 已支付
        Integer PAY = 2;
        // 处理中
        Integer HANDLE = 3;
        // 已发货
        Integer SHIPPED = 4;


    }

}
