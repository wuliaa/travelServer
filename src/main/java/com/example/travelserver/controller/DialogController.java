package com.example.travelserver.controller;

import com.example.travelserver.model.Dialog;
import com.example.travelserver.service.DialogService;
import com.example.travelserver.vo.DialogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping({"/dialog"})
public class DialogController {

    @Autowired
    DialogService dialogService;

    /**
     * 根据fromUserId和toUserId查询所有有关聊天记录
     * 方便得到最后一条
     * @param fromUserId
     * @param toUserId
     * @return
     */
    @RequestMapping(value="/queryLast")
    @ResponseBody
    public List<Dialog> getAll(String fromUserId, String toUserId){
        return dialogService.selectLastByUserId(fromUserId,toUserId);
    }

    /**
     * 根据userId得到消息记录列表，返回和所有人聊天的最后一条
     * @param fromUserId
     * @return
     */
    @RequestMapping(value="/queryTheLast")
    @ResponseBody
    public List<DialogVo> getAllTheLast(String fromUserId){
        return dialogService.queryLastByUserId(fromUserId);
    }

    /**
     * 将消息标记为已读
     */
    @RequestMapping(value = "/read")
    @ResponseBody
    public void changeSingedRead(String msgIds){
        List<String> msgIdList = Arrays.asList(msgIds.split(","));
        dialogService.updateDialogRead(msgIdList);
    }

    /**
     * 离线消息接收
     */
    @RequestMapping(value = "/offlineMessage")
    @ResponseBody
    public List<Dialog> getUnReadMsgList(String toUserId){
        return dialogService.getOfflineMessage(toUserId);
    }

    /**
     * 根据fromUserId和toUserId查询所有有关聊天记录
     * 查看是否有未读
     * @param fromUserId
     * @return
     */
    @RequestMapping(value="/queryUnread")
    @ResponseBody
    public boolean hasUnread(String fromUserId){
        List<DialogVo> list =  dialogService.queryLastByUserId(fromUserId);
        for(DialogVo dialog : list){
            if(!dialog.getFromUserId().equals(fromUserId) &&dialog.getSignFlag() != 2)
                return true;
        }
        return false;
    }
}
