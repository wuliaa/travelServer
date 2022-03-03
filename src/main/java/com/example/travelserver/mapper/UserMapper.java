package com.example.travelserver.mapper;

import com.example.travelserver.model.User;
import org.apache.ibatis.annotations.Options;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User queryUserNameIsExit(String username);

    User queryUserByUserId(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}