package com.example.travelserver.service.Impl;

import com.example.travelserver.mapper.UserMapper;
import com.example.travelserver.model.Resp;
import com.example.travelserver.model.User;
import com.example.travelserver.service.UserService;
import com.example.travelserver.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    //注入mapper
    @Autowired
    UserMapper userMapper;

    @Autowired
    Sid sid;

    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 查找用户是否存在
     * @param username
     * @return
     */
    @Override
    public User queryUserNameIsExit(String username) {
        return userMapper.queryUserNameIsExit(username);
    }

    /**
     * 上传用户头像
     * @param file
     * @return
     */
    @Override
    public Resp<String> uploadUserHead(MultipartFile file) {
        if(file.isEmpty()){
            return Resp.fail("400","文件为空!");
        }
        String OriginalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis()+"."+
                OriginalFilename.substring(OriginalFilename
                        .lastIndexOf(".")+1);
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
        return Resp.success(fileName);
    }

    /**
     * 注册 新增用户
     * @param user
     * @return
     */
    @Override
    public User insertUser(User user) {
        user.setUserId(sid.nextShort());
        user.setPasswd(MD5Utils.getPwd(user.getPasswd()));
        user.setCreateTime(new Date());
        int effectNum = userMapper.insert(user);
        if(effectNum<=0){
            user.setUserId("-1");
        }
        return user;
    }
}
