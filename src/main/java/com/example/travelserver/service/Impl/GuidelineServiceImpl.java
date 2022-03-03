package com.example.travelserver.service.Impl;

import com.example.travelserver.mapper.CollectionMapper;
import com.example.travelserver.mapper.GuidelineMapper;
import com.example.travelserver.mapper.UserMapper;
import com.example.travelserver.model.Guideline;
import com.example.travelserver.model.Resp;
import com.example.travelserver.model.User;
import com.example.travelserver.service.GuidelineService;
import com.example.travelserver.vo.GuidelineVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GuidelineServiceImpl implements GuidelineService {

    //注入mapper
    @Autowired
    GuidelineMapper guidelineMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CollectionMapper collectionMapper;

    @Override
    public Resp<StringBuilder> uploadGuidelinePhotos(MultipartFile[] files) {
        if(files.length == 0){
            return Resp.fail("400","请选择要上传的文件");
        }
        StringBuilder names= new StringBuilder();
        for (MultipartFile file : files) {
            if(file.isEmpty()){
                return Resp.fail("400","文件上传失败");
            }
            String OriginalFilename = file.getOriginalFilename();
            String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
            String filePath = "D:\\travelServer\\upload\\";
            File dest = new File(filePath+fileName);
            if(!dest.getParentFile().exists())
                dest.getParentFile().mkdirs();
            try {
                file.transferTo(dest);
            }catch (Exception e){
                e.printStackTrace();
                return Resp.fail("500",OriginalFilename+"上传失败！");
            }
            names.append(fileName).append(",");
        }
        names.deleteCharAt(names.length()-1);
        System.out.println(names);
        return Resp.success(names);
    }

    @Override
    public Guideline insertGuideline(Guideline guideline) {
        guideline.setCreateTime(new Date());
        guidelineMapper.insert(guideline);
        return guideline;
    }

    @Override
    public List<GuidelineVo> queryAllGuideline() {
        List<Guideline> list = guidelineMapper.selectAll();
        List<GuidelineVo> listVo=new ArrayList<>();
        for(Guideline i:list){
            GuidelineVo vo = new GuidelineVo();
            //System.out.println(i.getUserId());
            User user = userMapper.queryUserByUserId(i.getUserId());
            vo.setPortrait(user.getUserHead());
            vo.setNickname(user.getNickname());
            BeanUtils.copyProperties(i,vo);
            listVo.add(vo);
        }
        return listVo;
    }

    /**
     * 根据地点查询所有旅游攻略
     * @param landmarkName
     * @return
     */
    @Override
    public List<GuidelineVo> queryLandmarkName(String landmarkName) {
        List<Guideline> list = guidelineMapper.queryLandmarkName(landmarkName);
        List<GuidelineVo> listVo=new ArrayList<>();
        for(Guideline i:list){
            GuidelineVo vo = new GuidelineVo();
            User user = userMapper.queryUserByUserId(i.getUserId());
            vo.setPortrait(user.getUserHead());
            vo.setNickname(user.getNickname());
            BeanUtils.copyProperties(i,vo);
            listVo.add(vo);
        }
        return listVo;
    }

    @Override
    public List<GuidelineVo> queryGuidelineByUserId(String userId) {
        List<Guideline> list = guidelineMapper.queryGuidelineByUserId(userId);
        List<GuidelineVo> listVo=new ArrayList<>();
        for(Guideline i:list){
            GuidelineVo vo = new GuidelineVo();
            User user = userMapper.queryUserByUserId(i.getUserId());
            vo.setPortrait(user.getUserHead());
            vo.setNickname(user.getNickname());
            BeanUtils.copyProperties(i,vo);
            listVo.add(vo);
        }
        return listVo;
    }

    @Override
    public List<GuidelineVo> selectByPage(Integer page, Integer rows) {
        List<Guideline> list = guidelineMapper.selectByPage(page,rows);
        List<GuidelineVo> listVo=new ArrayList<>();
        for(Guideline i:list){
            GuidelineVo vo = new GuidelineVo();
            //System.out.println(i.getUserId());
            User user = userMapper.queryUserByUserId(i.getUserId());
            vo.setPortrait(user.getUserHead());
            vo.setNickname(user.getNickname());
            BeanUtils.copyProperties(i,vo);
            listVo.add(vo);
        }
        return listVo;
    }

    @Override
    public List<Guideline> queryAll() {
        return guidelineMapper.selectAll();
    }

    @Override
    public int deleteMyGuideline(Long id) {
        Long guidelineId = id;
        collectionMapper.deleteByGuidelineId(guidelineId);
        return guidelineMapper.deleteByPrimaryKey(id);
    }
}
