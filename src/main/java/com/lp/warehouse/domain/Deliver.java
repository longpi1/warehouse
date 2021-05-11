package com.lp.warehouse.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @auth lp
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_deliver")
public class Deliver implements Serializable {
    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String paytype;

    private Date delivertime;

    private String operateperson;

    private Integer number;

    private String remark;

    private Double deliverprice;

    private Integer customerid;

    private Integer goodsid;
    @TableField(exist=false)
    private String customerplace;
    @TableField(exist=false)
    private String customername;
    @TableField(exist=false)
    private String goodsname;
    @TableField(exist=false)
    private String size;//规格
}
