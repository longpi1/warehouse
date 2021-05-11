package com.lp.sys.common;

import java.util.List;

import com.lp.sys.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author lp
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser {

	private User user;
	
	private List<String> roles;
	
	private List<String> permissions;
}
