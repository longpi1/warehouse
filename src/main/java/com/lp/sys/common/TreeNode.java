package com.lp.sys.common;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

	private Integer id;
	@JsonProperty("parentId")
	private Integer pid;
	private String title;
	private String icon;
	private String href;
	private Boolean spread;
	private List<TreeNode> children = new ArrayList<TreeNode>();
	
	private String checkArr="0";//0代表不选中  1代表选中 
	
	/**
	 * 首页左边导航树的构造器
	 */
	public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.icon = icon;
		this.href = href;
		this.spread = spread;
	}

	/**
	 * dtree的数据格式
	 * @param id
	 * @param pid
	 * @param title
	 * @param spread
	 */
	public TreeNode(Integer id, Integer pid, String title, Boolean spread) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.spread = spread;
	}

	/**
	 * dTree复选树的构造器
	 * @param id
	 * @param pid
	 * @param title
	 * @param spread
	 * @param checkArr
	 */
	public TreeNode(Integer id, Integer pid, String title, Boolean spread, String checkArr) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.spread = spread;
		this.checkArr = checkArr;
	}

	
}
