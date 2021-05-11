package com.lp.warehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lp.warehouse.domain.*;
import com.lp.warehouse.service.*;
import com.lp.warehouse.vo.DeliverVo;
import com.lp.sys.common.DataGridView;
import com.lp.sys.common.ResultObj;
import com.lp.sys.common.WebUtils;
import com.lp.sys.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @auth lp
 */
@RestController
@RequestMapping("deliver")
public class DeliverController {
    @Autowired
    private DeliverService deliverService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询
     */
    @RequestMapping("loadAllDeliver")
    public DataGridView loadAllDeliver(DeliverVo deliverVo) {
        IPage<Deliver> page = new Page<>(deliverVo.getPage(), deliverVo.getLimit());
        QueryWrapper<Deliver> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(deliverVo.getCustomerid()!=null&&deliverVo.getCustomerid()!=0,"customerid",deliverVo.getCustomerid());
        queryWrapper.eq(deliverVo.getGoodsid()!=null&&deliverVo.getGoodsid()!=0,"goodsid",deliverVo.getGoodsid());
        queryWrapper.ge(deliverVo.getStartTime()!=null, "delivertime", deliverVo.getStartTime());
        queryWrapper.le(deliverVo.getEndTime()!=null, "delivertime", deliverVo.getEndTime());
        queryWrapper.like(StringUtils.isNotBlank(deliverVo.getOperateperson()), "operateperson", deliverVo.getOperateperson());
        queryWrapper.like(StringUtils.isNotBlank(deliverVo.getRemark()), "remark", deliverVo.getRemark());
        queryWrapper.orderByDesc("delivertime");
        this.deliverService.page(page, queryWrapper);
        List<Deliver> records = page.getRecords();
        for (Deliver deliver : records) {
            Customer customer = this.customerService.getById(deliver.getCustomerid());
            if(null!=customer) {
                deliver.setCustomername(customer.getCustomername());
                deliver.setCustomerplace(customer.getAddress());
            }
            Goods goods = this.goodsService.getById(deliver.getGoodsid());
            if(null!=goods) {
                deliver.setGoodsname(goods.getGoodsname());
                deliver.setSize(goods.getSize());
               // deliver.setDeliverprice(goods.getPrice());
            }
        }
        return new DataGridView(page.getTotal(), records);
    }

    /**
     * 添加
     */
    @RequestMapping("addDeliver")
    public ResultObj addDeliver(DeliverVo deliverVo) {
        try {
            //库存变化
            QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("id",deliverVo.getGoodsid());
            Goods goods = goodsService.getOne(queryWrapper);

            goods.setNumber(goods.getNumber()+deliverVo.getNumber());

            deliverVo.setDelivertime(new Date());
            User user=(User) WebUtils.getSession().getAttribute("user");
            deliverVo.setOperateperson(user.getName());
            this.deliverService.save(deliverVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改
     */
    @RequestMapping("updateDeliver")
    public ResultObj updateDeliver(DeliverVo deliverVo) {
        try
        {
            this.deliverService.updateById(deliverVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除
     */
    @RequestMapping("deleteDeliver")
    public ResultObj deleteDeliver(Integer id) {
        try {
            this.deliverService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
