package com.hechuan.waimai.service;

import com.hechuan.waimai.dto.Category;
import com.hechuan.waimai.util.ResultVO;

import java.security.PrivateKey;
import java.util.List;

public interface CategoryService {

    /**
     * 添加分类
     * @param category
     * @return
     */
    ResultVO addCategory(Category category);

    /**
     * 编辑分类
     * @param category
     * @return
     */
    ResultVO updateCategory(Category category);

    /**
     * 查询所有分类
     * @param
     * @return
     */
    List<Category> queryCategoryList();
}
