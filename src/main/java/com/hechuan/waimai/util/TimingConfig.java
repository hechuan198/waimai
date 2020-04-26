package com.hechuan.waimai.util;

import com.hechuan.waimai.dao.OrderMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@Configuration
@EnableScheduling
public class TimingConfig {

    @Resource
    OrderMapper orderMapper;

    @Scheduled(fixedRate = 10000)
    private void cancelOrder(){
        orderMapper.autoCancelOrder();
    }
}
