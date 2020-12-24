package com.example.chatonline.controller;

import com.example.chatonline.Config.DateConverterConfig;
import com.example.chatonline.Model.Group;
import com.example.chatonline.Model.JsonResult;
import com.example.chatonline.Model.Message;
import com.example.chatonline.Model.User;
import com.example.chatonline.Service.MessageServiceImpl;
import com.example.chatonline.Service.UserServiceImpl;
import com.example.chatonline.Util.COMUtil;
import com.example.chatonline.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private  JsonResult jsonResult;
    @Autowired
    private MessageServiceImpl messageServiceImpl;
    @Autowired
    private DateConverterConfig dateConverterConfig;

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
     *       "message": "success",
     *       "data":{}
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 登录失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "登录失败",
     *       "data":null
     *     }
     *
     */
    @CrossOrigin
    @PostMapping("/login")
    public JsonResult login2(@RequestBody String Json,
                             HttpServletResponse response) throws Exception {
        Map<String,Object> map = (Map)JSON.parse(Json);
        String userId = (String) map.get("userId");
        String password = (String) map.get("password");
        User user = userServiceImpl.login(userId,password);
        if (user != null) {
            user.setGroups(userServiceImpl.ShowGroup(userId));
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
     *       "message": "success",
     *       "data": userId
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 登录失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "注册失败",
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
        //直至ID唯一
        do {
            int tmp=COMUtil.initUserId();
            user.setUserId(tmp+"");
            flag = userServiceImpl.register(user);
        }while (!flag);
        //初始分组
        userServiceImpl.CreatGroup(user.getUserId(),"我的好友",1);
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
     *       "message": "success",
     *       "data": [user1,user2...]
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 查找失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "未查询到该用户",
     *       "data":null
     *     }
     *
     */
    @CrossOrigin
    @GetMapping("/find")
    public JsonResult find(@RequestParam("userId") String userId)
    {
        ArrayList<User> data = userServiceImpl.Query(userId);
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
     *       "message": "success",
     *       "data": [group1,group2...]
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 预添加失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "该用户已在好友列表中",
     *       "data":null
     *     }
     *
     *      HTTP/1.1 200 OK
     *     {
     *       "code":-1,
     *       "message": "未能查询到分组信息",
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
        if(userId.equals(friendId))
        {
            return JsonResult.error("不能添加自己为好友",null);
        }
        if(userServiceImpl.FindRelation(userId,friendId))
        {
            return JsonResult.fail("该用户已在好友列表中");
        }
        ArrayList<Group> groupinfo = userServiceImpl.ShowGroup(userId);
            return JsonResult.success(groupinfo);

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
     *       "message": "success",
     *       "data": "发送成功"
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 预添加失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "发送失败",
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
        message.setSendtime(new Date());
        //删除之前的验证消息
        messageServiceImpl.DelVerifyMessage(message);
        if(messageServiceImpl.AddVerifyMessage(message,note,groupname))
            return JsonResult.success("发送成功");
        else
            return JsonResult.fail("发送失败");
    }


    /**
     * @api {post} showveritymessage 显示验证消息
     * @apiDescription  显示验证消息接口
     * @apiGroup 消息
     * @apiName 显示验证消息
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 有验证消息-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "success",
     *       "data": [
     *         {
     *             "sendid": "",
     *             "reciveid": "",
     *             "sendtime": "",
     *             "messagetext": "",
     *             "texttype": 1 //1表示已同意
     *         },
     *         {
     *             "sendid": "",
     *             "reciveid": "",
     *             "sendtime": "",
     *             "messagetext": "",
     *             "texttype": 0 //0表示尚未处理
     *         },
     *         {
     *             "sendid": "",
     *             "reciveid": "",
     *             "sendtime": "",
     *             "messagetext": "",
     *             "texttype": -1 //-1表示已拒绝
     *         }
     *     ]
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 无验证消息-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "暂无验证消息",
     *       "data":null
     *     }
     */
    @CrossOrigin
    @PostMapping("/showveritymessage")
    public  JsonResult recive(@RequestBody String Json)
    {
        Map<String,Object> map = (Map)JSON.parse(Json);
        String userId = (String) map.get("userId");
        ArrayList<Message> messages = messageServiceImpl.ShowVerifyMessage(userId);
        if(messages.size()!=0)
        {
            return JsonResult.success(messages);
        }
        else
            return JsonResult.fail("暂无验证消息");
    }

//    @CrossOrigin
//    @PostMapping("/prehandlemessage")
//    public JsonResult prehandle(@RequestBody String Json)
//    {
//        Map<String,Object> map =(Map) JSON.parse(Json);
//        int type = (Integer) map.get("type");
//        String userId = (String) map.get("userId");
//        String sendId = (String) map.get("sendId");
//        //接受，设置所属分组，和备注
//        if(type==1)
//        {
//            ArrayList<Group> groupinfo = userService.ShowGroup(userId);
//            if(groupinfo!=null)
//            {
//                return JsonResult.success(groupinfo);
//            }
//            else
//                return JsonResult.error("未能查询到分组信息",null);
//        }
//        //拒绝
//        else
//        {
//            boolean flag;
//            do{
//                flag=messageService.HandleVerifyMessage(type,sendId,userId);
//            }while(!flag);
//            return JsonResult.fail("已拒绝");
//        }
//    }
    /**
     * @api {post} handlemessage 接受好友请求
     * @apiDescription  接受好友请求接口
     * @apiGroup 消息
     * @apiName 接受好友请求
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     * @apiParam {String} sendId 发送方ID
     * @apiParam {String} groupname 分组名
     * @apiParam {String} note 备注
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回分组信息 成功的时候才存在
     *
     * @apiSuccessExample {json} 添加好友成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "message": "success",
     *       "data": "已添加",
     *       "code": 1
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 未能查询到分组信息-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "添加失败",
     *       "data":null
     *     }
     */
    @CrossOrigin
    @PostMapping("/handlemessage")
    public JsonResult handle(@RequestBody String Json) {
        Map<String, Object> map = (Map) JSON.parse(Json);

        String userId = (String) map.get("userId");
        String sendId = (String) map.get("sendId");
        String groupname = (String) map.get("groupname");
        String note = (String) map.get("note");
        Message message = new Message();
        message.setSendid(sendId);
        message.setReciveid(userId);

        //获取发送方的备注和分组信息
        map = messageServiceImpl.FindVerifyMessage(message);

        int flag;
        flag= userServiceImpl.AddFriend(userId,sendId,note,groupname,(String) map.get("note"),(String) map.get("groupname"));
        if (flag==1) {
            return  JsonResult.success("已添加");
        } else {
            return JsonResult.fail("添加失败");
        }
    }
    /**
     * @api {post} refuseadd 拒绝好友请求
     * @apiDescription  拒绝好友请求接口
     * @apiGroup 消息
     * @apiName 拒绝好友请求
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     * @apiParam {String} sendId 发送方ID
     * @apiParam {String} sendtime 发送时间
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回分组信息 成功的时候才存在
     *
     * @apiSuccessExample {json} 拒绝好友请求成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "message": "success",
     *       "data": "已拒绝",
     *       "code": 1
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 拒绝好友请求失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "拒绝失败",
     *       "data":null
     *     }
     *
     */

    @CrossOrigin
    @PostMapping("/refuseadd")
    public JsonResult Refuse(@RequestBody String Json)
    {
        Map<String, Object> map = (Map) JSON.parse(Json);
        String userId = (String) map.get("userId");
        String sendId = (String) map.get("sendId");
        Date sendtime = dateConverterConfig.convert((String) map.get("sendtime"));
        boolean flag;
        flag = messageServiceImpl.Refuseadd(userId,sendId,sendtime);
        if(flag)
        {
            return JsonResult.success("已拒绝");
        }
        else
        {
            return JsonResult.fail("拒绝失败");
        }

    }

    /**
     * @api {post} deleteveritymessage 删除验证消息
     * @apiDescription  删除验证消息接口
     * @apiGroup 消息
     * @apiName 删除验证消息
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     * @apiParam {String} sendId 发送方ID
     * @apiParam {String} sendtime 发送时间
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回分组信息 成功的时候才存在
     *
     * @apiSuccessExample {json} 删除验证消息成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "message": "success",
     *       "data": "删除成功",
     *       "code": 1
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 删除验证消息失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "删除失败",
     *       "data":null
     *     }
     *
     */
    @CrossOrigin
    @PostMapping("/deleteveritymessage")
    public JsonResult Delete(@RequestBody String Json)
    {
        Map<String, Object> map = (Map) JSON.parse(Json);
        String userId = (String) map.get("userId");
        String sendId = (String) map.get("sendId");
        Date sendtime = dateConverterConfig.convert((String) map.get("sendtime"));
        boolean flag;
        flag = messageServiceImpl.DeleteVerifyMessage(userId,sendId,sendtime);
        if(flag)
        {
            return JsonResult.success("删除成功");
        }
        else
        {
            return JsonResult.fail("删除失败");
        }
    }



}




