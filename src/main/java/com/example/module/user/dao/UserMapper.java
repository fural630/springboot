package com.example.module.user.dao;

import java.util.List;

import com.example.module.user.po.UserPO;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserPO record);

    int insertSelective(UserPO record);

    UserPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserPO record);

    int updateByPrimaryKey(UserPO record);
    
    List<UserPO> getUserPage();
}