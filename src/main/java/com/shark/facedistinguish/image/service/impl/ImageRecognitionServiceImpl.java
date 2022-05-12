package com.shark.facedistinguish.image.service.impl;

import com.baidu.aip.ocr.AipOcr;
import com.shark.facedistinguish.image.service.ImageRecognitionService;
import com.shark.facedistinguish.util.AipOcrBaidu;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ImageRecognitionServiceImpl implements ImageRecognitionService {

    @Override
    public String basicCharacterGeneral(String path) {

        // 需要判断文件 是否能获取到
        AipOcr aipOcrClient = AipOcrBaidu.getAipOcrClient();

        // 调用百度接口
        JSONObject res = aipOcrClient.basicGeneral(path, new HashMap<>());

        // toString(int indentFactor) 相对上一行的缩进
//        System.out.println(res.toString(2));
        return res.toString();
    }
}
