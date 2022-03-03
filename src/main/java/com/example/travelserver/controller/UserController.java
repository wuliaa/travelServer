package com.example.travelserver.controller;

import com.example.travelserver.model.Resp;
import com.example.travelserver.model.User;
import com.example.travelserver.service.UserService;
import com.example.travelserver.utils.MD5Utils;
import com.example.travelserver.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/user"})
public class UserController {
    @Autowired
    UserService userService;

    //用户登录
//    @RequestMapping("/login")
//    @ResponseBody
//    public Resp<String> login(User user){
//        User userResult  = userService.queryUserNameIsExit(user.getNickname());
//        if(userResult==null){
//            return Resp.fail("404","此用户不存在");
//        }
//        if(!userResult.getPasswd().equals(MD5Utils.getPwd(user.getPasswd()))){
//            return Resp.fail("404","密码不正确");
//        }
//        return Resp.success("登陆成功");
//    }
    @RequestMapping("/login")
    @ResponseBody
    public Resp<UserVo> login(User user){
        User userResult  = userService.queryUserNameIsExit(user.getNickname());
        if(userResult==null){
            return Resp.fail("404","此用户不存在");
        }
        if(!userResult.getPasswd().equals(MD5Utils.getPwd(user.getPasswd()))){
            return Resp.fail("404","密码不正确");
        }
        UserVo ret = new UserVo();
        BeanUtils.copyProperties(userResult,ret);
        return Resp.success(ret);
    }

    @RequestMapping(value="/getUser")
    @ResponseBody
    public String getUserById(Long id){
        User user = userService.getUserById(id);
        return user.getNickname();
    }

    /**
     * 用户上传头像
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Resp<String> upload(@RequestParam("file") MultipartFile file){
        return userService.uploadUserHead(file);
    }

    /**
     * 用户注册
     */
    @RequestMapping("/register")
    @ResponseBody
    public Resp<String> register(User user){
        User userQuery = userService.queryUserNameIsExit(user.getNickname());
        if(userQuery!=null){
            return Resp.fail("404","该用户名已被注册");
        }
        User userResult = userService.insertUser(user);
        if(userResult.getUserId().equals("-1")){
            return Resp.fail("500","用户注册失败");
        }
        return Resp.success(userResult.getUserId());
    }
}
