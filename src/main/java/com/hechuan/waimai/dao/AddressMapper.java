package com.hechuan.waimai.dao;


import com.hechuan.waimai.dto.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    /**
     * 查询用户所有地址
     * @return
     */
    List<Address> queryAddressList(Address address);


    /**
     * 删除用户地址
     * @param id
     */
    void deleteAddress(String id);
}