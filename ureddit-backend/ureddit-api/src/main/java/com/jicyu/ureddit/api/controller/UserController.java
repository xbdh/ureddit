package com.jicyu.ureddit.api.controller;



import com.jicyu.ureddit.api.service.IUserApiService;
import com.jicyu.ureddit.api.vo.req.user.UserGetByIdReqVO;
import com.jicyu.ureddit.api.vo.req.user.UserGetByNameReqVO;
import com.jicyu.ureddit.api.vo.req.user.UserGetByUserIdReqVO;
import com.jicyu.ureddit.api.vo.req.user.UserNewReqVO;
import com.jicyu.ureddit.api.vo.resp.user.UserGetRespVO;
import com.jicyu.ureddit.api.vo.resp.user.UserNewRespVO;
import com.jicyu.ureddit.common.vo.WebResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class UserController {

    private IUserApiService userApiService;

    @Autowired
    public void setUserApiService(IUserApiService userApiService) {
        this.userApiService = userApiService;
    }

    @PostMapping("/createUser")
    public WebResponseVO createUser(@RequestBody UserNewReqVO reqVO) {
        UserNewRespVO respVO = userApiService.createUser(reqVO);
        return WebResponseVO.success(respVO);

    }

    @PostMapping("/findUserById")
    public WebResponseVO findUserById(@RequestBody UserGetByIdReqVO reqVO) {
        UserGetRespVO respVO = userApiService.getUserById(reqVO);
        return WebResponseVO.success(respVO);

    }

    @PostMapping("/findUserByUserId")
    public WebResponseVO findUserByUserId(@RequestBody UserGetByUserIdReqVO reqVO) {
        UserGetRespVO respVO = userApiService.getUserByUserId(reqVO);
        return WebResponseVO.success(respVO);

    }

    @PostMapping("/findUserByUsername")
    public WebResponseVO findUserByUsername(@RequestBody UserGetByNameReqVO reqVO) {
        UserGetRespVO respVO = userApiService.getUserByUsername(reqVO);
        return WebResponseVO.success(respVO);
    }

}
