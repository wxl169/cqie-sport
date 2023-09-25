package com.ruoyi.client.interceptor;

import cn.hutool.json.JSONUtil;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * 登录拦截器
 * @author 16956
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("token");
        System.out.println("过滤器:"+token);
        if (token == null){
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(JSONUtil.toJsonStr(R.fail("请先登录账号")));
            return false;
        }
        Object o = redisTemplate.opsForValue().get(token);
        if (o != null) {
            //token自动延期1小时
            redisTemplate.expire(token,60*60, TimeUnit.SECONDS);
            redisTemplate.expire(o,60*60,TimeUnit.SECONDS);
            return true;
        }
        else {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(JSONUtil.toJsonStr(R.fail("请先登录账号")));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //log.info("postHandle " + request.getServletPath());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //log.info("afterCompletion ");
    }


}
