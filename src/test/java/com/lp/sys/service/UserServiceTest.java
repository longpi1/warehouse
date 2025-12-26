package com.lp.sys.service;

import com.lp.sys.domain.User;
import com.lp.sys.mapper.RoleMapper;
import com.lp.sys.mapper.UserMapper;
import com.lp.sys.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * 用户服务单元测试
 *
 * @author lp
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RoleMapper roleMapper;

    private User testUser;

    @Before
    public void setUp() {
        // 初始化测试用户
        testUser = new User();
        testUser.setId(1);
        testUser.setName("测试用户");
        testUser.setLoginname("testuser");
        testUser.setPwd("encrypted_password");
        testUser.setSalt("random_salt");
        testUser.setType(1);  // 普通用户
        testUser.setAvailable(1);  // 可用
    }

    /**
     * 测试: 删除用户 - 成功场景
     */
    @Test
    public void testRemoveById_Success() {
        // Given: 用户存在
        Integer userId = 1;
        when(userMapper.deleteById(userId)).thenReturn(1);

        // When: 删除用户
        boolean result = userService.removeById(userId);

        // Then: 验证删除用户角色关联
        verify(roleMapper).deleteRoleUserByUid(userId);
        verify(userMapper).deleteById(userId);
    }

    /**
     * 测试: 保存用户角色 - 分配单个角色
     */
    @Test
    public void testSaveUserRole_SingleRole() {
        // Given: 用户ID和角色ID
        Integer userId = 1;
        Integer[] roleIds = {2};

        // When: 分配角色
        userService.saveUserRole(userId, roleIds);

        // Then: 验证先删除旧关系,再插入新关系
        verify(roleMapper).deleteRoleUserByUid(userId);
        verify(roleMapper, times(1)).insertUserRole(userId, 2);
    }

    /**
     * 测试: 保存用户角色 - 分配多个角色
     */
    @Test
    public void testSaveUserRole_MultipleRoles() {
        // Given: 用户ID和多个角色ID
        Integer userId = 1;
        Integer[] roleIds = {2, 3, 4};

        // When: 分配多个角色
        userService.saveUserRole(userId, roleIds);

        // Then: 验证删除并插入3次
        verify(roleMapper).deleteRoleUserByUid(userId);
        verify(roleMapper, times(3)).insertUserRole(eq(userId), anyInt());
        verify(roleMapper).insertUserRole(userId, 2);
        verify(roleMapper).insertUserRole(userId, 3);
        verify(roleMapper).insertUserRole(userId, 4);
    }

    /**
     * 测试: 保存用户角色 - 清空所有角色
     */
    @Test
    public void testSaveUserRole_EmptyRoles() {
        // Given: 用户ID和空角色数组
        Integer userId = 1;
        Integer[] roleIds = {};

        // When: 清空角色
        userService.saveUserRole(userId, roleIds);

        // Then: 只删除不插入
        verify(roleMapper).deleteRoleUserByUid(userId);
        verify(roleMapper, never()).insertUserRole(anyInt(), anyInt());
    }

    /**
     * 测试: 保存用户角色 - 角色为null
     */
    @Test
    public void testSaveUserRole_NullRoles() {
        // Given: 用户ID和null角色
        Integer userId = 1;
        Integer[] roleIds = null;

        // When: 角色为null
        userService.saveUserRole(userId, roleIds);

        // Then: 只删除不插入
        verify(roleMapper).deleteRoleUserByUid(userId);
        verify(roleMapper, never()).insertUserRole(anyInt(), anyInt());
    }

    /**
     * 测试: 保存用户角色 - 重新分配角色
     */
    @Test
    public void testSaveUserRole_ReassignRoles() {
        // Given: 用户已有角色,现在重新分配
        Integer userId = 1;
        Integer[] newRoleIds = {5, 6};

        // When: 重新分配角色
        userService.saveUserRole(userId, newRoleIds);

        // Then: 先删除旧角色,再分配新角色
        verify(roleMapper).deleteRoleUserByUid(userId);
        verify(roleMapper).insertUserRole(userId, 5);
        verify(roleMapper).insertUserRole(userId, 6);
    }

    /**
     * 测试: 删除用户 - 验证级联删除
     */
    @Test
    public void testRemoveById_CascadeDelete() {
        // Given: 用户有关联的角色
        Integer userId = 1;
        when(userMapper.deleteById(userId)).thenReturn(1);

        // When: 删除用户
        userService.removeById(userId);

        // Then: 先删除角色关联,再删除用户
        verify(roleMapper).deleteRoleUserByUid(userId);
        verify(userMapper).deleteById(userId);
    }
}
