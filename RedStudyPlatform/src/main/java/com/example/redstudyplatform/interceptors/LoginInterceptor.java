package com.example.redstudyplatform.interceptors;

import com.example.redstudyplatform.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("Authorization");
        //验证token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            String role = (String) claims.get("role"); // 获取role信息
            String requestURI = request.getRequestURI();
            // 进行基于角色的权限控制
            if (hasPermission(role, requestURI)) {
                return true; // 放行
            } else {
                response.setStatus(403); // 禁止访问
                return false;
            }
        } catch (Exception e) {
            response.setStatus(401);//不放行
            return false;
        }
    }

    private boolean hasPermission(String role, String requestURI) {
        if ("Study".equals(role)) {
            return requestURI.startsWith("/study/");//只能访问“study”开头的url
        }else if ("System_Administrator".equals(role)){
            return requestURI.startsWith("/system/");//只能访问“system”开头的url
        }else if ("Base_Administrator".equals(role)){
            return requestURI.startsWith("/base/");//只能访问“base”开头的url
        }
        return false;//无权限
    }
}
