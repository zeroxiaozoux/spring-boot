package com.zero.demo.config;

import com.alibaba.fastjson.JSONObject;
import com.zero.demo.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.SortedMap;

/**
 * @author admin
 * @date 2020/8/6-15:28
 */

@Slf4j
@Component
public class SignAuthFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {

       if (log.isInfoEnabled()) log.info("初始化 SignAuthFilter");
    }


    @Override
    public void doFilter(ServletRequest res, ServletResponse req, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) req;
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest request = (HttpServletRequest) res;
        HttpServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        //获取图标不需要验证签名
        if (ConstUtils.OPEN_API.equals(requestWrapper.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            boolean isSigned = true;
            // 时间戳验证
            String timestamp = null;
            if (!HttpMethod.GET.name().equals(request.getMethod())) {
               // timestamp = String.valueOf(request.getParameterMap().get(ConstUtils.TIMESTAMP));
                Map<String, String> map = HttpUtils.getAllRequestParam(requestWrapper);
                timestamp = String.valueOf(map.get(ConstUtils.TIMESTAMP));
            } else {
                timestamp = HttpUtils.getUrlParams(request).get(ConstUtils.TIMESTAMP);
            }
            if (StringUtils.isEmpty(timestamp)) {
                if (log.isErrorEnabled())  log.error("时间戳参数timestamp为空");
                ErrorResponse(response);
                return;
            }else {
                long now = System.currentTimeMillis();
                long time = 0L;
                try {
                     time = Long.parseLong(timestamp);
                } catch (Exception e) {
                    e.printStackTrace();
                    ErrorResponse(response);
                    return;
                }
                if (now - time > ConstUtils.MAX_REQUEST) {
                    if (log.isErrorEnabled())  log.error("时间戳已过期：服务端时间-[{}]客服端时间-[{}]差值-[{}]", now, time, (now - time));
                    ErrorResponse(response);
                    return ;
                }
            }
            //获取全部参数(包括URL和body上的)
            SortedMap<String, String> allParams = HttpUtils.getAllParams(requestWrapper);
            //对参数进行签名验证
             isSigned = SignUtil.verifySign(allParams);
            if (isSigned) {
                if (log.isInfoEnabled()) log.info("签名通过");
                chain.doFilter(requestWrapper, response);
            } else {
                if (log.isInfoEnabled()) log.info("签名校验失败");
                ErrorResponse(response);
            }
        }
    }

    private void ErrorResponse(HttpServletResponse response) throws IOException {
        //校验失败返回前端
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
//                JSONObject resParam = new JSONObject();
//                resParam.put("msg", "参数校验出错");
//                resParam.put("success", "false");
        ResultVO<String> ret =  ResultUtils.signError();
        out.append(JSONObject.toJSONString(ret));
    }

}
