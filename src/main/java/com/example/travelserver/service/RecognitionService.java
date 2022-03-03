package com.example.travelserver.service;

import com.example.travelserver.model.Recognition;
import com.example.travelserver.model.Resp;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface RecognitionService {

    Resp<String> uploadRecognitionPhoto(MultipartFile file);

    int insertRecognition(Recognition recognition);

    List<Recognition> queryRecognitionByUserId(String userId);
}
