package com.example.chatonline.controller;

import com.example.chatonline.Model.JsonResult;
import com.example.chatonline.Model.Message;
import com.example.chatonline.Service.MessageService;
import com.example.chatonline.Service.UserService;
import com.example.chatonline.Util.JWTUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController4 {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private  UserService userService;
    @Autowired
    private  JsonResult jsonResult;
    @Autowired
    private MessageService messageService;


    /**
     * @api {post} addImage 发送添加好友映像请求
     * @apiDescription  添加好友映像请求接口
     * @apiGroup 好友
     * @apiName 添加好友映像
     * @apiversion 0.1.0
     *
     * @apiParam {String} user_id 用户ID
     * @apiParam {String} friend_id 好友ID
     * @apiParam {String} message 好友映像

     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，留言的时候才存在
     *
     * @apiSuccessExample {json} 留言成功-示例:
     *    {
     *     "timestamp": "2020-12-20T07:28:36.404+00:00",
     *     "status": 500,
     *     "error": "Internal Server Error",
     *     "message": "",
     *     "path": "/addImage"
     * }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 留言失败-示例：

     */
    @CrossOrigin
    @GetMapping("/addImage")
    public JsonResult AddImage(@RequestParam("userId") String userId,@RequestParam("friendId") String friendId,@RequestParam("mes") String mes){
        Message message = new Message();
        message.setSendid(userId);
        message.setReciveid(friendId);
        message.setMessagetext(mes);

        if (messageService.addImage(message))
            return jsonResult.success("留言成功");
        else
            return JsonResult.fail("留言失败");
    }





    
}
