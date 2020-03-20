package com.hechuan.waimai.service.impl;

import com.hechuan.waimai.dao.CategoryMapper;
import com.hechuan.waimai.dto.Category;
import com.hechuan.waimai.service.CategoryService;
import com.hechuan.waimai.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResultVO addCategory(Category category) {
        Category categoryResult = categoryMapper.selectCate(category);
        if (categoryResult != null) {
            return ResultVO.error("该分类已存在");
        }
        categoryMapper.insert(category);
        log.info("【添加成功】");
        return ResultVO.success("添加分类成功");
    }

    @Override
    public ResultVO updateCategory(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
        log.info("【修改成功】");
        return ResultVO.success("修改分类成功");

    }

    /**
     * 查询所有分类
     *
     * @param
     * @return
     */
    @Override
    public List<Category> queryCategoryList() {

        List<Category> categoryList = categoryMapper.queryCategoryList();

        return categoryList;
    }

    @Override
    public Category queryCategoryByName(Category categoryRequest) {

        Category category = categoryMapper.queryCategory(categoryRequest);

        return category;
    }
}
