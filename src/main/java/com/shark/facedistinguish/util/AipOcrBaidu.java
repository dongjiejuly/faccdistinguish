package com.shark.facedistinguish.util;

import com.baidu.aip.ocr.AipOcr;
import com.shark.facedistinguish.config.ApiBaiduConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class AipOcrBaidu {

    @Resource
    private ApiBaiduConfig apiBaiduConfig;

    static AipOcr aipOcr;

    @PostConstruct
    public void init(){

        AipOcr client = new AipOcr(apiBaiduConfig.getAppId(), apiBaiduConfig.getAppKey(),
                apiBaiduConfig.getSecretKey());

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        aipOcr = client;
    }

    public static AipOcr getAipOcrClient() {
        return aipOcr;
    }
}
