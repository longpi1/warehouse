package com.lp.warehouse.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lp.sys.exception.BusinessException;
import com.lp.warehouse.domain.Provider;
import com.lp.warehouse.service.GoodsService;
import com.lp.warehouse.service.ProviderService;
import com.lp.warehouse.vo.OutportVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * 退货服务实现类
 * </p>
 *
 * @author lp
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService {

    @Autowired
    private InportMapper inportMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ProviderService providerService;

    @Override
    public IPage<Outport> pageWithRelations(OutportVo outportVo) {
        log.info("开始查询退货记录, page={}, limit={}", outportVo.getPage(), outportVo.getLimit());

        // 1. 分页查询退货记录
        IPage<Outport> page = new Page<>(outportVo.getPage(), outportVo.getLimit());
        QueryWrapper<Outport> queryWrapper = buildQueryWrapper(outportVo);
        this.page(page, queryWrapper);

        List<Outport> records = page.getRecords();
        if (records.isEmpty()) {
            return page;
        }

        // 2. 批量查询关联数据（解凳N+1问题）
        fillRelationData(records);

        log.info("查询退货记录完成, total={}", page.getTotal());
        return page;
    }

    @Override
    public void addOutPort(Integer id, Integer number, String remark) {
        log.info("开始添加退货记录, inportId={}, number={}", id, number);

        // 1. 参数校验
        if (id == null || id <= 0) {
            throw new BusinessException("进货单ID不能为空");
        }
        if (number == null || number <= 0) {
            throw new BusinessException("退货数量必须大于0");
        }

        // 2. 根据进货单ID查询进货单信息
        Inport inport = this.inportMapper.selectById(id);
        if (inport == null) {
            throw new BusinessException("进货单不存在");
        }

        // 3. 检查退货数量
        if (number > inport.getNumber()) {
            throw new BusinessException("退货数量不能大于进货数量");
        }

        // 4. 根据商品ID查询商品信息
        Goods goods = this.goodsMapper.selectById(inport.getGoodsid());
        if (goods == null) {
            throw new BusinessException("商品不存在");
        }

        // 5. 检查库存是否足够
        if (goods.getNumber() < number) {
            throw new BusinessException("库存不足，当前库存: " + goods.getNumber());
        }

        // 6. 更新库存
        int oldStock = goods.getNumber();
        goods.setNumber(goods.getNumber() - number);
        this.goodsMapper.updateById(goods);

        // 7. 添加退货单信息
        Outport entity = new Outport();
        entity.setGoodsid(inport.getGoodsid());
        entity.setNumber(number);
        User user = (User) WebUtils.getSession().getAttribute("user");
        if (user != null) {
            entity.setOperateperson(user.getName());
        }
        entity.setOutportprice(inport.getInportprice());
        entity.setOutputtime(new Date());
        entity.setPaytype(inport.getPaytype());
        entity.setProviderid(inport.getProviderid());
        entity.setRemark(remark);
        this.getBaseMapper().insert(entity);

        log.info("退货记录添加成功, id={}, 库存更新: {} -> {}",
                entity.getId(), oldStock, goods.getNumber());
    }

    /**
     * 构建查询条件
     */
    private QueryWrapper<Outport> buildQueryWrapper(OutportVo outportVo) {
        QueryWrapper<Outport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(outportVo.getProviderid() != null && outportVo.getProviderid() != 0,
                "providerid", outportVo.getProviderid());
        queryWrapper.eq(outportVo.getGoodsid() != null && outportVo.getGoodsid() != 0,
                "goodsid", outportVo.getGoodsid());
        queryWrapper.ge(outportVo.getStartTime() != null,
                "outputtime", outportVo.getStartTime());
        queryWrapper.le(outportVo.getEndTime() != null,
                "outputtime", outportVo.getEndTime());
        queryWrapper.like(StringUtils.isNotBlank(outportVo.getOperateperson()),
                "operateperson", outportVo.getOperateperson());
        queryWrapper.like(StringUtils.isNotBlank(outportVo.getRemark()),
                "remark", outportVo.getRemark());
        queryWrapper.orderByDesc("outputtime");
        return queryWrapper;
    }

    /**
     * 填充关联数据（批量查询，解凳N+1问题）
     */
    private void fillRelationData(List<Outport> records) {
        // 收集所有ID
        Set<Integer> providerIds = records.stream()
                .map(Outport::getProviderid)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Set<Integer> goodsIds = records.stream()
                .map(Outport::getGoodsid)
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
        for (Outport outport : records) {
            Provider provider = providerMap.get(outport.getProviderid());
            if (provider != null) {
                outport.setProvidername(provider.getProvidername());
            }

            Goods goods = goodsMap.get(outport.getGoodsid());
            if (goods != null) {
                outport.setGoodsname(goods.getGoodsname());
                outport.setSize(goods.getSize());
            }
        }
    }
}
