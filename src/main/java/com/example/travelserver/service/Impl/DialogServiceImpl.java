package com.example.travelserver.service.Impl;

import com.example.travelserver.mapper.DialogMapper;
import com.example.travelserver.mapper.UserMapper;
import com.example.travelserver.model.Dialog;
import com.example.travelserver.model.User;
import com.example.travelserver.netty.ChatMsg;
import com.example.travelserver.service.DialogService;
import com.example.travelserver.vo.DialogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DialogServiceImpl implements DialogService {

    @Autowired
    DialogMapper dialogMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public int insertDialog(ChatMsg chatMsg) {
        Dialog dialog = new Dialog();
        dialog.setFromUserId(chatMsg.getFromUserId());
        dialog.setToUserId(chatMsg.getToUserId());
        dialog.setContent(chatMsg.getContent());
        dialog.setCreateTime(new Date());
        dialog.setSignFlag(0);
        dialogMapper.insert(dialog);
        return dialog.getId().intValue();
    }

    @Override
    public List<DialogVo> queryLastByUserId(String fromUserId) {
        List<Dialog> list = dialogMapper.selectByUserId(fromUserId);
        List<DialogVo> listVo = new ArrayList<>();
        TreeSet<String> treeSet = new TreeSet<>();
        for(Dialog dialog:list){
            if(dialog.getFromUserId().equals(fromUserId)){
                treeSet.add(dialog.getToUserId());
            }else{
                treeSet.add(dialog.getFromUserId());
            }
        }
        for(String i:treeSet){
            List<Dialog> l = dialogMapper.selectLastByUserId(fromUserId,i);
            Dialog d = l.get(l.size() - 1);
            String userId = "";
            if(d.getFromUserId().equals(fromUserId)){
                userId = d.getToUserId();
            }else{
                userId = d.getFromUserId();
            }
            User user = userMapper.queryUserByUserId(userId);
            DialogVo vo = new DialogVo();
            vo.setPortrait(user.getUserHead());
            vo.setNickname(user.getNickname());
            BeanUtils.copyProperties(d,vo);
            listVo.add(vo);
        }
        return listVo;
    }

    @Override
    public List<Dialog> selectLastByUserId(String fromUserId, String toUserId) {
        return dialogMapper.selectLastByUserId(fromUserId,toUserId);
    }

    @Override
    public void updateDialogSigned(List<String> msgIdList) {
        dialogMapper.updateDialogSigned(msgIdList);
    }

    @Override
    public void updateDialogRead(List<String> msgIdList) {
        dialogMapper.updateDialogRead(msgIdList);
    }

    @Override
    public List<Dialog> getOfflineMessage(String toUserId) {
        return dialogMapper.getOfflineMessage(toUserId);
    }
}
