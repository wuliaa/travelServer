package com.example.travelserver.mapper;

import com.example.travelserver.model.Dialog;

import java.util.List;

public interface DialogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dialog record);

    List<Dialog> selectByUserId(String fromUserId);

    List<Dialog> selectLastByUserId(String fromUserId,String toUserId);

    void updateDialogSigned(List<String> msgIdList);

    void updateDialogRead(List<String> msgIdList);

    List<Dialog> getOfflineMessage(String toUserId);

    int insertSelective(Dialog record);

    Dialog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dialog record);

    int updateByPrimaryKey(Dialog record);
}