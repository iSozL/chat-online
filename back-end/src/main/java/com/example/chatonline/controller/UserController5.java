package com.example.chatonline.controller;

import com.example.chatonline.Model.JsonResult;
import com.example.chatonline.Service.MessageService;
import com.example.chatonline.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
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


    /**
     * @api {get} DeleteFriend 删除好友
     * @apiDescription  删除好友接口
     * @apiGroup 好友
     * @apiName 删除好友
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户Id
     * @apiParam {String} friendId 好友Id
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 删除成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "success",
     *       "data": "好友删除成功",
     *     }
     *
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     *
     * @apiErrorExample {json} 删除失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "好友删除失败"
     *       "data":null
     *     }
     *
     */
    @CrossOrigin
    @GetMapping("/DeleteFriend")
    public JsonResult DeleteFriend(@RequestParam("userId") String userId,@RequestParam("friendId") String friendId)
    {
        messageService.DeleteAllMessage(userId,friendId);
        userService.DeleteRelation(userId,friendId);
        boolean i = userService.DeleteRelation(friendId,userId);
        if(i) {
            return JsonResult.success("好友删除成功");
        }else
            return JsonResult.fail("好友删除失败");
    }
}
