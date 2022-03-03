package com.example.travelserver.service.Impl;

import com.example.travelserver.mapper.RecognitionMapper;
import com.example.travelserver.model.Recognition;
import com.example.travelserver.model.Resp;
import com.example.travelserver.service.RecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
public class RecognitionServiceImpl implements RecognitionService {

    @Autowired
    RecognitionMapper recognitionMapper;

    @Override
    public Resp<String> uploadRecognitionPhoto(MultipartFile file) {
        if(file.isEmpty()){
            return Resp.fail("400","文件为空!");
        }
        String OriginalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        String filePath = "D:\\travelServer\\upload\\";
        File dest = new File(filePath+fileName);
        if(!dest.getParentFile().exists())
            dest.getParentFile().mkdirs();
        try {
            file.transferTo(dest);
        }catch (Exception e){
            e.printStackTrace();
            return Resp.fail("500",OriginalFilename+"上传失败！");
        }
        return Resp.success(fileName);
    }

    @Override
    public int insertRecognition(Recognition recognition) {
        recognition.setCreateTime(new Date());
        return recognitionMapper.insert(recognition);
    }

    @Override
    public List<Recognition> queryRecognitionByUserId(String userId) {
        return recognitionMapper.queryRecognitionByUserId(userId);
    }
}
