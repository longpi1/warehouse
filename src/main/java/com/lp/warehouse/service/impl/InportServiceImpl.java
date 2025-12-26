package com.lp.warehouse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lp.sys.common.WebUtils;
import com.lp.sys.domain.User;
import com.lp.sys.exception.BusinessException;
import com.lp.warehouse.domain.Goods;
import com.lp.warehouse.domain.Inport;
import com.lp.warehouse.domain.Provider;
import com.lp.warehouse.mapper.GoodsMapper;
import com.lp.warehouse.mapper.InportMapper;
import com.lp.warehouse.service.GoodsService;
import com.lp.warehouse.service.InportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import com.lp.warehouse.service.ProviderService;
import com.lp.warehouse.vo.InportVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 进货服务实现类
 * </p>
 *
 * @author lp
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ProviderService providerService;

    @Override
    public IPage<Inport> pageWithRelations(InportVo inportVo) {
        log.info("开始查询进货记录, page={}, limit={}", inportVo.getPage(), inportVo.getLimit());

        // 1. 分页查询进货记录
        IPage<Inport> page = new Page<>(inportVo.getPage(), inportVo.getLimit());
        QueryWrapper<Inport> queryWrapper = buildQueryWrapper(inportVo);
        this.page(page, queryWrapper);

        List<Inport> records = page.getRecords();
        if (records.isEmpty()) {
            return page;
        }

        // 2. 批量查询关联数据（解凳N+1问题）
        fillRelationData(records);

        log.info("查询进货记录完成, total={}", page.getTotal());
        return page;
    }

    @Override
    public void addInportWithStock(InportVo inportVo) {
        log.info("开始添加进货记录, goodsId={}, number={}", inportVo.getGoodsid(), inportVo.getNumber());

        // 1. 参数校验
        validateInportVo(inportVo);

        // 2. 设置进货时间和操作人
        inportVo.setInporttime(new Date());
        User user = (User) WebUtils.getSession().getAttribute("user");
        if (user != null) {
            inportVo.setOperateperson(user.getName());
        }

        // 3. 查询商品信息
        Goods goods = goodsService.getById(inportVo.getGoodsid());
        if (goods == null) {
            throw new BusinessException("商品不存在");
        }

        // 4. 更新库存
        goods.setNumber(goods.getNumber() + inportVo.getNumber());
        goodsService.updateById(goods);

        // 5. 保存进货记录
        this.save(inportVo);

        log.info("进货记录添加成功, id={}, 库存更新: {} -> {}",
                inportVo.getId(),
                goods.getNumber() - inportVo.getNumber(),
                goods.getNumber());
    }

    @Override
    public void updateInport(InportVo inportVo) {
        log.info("开始更新进货记录, id={}", inportVo.getId());

        // 1. 查询原进货记录
        Inport oldInport = this.getById(inportVo.getId());
        if (oldInport == null) {
            throw new BusinessException("进货记录不存在");
        }

        // 2. 查询商品信息
        Goods goods = goodsService.getById(oldInport.getGoodsid());
        if (goods == null) {
            throw new BusinessException("商品不存在");
        }

        // 3. 重新计算库存：当前库存 - 旧数量 + 新数量
        int oldStock = goods.getNumber();
        goods.setNumber(goods.getNumber() - oldInport.getNumber() + inportVo.getNumber());
        goodsService.updateById(goods);

        // 4. 更新进货记录
        this.updateById(inportVo);

        log.info("进货记录更新成功, id={}, 库存更新: {} -> {}",
                inportVo.getId(), oldStock, goods.getNumber());
    }

    @Override
    public void deleteInport(Integer id) {
        log.info("开始删除进货记录, id={}", id);

        // 1. 查询进货记录
        Inport inport = this.getById(id);
        if (inport == null) {
            throw new BusinessException("进货记录不存在");
        }

        // 2. 查询商品信息
        Goods goods = goodsService.getById(inport.getGoodsid());
        if (goods == null) {
            throw new BusinessException("商品不存在");
        }

        // 3. 检查库存是否足够
        if (goods.getNumber() < inport.getNumber()) {
            throw new BusinessException("库存不足，无法删除该进货记录");
        }

        // 4. 减少库存
        int oldStock = goods.getNumber();
        goods.setNumber(goods.getNumber() - inport.getNumber());
        goodsService.updateById(goods);

        // 5. 删除进货记录
        this.removeById(id);

        log.info("进货记录删除成功, id={}, 库存更新: {} -> {}",
                id, oldStock, goods.getNumber());
    }

    /**
     * 构建查询条件
     */
    private QueryWrapper<Inport> buildQueryWrapper(InportVo inportVo) {
        QueryWrapper<Inport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(inportVo.getProviderid() != null && inportVo.getProviderid() != 0,
                "providerid", inportVo.getProviderid());
        queryWrapper.eq(inportVo.getGoodsid() != null && inportVo.getGoodsid() != 0,
                "goodsid", inportVo.getGoodsid());
        queryWrapper.ge(inportVo.getStartTime() != null,
                "inporttime", inportVo.getStartTime());
        queryWrapper.le(inportVo.getEndTime() != null,
                "inporttime", inportVo.getEndTime());
        queryWrapper.like(StringUtils.isNotBlank(inportVo.getOperateperson()),
                "operateperson", inportVo.getOperateperson());
        queryWrapper.like(StringUtils.isNotBlank(inportVo.getRemark()),
                "remark", inportVo.getRemark());
        queryWrapper.orderByDesc("inporttime");
        return queryWrapper;
    }

    /**
     * 填充关联数据（批量查询，解凳N+1问题）
     */
    private void fillRelationData(List<Inport> records) {
        // 收集所有ID
        Set<Integer> providerIds = records.stream()
                .map(Inport::getProviderid)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Set<Integer> goodsIds = records.stream()
                .map(Inport::getGoodsid)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // 批量查询
        Map<Integer, Provider> providerMap = new HashMap<>();
        if (!providerIds.isEmpty()) {
            providerMap = providerService.listByIds(providerIds)
                    .stream()
                    .collect(Collectors.toMap(Provider::getId, p -> p));
        }

        Map<Integer, Goods> goodsMap = new HashMap<>();
        if (!goodsIds.isEmpty()) {
            goodsMap = goodsService.listByIds(goodsIds)
                    .stream()
                    .collect(Collectors.toMap(Goods::getId, g -> g));
        }

        // 填充数据
        for (Inport inport : records) {
            Provider provider = providerMap.get(inport.getProviderid());
            if (provider != null) {
                inport.setProvidername(provider.getProvidername());
            }

            Goods goods = goodsMap.get(inport.getGoodsid());
            if (goods != null) {
                inport.setGoodsname(goods.getGoodsname());
                inport.setSize(goods.getSize());
            }
        }
    }

    /**
     * 校验进货参数
     */
    private void validateInportVo(InportVo inportVo) {
        if (inportVo.getGoodsid() == null || inportVo.getGoodsid() <= 0) {
            throw new BusinessException("商品ID不能为空");
        }
        if (inportVo.getProviderid() == null || inportVo.getProviderid() <= 0) {
            throw new BusinessException("供应商ID不能为空");
        }
        if (inportVo.getNumber() == null || inportVo.getNumber() <= 0) {
            throw new BusinessException("进货数量必须大于0");
        }
        if (inportVo.getInportprice() != null && inportVo.getInportprice() < 0) {
            throw new BusinessException("进货价格不能为负数");
        }
    }

    // 保留原有方法以兼容旧代码
    @Override
    public boolean save(Inport entity) {
        // 根据商品编号查询商品
        Goods goods = goodsMapper.selectById(entity.getGoodsid());
        if (goods != null) {
            goods.setNumber(goods.getNumber() + entity.getNumber());
            goodsMapper.updateById(goods);
        }
        // 保存进货信息
        return super.save(entity);
    }

    @Override
    public boolean updateById(Inport entity) {
        // 根据进货ID查询进货
        Inport inport = this.baseMapper.selectById(entity.getId());
        if (inport != null) {
            // 根据商品ID查询商品信息
            Goods goods = this.goodsMapper.selectById(entity.getGoodsid());
            if (goods != null) {
                // 库存的算法  当前库存-进货单修改之前的数量+修改之后的数量
                goods.setNumber(goods.getNumber() - inport.getNumber() + entity.getNumber());
                this.goodsMapper.updateById(goods);
            }
        }
        // 更新进货单
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        // 根据进货ID查询进货
        Inport inport = this.baseMapper.selectById(id);
        if (inport != null) {
            // 根据商品ID查询商品信息
            Goods goods = this.goodsMapper.selectById(inport.getGoodsid());
            if (goods != null) {
                // 库存的算法  当前库存-进货单数量
                goods.setNumber(goods.getNumber() - inport.getNumber());
                this.goodsMapper.updateById(goods);
            }
        }
        return super.removeById(id);
    }
}
