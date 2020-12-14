package com.example.chatonline.controller;

import com.example.chatonline.Model.JsonResult;
import com.example.chatonline.Model.Message;
import com.example.chatonline.Model.User;
import com.example.chatonline.Service.MessageService;
import com.example.chatonline.Service.UserService;
import com.example.chatonline.Util.COMUtil;
import com.example.chatonline.Util.DBUtil;
import com.example.chatonline.Util.JWTUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private  UserService userService;
    @Autowired
    private  JsonResult jsonResult;
    @Autowired
    private MessageService messageService;

    @PostMapping("/login")
    public JsonResult login2(@RequestParam("userId") String userId,
                             @RequestParam("password") String password,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        User user = null;
        user = userService.login(userId,password);
        System.out.println(user.getSex());
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
        Boolean flag=false;
        user.setNickname(nickname);
        user.setPassword(password);
        do {
            user.setUserId(COMUtil.initUserId()+"");
            flag = userService.register(user);
        }while (!flag);
        return JsonResult.success(user);
    }
    @PostMapping("/find")
    public JsonResult find(@RequestParam("userId") String userId)
    {
        User data = userService.Query(userId);
        if(data!=null)
            return  JsonResult.success(data);
        else
            return JsonResult.fail("未查询到该用户");
    }
    @PostMapping("/PreAddfrien")
    public JsonResult FinishFriendInfo(@RequestParam("userId") String userId,
                                       @RequestParam("friendId") String friendId)
    {
        if(userService.FindFriend(friendId)!=null)
        {
            return JsonResult.fail("该用户已在好友列表中");
        }
        ArrayList<String> groupinfo = userService.ShowGroup(userId);
        if(groupinfo!=null)
        {
            return JsonResult.success(groupinfo);
        }
        else
            return JsonResult.fail("未能查询到分组信息");
    }

    @PostMapping("/addfriend")
    public JsonResult add(@RequestParam("userId") String userId,@RequestParam("friendId") String friendId,
                            @RequestParam("message") String vinfo,@RequestParam("groupname") String groupname,
                          @RequestParam("note") String note)
    {
        Message message =new Message();
        message.setSendid(userId);
        message.setReciveid(friendId);
        message.setMessagetext(vinfo);
        //删除之前的验证消息
        messageService.DelMessage(message);
        if(userService.AddFriend(message,note,groupname))
            return JsonResult.success("发送成功");
        else
            return JsonResult.fail("发送失败");
    }



}




