package com.lp.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lp.sys.exception.BusinessException;
import com.lp.warehouse.domain.Goods;
import com.lp.warehouse.domain.Inport;
import com.lp.warehouse.service.impl.InportServiceImpl;
import com.lp.warehouse.vo.InportVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * 进货服务单元测试
 *
 * @author lp
 */
@RunWith(MockitoJUnitRunner.class)
public class InportServiceTest {

    @InjectMocks
    private InportServiceImpl inportService;

    @Mock
    private GoodsService goodsService;

    @Mock
    private ProviderService providerService;

    private InportVo validInportVo;
    private Goods testGoods;

    @Before
    public void setUp() {
        // 初始化测试数据
        validInportVo = new InportVo();
        validInportVo.setGoodsid(1);
        validInportVo.setProviderid(1);
        validInportVo.setNumber(100);
        validInportVo.setInportprice(50.0);
        validInportVo.setPage(1);
        validInportVo.setLimit(10);

        testGoods = new Goods();
        testGoods.setId(1);
        testGoods.setGoodsname("测试商品");
        testGoods.setNumber(200);
    }

    /**
     * 测试: 添加进货记录 - 成功场景
     */
    @Test
    public void testAddInportWithStock_Success() {
        // Given: 商品存在且库存充足
        when(goodsService.getById(1)).thenReturn(testGoods);
        when(goodsService.updateById(any(Goods.class))).thenReturn(true);

        // When: 添加进货记录
        inportService.addInportWithStock(validInportVo);

        // Then: 验证商品库存增加
        verify(goodsService).getById(1);
        verify(goodsService).updateById(argThat(goods ->
                goods.getNumber() == 300  // 200 + 100
        ));
    }

    /**
     * 测试: 添加进货记录 - 商品ID为空
     */
    @Test(expected = BusinessException.class)
    public void testAddInportWithStock_NullGoodsId() {
        // Given: 商品ID为空
        validInportVo.setGoodsid(null);

        // When & Then: 抛出业务异常
        inportService.addInportWithStock(validInportVo);
    }

    /**
     * 测试: 添加进货记录 - 商品ID无效
     */
    @Test(expected = BusinessException.class)
    public void testAddInportWithStock_InvalidGoodsId() {
        // Given: 商品ID为0
        validInportVo.setGoodsid(0);

        // When & Then: 抛出业务异常
        inportService.addInportWithStock(validInportVo);
    }

    /**
     * 测试: 添加进货记录 - 供应商ID为空
     */
    @Test(expected = BusinessException.class)
    public void testAddInportWithStock_NullProviderId() {
        // Given: 供应商ID为空
        validInportVo.setProviderid(null);

        // When & Then: 抛出业务异常
        inportService.addInportWithStock(validInportVo);
    }

    /**
     * 测试: 添加进货记录 - 进货数量为0
     */
    @Test(expected = BusinessException.class)
    public void testAddInportWithStock_ZeroNumber() {
        // Given: 进货数量为0
        validInportVo.setNumber(0);

        // When & Then: 抛出业务异常
        inportService.addInportWithStock(validInportVo);
    }

    /**
     * 测试: 添加进货记录 - 进货数量为负数
     */
    @Test(expected = BusinessException.class)
    public void testAddInportWithStock_NegativeNumber() {
        // Given: 进货数量为负数
        validInportVo.setNumber(-10);

        // When & Then: 抛出业务异常
        inportService.addInportWithStock(validInportVo);
    }

    /**
     * 测试: 添加进货记录 - 进货价格为负数
     */
    @Test(expected = BusinessException.class)
    public void testAddInportWithStock_NegativePrice() {
        // Given: 进货价格为负数
        validInportVo.setInportprice(-50.0);

        // When & Then: 抛出业务异常
        inportService.addInportWithStock(validInportVo);
    }

    /**
     * 测试: 添加进货记录 - 商品不存在
     */
    @Test(expected = BusinessException.class)
    public void testAddInportWithStock_GoodsNotFound() {
        // Given: 商品不存在
        when(goodsService.getById(1)).thenReturn(null);

        // When & Then: 抛出业务异常
        inportService.addInportWithStock(validInportVo);
    }

    /**
     * 测试: 更新进货记录 - 成功场景
     */
    @Test
    public void testUpdateInport_Success() {
        // Given: 原进货记录和商品都存在
        Inport oldInport = new Inport();
        oldInport.setId(1);
        oldInport.setGoodsid(1);
        oldInport.setNumber(50);

        validInportVo.setId(1);
        validInportVo.setNumber(100);

        when(inportService.getById(1)).thenReturn(oldInport);
        when(goodsService.getById(1)).thenReturn(testGoods);
        when(goodsService.updateById(any(Goods.class))).thenReturn(true);

        // When: 更新进货记录
        inportService.updateInport(validInportVo);

        // Then: 验证库存重新计算 (200 - 50 + 100 = 250)
        verify(goodsService).updateById(argThat(goods ->
                goods.getNumber() == 250
        ));
    }

    /**
     * 测试: 更新进货记录 - 进货记录不存在
     */
    @Test(expected = BusinessException.class)
    public void testUpdateInport_InportNotFound() {
        // Given: 进货记录不存在
        validInportVo.setId(999);
        when(inportService.getById(999)).thenReturn(null);

        // When & Then: 抛出业务异常
        inportService.updateInport(validInportVo);
    }

    /**
     * 测试: 删除进货记录 - 成功场景
     */
    @Test
    public void testDeleteInport_Success() {
        // Given: 进货记录和商品都存在,库存充足
        Inport inport = new Inport();
        inport.setId(1);
        inport.setGoodsid(1);
        inport.setNumber(50);

        when(inportService.getById(1)).thenReturn(inport);
        when(goodsService.getById(1)).thenReturn(testGoods);  // 库存200
        when(goodsService.updateById(any(Goods.class))).thenReturn(true);

        // When: 删除进货记录
        inportService.deleteInport(1);

        // Then: 验证库存减少 (200 - 50 = 150)
        verify(goodsService).updateById(argThat(goods ->
                goods.getNumber() == 150
        ));
    }

    /**
     * 测试: 删除进货记录 - 库存不足
     */
    @Test(expected = BusinessException.class)
    public void testDeleteInport_InsufficientStock() {
        // Given: 进货数量大于当前库存
        Inport inport = new Inport();
        inport.setId(1);
        inport.setGoodsid(1);
        inport.setNumber(300);  // 大于库存200

        testGoods.setNumber(200);

        when(inportService.getById(1)).thenReturn(inport);
        when(goodsService.getById(1)).thenReturn(testGoods);

        // When & Then: 抛出业务异常
        inportService.deleteInport(1);
    }

    /**
     * 测试: 删除进货记录 - 进货记录不存在
     */
    @Test(expected = BusinessException.class)
    public void testDeleteInport_InportNotFound() {
        // Given: 进货记录不存在
        when(inportService.getById(1)).thenReturn(null);

        // When & Then: 抛出业务异常
        inportService.deleteInport(1);
    }

    /**
     * 测试: 库存边界值 - 大数量进货
     */
    @Test
    public void testAddInportWithStock_LargeNumber() {
        // Given: 大数量进货
        validInportVo.setNumber(999999);
        testGoods.setNumber(1);

        when(goodsService.getById(1)).thenReturn(testGoods);
        when(goodsService.updateById(any(Goods.class))).thenReturn(true);

        // When: 添加进货记录
        inportService.addInportWithStock(validInportVo);

        // Then: 验证库存正确增加
        verify(goodsService).updateById(argThat(goods ->
                goods.getNumber() == 1000000
        ));
    }

    /**
     * 测试: 删除进货记录 - 库存刚好等于进货数量
     */
    @Test
    public void testDeleteInport_ExactStock() {
        // Given: 库存刚好等于进货数量
        Inport inport = new Inport();
        inport.setId(1);
        inport.setGoodsid(1);
        inport.setNumber(200);  // 等于库存

        testGoods.setNumber(200);

        when(inportService.getById(1)).thenReturn(inport);
        when(goodsService.getById(1)).thenReturn(testGoods);
        when(goodsService.updateById(any(Goods.class))).thenReturn(true);

        // When: 删除进货记录
        inportService.deleteInport(1);

        // Then: 验证库存变为0
        verify(goodsService).updateById(argThat(goods ->
                goods.getNumber() == 0
        ));
    }
}
