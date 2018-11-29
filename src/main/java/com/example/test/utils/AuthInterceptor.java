package com.example.test.utils;

import com.example.test.entity.po.User;
import com.example.test.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@EnableWebMvc
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String value = request.getHeader("Flag");
        if (value == null) {
            value = "false";
        }
        if (!StringUtils.isEmpty(value) && value.equals("true")) {
            return true;
        }
        if (value.equals("false")) {
            if (request.getRequestURI().endsWith(".json")) {
                return true;
            }
            //OPTIONS预请求 ,不做拦截
            if ("OPTIONS".equals(request.getMethod())) {
                log.error(request.getRequestURL() + "-" + request.getMethod());
                response.setStatus(HttpServletResponse.SC_OK);
                return true;
            }
            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token为空,不能访问!!");
                return false;
            }
            User user = userMapper.selectUserByToken(token);
            if (user == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token校验失败!!");
                return false;
            }
        }
        return super.preHandle(request, response, handler);

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}
