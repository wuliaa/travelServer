package com.example.travelserver.controller;

import com.example.travelserver.model.Recognition;
import com.example.travelserver.model.Resp;
import com.example.travelserver.service.RecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping({"/recognition"})
public class RecognitionController {

    @Autowired
    RecognitionService recognitionService;

    /**
     * 用户上传地标识别图片
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Resp<String> upload(@RequestParam("file") MultipartFile file){
        return recognitionService.uploadRecognitionPhoto(file);
    }

    /**
     * 用户地标识别
     */
    @RequestMapping("/addRecognition")
    @ResponseBody
    public Resp<String> addRecognition(Recognition recognition){
        int e = recognitionService.insertRecognition(recognition);
        if(e<=0)
            return Resp.fail("500","增加失败");
        else return Resp.success("增加成功");
    }

    @RequestMapping("/query")
    @ResponseBody
    public List<Recognition> queryRecognitionByUserId(String userId){
        return recognitionService.queryRecognitionByUserId(userId);
    }
}
