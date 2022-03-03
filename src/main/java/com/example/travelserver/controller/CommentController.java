package com.example.travelserver.controller;

import com.example.travelserver.model.Comment;
import com.example.travelserver.service.CommentService;
import com.example.travelserver.service.UserService;
import com.example.travelserver.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/comment"})
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping("/queryComment")
    @ResponseBody
    public List<CommentVo> findByGuidelineId(Long guidelineId){
        return commentService.findByGuidelineId(guidelineId);
    }

    @RequestMapping("/addComment")
    @ResponseBody
    public CommentVo addComment(Comment comment){
        return commentService.insertComment(comment);
    }
}
