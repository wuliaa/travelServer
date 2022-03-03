package com.example.travelserver.controller;


import com.example.travelserver.utils.AliLandmark;
import com.example.travelserver.utils.Cost;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/test"})
public class TestController {
    @RequestMapping(value="/test")
    @ResponseBody
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/alitest")
    @ResponseBody
    public String aliTest(String base64){
        AliLandmark ali = new AliLandmark();
        String res;
        try(Cost c = new Cost()){
            res = ali.landmark(base64);
        }
        System.out.println("------over-------");
        return res;
    }
}
