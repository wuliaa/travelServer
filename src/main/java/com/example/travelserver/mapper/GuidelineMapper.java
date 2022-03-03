package com.example.travelserver.mapper;

import com.example.travelserver.model.Guideline;

import java.util.List;

public interface GuidelineMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Guideline record);

    int insertSelective(Guideline record);

    Guideline selectByPrimaryKey(Long id);

    List<Guideline> selectAll();

    List<Guideline> queryLandmarkName(String landmarkName);

    List<Guideline> queryGuidelineByUserId(String userId);

    List<Guideline> selectByPage(Integer page,Integer rows);

    int updateByPrimaryKeySelective(Guideline record);

    int updateByPrimaryKey(Guideline record);
}