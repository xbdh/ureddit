package com.jicyu.ureddit.api.service.impl;

import com.jicyu.ureddit.api.rpcclient.IUserRpcClient;
import com.jicyu.ureddit.api.service.IUserApiService;
import com.jicyu.ureddit.api.vo.req.user.UserGetByIdReqVO;
import com.jicyu.ureddit.api.vo.req.user.UserGetByNameReqVO;
import com.jicyu.ureddit.api.vo.req.user.UserGetByUserIdReqVO;
import com.jicyu.ureddit.api.vo.req.user.UserNewReqVO;
import com.jicyu.ureddit.api.vo.resp.user.UserGetRespVO;
import com.jicyu.ureddit.api.vo.resp.user.UserNewRespVO;
import com.jicyu.ureddit.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApiServiceImpl implements IUserApiService {

    private IUserRpcClient userRpc;
    @Autowired
    public UserApiServiceImpl(IUserRpcClient userRpc) {
        this.userRpc = userRpc;
    }
    @Override
    public UserNewRespVO createUser(UserNewReqVO userNewReqVO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userNewReqVO.getUsername());
        userDTO.setUserId(userNewReqVO.getUserId());
        userDTO.setImage(userNewReqVO.getImage());

        String id = userRpc.insertUser(userDTO);

        UserNewRespVO respVO = new UserNewRespVO();
        respVO.setId(id);

        return respVO;
    }

    @Override
    public UserGetRespVO getUserById(UserGetByIdReqVO userGetByIdReqVO) {
        UserDTO userD = userRpc.findUserById(userGetByIdReqVO.getId());
        UserGetRespVO respVO = new UserGetRespVO();
        respVO.setId(userD.getId());
        respVO.setUsername(userD.getUsername());
        respVO.setImage(userD.getImage());
        return respVO;

    }

    @Override
    public UserGetRespVO getUserByUserId(UserGetByUserIdReqVO userGetByUserIdReqVO) {
        UserDTO userD = userRpc.findUserByUserId(userGetByUserIdReqVO.getUserId());
        UserGetRespVO respVO = new UserGetRespVO();
        respVO.setId(userD.getId());
        respVO.setUsername(userD.getUsername());
        respVO.setImage(userD.getImage());
        return respVO;
    }

    @Override
    public UserGetRespVO getUserByUsername(UserGetByNameReqVO userGetByNameReqVO) {
           UserDTO userD = userRpc.findUserByUsername(userGetByNameReqVO.getUsername());
            UserGetRespVO respVO = new UserGetRespVO();
            respVO.setId(userD.getId());
            respVO.setUsername(userD.getUsername());
            respVO.setImage(userD.getImage());
            return respVO;
    }
}
