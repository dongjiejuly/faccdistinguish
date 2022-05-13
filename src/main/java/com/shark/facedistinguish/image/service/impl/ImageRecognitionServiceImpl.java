package com.shark.facedistinguish.image.service.impl;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Util;
import com.shark.facedistinguish.image.service.ImageRecognitionService;
import com.shark.facedistinguish.util.AipOcrBaidu;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@Service
public class ImageRecognitionServiceImpl implements ImageRecognitionService {

    @Override
    public String localImageOcr(String path) {

        // 需要判断文件 是否能获取到
        AipOcr client = AipOcrBaidu.getAipOcrClient();

        // 调用百度接口
        JSONObject res = client.basicGeneral(path, new HashMap<>());

        // toString(int indentFactor) 相对上一行的缩进
//        System.out.println(res.toString(2));
        return res.toString();
    }

    @Override
    public String localImageOcrByStream(String path) throws IOException {
        byte[] bytes = Util.readFileByBytes(path);

        AipOcr client = AipOcrBaidu.getAipOcrClient();
        JSONObject res = client.basicGeneral(bytes, new HashMap<>());
        System.out.println(res.toString(2));
        return res.toString();
    }

    @Override
    public String highlyLocalImageOcr(String path, Boolean detail) throws IOException {
//        byte[] bytes = Util.readFileByBytes(path);
        HashMap<String, String> paramsMap = new HashMap<>();
        if (Objects.nonNull(detail) && detail) {
            paramsMap.put("detect_direction", "true");
            paramsMap.put("probability", "true");
        }
        AipOcr client = AipOcrBaidu.getAipOcrClient();
        JSONObject res = client.basicAccurateGeneral(path, paramsMap);
        System.out.println(res.toString(2));
        return res.toString();
    }

    @Override
    public String idCardLocalImageOcr(String path, String idCardSide) throws IOException {

        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("detect_direction", "true");
        paramsMap.put("detect_risk", "false");

        AipOcr client = AipOcrBaidu.getAipOcrClient();
        JSONObject res = client.idcard(path, idCardSide, paramsMap);
        System.out.println(res.toString(2));
        return res.toString();
    }


}
