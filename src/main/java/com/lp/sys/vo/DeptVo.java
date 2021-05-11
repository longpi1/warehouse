package com.lp.sys.vo;

import com.lp.sys.domain.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DeptVo extends Dept{
	private static final long serialVersionUID = 1L;

	
	private Integer page=1;
	private Integer limit=10;
	
}
