package com.hechuan.waimai.controller;


import com.alibaba.fastjson.JSON;
import com.hechuan.waimai.dto.Category;
import com.hechuan.waimai.service.CategoryService;
import com.hechuan.waimai.service.impl.ProductServiceImpl;
import com.hechuan.waimai.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("category/")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Autowired
    private CategoryService categoryService;

    /**
     * 添加/编辑分类
     * @param category
     * @return
     */
    @PostMapping("addCategory")
    public ResultVO addCategory(Category category){
        log.info("【添加/编辑分类，请求参数】 = {}", JSON.toJSONString(category));
        ResultVO resultVO = categoryService.addCategory(category);

        return resultVO;
    }

    /**
     * 查询所有分类
     * @param
     * @return
     */
    @PostMapping("queryCategoryList")
    public ResultVO queryCategoryList(){

        log.info("【查询所有分类开始】");
        List<Category> categoryList = categoryService.queryCategoryList();
        log.info("【分类结果】 = {}",JSON.toJSONString(categoryList));
        return ResultVO.success(categoryList);
    }

}
