package com.jicyu.ureddit.api.service;

import com.jicyu.ureddit.api.vo.req.user.UserGetByIdReqVO;
import com.jicyu.ureddit.api.vo.req.user.UserGetByNameReqVO;
import com.jicyu.ureddit.api.vo.req.user.UserGetByUserIdReqVO;
import com.jicyu.ureddit.api.vo.req.user.UserNewReqVO;
import com.jicyu.ureddit.api.vo.resp.user.UserGetRespVO;
import com.jicyu.ureddit.api.vo.resp.user.UserNewRespVO;

public interface IUserApiService {
    public UserNewRespVO createUser(UserNewReqVO userNewReqVO);
    public UserGetRespVO getUserById(UserGetByIdReqVO userGetByIdReqVO);
    public UserGetRespVO getUserByUserId(UserGetByUserIdReqVO userGetByUserIdReqVO);
    public UserGetRespVO getUserByUsername(UserGetByNameReqVO    userGetByNameReqVO); // username is unique
}
