package com.lp.sys.service.impl;

import com.lp.sys.domain.Role;
import com.lp.sys.mapper.RoleMapper;
import com.lp.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lp
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Override
    public boolean removeById(Serializable id) {
        this.getBaseMapper().deleteRolePermissionByRid(id);
        this.getBaseMapper().deleteRoleUserByRid(id);
        return super.removeById(id);
    }

    /**
     * 根据角色ID查询当前角色拥有的所有的权限或菜单ID
     */
    @Override
    public List<Integer> queryRolePermissionIdsByRid(Integer roleId) {
        return this.getBaseMapper().queryRolePermissionIdsByRid(roleId);
    }

    /**
     * 保存角色和菜单权限之间的关系
     */
    @Override
    public void saveRolePermission(Integer rid, Integer[] ids) {
        RoleMapper roleMapper = this.getBaseMapper();
        roleMapper.deleteRolePermissionByRid(rid);
        if (ids != null && ids.length > 0) {
            for (Integer pid : ids) {
                roleMapper.saveRolePermission(rid, pid);
            }
        }
    }


    @Override
    public List<Integer> queryUserRoleIdsByUid(Integer id) {
        return this.getBaseMapper().queryUserRoleIdsByUid(id);
    }
}
