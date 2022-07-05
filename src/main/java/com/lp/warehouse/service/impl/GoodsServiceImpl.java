package com.lp.warehouse.service.impl;

import com.lp.warehouse.domain.Goods;
import com.lp.warehouse.mapper.GoodsMapper;
import com.lp.warehouse.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lp
 */
@Service
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {


    @Override
    public boolean save(Goods entity) {
        // TODO Auto-generated method stub
        return super.save(entity);
    }

    @Override
    public boolean updateById(Goods entity) {
        // TODO Auto-generated method stub
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        // TODO Auto-generated method stub
        return super.removeById(id);
    }

    @Override
    public Goods getById(Serializable id) {
        // TODO Auto-generated method stub
        return super.getById(id);
    }
}
