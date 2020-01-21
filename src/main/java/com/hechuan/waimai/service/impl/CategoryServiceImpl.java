package com.hechuan.waimai.service.impl;

import com.hechuan.waimai.dao.CategoryMapper;
import com.hechuan.waimai.dto.Category;
import com.hechuan.waimai.service.CategoryService;
import com.hechuan.waimai.util.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResultVO addCategory(Category category) {
        if (category.getParentId() != null) {
            Category categoryResult = categoryMapper.selectCate(category);
            if (categoryResult != null){
                return ResultVO.error("该分类已存在");
            }
            categoryMapper.insert(category);
            return  ResultVO.success("添加分类成功");
        }
        if (category.getId() != null){
            categoryMapper.updateByPrimaryKeySelective(category);
        return ResultVO.success("修改分类成功");
        }
        return ResultVO.error("操作分类失败");
    }
    /**
     * 查询所有分类
     * @param
     * @return
     */
    @Override
    public List<Category> queryCategoryList() {

        List<Category> categoryList = categoryMapper.queryCategoryList();

        return categoryList;
    }
}
