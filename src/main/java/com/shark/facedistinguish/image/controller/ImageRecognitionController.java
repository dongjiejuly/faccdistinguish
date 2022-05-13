package com.shark.facedistinguish.image.controller;

import com.shark.facedistinguish.image.service.ImageRecognitionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/image/recognition")
public class ImageRecognitionController {

    @Autowired
    private ImageRecognitionService imageRecognitionService;

    /**
     * 通用文字识别
     * 服务器本地路径图片识别
     * 代码参考地址： https://ai.baidu.com/ai-doc/OCR/Nkibizxlf
     *
     * @param path 参数为本地图片路径
     * @return 识别结果
     */
    @GetMapping(value = "/local")
    public String localImageOcr(@RequestParam(value = "path") String path) {
//        String path = "/Users/liuxu29/IdeaProjects/image/test.png";

        // 校验请求参数不能为空
        if (StringUtils.isBlank(path)) {
            return "请求参数[path]不能为空";
        }

        return imageRecognitionService.localImageOcr(path);
    }

    /**
     * 通用文字识别
     * 远程url图片识别
     * TODO 需要阿里OSS图片地址用来测试正确性
     *
     * @param url url图片识别
     * @return 识别结果
     */
    @GetMapping(value = "/remote")
    public String urlImageOcr(@RequestParam(value = "url") String url) {

        // 校验请求参数不能为空
        if (StringUtils.isBlank(url)) {
            return "请求参数[path]不能为空";
        }

        return imageRecognitionService.localImageOcr(url);
    }

    /**
     * 通用文字识别
     *
     * @param path 本地图片位置
     * @return
     */
    @GetMapping(value = "/local/by/stream")
    public String localImageOcrByStream(@RequestParam(value = "path") String path) {

        // 校验请求参数不能为空
        if (StringUtils.isBlank(path)) {
            return "请求参数[path]不能为空";
        }

        try {
            return imageRecognitionService.localImageOcrByStream(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "无法获取到指定路径的文件，请确保文件路劲正确";
        }
    }

    /**
     * 通用文字识别（高精度版）
     *
     * @param path 本地图片位置
     * @return
     */
    @GetMapping(value = "highly/local/by/stream")
    public String highlyLocalImageOcr(@RequestParam(value = "path") String path,
                                      @RequestParam(value = "detail", required = false) Boolean detail) {

        // 校验请求参数不能为空
        if (StringUtils.isBlank(path)) {
            return "请求参数[path]不能为空";
        }

        try {
            return imageRecognitionService.highlyLocalImageOcr(path, detail);
        } catch (IOException e) {
            e.printStackTrace();
            return "无法获取到指定路径的文件，请确保文件路劲正确";
        }
    }

    /**
     * 身份证识别
     *
     * @param path 本地图片位置
     * @return
     */
    @GetMapping(value = "idCard/local")
    public String idCardLocalImageOcr(@RequestParam(value = "path") String path,
                                      @RequestParam(value = "idCardSide") String idCardSide) {

        // 校验请求参数不能为空
        if (StringUtils.isBlank(path)) {
            return "请求参数[path]不能为空";
        }

        try {
            return imageRecognitionService.idCardLocalImageOcr(path, idCardSide);
        } catch (IOException e) {
            e.printStackTrace();
            return "无法获取到指定路径的文件，请确保文件路劲正确";
        }
    }
}
