package com.lp.sys.service;

import com.lp.sys.domain.Role;
import com.lp.sys.mapper.RoleMapper;
import com.lp.sys.service.impl.RoleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * 角色服务单元测试
 *
 * @author lp
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleMapper roleMapper;

    private Role testRole;

    @Before
    public void setUp() {
        // 初始化测试角色
        testRole = new Role();
        testRole.setId(1);
        testRole.setName("测试角色");
        testRole.setRemark("测试角色描述");
        testRole.setAvailable(1);
    }

    /**
     * 测试: 删除角色 - 成功场景
     */
    @Test
    public void testRemoveById_Success() {
        // Given: 角色存在
        Integer roleId = 1;
        when(roleMapper.deleteById(roleId)).thenReturn(1);

        // When: 删除角色
        boolean result = roleService.removeById(roleId);

        // Then: 验证级联删除
        verify(roleMapper).deleteRolePermissionByRid(roleId);  // 删除角色-权限关联
        verify(roleMapper).deleteRoleUserByRid(roleId);        // 删除角色-用户关联
        verify(roleMapper).deleteById(roleId);                 // 删除角色
    }

    /**
     * 测试: 查询角色权限ID - 有权限
     */
    @Test
    public void testQueryRolePermissionIds_HasPermissions() {
        // Given: 角色拥有权限
        Integer roleId = 1;
        List<Integer> permissionIds = Arrays.asList(10, 11, 12);
        when(roleMapper.queryRolePermissionIdsByRid(roleId)).thenReturn(permissionIds);

        // When: 查询角色权限
        List<Integer> result = roleService.queryRolePermissionIdsByRid(roleId);

        // Then: 返回权限ID列表
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(10));
        assertTrue(result.contains(11));
        assertTrue(result.contains(12));
    }

    /**
     * 测试: 查询角色权限ID - 无权限
     */
    @Test
    public void testQueryRolePermissionIds_NoPermissions() {
        // Given: 角色没有权限
        Integer roleId = 1;
        when(roleMapper.queryRolePermissionIdsByRid(roleId)).thenReturn(Arrays.asList());

        // When: 查询角色权限
        List<Integer> result = roleService.queryRolePermissionIdsByRid(roleId);

        // Then: 返回空列表
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * 测试: 保存角色权限 - 分配单个权限
     */
    @Test
    public void testSaveRolePermission_SinglePermission() {
        // Given: 角色ID和权限ID
        Integer roleId = 1;
        Integer[] permissionIds = {10};

        // When: 分配权限
        roleService.saveRolePermission(roleId, permissionIds);

        // Then: 先删除后插入
        verify(roleMapper).deleteRolePermissionByRid(roleId);
        verify(roleMapper, times(1)).saveRolePermission(roleId, 10);
    }

    /**
     * 测试: 保存角色权限 - 分配多个权限
     */
    @Test
    public void testSaveRolePermission_MultiplePermissions() {
        // Given: 角色ID和多个权限ID
        Integer roleId = 1;
        Integer[] permissionIds = {10, 11, 12, 13};

        // When: 分配多个权限
        roleService.saveRolePermission(roleId, permissionIds);

        // Then: 验证删除并插入4次
        verify(roleMapper).deleteRolePermissionByRid(roleId);
        verify(roleMapper, times(4)).saveRolePermission(eq(roleId), anyInt());
        verify(roleMapper).saveRolePermission(roleId, 10);
        verify(roleMapper).saveRolePermission(roleId, 11);
        verify(roleMapper).saveRolePermission(roleId, 12);
        verify(roleMapper).saveRolePermission(roleId, 13);
    }

    /**
     * 测试: 保存角色权限 - 清空所有权限
     */
    @Test
    public void testSaveRolePermission_EmptyPermissions() {
        // Given: 角色ID和空权限数组
        Integer roleId = 1;
        Integer[] permissionIds = {};

        // When: 清空权限
        roleService.saveRolePermission(roleId, permissionIds);

        // Then: 只删除不插入
        verify(roleMapper).deleteRolePermissionByRid(roleId);
        verify(roleMapper, never()).saveRolePermission(anyInt(), anyInt());
    }

    /**
     * 测试: 保存角色权限 - 权限为null
     */
    @Test
    public void testSaveRolePermission_NullPermissions() {
        // Given: 角色ID和null权限
        Integer roleId = 1;
        Integer[] permissionIds = null;

        // When: 权限为null
        roleService.saveRolePermission(roleId, permissionIds);

        // Then: 只删除不插入
        verify(roleMapper).deleteRolePermissionByRid(roleId);
        verify(roleMapper, never()).saveRolePermission(anyInt(), anyInt());
    }

    /**
     * 测试: 保存角色权限 - 重新分配权限
     */
    @Test
    public void testSaveRolePermission_ReassignPermissions() {
        // Given: 角色已有权限,现在重新分配
        Integer roleId = 1;
        Integer[] newPermissionIds = {20, 21};

        // When: 重新分配权限
        roleService.saveRolePermission(roleId, newPermissionIds);

        // Then: 先删除旧权限,再分配新权限
        verify(roleMapper).deleteRolePermissionByRid(roleId);
        verify(roleMapper).saveRolePermission(roleId, 20);
        verify(roleMapper).saveRolePermission(roleId, 21);
    }

    /**
     * 测试: 查询用户角色ID - 用户有角色
     */
    @Test
    public void testQueryUserRoleIds_HasRoles() {
        // Given: 用户拥有角色
        Integer userId = 1;
        List<Integer> roleIds = Arrays.asList(1, 2, 3);
        when(roleMapper.queryUserRoleIdsByUid(userId)).thenReturn(roleIds);

        // When: 查询用户角色
        List<Integer> result = roleService.queryUserRoleIdsByUid(userId);

        // Then: 返回角色ID列表
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    /**
     * 测试: 查询用户角色ID - 用户无角色
     */
    @Test
    public void testQueryUserRoleIds_NoRoles() {
        // Given: 用户没有角色
        Integer userId = 1;
        when(roleMapper.queryUserRoleIdsByUid(userId)).thenReturn(Arrays.asList());

        // When: 查询用户角色
        List<Integer> result = roleService.queryUserRoleIdsByUid(userId);

        // Then: 返回空列表
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * 测试: 删除角色 - 验证删除顺序
     */
    @Test
    public void testRemoveById_VerifyDeleteOrder() {
        // Given: 角色ID
        Integer roleId = 1;
        when(roleMapper.deleteById(roleId)).thenReturn(1);

        // When: 删除角色
        roleService.removeById(roleId);

        // Then: 验证删除顺序(先删除关联,再删除角色本身)
        verify(roleMapper).deleteRolePermissionByRid(roleId);
        verify(roleMapper).deleteRoleUserByRid(roleId);
        verify(roleMapper).deleteById(roleId);
    }

    /**
     * 测试: 保存角色权限 - 大量权限
     */
    @Test
    public void testSaveRolePermission_ManyPermissions() {
        // Given: 分配大量权限
        Integer roleId = 1;
        Integer[] permissionIds = new Integer[100];
        for (int i = 0; i < 100; i++) {
            permissionIds[i] = i + 1;
        }

        // When: 保存权限
        roleService.saveRolePermission(roleId, permissionIds);

        // Then: 验证删除并插入100次
        verify(roleMapper).deleteRolePermissionByRid(roleId);
        verify(roleMapper, times(100)).saveRolePermission(eq(roleId), anyInt());
    }
}
