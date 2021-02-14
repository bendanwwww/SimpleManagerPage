package com.manager.manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登陆控制器
 *
 * @author lsy <liushuoyang03@kuaishou.com>
 * Created on 2021-02-14
 */
@RestController
@RequestMapping("login")
public class LoginController {

    /**
     * 用户登陆
     *
     * @return
     */
    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    public String login() {
        return "login.html";
    }
}
