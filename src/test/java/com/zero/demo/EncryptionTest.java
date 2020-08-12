package com.zero.demo;


import com.zero.demo.util.HttpUtils;
import com.zero.demo.util.SignUtil;

import java.util.UUID;

/**
 * @author admin
 * @date 2020/8/6-14:56
 */
public class EncryptionTest {
    public static void main(String[] args) {
        String decrptContent = "{\"secretKey\":\"zero_secret_open\",\"timestamp\":\"15800000000000000\"}";
        String str = SignUtil.getParamsSignForStr(decrptContent);
        System.out.println(str);
    }
}
