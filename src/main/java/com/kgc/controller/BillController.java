package com.kgc.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kgc.dao.ProviderMapper;
import com.kgc.pojo.Bill;
import com.kgc.pojo.Provider;
import com.kgc.pojo.User;
import com.kgc.service.BillService;
import com.kgc.utils.Data;
import org.aspectj.bridge.MessageWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class BillController {
    @Resource
    private BillService billService;
    @Resource
    private ProviderMapper providerMapper;

    @RequestMapping("/bill")
    public String bill(HttpSession session, @RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) String productName, @RequestParam(required = false) Integer providerId, @RequestParam(required = false) Integer isPayment) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        EntityWrapper<Bill> wrapper = new EntityWrapper<Bill>();
        if (productName != null && !"".equals(productName)) {
            wrapper.like("productName", productName);
        }
        if (providerId != null && providerId != 0) {
            wrapper.eq("providerId",providerId);
        }
        if (isPayment != null && isPayment != 0) {
            wrapper.eq("isPayment",isPayment);
        }
        Page<Bill> page = billService.selectByBill(5, pageIndex,wrapper);
        List<Bill> bills = page.getRecords();
        List<Provider> providerList = providerMapper.selectList(null);
        for (Bill bill : bills) {
            for (Provider provider : providerList) {
                if (provider.getId().equals(bill.getProviderid())) {
                    bill.setProvider(provider);
                }
            }
        }
        session.setAttribute("blist", bills);
        session.setAttribute("page", page);
        session.setAttribute("plist", providerList);
        return "bill/billlist";
    }
    @RequestMapping("/billview")
    public String billView(Long billid,HttpSession session){
        Bill bill = billService.selectByBillId(billid);
        session.setAttribute("bill",bill);
        return "bill/billview";
    }
    @RequestMapping("/billdel")
    @ResponseBody
    public Object billDel(Long billid){
        int i = billService.delBillByid(billid);
        Data data = new Data();
        if(i==0){
            data.setDelResult("false");
        }else {
            data.setDelResult("true");
        }
        return data;
    }
    @RequestMapping("/billadd")
    public String billAdd(){
        return "bill/billadd";
    }
    @RequestMapping("/billselect")
    @ResponseBody
    public List<Provider> billSelect(){
        List<Provider> providers = providerMapper.selectList(null);
        System.out.println(providers);
        return providers;
    }
    @RequestMapping("/provider")
    public String provider(){
        return "pro/providerlist";
    }
}
