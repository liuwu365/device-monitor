package com.device.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 是否登录
 * @Date: 2017-08-14 17:39
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {
    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");

        // 拦截配置
        //addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            // 获得用户请求的URI
            //String path = request.getRequestURI();
            //UserInfo userInfo = SessionUtil.getUser(request);//当前登录用户
            //System.out.println(String.format("得到用户的请求:%s ,当前用户:%s", path, CheckUtil.isEmpty(userInfo) ? null : userInfo.getId()));
            //if (!CheckUtil.isEmpty(userInfo)) {
            //    return true;
            //} else {
            //    response.sendRedirect("/interface/user/login");
            //    return false;
            //}

            return true;
        }
    }

}
