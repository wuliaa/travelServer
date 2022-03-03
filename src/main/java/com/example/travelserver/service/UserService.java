package com.example.travelserver.service;

import com.example.travelserver.model.Resp;
import com.example.travelserver.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService {

    User getUserById(Long id);

    User queryUserNameIsExit(String username);

    Resp<String> uploadUserHead(MultipartFile file);

    User insertUser(User user);
}
