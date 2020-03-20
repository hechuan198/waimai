package com.hechuan.waimai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = {"com.hechuan.waimai.dao"})
public class WaimaiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WaimaiApplication.class, args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WaimaiApplication.class);
    }


}
