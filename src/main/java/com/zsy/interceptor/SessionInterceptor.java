package com.zsy.interceptor;

import com.zsy.pojo.User;
import com.zsy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname SessionInterceptor
 * @Description TODO
 * @Date 2020/2/26 20:01
 * @Created by hero
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    //在Controller处理之前进行调用。
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在每次刷新或者访问后判断Cookie是否在数据库中存在
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {

                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("token")) {
                            User user = userService.selByToken(cookie.getValue());
                            if (user != null) {
                                request.getSession().setAttribute("user", user);
                            }
                            break;
                        }
                    }

                }
        return true;
    }

    //在请求完成后执行。
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //该方法将在整个请求完成之后，也就是页面渲染了视图执行。
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
