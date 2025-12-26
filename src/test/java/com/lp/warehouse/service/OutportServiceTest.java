package com.lp.warehouse.service;

import com.lp.sys.exception.BusinessException;
import com.lp.warehouse.domain.Goods;
import com.lp.warehouse.domain.Inport;
import com.lp.warehouse.domain.Outport;
import com.lp.warehouse.mapper.GoodsMapper;
import com.lp.warehouse.mapper.InportMapper;
import com.lp.warehouse.mapper.OutportMapper;
import com.lp.warehouse.service.impl.OutportServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * 退货服务单元测试
 *
 * @author lp
 */
@RunWith(MockitoJUnitRunner.class)
public class OutportServiceTest {

    @InjectMocks
    private OutportServiceImpl outportService;

    @Mock
    private InportMapper inportMapper;

    @Mock
    private GoodsMapper goodsMapper;

    @Mock
    private OutportMapper outportMapper;

    @Mock
    private GoodsService goodsService;

    private Inport testInport;
    private Goods testGoods;

    @Before
    public void setUp() {
        // 初始化测试数据
        testInport = new Inport();
        testInport.setId(1);
        testInport.setGoodsid(1);
        testInport.setProviderid(1);
        testInport.setNumber(100);
        testInport.setInportprice(50.0);
        testInport.setPaytype("现金");

        testGoods = new Goods();
        testGoods.setId(1);
        testGoods.setGoodsname("测试商品");
        testGoods.setNumber(200);
    }

    /**
     * 测试: 添加退货记录 - 成功场景
     */
    @Test
    public void testAddOutPort_Success() {
        // Given: 进货单存在,商品存在,库存充足
        when(inportMapper.selectById(1)).thenReturn(testInport);
        when(goodsMapper.selectById(1)).thenReturn(testGoods);
        when(goodsMapper.updateById(any(Goods.class))).thenReturn(1);
        when(outportMapper.insert(any(Outport.class))).thenReturn(1);

        // When: 添加退货记录
        outportService.addOutPort(1, 50, "质量问题");

        // Then: 验证库存减少 (200 - 50 = 150)
        verify(goodsMapper).updateById(argThat(goods ->
                goods.getNumber() == 150
        ));
        verify(outportMapper).insert(any(Outport.class));
    }

    /**
     * 测试: 添加退货记录 - 进货单ID为空
     */
    @Test(expected = BusinessException.class)
    public void testAddOutPort_NullInportId() {
        // When & Then: 抛出业务异常
        outportService.addOutPort(null, 50, "备注");
    }

    /**
     * 测试: 添加退货记录 - 进货单ID无效
     */
    @Test(expected = BusinessException.class)
    public void testAddOutPort_InvalidInportId() {
        // When & Then: 抛出业务异常
        outportService.addOutPort(0, 50, "备注");
    }

    /**
     * 测试: 添加退货记录 - 退货数量为空
     */
    @Test(expected = BusinessException.class)
    public void testAddOutPort_NullNumber() {
        // When & Then: 抛出业务异常
        outportService.addOutPort(1, null, "备注");
    }

    /**
     * 测试: 添加退货记录 - 退货数量为0
     */
    @Test(expected = BusinessException.class)
    public void testAddOutPort_ZeroNumber() {
        // When & Then: 抛出业务异常
        outportService.addOutPort(1, 0, "备注");
    }

    /**
     * 测试: 添加退货记录 - 退货数量为负数
     */
    @Test(expected = BusinessException.class)
    public void testAddOutPort_NegativeNumber() {
        // When & Then: 抛出业务异常
        outportService.addOutPort(1, -10, "备注");
    }

    /**
     * 测试: 添加退货记录 - 进货单不存在
     */
    @Test(expected = BusinessException.class)
    public void testAddOutPort_InportNotFound() {
        // Given: 进货单不存在
        when(inportMapper.selectById(1)).thenReturn(null);

        // When & Then: 抛出业务异常
        outportService.addOutPort(1, 50, "备注");
    }

    /**
     * 测试: 添加退货记录 - 退货数量大于进货数量
     */
    @Test(expected = BusinessException.class)
    public void testAddOutPort_ExceedsInportNumber() {
        // Given: 退货数量大于进货数量
        when(inportMapper.selectById(1)).thenReturn(testInport);

        // When & Then: 抛出业务异常 (进货100,退货150)
        outportService.addOutPort(1, 150, "备注");
    }

    /**
     * 测试: 添加退货记录 - 商品不存在
     */
    @Test(expected = BusinessException.class)
    public void testAddOutPort_GoodsNotFound() {
        // Given: 商品不存在
        when(inportMapper.selectById(1)).thenReturn(testInport);
        when(goodsMapper.selectById(1)).thenReturn(null);

        // When & Then: 抛出业务异常
        outportService.addOutPort(1, 50, "备注");
    }

    /**
     * 测试: 添加退货记录 - 库存不足
     */
    @Test(expected = BusinessException.class)
    public void testAddOutPort_InsufficientStock() {
        // Given: 库存不足
        testGoods.setNumber(30);  // 库存30,要退50
        when(inportMapper.selectById(1)).thenReturn(testInport);
        when(goodsMapper.selectById(1)).thenReturn(testGoods);

        // When & Then: 抛出业务异常
        outportService.addOutPort(1, 50, "备注");
    }

    /**
     * 测试: 添加退货记录 - 退货数量等于进货数量
     */
    @Test
    public void testAddOutPort_EqualToInportNumber() {
        // Given: 退货数量等于进货数量
        when(inportMapper.selectById(1)).thenReturn(testInport);
        when(goodsMapper.selectById(1)).thenReturn(testGoods);
        when(goodsMapper.updateById(any(Goods.class))).thenReturn(1);
        when(outportMapper.insert(any(Outport.class))).thenReturn(1);

        // When: 退货数量等于进货数量
        outportService.addOutPort(1, 100, "全部退货");

        // Then: 验证库存减少 (200 - 100 = 100)
        verify(goodsMapper).updateById(argThat(goods ->
                goods.getNumber() == 100
        ));
        verify(outportMapper).insert(argThat(outport ->
                outport.getNumber() == 100 &&
                        "全部退货".equals(outport.getRemark())
        ));
    }

    /**
     * 测试: 添加退货记录 - 库存刚好等于退货数量
     */
    @Test
    public void testAddOutPort_ExactStock() {
        // Given: 库存刚好等于退货数量
        testGoods.setNumber(50);
        when(inportMapper.selectById(1)).thenReturn(testInport);
        when(goodsMapper.selectById(1)).thenReturn(testGoods);
        when(goodsMapper.updateById(any(Goods.class))).thenReturn(1);
        when(outportMapper.insert(any(Outport.class))).thenReturn(1);

        // When: 退货50,库存也是50
        outportService.addOutPort(1, 50, "备注");

        // Then: 验证库存变为0
        verify(goodsMapper).updateById(argThat(goods ->
                goods.getNumber() == 0
        ));
    }

    /**
     * 测试: 添加退货记录 - 验证退货单信息完整性
     */
    @Test
    public void testAddOutPort_VerifyOutportData() {
        // Given: 准备测试数据
        when(inportMapper.selectById(1)).thenReturn(testInport);
        when(goodsMapper.selectById(1)).thenReturn(testGoods);
        when(goodsMapper.updateById(any(Goods.class))).thenReturn(1);
        when(outportMapper.insert(any(Outport.class))).thenReturn(1);

        // When: 添加退货记录
        outportService.addOutPort(1, 50, "质量问题退货");

        // Then: 验证退货单信息
        verify(outportMapper).insert(argThat(outport ->
                outport.getGoodsid().equals(1) &&
                        outport.getProviderid().equals(1) &&
                        outport.getNumber().equals(50) &&
                        outport.getOutportprice().equals(50.0) &&
                        "现金".equals(outport.getPaytype()) &&
                        "质量问题退货".equals(outport.getRemark()) &&
                        outport.getOutputtime() != null
        ));
    }

    /**
     * 测试: 添加退货记录 - 最小退货数量
     */
    @Test
    public void testAddOutPort_MinimumNumber() {
        // Given: 最小退货数量
        when(inportMapper.selectById(1)).thenReturn(testInport);
        when(goodsMapper.selectById(1)).thenReturn(testGoods);
        when(goodsMapper.updateById(any(Goods.class))).thenReturn(1);
        when(outportMapper.insert(any(Outport.class))).thenReturn(1);

        // When: 退货1件
        outportService.addOutPort(1, 1, "备注");

        // Then: 验证库存减少1 (200 - 1 = 199)
        verify(goodsMapper).updateById(argThat(goods ->
                goods.getNumber() == 199
        ));
    }

    /**
     * 测试: 添加退货记录 - 空备注
     */
    @Test
    public void testAddOutPort_NullRemark() {
        // Given: 备注为空
        when(inportMapper.selectById(1)).thenReturn(testInport);
        when(goodsMapper.selectById(1)).thenReturn(testGoods);
        when(goodsMapper.updateById(any(Goods.class))).thenReturn(1);
        when(outportMapper.insert(any(Outport.class))).thenReturn(1);

        // When: 备注为null
        outportService.addOutPort(1, 50, null);

        // Then: 正常执行
        verify(outportMapper).insert(argThat(outport ->
                outport.getRemark() == null
        ));
    }
}
