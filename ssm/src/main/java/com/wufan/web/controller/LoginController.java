package com.wufan.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wufan.commons.constant.ConstantUtils;
import com.wufan.commons.utils.CookieUtils;
import com.wufan.domain.User;
import com.wufan.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * 跳转登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = {"/", "login"}, method = RequestMethod.GET)
	public String login() {
		return "/login";
	}

	/**
	 * 登录逻辑
	 * 
	 * @param username
	 * @param password
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam(required = true) String username, @RequestParam(required = true) String password,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		boolean isRemember = httpServletRequest.getParameter("isRemember") == null ? false : true;
		User user = userService.login(username, password);
		// 失败
		if (user == null) {
			httpServletRequest.getSession().setAttribute("result", "-1");
			return login();
		}
		// 成功
		else {
			// 用户信息存储一周
			if (isRemember) {
				CookieUtils.setCookie(httpServletRequest, httpServletResponse, "userInfo",
						String.format("%s:%s", username, password), 7 * 24 * 60 * 60);
			}
			// 将登录信息放入会话
			httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, user);
			return "index";
		}
	}

	/**
	 * 登录注销
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "loginout", method = RequestMethod.GET)
	public String loginout(HttpServletRequest httpServletRequest) {
		httpServletRequest.getSession().invalidate();
		return login();
	}

}
