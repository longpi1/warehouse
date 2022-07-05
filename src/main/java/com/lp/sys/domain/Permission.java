package com.lp.sys.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author lp
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer pid;

    /**
     * 权限类型[menu/permission]
     */
    private String type;

    private String title;

    /**
     * 权限编码[只有type= permission才有  user:view]
     */
    private String percode;

    private String icon;

    private String href;

    private String target;

    private Integer open;

    private Integer ordernum;

    /**
     * 状态【0不可用1可用】
     */
    private Integer available;


}
