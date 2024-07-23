package com.example.softwareproject.interceptor;

import com.example.softwareproject.pojo.Result;
import com.example.softwareproject.util.JsonUtil;
import com.example.softwareproject.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取url
        String url = request.getRequestURL().toString();
        log.info("get url:" + url);

        //获取令牌
        String jwt = request.getHeader("Authorization");
        //若令牌为空则不放行
        if (!StringUtils.hasLength(jwt)) {
            if (url.contains("/weibo/info/homePage")) {
                log.info("/weibo/info/homePage 允许无令牌访问");
                request.setAttribute("isLogin",false);
                return true;
            }
            return JwtUtil.setResponse("令牌为空，请先登录",response,false);
        }
        try {
            if(JwtUtil.isTokenExpired(jwt)){
               return JwtUtil.setResponse("令牌已过期，请再次登录",response,false);
            }else {
                log.info("令牌在有效期内");
            }
            Claims claims = JwtUtil.parseJWT(jwt);
            Long userId=claims.get("userId",Long.class);
            request.setAttribute("userId",userId);
            request.setAttribute("isLogin",true);
        } catch (Exception e) {
            e.printStackTrace();
            return JwtUtil.setResponse("令牌解析失败",response,false);
        }
        log.info("令牌合法，放行该次请求");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
