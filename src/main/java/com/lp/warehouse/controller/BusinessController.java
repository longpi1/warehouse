package com.lp.warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 业务管理的路由器
 * @author lp
 *
 */
@Controller
@RequestMapping("/bus")
public class BusinessController {


	@RequestMapping("toCustomerManager")
	public String toCustomerManager() {
		return "warehouse/customer/customerManager";
	}

	@RequestMapping("toProviderManager")
	public String toProviderManager() {
		return "warehouse/provider/providerManager";
	}

	@RequestMapping("toGoodsManager")
	public String toGoodsManager() {
		return "warehouse/goods/goodsManager";
	}

	@RequestMapping("toInportManager")
	public String toInportManager() {
		return "warehouse/inport/inportManager";
	}

	@RequestMapping("toOutportManager")
	public String toOutportManager() {
		return "warehouse/outport/outportManager";
	}

	@RequestMapping("toDelivertManager")
	public String toDeliverManager() {
		return "warehouse/deliver/deliverManager";
	}
}
