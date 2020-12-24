package com.example.chatonline.controller;

import com.example.chatonline.Model.JsonResult;
import com.example.chatonline.Service.UserServiceImpl;
import com.example.chatonline.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class VarifyController {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private  JsonResult jsonResult;
    @PostMapping("/varify")
    public JsonResult varity(@RequestParam("token") String token)
    {
        Map<String,Object> map = jwtUtil.parseJWTToken(token);
        if(map!=null)
            return  JsonResult.success(map);
        else
            return  JsonResult.fail("token已失效");
    }
}
