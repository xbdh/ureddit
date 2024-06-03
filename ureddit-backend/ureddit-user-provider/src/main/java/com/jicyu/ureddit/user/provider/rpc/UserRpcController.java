package com.jicyu.ureddit.user.provider.rpc;

import com.jicyu.ureddit.dto.user.UserDTO;
import com.jicyu.ureddit.user.provider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRpcController {
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/insertUser")
    public String insertUser(@RequestBody  UserDTO userDTO) {

        return userService.insertUser(userDTO);
    }

    @GetMapping("/findUserById")
    public UserDTO findUserById(@RequestParam ("id")  String id) {

        return userService.findUserById(id);
    }

    @GetMapping("/findUserByUserId")
    public UserDTO findUserByUserId(@RequestParam ("userId") String UserId) {
        return userService.findUserByUserId(UserId);
    }

   @GetMapping("/findUserByUsername")
    public UserDTO findUserByUsername(@RequestParam("username") String username) {

        return userService.findUserByUsername(username);
    }
}
