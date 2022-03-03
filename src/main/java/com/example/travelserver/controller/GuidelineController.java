package com.example.travelserver.controller;

import com.example.travelserver.model.Guideline;
import com.example.travelserver.model.Resp;
import com.example.travelserver.service.GuidelineService;
import com.example.travelserver.vo.GuidelineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping({"/guideline"})
public class GuidelineController {

    @Autowired
    GuidelineService guidelineService;

    /**
     * 用户上传旅游攻略图片
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Resp<StringBuilder> upload(@RequestParam("files") MultipartFile[] files){
        return guidelineService.uploadGuidelinePhotos(files);
    }

    /**
     * 用户发表旅游攻略
     */
    @RequestMapping("/postGuideline")
    @ResponseBody
    public Resp<String> postGuideline(Guideline guideline){
        guidelineService.insertGuideline(guideline);
        return Resp.success("发布成功");
    }

    @RequestMapping(value="/getAllGuideline")
    @ResponseBody
    public List<GuidelineVo> getAllGuideline(){
        return guidelineService.queryAllGuideline();
    }

    @RequestMapping(value="/getAll")
    @ResponseBody
    public List<Guideline> getAll(){
        return guidelineService.queryAll();
    }

    /**
     * 根据地点得到所有旅游攻略
     * @param landmarkName
     * @return
     */
    @RequestMapping(value="/getAllByLandmarkName")
    @ResponseBody
    public List<GuidelineVo> getAllByLandmarkName(String landmarkName){
        return guidelineService.queryLandmarkName(landmarkName);
    }

    /**
     * 获得该用户发表的所有旅游攻略
     * @param userId
     * @return
     */
    @RequestMapping(value="/getAllByUserId")
    @ResponseBody
    public List<GuidelineVo> queryGuidelineByUserId(String userId){
        return guidelineService.queryGuidelineByUserId(userId);
    }

    @RequestMapping(value = "/getGuidelineByPage")
    @ResponseBody
    public List<GuidelineVo> selectByPage(Integer page){
        return guidelineService.selectByPage(page,4);
    }

    /**
     * 用户删除自己的旅游攻略
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public int deleteMyGuideline(Long id){
        return guidelineService.deleteMyGuideline(id);
    }
}
