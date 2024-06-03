package com.jicyu.ureddit.user.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jicyu.ureddit.common.utils.ConvertBeanUtils;
import com.jicyu.ureddit.dto.user.UserDTO;
import com.jicyu.ureddit.user.provider.dao.mapper.IUserMapper;
import com.jicyu.ureddit.user.provider.dao.po.UserPO;
import com.jicyu.ureddit.user.provider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private IUserMapper userMapper;

    @Autowired
    public void setUserMapper(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public String insertUser(UserDTO userDTO) {
        UserPO userPO= ConvertBeanUtils.convert(userDTO,UserPO.class);
        userMapper.insert(userPO);
        String id = userPO.getId();

        return id;
    }

    @Override
    public UserDTO findUserByUserId(String userId) {
        LambdaQueryWrapper<UserPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserPO::getUserId,userId);
        UserPO userPO = userMapper.selectOne(wrapper);
        //System.out.println(userPO);
        return ConvertBeanUtils.convert(userPO,UserDTO.class);
    }

    @Override
    public UserDTO findUserById(String id) {
        LambdaQueryWrapper<UserPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserPO::getId,id);
        UserPO userPO = userMapper.selectOne(wrapper);
        return ConvertBeanUtils.convert(userPO,UserDTO.class);
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        LambdaQueryWrapper<UserPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserPO::getUsername,username);
        UserPO userPO = userMapper.selectOne(wrapper);
        return ConvertBeanUtils.convert(userPO,UserDTO.class);
    }
}
