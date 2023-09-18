package com.ruoyi.client.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.core.domain.R;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 16956
 */
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("OPTIONS".equalsIgnoreCase(method)) {
            return true;
        }

        //String token = request.getParameter("token");
        String token = request.getHeader("token");
        if (token == null) {
            //提示请先登录
            R resultVO = R.fail("请先登录", null);
            doResponse(response, resultVO);
            return false;
        } else {
            try {
                //验证token
                JwtParser parser = Jwts.parser();
                //解析token的SigningKey必须和生成token时设置密码一致
                parser.setSigningKey("loginKey");

                //如果token正确（密码正确，有效期内）则正常执行，否则抛出异常
                Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                //获取token中的用户数据
                Claims body = claimsJws.getBody();
                //获取生成token时设置的subject
                String subject = body.getSubject();
                //获取生成token时设置的map中的值
                String v1 = body.get("key1", String.class);
                //校验成功
                return true;
            } catch (ExpiredJwtException e) {
                doResponse(response, R.fail("登录过期，请重新登录"));
                return false;
            } catch (UnsupportedJwtException e) {
                doResponse(response, R.fail("Token不合法，请自重"));
                return false;
            } catch (Exception e) {
                doResponse(response, R.fail("请先登录"));
                return false;
            }
        }
    }

    private void doResponse(HttpServletResponse response, R r) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String info = new ObjectMapper().writeValueAsString(r);
        out.print(info);
        out.flush();
        out.close();
    }
}
