package com.kgc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kgc.dao.BillMapper;
import com.kgc.dao.ProviderMapper;
import com.kgc.dao.UserMapper;
import com.kgc.pojo.Bill;
import com.kgc.pojo.Provider;
import com.kgc.pojo.User;
import com.kgc.service.BillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    @Resource
    private BillMapper billMapper;
    @Resource
    private ProviderMapper providerMapper;
    @Override
    public Page<Bill> selectByBill(int pageSize, int pageNum, EntityWrapper wrapper) {
        Page page = new Page(pageNum,pageSize);
        List<Bill> bills = billMapper.selectPage(page, wrapper);
        page.setRecords(bills);
        return page;
    }

    @Override
    public Bill selectByBillId(Long billId) {
        Bill bill = new Bill();
        bill.setId(billId);
        bill = billMapper.selectOne(bill);
        Provider provider = new Provider();
        provider.setId(bill.getProviderid());
        provider = providerMapper.selectOne(provider);
        bill.setProvider(provider);
        return bill;
    }

    @Override
    public int delBillByid(Long billId) {
        Integer integer = billMapper.deleteById(billId);
        System.out.println(integer);
        return integer;
    }
}
