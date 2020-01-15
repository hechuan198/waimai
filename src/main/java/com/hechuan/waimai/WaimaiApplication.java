package com.hechuan.waimai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.hechuan.waimai.dao"})
public class WaimaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaimaiApplication.class, args);
    }

}
