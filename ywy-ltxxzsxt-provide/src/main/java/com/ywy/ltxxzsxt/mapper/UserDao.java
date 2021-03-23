package com.ywy.ltxxzsxt.mapper;

import com.ywy.ltxxzsxt.domain.Users;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}