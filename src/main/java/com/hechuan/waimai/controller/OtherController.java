package com.hechuan.waimai.controller;

import com.alibaba.fastjson.JSON;
import com.hechuan.waimai.dto.CensusDTO;
import com.hechuan.waimai.service.OtherService;
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
@RequestMapping("other/")
public class OtherController {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Autowired
    private OtherService otherService;

    /**
     * 查询数据统计--个数
     * @return
     */
    @PostMapping("queryCensus")
    public ResultVO queryCensus(){
        log.info("【查询数据统计开始】");
        CensusDTO censusDTO = otherService.queryCensus();
        log.info("【查询数据统计返回结果】 = {}", JSON.toJSONString(censusDTO));

        return ResultVO.success(censusDTO);
    }

}
