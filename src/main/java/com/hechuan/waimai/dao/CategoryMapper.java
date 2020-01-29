package com.hechuan.waimai.dao;

import com.hechuan.waimai.dto.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * 查询一条分类信息
     * @param category
     * @return
     */
    Category selectCate(Category category);

    List<Category> queryCategoryList();

    /**
     * 查询分类总数
     * @return
     */
    Integer queryCategoryCount();

//    void addCate(Category category);
}