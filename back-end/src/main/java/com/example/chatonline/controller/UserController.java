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
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private  UserService userService;
    @Autowired
    private  JsonResult jsonResult;
    @Autowired
    private MessageService messageService;

    /**
     * @api {post} login 登录
     * @apiDescription  用户登录接口
     * @apiGroup 登录注册
     * @apiName 登录
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 账号
     * @apiParam {String} password 密码
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回数据，成功的时候才存在
     *
     * @apiSuccessExample {json} 登录成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "success"
     *       "data":{}
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 登录失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "登录失败"
     *       "data":null
     *     }
     *
     */
    @CrossOrigin
    @PostMapping("/login")
//    @ResponseBody
    public JsonResult login2(@RequestBody String Json,
                             HttpServletResponse response) throws Exception {
        Map<String,Object> map = (Map)JSON.parse(Json);
        String userId = (String) map.get("userId");
        String password = (String) map.get("password");
        User user = userService.login(userId,password);
        if (user != null) {
            String token = jwtUtil.creatJwtToken(userId,password);
            response.addHeader("token",token);
            return jsonResult.success(user);
        } else {
            return jsonResult.fail("登录失败");
        }
    }

    /**
     * @api {post} register 注册
     * @apiDescription  用户注册接口
     * @apiGroup 登录注册
     * @apiName 注册
     * @apiversion 0.1.0
     *
     * @apiParam {String} nickname 昵称
     * @apiParam {String} password 密码
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回注册ID，成功的时候才存在
     *
     * @apiSuccessExample {json} 登录成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "success"
     *       "data": userId
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 登录失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "注册失败"
     *       "data":null
     *     }
     *
     */
    @CrossOrigin
    @PostMapping("/register")
    public JsonResult register(@RequestBody String Json) throws Exception {
        Map<String,Object> map = (Map)JSON.parse(Json);
        String nickname= (String) map.get("nickname");
        String password = (String) map.get("password");
        if(nickname.equals("")||password.equals(""))
            return JsonResult.fail("注册失败");
        User user = new User();
        Boolean flag;
        user.setNickname(nickname);
        user.setPassword(password);
        do {
            user.setUserId(COMUtil.initUserId()+"");
            flag = userService.register(user);
        }while (!flag);
        return JsonResult.success(user.getUserId());
    }


    /**
     * @api {get} find 查找用户
     * @apiDescription  查找用户接口
     * @apiGroup 用户
     * @apiName 查找用户
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回用户基本信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 查找成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "success"
     *       "data": {
     *
     *       }
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 查找失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "未查询到该用户"
     *       "data":null
     *     }
     *
     */
    @CrossOrigin
    @GetMapping("/find")
    public JsonResult find(@RequestParam("userId") String userId)
    {
        User data = userService.Query(userId);
        if(data!=null)
            return  JsonResult.success(data);
        else
            return JsonResult.fail("未查询到该用户");
    }
    /**
     * @api {post} PreAddfriend 预添加好友
     * @apiDescription  预添加好友接口
     * @apiGroup 好友
     * @apiName 预添加好友
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     * @apiParam {String} friendId 预好友ID
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回用户分组信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 预添加成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "success"
     *       "data": [group1,group2...]
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 预添加失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "该用户已在好友列表中"
     *       "data":null
     *     }
     *
     *      HTTP/1.1 200 OK
     *     {
     *       "code":-1,
     *       "message": "未能查询到分组信息"
     *       "data":null
     *      }
     */
    @CrossOrigin
    @PostMapping("/PreAddfriend")
    public JsonResult FinishFriendInfo(@RequestBody String Json)
    {
        Map<String,Object> map = (Map)JSON.parse(Json);
        String userId= (String) map.get("userId");
        String friendId = (String) map.get("friendId");
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
            return JsonResult.error("未能查询到分组信息",null);
    }

    /**
     * @api {post} addfriend 发送添加好友请求
     * @apiDescription  添加好友请求接口
     * @apiGroup 好友
     * @apiName 添加好友
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     * @apiParam {String} friendId 预好友ID
     * @apiParam {String} message 验证消息
     * @apiParam {String} groupname 分组名
     * @apiParam {String} note 好友备注
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 预添加成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "success"
     *       "data": 发送成功"
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 预添加失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "发送失败"
     *       "data":null
     *     }
     */
    @CrossOrigin
    @PostMapping("/addfriend")
    public JsonResult add(@RequestBody String Json)
    {
        Map<String,Object> map = (Map)JSON.parse(Json);
        String groupname = (String) map.get("groupname");
        String note = (String) map.get("note");
        Message message =new Message();
        message.setSendid((String) map.get("userId"));
        message.setReciveid((String) map.get("friendId"));
        message.setMessagetext((String) map.get("message"));
        //删除之前的验证消息
        messageService.DelVerifyMessage(message);
        if(messageService.AddVerifyMessage(message,note,groupname))
            return JsonResult.success("发送成功");
        else
            return JsonResult.fail("发送失败");
    }
}




