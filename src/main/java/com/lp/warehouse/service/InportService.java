package com.lp.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lp.warehouse.domain.Inport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lp.warehouse.vo.InportVo;

/**
 * <p>
 * 进货服务类
 * </p>
 *
 * @author lp
 */
public interface InportService extends IService<Inport> {

    /**
     * 分页查询进货记录（包含关联数据）
     *
     * @param inportVo 查询条件
     * @return 分页结果
     */
    IPage<Inport> pageWithRelations(InportVo inportVo);

    /**
     * 添加进货记录（自动更新库存）
     *
     * @param inportVo 进货信息
     */
    void addInportWithStock(InportVo inportVo);

    /**
     * 更新进货记录
     *
     * @param inportVo 进货信息
     */
    void updateInport(InportVo inportVo);

    /**
     * 删除进货记录
     *
     * @param id 进货记录ID
     */
    void deleteInport(Integer id);
}
