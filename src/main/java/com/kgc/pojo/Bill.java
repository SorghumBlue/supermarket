package com.kgc.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * smbms_bill
 * @author 
 */
@Data
public class Bill implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 账单编码
     */
    private String billcode;

    /**
     * 商品名称
     */
    private String productname;

    /**
     * 商品描述
     */
    private String productdesc;

    /**
     * 商品单位
     */
    private String productunit;

    /**
     * 商品数量
     */
    private BigDecimal productcount;

    /**
     * 商品总额
     */
    private BigDecimal totalprice;

    /**
     * 是否支付（1：未支付 2：已支付）
     */
    private Integer ispayment;

    /**
     * 创建者（userId）
     */
    private Long createdby;

    /**
     * 创建时间
     */
    private Date creationdate;

    /**
     * 更新者（userId）
     */
    private Long modifyby;

    /**
     * 更新时间
     */
    private Date modifydate;

    /**
     * 供应商ID
     */
    private Long providerid;
    @TableField(exist = false)
    private Provider provider;

    private static final long serialVersionUID = 1L;

}