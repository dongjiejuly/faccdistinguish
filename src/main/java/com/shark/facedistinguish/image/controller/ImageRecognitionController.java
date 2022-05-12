package com.shark.facedistinguish.image.controller;

import com.shark.facedistinguish.image.service.ImageRecognitionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/image/recognition")
public class ImageRecognitionController {

    @Autowired
    private ImageRecognitionService imageRecognitionService;

    @GetMapping(value = "/general")
    public String basicCharacterGeneral(@RequestParam(value = "path") String path) {
//        String path = "/Users/liuxu29/IdeaProjects/image/test.png";

        // 校验请求参数不能为空
        if (StringUtils.isBlank(path)) {
            return "请求参数[path]不能为空";
        }

        return imageRecognitionService.basicCharacterGeneral(path);
    }
}
