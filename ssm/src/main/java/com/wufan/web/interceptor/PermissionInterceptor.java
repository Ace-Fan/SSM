package com.wufan.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wufan.commons.constant.ConstantUtils;
import com.wufan.domain.User;

public class PermissionInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //以login结尾的请求
        if (modelAndView != null && modelAndView.getViewName() != null && modelAndView.getViewName().endsWith("login")){
            User user = (User) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
            if(user !=null){
                httpServletResponse.sendRedirect("/ssm/index");
            }
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

