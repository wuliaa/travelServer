package com.example.travelserver.mapper;

import com.example.travelserver.model.Collection;

import java.util.List;

public interface CollectionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Long id);

    int deleteItem(Collection collection);

    Collection selectByUserAndGuidelineId(Collection collection);

    List<Collection> selectByUserId(String userId);

    int deleteByGuidelineId(Long guidelineId);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);
}