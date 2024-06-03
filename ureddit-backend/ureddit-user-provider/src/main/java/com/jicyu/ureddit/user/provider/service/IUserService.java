package com.jicyu.ureddit.user.provider.service;

import com.jicyu.ureddit.dto.user.UserDTO;

public interface IUserService {
    public String insertUser(UserDTO userDTO);
    public UserDTO findUserByUserId(String userId);
    public UserDTO findUserById(String Id);
    public UserDTO findUserByUsername(String username); // username is unique
}
