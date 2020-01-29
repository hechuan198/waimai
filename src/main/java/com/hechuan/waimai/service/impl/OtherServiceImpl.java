package com.hechuan.waimai.service.impl;

import com.hechuan.waimai.dao.CategoryMapper;
import com.hechuan.waimai.dao.OrderMapper;
import com.hechuan.waimai.dao.ProductMapper;
import com.hechuan.waimai.dao.UserMapper;
import com.hechuan.waimai.dto.CensusDTO;
import com.hechuan.waimai.service.OtherService;
import com.hechuan.waimai.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OtherServiceImpl implements OtherService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CensusDTO queryCensus() {

        Integer userCount = userMapper.queryUserCount();
        Integer orderCount = orderMapper.queryOrderCountStatus(Constant.OrderStatus.SHIPPED);
        Integer productCount = productMapper.queryProductCount();
        Integer cateCount = categoryMapper.queryCategoryCount();
        CensusDTO censusDTO = new CensusDTO();
        censusDTO.setCateCount(cateCount);
        censusDTO.setOrderCount(orderCount);
        censusDTO.setProductCount(productCount);
        censusDTO.setUserCount(userCount);
        return censusDTO;
    }
}
