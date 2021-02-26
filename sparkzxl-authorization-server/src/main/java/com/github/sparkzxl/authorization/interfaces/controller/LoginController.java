package com.github.sparkzxl.authorization.interfaces.controller;

import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@Api(tags = "登录页管理")
public class LoginController {

    @ApiOperation(value = "登录页面", notes = "登录页面")
    @GetMapping(value = "/authentication/require",produces = "text/html;charset=UTF-8")
    public String require() {
        return "login";
    }
}
