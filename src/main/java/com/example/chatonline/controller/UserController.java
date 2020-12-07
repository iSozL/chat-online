package com.example.chatonline.controller;

import com.example.chatonline.Model.JsonResult;
import com.example.chatonline.Model.User;
import com.example.chatonline.Service.UserService;
import com.example.chatonline.Util.COMUtil;
import com.example.chatonline.Util.DBUtil;
import com.example.chatonline.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@RestController
public class UserController {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private DBUtil dbUtil;
    @Autowired
    private  UserService userService;
    @Autowired
    private  JsonResult jsonResult;
   //登录
    @PostMapping("/login")
    //@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public JsonResult login(@RequestParam("userId") String userId,
                            @RequestParam("password") String password,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(userId);
        User user = new User();
        user.setUserId(id);
        user.setPassword(password);
        Connection con =DBUtil.getCon();
        user = userService.login(con, user);
        DBUtil.closeCon(con);
        if (user != null) {
            String token = jwtUtil.creatJwtToken(user.getUserId() + "",user.getPassword());
            response.addHeader("token",token);
            return jsonResult.success(user);
        } else {
            return jsonResult.fail("登陆失败");
        }
    }
    @PostMapping("/register")
    public JsonResult register(@RequestParam("nickname") String nickname,
                                @RequestParam("password") String password) throws Exception {
        if(nickname.equals("")||password.equals(""))
            return JsonResult.fail("注册失败");
        User user = new User();
        Boolean flag ;
        user.setNickname(nickname);
        user.setPassword(password);
        Connection con =DBUtil.getCon();
        do {
            user.setUserId(COMUtil.initUserId());
            flag = userService.register(con,user);
        }while (!flag);
        DBUtil.closeCon(con);
        return JsonResult.success(user);
    }


}




