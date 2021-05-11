package com.lp.sys.mapper;

import com.lp.sys.domain.Permission;

import java.io.Serializable;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lp
 */
public interface PermissionMapper extends BaseMapper<Permission> {

	/**
	 * /根据权限或菜单ID删除权限表各和角色的关系表里面的数据
	 * @param id
	 */
	void deleteRolePermissionByPid(@Param("id")Serializable id);

}
