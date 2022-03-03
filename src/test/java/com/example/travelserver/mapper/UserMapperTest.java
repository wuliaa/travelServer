package com.example.travelserver.mapper;

import com.example.travelserver.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void selectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey((long) 1);
        assertEquals("inny",user.getNickname());
    }
}