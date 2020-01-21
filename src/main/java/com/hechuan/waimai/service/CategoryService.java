package com.hechuan.waimai.service;

import com.hechuan.waimai.dto.Category;
import com.hechuan.waimai.util.ResultVO;

import java.security.PrivateKey;
import java.util.List;

public interface CategoryService {


    ResultVO addCategory(Category category);

    /**
     * 查询所有分类
     * @param
     * @return
     */
    List<Category> queryCategoryList();
}
