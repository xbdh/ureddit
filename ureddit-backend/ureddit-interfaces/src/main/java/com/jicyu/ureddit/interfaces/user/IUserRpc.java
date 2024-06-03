package com.jicyu.ureddit.interfaces.user;

import com.jicyu.ureddit.dto.user.UserDTO;

public interface IUserRpc {
    public String insertUser(UserDTO userDTO);
    public UserDTO findUserById(String id);

    public UserDTO findUserByUserId(String UserId);
    public UserDTO findUserByUsername(String username); // username is unique

}
