package com.example.chatonline.controller;

import com.example.chatonline.Model.JsonResult;
import com.example.chatonline.Service.UserService;
import com.example.chatonline.Util.DBUtil;
import com.example.chatonline.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        if(message!=null)
            return  JsonResult.success(message);
        else
            return  JsonResult.fail("token已失效");
    }
}
