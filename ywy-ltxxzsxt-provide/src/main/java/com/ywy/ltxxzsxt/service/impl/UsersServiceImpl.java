package com.ywy.ltxxzsxt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywy.ltxxzsxt.domain.Users;
import com.ywy.ltxxzsxt.mapper.UsersMapper;
import com.ywy.ltxxzsxt.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}