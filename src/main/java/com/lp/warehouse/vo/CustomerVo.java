package com.lp.warehouse.vo;

import com.lp.warehouse.domain.Customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerVo extends Customer {

    /*
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer limit = 10;

    private Integer[] ids;

}
