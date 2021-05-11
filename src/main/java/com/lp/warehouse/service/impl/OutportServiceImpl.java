package com.lp.warehouse.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lp.warehouse.domain.Goods;
import com.lp.warehouse.domain.Inport;
import com.lp.warehouse.domain.Outport;
import com.lp.warehouse.mapper.GoodsMapper;
import com.lp.warehouse.mapper.InportMapper;
import com.lp.warehouse.mapper.OutportMapper;
import com.lp.warehouse.service.OutportService;
import com.lp.sys.common.WebUtils;
import com.lp.sys.domain.User;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lp
 */
@Service
@Transactional
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService {

	@Autowired
	private InportMapper inportMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public void addOutPort(Integer id, Integer number, String remark) {
		//1,根据进货单ID查询进货单信息
		Inport inport = this.inportMapper.selectById(id);
		//2,根据商品ID查询商品信息
		Goods goods = this.goodsMapper.selectById(inport.getGoodsid());
		goods.setNumber(goods.getNumber()-number);
		this.goodsMapper.updateById(goods);
		//3,添加退货单信息
		Outport entity=new Outport();
		entity.setGoodsid(inport.getGoodsid());
		entity.setNumber(number);
		User user=(User)WebUtils.getSession().getAttribute("user");
		entity.setOperateperson(user.getName());
		entity.setOutportprice(inport.getInportprice());
		entity.setOutputtime(new Date());
		entity.setPaytype(inport.getPaytype());
		entity.setProviderid(inport.getProviderid());
		entity.setRemark(remark);
		this.getBaseMapper().insert(entity);
	}

}
