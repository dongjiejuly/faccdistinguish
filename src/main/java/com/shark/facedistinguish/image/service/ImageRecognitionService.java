package com.shark.facedistinguish.image.service;

import java.io.IOException;

public interface ImageRecognitionService {

    String localImageOcr(String path);

    String localImageOcrByStream(String path) throws IOException;

    String highlyLocalImageOcr(String path, Boolean detail) throws IOException;

    String idCardLocalImageOcr(String path, String idCardSide) throws IOException;
}
