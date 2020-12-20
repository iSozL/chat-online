package com.example.chatonline.controller;

import com.example.chatonline.Model.JsonResult;
import com.example.chatonline.Service.MessageService;
import com.example.chatonline.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController5 {
    @Autowired
    private UserService userService;
    @Autowired
    private JsonResult jsonResult;
    @Autowired
    private MessageService messageService;
    @CrossOrigin
    @GetMapping("/DeleteFriend")
    public JsonResult DeleteFriend(@RequestParam("userId") String userId,@RequestParam("friendId") String friendId)
    {
        int flag;
        flag=messageService.DeleteAllMessage(userId,friendId);
        if(flag==1){
            userService.DeleteRelation(userId,friendId);
            userService.DeleteRelation(friendId,userId);
            return  JsonResult.success("好友删除成功");
        }
        return JsonResult.fail("好友删除失败");
    }
}
