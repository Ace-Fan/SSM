package com.wufan.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wufan.commons.constant.ConstantUtils;
import com.wufan.domain.User;


public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        User user = (User) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);

        //Î´µÇÂ¼
        if(user == null){
            httpServletResponse.sendRedirect("/ssm/login");
        }
        //·ÅÐÐ
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
            if(modelAndView != null && modelAndView.getViewName() != null){
                System.out.println(modelAndView.getViewName());
            }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
