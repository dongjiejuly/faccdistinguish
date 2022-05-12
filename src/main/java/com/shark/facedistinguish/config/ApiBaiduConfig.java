package com.shark.facedistinguish.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ApiBaiduConfig {

    @Value(value = "${api.baidu.appid}")
    private String appId;

    @Value(value = "${api.baidu.appkey}")
    private String appKey;

    @Value(value = "${api.baidu.secretkey}")
    private String secretKey;
}
