package com.lp.warehouse.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lp.sys.common.Constast;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lp.warehouse.domain.Customer;
import com.lp.warehouse.service.CustomerService;
import com.lp.warehouse.vo.CustomerVo;
import com.lp.sys.common.DataGridView;
import com.lp.sys.common.ResultObj;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lp
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * 查询
	 */
	@RequestMapping("loadAllCustomer")
	public DataGridView loadAllCustomer(CustomerVo customerVo) {
		IPage<Customer> page = new Page<>(customerVo.getPage(), customerVo.getLimit());
		QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(customerVo.getCustomername()), "customername",
				customerVo.getCustomername());
		queryWrapper.like(StringUtils.isNotBlank(customerVo.getPhone()), "phone", customerVo.getPhone());
		queryWrapper.like(StringUtils.isNotBlank(customerVo.getConnectionperson()), "connectionperson",
				customerVo.getConnectionperson());
		this.customerService.page(page, queryWrapper);
		return new DataGridView(page.getTotal(), page.getRecords());
	}

	/**
	 * 添加
	 */
	@RequestMapping("addCustomer")
	public ResultObj addCustomer(CustomerVo customerVo) {
		try {
			this.customerService.save(customerVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改
	 */
	@RequestMapping("updateCustomer")
	public ResultObj updateCustomer(CustomerVo customerVo) {
		try {
			this.customerService.updateById(customerVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("deleteCustomer")
	public ResultObj deleteCustomer(Integer id) {
		try {
			this.customerService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 批量删除
	 */
	@RequestMapping("batchDeleteCustomer")
	public ResultObj batchDeleteCustomer(CustomerVo customerVo) {
		try {
			Collection<Serializable> idList = new ArrayList<Serializable>();
			for (Integer id : customerVo.getIds()) {
				idList.add(id);
			}
			this.customerService.removeByIds(idList);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 加载所有可用的客户
	 */
	@GetMapping("loadAllCustomerForSelect")
	public DataGridView loadAllCustomerForSelect() {
		QueryWrapper<Customer> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
		List<Customer> list = this.customerService.list(queryWrapper);
		return new DataGridView(list);
	}
}
