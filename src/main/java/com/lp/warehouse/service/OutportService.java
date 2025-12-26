package com.lp.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lp.warehouse.domain.Outport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lp.warehouse.vo.OutportVo;

/**
 * <p>
 * 退货服务类
 * </p>
 *
 * @author lp
 */
public interface OutportService extends IService<Outport> {

    /**
     * 分页查询退货记录（包含关联数据）
     *
     * @param outportVo 查询条件
     * @return 分页结果
     */
    IPage<Outport> pageWithRelations(OutportVo outportVo);

    /**
     * 添加退货记录（自动更新库存）
     *
     * @param id     进货单ID
     * @param number 退货数量
     * @param remark 备注
     */
    void addOutPort(Integer id, Integer number, String remark);

}
