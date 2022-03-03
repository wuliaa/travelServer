package com.example.travelserver.service;

import com.example.travelserver.model.Collection;
import com.example.travelserver.vo.GuidelineVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollectionService {
    Collection insertCollection(Collection collection);

    Collection deleteItem(Collection collection);

    Collection hasCollection(Collection collection);

    List<GuidelineVo> selectByUserId(String userId);
}
