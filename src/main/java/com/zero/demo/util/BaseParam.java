package com.zero.demo.util;

import lombok.Data;

/**
 * @author admin
 * @date 2020/8/10-15:35
 */
@Data
public class BaseParam {
    private String timestamp;
    private String sign;
    private String notify_url;
}
