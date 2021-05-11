package com.lp.sys.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lp.sys.common.ActiverUser;
import com.lp.sys.common.Constast;
import com.lp.sys.domain.Permission;
import com.lp.sys.domain.User;
import com.lp.sys.service.PermissionService;
import com.lp.sys.service.RoleService;
import com.lp.sys.service.UserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	@Lazy  //只有使用的时候才会加载 
	private UserService userService;
	
	@Autowired
	@Lazy
	private PermissionService permissionService;
	
	@Autowired
	@Lazy
	private RoleService roleService;

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}


	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("loginname", token.getPrincipal().toString());
		User user = userService.getOne(queryWrapper);
		if (null != user) {
			ActiverUser activerUser = new ActiverUser();
			activerUser.setUser(user);
			//根据用户ID查询percode
			//查询所有菜单
			QueryWrapper<Permission> qw=new QueryWrapper<>();
			//设置只能查询菜单
			qw.eq("type",Constast.TYPE_PERMISSION);
			qw.eq("available", Constast.AVAILABLE_TRUE);
			//根据用户ID+角色+权限去查询 
			Integer userId=user.getId();
			//根据用户ID查询角色
			List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(userId);
			//根据角色ID取到权限和菜单ID
			Set<Integer> pids=new HashSet<>();
			for (Integer rid : currentUserRoleIds) {
				List<Integer> permissionIds = roleService.queryRolePermissionIdsByRid(rid);
				pids.addAll(permissionIds);
			}
			List<Permission> list=new ArrayList<>();
			//根据角色ID查询权限
			if(pids.size()>0) {
				qw.in("id", pids);
				list=permissionService.list(qw);
			}
			List<String> percodes=new ArrayList<>();
			for (Permission permission : list) {
				percodes.add(permission.getPercode());
			}
			activerUser.setPermissions(percodes);
			ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser, user.getPwd(), credentialsSalt,
					this.getName());
			return info;
		}
		return null;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		ActiverUser activerUser=(ActiverUser) principals.getPrimaryPrincipal();
		User user=activerUser.getUser();
		List<String> permissions = activerUser.getPermissions();
		if(user.getType()==Constast.USER_TYPE_SUPER) {
			authorizationInfo.addStringPermission("*:*");
		}else {
			if(null!=permissions&&permissions.size()>0) {
				authorizationInfo.addStringPermissions(permissions);
			}
		}
		return authorizationInfo;
	}

}
