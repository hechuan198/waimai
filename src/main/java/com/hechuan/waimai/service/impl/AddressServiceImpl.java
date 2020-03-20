package com.hechuan.waimai.service.impl;

import com.hechuan.waimai.dao.AddressMapper;
import com.hechuan.waimai.dto.Address;
import com.hechuan.waimai.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {


    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> getAddressByCond(Address address) {
        return addressMapper.queryAddressList(address);

    }

    @Override
    public void addAddress(Address address) {
        addressMapper.insert(address);
    }

    @Override
    public void deleteAddress(String id) {
        addressMapper.deleteAddress(id);
    }

    @Override
    public Address queryAddress(String id) {
        Address address = addressMapper.selectByPrimaryKey(id);
        return address;
    }

    @Override
    public void updateAddress(Address address) {

        addressMapper.updateByPrimaryKeySelective(address);

    }
}
