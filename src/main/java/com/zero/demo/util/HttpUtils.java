package com.zero.demo.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author admin
 * @date 2020/8/6-15:37
 */
public class HttpUtils {
    /**
     * 将URL的参数和body参数合并
     *
     * @param request
     * @author show
     * @date 14:24 2019/5/29
     */
    public static SortedMap<String, String> getAllParams(HttpServletRequest request) throws IOException {

        SortedMap<String, String> result = new TreeMap<>();
        //获取URL上的参数-- 保证有 timestamp 参数
        Map<String, String> urlParams = getUrlParams(request);
        if (urlParams != null) {
            for (Map.Entry entry : urlParams.entrySet()) {
                result.put((String) entry.getKey(), (String) entry.getValue());
            }
        }
        Map<String, String> allRequestParam = new HashMap<>(16);
        // get请求不需要拿body参数
        if (!HttpMethod.GET.name().equals(request.getMethod())) {
            allRequestParam = getAllRequestParam(request);
        }
        //将URL的参数和body参数进行合并
        if (allRequestParam != null) {
            for (Map.Entry entry : allRequestParam.entrySet()) {
//                String value = null;
//                if (entry.getValue() instanceof JSONObject) {
//                    value = JSONObject.toJSONString(entry.getValue());
//                }
                result.put(String.valueOf(entry.getKey()),  String.valueOf(entry.getValue()));
            }
        }
        result.put(ConstUtils.SECRET_KEY, ConstUtils.SECRET_VALUE);
        return result;
    }

    /**
     * 获取 Body 参数
     *
     * @param request
     * @author show
     * @date 15:04 2019/5/30
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String str = "";
        StringBuilder wholeStr = new StringBuilder();
        //一行一行的读取body体里面的内容；
        while ((str = reader.readLine()) != null) {
            wholeStr.append(str);
        }
        //转化成json对象
        return JSONObject.parseObject(wholeStr.toString(), Map.class);
    }


    /**
     * 将URL请求参数转换成Map
     *
     * @param request
     * @author show
     */
    public static Map<String, String> getUrlParams(HttpServletRequest request) {

        String param = "";
        try {
            if (StringUtils.isEmpty(request.getQueryString())) return null;
            param = URLDecoder.decode(request.getQueryString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, String> result = new HashMap<>(16);
        String[] params = param.split("&");
        for (String s : params) {
            int index = s.indexOf("=");
            result.put(s.substring(0, index), s.substring(index + 1));
        }
        return result;
    }
}
