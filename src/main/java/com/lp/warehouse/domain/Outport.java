package com.lp.warehouse.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lp
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_outport")
public class Outport implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer providerid;

    private String paytype;

    private Date outputtime;

    private String operateperson;

    private Double outportprice;

    private Integer number;

    private String remark;

    private Integer goodsid;
    
    @TableField(exist=false)
    private String providername;
    @TableField(exist=false)
    private String goodsname;
    @TableField(exist=false)
    private String size;


}
