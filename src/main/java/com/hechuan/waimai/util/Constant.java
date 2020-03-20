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
        String PAY = "已付款";
        // 处理中
        String HANDLE = "处理中";
        // 已发货
        String SHIPPED = "已发货";


    }

}
