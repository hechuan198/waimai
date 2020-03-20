package com.hechuan.waimai.service;

import com.hechuan.waimai.dto.Address;

import java.util.List;

public interface AddressService {

    /**
     * 查询收货地址
     * @param address
     * @return
     */
    List<Address> getAddressByCond(Address address);

    /**
     * 插入收货地址
     * @param address
     */
    void addAddress(Address address);

    /**
     * 删除收货地址
     * @param id
     */
    void deleteAddress(String id);

    /**
     * 查询单个地址
     * @param id
     * @return
     */
    Address queryAddress(String id);

    /**
     * 修改地址
     * @param address
     */
    void updateAddress(Address address);
}
