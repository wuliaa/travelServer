package com.example.travelserver.service;

import com.example.travelserver.model.Guideline;
import com.example.travelserver.model.Resp;
import com.example.travelserver.vo.GuidelineVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface GuidelineService {

    Resp<StringBuilder> uploadGuidelinePhotos(MultipartFile[] files);

    Guideline insertGuideline(Guideline guideline);

    List<GuidelineVo> queryAllGuideline();

    List<GuidelineVo> queryLandmarkName(String landmarkName);

    List<GuidelineVo> queryGuidelineByUserId(String userId);

    List<GuidelineVo> selectByPage(Integer page,Integer rows);

    List<Guideline> queryAll();

    int deleteMyGuideline(Long id);
}
