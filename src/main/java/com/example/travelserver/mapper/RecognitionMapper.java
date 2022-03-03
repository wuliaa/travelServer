package com.example.travelserver.mapper;

import com.example.travelserver.model.Recognition;

import java.util.List;

public interface RecognitionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Recognition record);

    int insertSelective(Recognition record);

    Recognition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Recognition record);

    int updateByPrimaryKey(Recognition record);

    List<Recognition> queryRecognitionByUserId(String userId);
}