package com.zero.demo.util;

/**
 * @author admin
 * @date 2020/8/6-17:44
 */
public class ConstUtils {

    // 定义不需要签名的路径+可以从文件或者数据库加载
    public   static final String OPEN_API = "/open";
    /**
     * 时间戳请求最小限制(60s)
     * 设置的越小，安全系数越高，但是要注意一定的容错性
     */
    public static final long MAX_REQUEST = 60 * 1000L;
    /**
     * 平台颁发的秘钥 不需要网络传输的
     */
    public static final String SECRET_VALUE= "zero_secret_open";
    public static final String SECRET_KEY= "secretKey";
    /**
     * Http header 时间戳
     */
    public static final String TIMESTAMP= "timestamp";
}
