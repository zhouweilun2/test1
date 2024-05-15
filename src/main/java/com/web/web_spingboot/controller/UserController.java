package com.web.web_spingboot.controller;

import com.web.web_spingboot.common.CheckLogin;
import com.web.web_spingboot.common.MyPage;
import com.web.web_spingboot.common.Rs;
import com.web.web_spingboot.entity.User;
import com.web.web_spingboot.service.MessageService;
import com.web.web_spingboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 消息接口
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getOtherUsers")
    public Rs<MyPage<List<User>>> getOtherUsers(Integer userId, Integer current, Integer size){
        CheckLogin.check(userId);
        MyPage<List<User>> users = userService.getOtherUsers(userId, current, size);
        return Rs.ok(users);
    }
}
