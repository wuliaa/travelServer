package com.example.travelserver.service;

import com.example.travelserver.model.Comment;
import com.example.travelserver.vo.CommentVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    List<CommentVo> findByGuidelineId(Long guidelineId);

    CommentVo insertComment(Comment comment);
}
