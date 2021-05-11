package com.lp.sys.common;

/**
 * 常量接口
 * @author lp
 *
 */
public interface Constast {
	
	/**
	 * 状态码
	 * 
	 */
	public static final Integer OK=200;
	public static final Integer ERROR=-1;
	
	/**
	 * 用户默认密码
	 */
	public static final String USER_DEFAULT_PWD="123456";
	
	/**
	 * 菜单权限类型
	 */
	public static final String TYPE_MNEU = "menu";
	public static final String TYPE_PERMISSION = "permission";
	/**
	 * 可以状态
	 */
	public static final Object AVAILABLE_TRUE = 1;
	public static final Object AVAILABLE_FALSE = 0;
	
	/**
	 * 用户类型
	 */
	public static final Integer USER_TYPE_SUPER = 0;
	public static final Integer USER_TYPE_NORMAL = 1;
	
	/**
	 * 展开类型
	 */
	public static final Integer OPEN_TRUE = 1;
	public static final Integer OPEN_FALSE = 0;
	
	/**
	 * 商品默认图片
	 */
	public static final String IMAGES_DEFAULTGOODSIMG_PNG = "images/defaultgoodsimg.png";

}
