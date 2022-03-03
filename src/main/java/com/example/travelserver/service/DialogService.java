package com.example.travelserver.service;

import com.example.travelserver.model.Dialog;
import com.example.travelserver.netty.ChatMsg;
import com.example.travelserver.vo.DialogVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DialogService {

    int insertDialog(ChatMsg chatMsg);

    List<DialogVo> queryLastByUserId(String fromUserId);

    List<Dialog> selectLastByUserId(String fromUserId,String toUserId);

    void updateDialogSigned(List<String> msgIdList);

    void updateDialogRead(List<String> msgIdList);

    List<Dialog> getOfflineMessage(String toUserId);
}
