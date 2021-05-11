package com.lp.sys.controller;

import com.lp.sys.common.ImageVerificationCode;
import com.lp.sys.domain.Loginfo;
import com.lp.sys.service.LoginfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lp.sys.common.ActiverUser;
import com.lp.sys.common.ResultObj;
import com.lp.sys.common.WebUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * @author lp
 */
@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private LoginfoService loginfoService;
	@RequestMapping("getVerifiCode")
	@SuppressWarnings("rawtypes")
	public void getVerifiCode(HttpServletResponse response, HttpSession session) throws IOException {
       Map map = ImageVerificationCode.generateCodeAndPic();
  ServletOutputStream out = response.getOutputStream();
  BufferedImage img = (BufferedImage) map.get("codePic");
  String securityCode = (String) map.get("code");
  session.setAttribute("securityCode", securityCode);
		ImageIO.write(img, "jpg", response.getOutputStream());

	}

	@RequestMapping("login")
	public ResultObj login(String loginname,String pwd,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token=new UsernamePasswordToken(loginname, pwd);
		request.setCharacterEncoding("utf-8");
		String session_vcode=(String) request.getSession().getAttribute("securityCode");
		String code = request.getParameter("code");

		if(!code.equals(session_vcode)){
			return ResultObj.LOGIN_ERROR_CODE;
		}
		try {
			subject.login(token);
			ActiverUser activerUser=(ActiverUser) subject.getPrincipal();
			WebUtils.getSession().setAttribute("user", activerUser.getUser());
			//记录登陆日志
			Loginfo entity=new Loginfo();
			entity.setLoginname(activerUser.getUser().getName()+"-"+activerUser.getUser().getLoginname());
			entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
			entity.setLogintime(new Date());
			loginfoService.save(entity);
			return ResultObj.LOGIN_SUCCESS;
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return ResultObj.LOGIN_ERROR_PASS;
		}
	}
}

