package com.kgc.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kgc.pojo.Bill;
import com.kgc.pojo.User;

public interface BillService {
    Page<Bill> selectByBill(int pageSize, int pageNum, EntityWrapper wrapper);
    Bill selectByBillId(Long billId);
    int delBillByid(Long billId);
}
