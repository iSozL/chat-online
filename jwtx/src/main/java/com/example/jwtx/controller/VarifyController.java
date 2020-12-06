package com.example.jwtx.controller;

import com.example.jwtx.Model.JsonResult;
import com.example.jwtx.Service.UserService;
import com.example.jwtx.Util.DBUtil;
import com.example.jwtx.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class VarifyController {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private DBUtil dbUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private  JsonResult jsonResult;
    @PostMapping("/varify")
    public JsonResult varity(@RequestParam("token") String token)
    {
        Object message = jwtUtil.parseJWTToken(token);
        return  JsonResult.success(message);
    }


}
