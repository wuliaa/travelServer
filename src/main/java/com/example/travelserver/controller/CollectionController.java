package com.example.travelserver.controller;

import com.example.travelserver.model.Collection;
import com.example.travelserver.model.Resp;
import com.example.travelserver.service.CollectionService;
import com.example.travelserver.vo.GuidelineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/collection"})
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    /**
     * 用户收藏旅游攻略
     * @param collection
     * @return
     */
    @RequestMapping("/addCollection")
    @ResponseBody
    public Resp<String> addCollection(Collection collection){
        collectionService.insertCollection(collection);
        return Resp.success("收藏成功");
    }

    /**
     * 用户取消收藏旅游攻略
     * @param collection
     * @return
     */
    @RequestMapping("/deleteCollection")
    @ResponseBody
    public Resp<String> deleteCollection(Collection collection){
        collectionService.deleteItem(collection);
        return Resp.success("取消收藏");
    }

    /**
     * 用户是否收藏旅游攻略
     * @param collection
     * @return
     */
    @RequestMapping("/hasCollection")
    @ResponseBody
    public Resp<Boolean> hasCollection(Collection collection){
        Collection c = collectionService.hasCollection(collection);
        if(c == null)return Resp.success(false);
        else return Resp.success(true);
    }

    /**
     * 用户查询收藏的旅游攻略
     * @param userId
     * @return
     */
    @RequestMapping("/queryCollection")
    @ResponseBody
    public List<GuidelineVo> queryCollection(String userId){
        return collectionService.selectByUserId(userId);
    }
}
