package com.zero.demo.util;

/**
 * @author admin
 * @date 2020/8/6-15:55
 */
public class ResultUtils {

    public static ResultVO<String> signError(){
        return   new ResultVO<String>(ResultCode.SIGN, null);
    }
}
