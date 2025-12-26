package com.lp.warehouse.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lp.warehouse.domain.Outport;
import com.lp.warehouse.service.OutportService;
import com.lp.warehouse.vo.OutportVo;
import com.lp.sys.common.DataGridView;
import com.lp.sys.common.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 退货管理控制器
 *
 * @author lp
 */
@RestController
@RequestMapping("outport")
@Slf4j
public class OutportController {

    @Autowired
    private OutportService outportService;

    /**
     * 分页查询退货记录
     */
    @RequestMapping("loadAllOutport")
    public DataGridView loadAllOutport(OutportVo outportVo) {
        IPage<Outport> page = outportService.pageWithRelations(outportVo);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加退货记录
     */
    @RequestMapping("addOutport")
    public ResultObj addOutport(Integer id, Integer number, String remark) {
        outportService.addOutPort(id, number, remark);
        return ResultObj.OPERATE_SUCCESS;
    }
}

