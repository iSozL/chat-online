package com.example.chatonline.controller;

import com.alibaba.fastjson.JSON;
import com.example.chatonline.Config.DateConverterConfig;
import com.example.chatonline.Model.Group;
import com.example.chatonline.Model.JsonResult;
import com.example.chatonline.Model.Message;
import com.example.chatonline.Model.User;
import com.example.chatonline.Service.MessageService;
import com.example.chatonline.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.util.*;

@RestController
@CrossOrigin
public class UserController2 {
    @Autowired
    private UserService userService;
    @Autowired
    private JsonResult jsonResult;
    @Autowired
    private MessageService messageService;
    @Autowired
    private DateConverterConfig dateConverterConfig;
    /**
     *
     * @api {get} ShowFriendLastMessage 消息列表
     * @apiDescription  显示消息列表接口
     * @apiGroup 消息
     * @apiName 显示消息列表
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 消息列表显示成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "success",
     *       "data": [
     *         {
     *             "messagetxt": "",
     *             "friendId": "",
     *             "nickname": "",
     *             "sendtime": "2020-12-03T09:46:01.000+00:00"
     *         }]
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 消息列表为空-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "消息列表为空",
     *       "data":null
     *     }
     */
    @GetMapping("/ShowFriendLastMessage")
    @CrossOrigin
    public JsonResult ShowFriendLastMessage(@RequestParam("userId") String userId) {
        ArrayList<Map<String, Object>> sendmessages = messageService.ShowsendLastMessage(userId);
        ArrayList<Map<String, Object>> recivemessages = messageService.ShowreciveLastMessage(userId);
        ArrayList<Map<String, Object>> needmessages=new ArrayList<>();


        //合并最新消息
        System.out.println(sendmessages.size());
        System.out.println(recivemessages.size());
        Iterator<Map<String, Object>> it = sendmessages.iterator();
        while (it.hasNext()) {
            Map<String, Object> sendmap = it.next();
            Iterator<Map<String, Object>> jt = recivemessages.iterator();
            while (jt.hasNext()) {
                Map<String, Object> recivemap = jt.next();
                //判断最新时间
                if (sendmap.get("sendId").equals(recivemap.get("reciveId")) &&
                        sendmap.get("reciveId").equals(recivemap.get("sendId"))) {
                    Map<String, Object> needmessage = new HashMap<>();
                    long IMillisecond = ((Date) sendmap.get("sendtime")).getTime();
                    long JMillisecond = ((Date) recivemap.get("sendtime")).getTime();
                    if (IMillisecond > JMillisecond) {
                        needmessage.put("friendId",sendmap.get("reciveId"));
                        needmessage.put("nickname", sendmap.get("nickname"));
                        needmessage.put("messagetxt", sendmap.get("messagetxt"));
                        needmessage.put("sendtime", sendmap.get("sendtime"));
                    } else {
                        needmessage.put("friendId",recivemap.get("sendId"));
                        needmessage.put("nickname", recivemap.get("nickname"));
                        needmessage.put("messagetxt", recivemap.get("messagetxt"));
                        needmessage.put("sendtime", recivemap.get("sendtime"));
                    }
                    needmessages.add(needmessage);
                    it.remove();
                    jt.remove();
                    break;
                }
            }
        }

        //对方尚未发过消息
        it = sendmessages.iterator();
        while (it.hasNext()) {
            Map<String, Object> needmessage = new HashMap<>();
            Map<String, Object> sendmap = it.next();
            needmessage.put("friendId",sendmap.get("reciveId"));
            needmessage.put("nickname", sendmap.get("nickname"));
            needmessage.put("messagetxt", sendmap.get("messagetxt"));
            needmessage.put("sendtime", sendmap.get("sendtime"));
            needmessages.add(needmessage);
        }
        //自己尚未发过消息
        Iterator<Map<String, Object>> jt = recivemessages.iterator();
        while (jt.hasNext()) {
            Map<String, Object> needmessage = new HashMap<>();
            Map<String, Object> recivemap = jt.next();
            needmessage.put("friendId",recivemap.get("sendId"));
            needmessage.put("nickname", recivemap.get("nickname"));
            needmessage.put("messagetxt", recivemap.get("messagetxt"));
            needmessage.put("sendtime", recivemap.get("sendtime"));
            needmessages.add(needmessage);
        }

        if(needmessages.size()!=0)
        {
            return  JsonResult.success(needmessages);
        }
        else
        {
            return JsonResult.fail("消息列表为空");
        }
    }

    /**
     *
     * @api {get} ShowGroup 显示好友分组
     * @apiDescription  显示好友分组接口
     * @apiGroup 好友
     * @apiName 显示好友分组
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 显示好友分组-示例:
     *     HTTP/1.1 200 OK
     *     "message": "success",
     *     "data": [
     *         {
     *             "groupname": "分组一",
     *             "count_num": 6
     *         },
     *         {
     *             "groupname": "分组二",
     *             "count_num": 6
     *         }
     *     ],
     *     "code": 1
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 分组信息为空-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":-1,
     *       "message": "未能查询到分组信息",
     *       "data":null
     *     }
     */
    @GetMapping("/ShowGroup")
    @CrossOrigin
    public JsonResult ShowGroup(@RequestParam("userId") String userId)
    {
        ArrayList<Group> groupinfo = userService.ShowGroup(userId);
        if(groupinfo!=null)
        {
            return JsonResult.success(groupinfo);
        }
        else
            return JsonResult.error("未能查询到分组信息",null);
    }
    /**
     *
     * @api {post} updateinfo 编辑个人资料
     * @apiDescription  编辑个人资料接口
     * @apiGroup 用户
     * @apiName 编辑个人资料
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     * @apiParam {String} nickname 昵称
     * @apiParam {Date} birth 生日
     * @apiParam {String} sex 性别
     * @apiParam {String} address 地址
     * @apiParam {String} signature 个性签名
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 修改成功-示例:
     *     HTTP/1.1 200 OK
     *     "message": "success",
     *     "data": ""修改成功,
     *     "code": 1
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 修改失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "修改失败",
     *       "data":null
     *     }
     */
    @CrossOrigin
    @PostMapping("/updateinfo")
    public JsonResult UpdateInfo(@RequestBody  String Json)
    {
        Map<String,Object> map = (Map)JSON.parse(Json);
        String userId= (String) map.get("userId");
        String nickname = (String) map.get("nickname");
        String sex = (String) map.get("sex");
        String address = (String) map.get("address");
        String signature = (String) map.get("signature");
        String phone = (String) map.get("phone");
        Date birth= dateConverterConfig.convert((String)map.get("birth"));
        if(userService.UpdateInfo(userId,nickname,sex,birth,signature,address,phone))
        {
            return JsonResult.success("修改成功");
        }
        else
        {
            return JsonResult.fail("修改失败");
        }
    }
    /**
     *
     * @api {get} ShowInfo 显示个人信息
     * @apiDescription  显示个人信息接口
     * @apiGroup 用户
     * @apiName 显示个人信息
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 显示好友分组-示例:
     *     HTTP/1.1 200 OK
     *     "message": "success",
     *     "data":{
     *         "userId": null,
     *         "password": null,
     *         "nickname": "修改资料",
     *         "sex": "男",
     *         "groups": null,
     *         "birthday": "2020-12-10",
     *         "address": "南昌市",
     *         "phone": "12345"
     *     },
     *     "code": 1
     *     }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 分组信息为空-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "显示失败",
     *       "data":null
     *     }
     */
    @CrossOrigin
    @GetMapping("/ShowInfo")
    public JsonResult ShowInfo(@RequestParam("userId") String userId)
    {
        User user=userService.ShowInfo(userId);
        if(user!=null)
        {
            return JsonResult.success(user);
        }
        else
            return JsonResult.fail("显示失败");
        }

}
