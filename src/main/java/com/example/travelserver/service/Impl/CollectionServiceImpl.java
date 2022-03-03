package com.example.travelserver.service.Impl;

import com.example.travelserver.mapper.CollectionMapper;
import com.example.travelserver.mapper.GuidelineMapper;
import com.example.travelserver.mapper.UserMapper;
import com.example.travelserver.model.Collection;
import com.example.travelserver.model.Guideline;
import com.example.travelserver.model.User;
import com.example.travelserver.service.CollectionService;
import com.example.travelserver.vo.GuidelineVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionMapper collectionMapper;

    @Autowired
    GuidelineMapper guidelineMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Collection insertCollection(Collection collection) {
        collectionMapper.insert(collection);
        return collection;
    }

    @Override
    public Collection deleteItem(Collection collection) {
        collectionMapper.deleteItem(collection);
        return collection;
    }

    @Override
    public Collection hasCollection(Collection collection) {
        return collectionMapper.selectByUserAndGuidelineId(collection);
    }

    @Override
    public List<GuidelineVo> selectByUserId(String userId) {
        List<Collection> list = collectionMapper.selectByUserId(userId);
        List<GuidelineVo> listVo=new ArrayList<>();
        for(Collection c:list){
            Guideline g = guidelineMapper.selectByPrimaryKey(c.getGuidelineId());
            GuidelineVo vo = new GuidelineVo();
            User user = userMapper.queryUserByUserId(g.getUserId());
            vo.setPortrait(user.getUserHead());
            vo.setNickname(user.getNickname());
            BeanUtils.copyProperties(g,vo);
            listVo.add(vo);
        }
        return listVo;
    }
}
