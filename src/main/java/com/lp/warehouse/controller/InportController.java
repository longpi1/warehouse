package com.lp.warehouse.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lp.warehouse.domain.Inport;
import com.lp.warehouse.service.InportService;
import com.lp.warehouse.vo.InportVo;
import com.lp.sys.common.DataGridView;
import com.lp.sys.common.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 进货管理控制器
 *
 * @author lp
 */
@RestController
@RequestMapping("inport")
@Slf4j
public class InportController {

    @Autowired
    private InportService inportService;

    /**
     * 分页查询进货记录
     */
    @RequestMapping("loadAllInport")
    public DataGridView loadAllInport(InportVo inportVo) {
        IPage<Inport> page = inportService.pageWithRelations(inportVo);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加进货记录
     */
    @RequestMapping("addInport")
    public ResultObj addInport(InportVo inportVo) {
        inportService.addInportWithStock(inportVo);
        return ResultObj.ADD_SUCCESS;
    }

    /**
     * 修改进货记录
     */
    @RequestMapping("updateInport")
    public ResultObj updateInport(InportVo inportVo) {
        inportService.updateInport(inportVo);
        return ResultObj.UPDATE_SUCCESS;
    }

    /**
     * 删除进货记录
     */
    @RequestMapping("deleteInport")
    public ResultObj deleteInport(Integer id) {
        inportService.deleteInport(id);
        return ResultObj.DELETE_SUCCESS;
    }
}

