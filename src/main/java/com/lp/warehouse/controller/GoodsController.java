package com.lp.warehouse.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lp.warehouse.domain.Goods;
import com.lp.warehouse.domain.Provider;
import com.lp.warehouse.service.GoodsService;
import com.lp.warehouse.service.ProviderService;
import com.lp.warehouse.vo.GoodsVo;
import com.lp.sys.common.AppFileUtils;
import com.lp.sys.common.Constast;
import com.lp.sys.common.DataGridView;
import com.lp.sys.common.ResultObj;

/**
 * @author lp
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ProviderService providerService;

    /**
     * 查询
     */
    @RequestMapping("loadAllGoods")
    public DataGridView loadAllGoods(GoodsVo goodsVo) {
        IPage<Goods> page = new Page<>(goodsVo.getPage(), goodsVo.getLimit());
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(goodsVo.getProviderid() != null && goodsVo.getProviderid() != 0, "providerid", goodsVo.getProviderid());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getGoodsname()), "goodsname", goodsVo.getGoodsname());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getProductcode()), "productcode", goodsVo.getProductcode());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getPromitcode()), "promitcode", goodsVo.getPromitcode());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getDescription()), "description", goodsVo.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getSize()), "size", goodsVo.getSize());
        this.goodsService.page(page, queryWrapper);
        List<Goods> records = page.getRecords();
        for (Goods goods : records) {
            Provider provider = this.providerService.getById(goods.getProviderid());
            if (null != provider) {
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(), records);
    }

    /**
     * 添加
     */
    @RequestMapping("addGoods")
    public ResultObj addGoods(GoodsVo goodsVo) {
        try {
            if (goodsVo.getGoodsimg() != null && goodsVo.getGoodsimg().endsWith("_temp")) {
                String newName = AppFileUtils.renameFile(goodsVo.getGoodsimg());
                goodsVo.setGoodsimg(newName);
            }
            this.goodsService.save(goodsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改
     */
    @RequestMapping("updateGoods")
    public ResultObj updateGoods(GoodsVo goodsVo) {
        try {
            //说明是不默认图片
            if (!(goodsVo.getGoodsimg() != null && goodsVo.getGoodsimg().equals(Constast.IMAGES_DEFAULTGOODSIMG_PNG))) {
                if (goodsVo.getGoodsimg().endsWith("_temp")) {
                    String newName = AppFileUtils.renameFile(goodsVo.getGoodsimg());
                    goodsVo.setGoodsimg(newName);
                    //删除原先的图片
                    String oldPath = this.goodsService.getById(goodsVo.getId()).getGoodsimg();
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            this.goodsService.updateById(goodsVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     */
    @RequestMapping("deleteGoods")
    public ResultObj deleteGoods(Integer id, String goodsimg) {
        try {
            //删除原文件
            AppFileUtils.removeFileByPath(goodsimg);
            this.goodsService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载所有可用的供应商
     */
    @RequestMapping("loadAllGoodsForSelect")
    public DataGridView loadAllGoodsForSelect() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Goods> list = this.goodsService.list(queryWrapper);
        for (Goods goods : list) {
            Provider provider = this.providerService.getById(goods.getProviderid());
            if (null != provider) {
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }

    /**
     * 根据供应商ID查询商品信息
     */
    @RequestMapping("loadGoodsByProviderId")
    public DataGridView loadGoodsByProviderId(Integer providerid) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        queryWrapper.eq(providerid != null, "providerid", providerid);
        List<Goods> list = this.goodsService.list(queryWrapper);
        for (Goods goods : list) {
            Provider provider = this.providerService.getById(goods.getProviderid());
            if (null != provider) {
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }
}
