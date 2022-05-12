package com.shark.facedistinguish.image.service;

import java.io.IOException;

public interface ImageRecognitionService {

    String localImageOcr(String path);

    String localImageOcrByStream(String path) throws IOException;
}
