package com.example.travelserver.mapper;

import com.example.travelserver.model.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> findByGuidelineId(Long guidelineId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}