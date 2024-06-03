package com.jicyu.ureddit.api.rpcclient;

import com.jicyu.ureddit.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ureddit-user-provider",url = "${rpcClient.userUrl}")
public interface IUserRpcClient {

    @PostMapping("/user/insertUser")
    String insertUser(@RequestBody UserDTO userDTO) ;

    @GetMapping("/user/findUserById")
    UserDTO findUserById(@RequestParam("id")  String id) ;


    @GetMapping("/user/findUserByUserId")
    UserDTO findUserByUserId(@RequestParam ("userId") String UserId);

    @GetMapping("/user/findUserByUsername")
    UserDTO findUserByUsername(@RequestParam("username") String username);

}


