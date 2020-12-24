package com.example.chatonline.controller;

import com.example.chatonline.Model.JsonResult;
import com.example.chatonline.Service.MessageServiceImpl;
import com.example.chatonline.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController3 {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private JsonResult jsonResult;
    @Autowired
    private MessageServiceImpl messageServiceImpl;
    /**
     * @api {get} GroupMove 好友移动
     * @apiDescription  好友移动接口
     * @apiGroup 好友
     * @apiName 好友移动
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户Id
     * @apiParam {String} friendId 好友Id
     * @apiParam {String} preGroupname 移动前分组名
     * @apiParam {String} postGroupname 移动后分组名
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 移动成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "success",
     *       "data": "移动好友分组成功",
     *     }
     *
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     *
     * @apiErrorExample {json} 移动失败-示例：
     *      HTTP/1.1 4xx
     *     {
     *       "code":0,
     *       "message": "好友分组移动失败"
     *       "data":null
     *     }
     *
     */
    @CrossOrigin
    @GetMapping("/GroupMove")
    public JsonResult GroupMove(@RequestParam("userId") String userId,@RequestParam("friendId") String friendId,@RequestParam("preGroupname") String preGroupname,@RequestParam("postGroupname") String postGroupname)
    {

        if(userServiceImpl.GroupMove(userId,friendId,postGroupname))
            return  JsonResult.success("移动好友分组成功");
        else
            return JsonResult.fail("好友分组移动失败");
    }

    /**
     * @api {get} GroupFriends 好友列表
     * @apiDescription  好友列表接口
     * @apiGroup 好友
     * @apiName 好友列表
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户Id
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回分组内的好友信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 查询成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "success",
     *       "data": {
     *             "birthday": "2020-12-19",
     *             "note": "test",
     *             "address": "ncu",
     *             "phone": "12345678910",
     *             "signature": "笑一笑就好",  个性签名
     *             "nickname": "和规范化",
     *             "userId": "5",
     *             "groupname": "分组二",
     *         },
     *     }
     *
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     *
     * @apiErrorExample {json} 查询错误-示例：
     *      HTTP/1.1 4xx
     *     {
     *       "code":-1,
     *       "message": "未查询到该用户好友信息",
     *       "data":null,
     *     }
     */
    @CrossOrigin
    @GetMapping("/GroupFriends")
    public JsonResult GroupFriends(@RequestParam("userId") String userId)
    {
        ArrayList<Map<String,Object>> users = userServiceImpl.GroupFriends(userId);

        if(users!=null)
            return  JsonResult.success(users);
        else
            return JsonResult.error("未查询到该用户好友信息",null);
    }

    /**
     * @api {get} CreatGroup 添加好友分组
     * @apiDescription  添加好友分组接口
     * @apiGroup 好友
     * @apiName 添加好友分组
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户Id
     * @apiParam {String} groupname 分组名
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 添加成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "success",
     *       "data": "添加好友分组成功"
     *     }
     *
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     *
     * @apiErrorExample {json} 添加失败-示例：
     *      HTTP/1.1 4xx
     *     {
     *       "code":0,
     *       "message": "添加好友分组失败"
     *       "data":null
     *     }
     *
     */
    @CrossOrigin
    @GetMapping("/CreatGroup")
    public JsonResult CreatGroup(@RequestParam("userId") String userId,@RequestParam("groupname") String groupname)
    {
        boolean date = userServiceImpl.CreatGroup(userId,groupname,0);
        if(date)
            return  JsonResult.success("添加好友分组成功");
        else
            return JsonResult.fail("添加好友分组失败");
    }

    /**
     * @api {get} DelGroup 删除好友分组
     * @apiDescription  删除分组接口
     * @apiGroup 好友
     * @apiName 删除好友分组
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户Id
     * @apiParam {String} groupname 分组名
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
     *       "data": "删除好友分组成功"
     *     }
     *
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     *
     * @apiErrorExample {json} 删除失败-示例1：
     *      HTTP/1.1 4xx
     *     {
     *       "code":0,
     *       "message": "删除好友分组失败"
     *       "data":null
     *     }
     * @apiErrorExample {json} 删除失败-示例2：
     *      HTTP/1.1 4xx
     *     {
     *       "code":0,
     *       "message": "不能删除系统默认分组"
     *       "data":null
     *     }
     */
    @CrossOrigin
    @GetMapping("/DelGroup")
    public JsonResult DelGroup(@RequestParam("userId") String userId,@RequestParam("groupname") String groupname)
    {
        String defGroupname = userServiceImpl.getDefGroupname(userId);
        if (defGroupname.equals(groupname)){
            return JsonResult.fail("不能删除系统默认分组");
        }
        else {
            ArrayList<Map<String, Object>> friendIdList = userServiceImpl.getFriendsId(userId, groupname);
            for (int i = 0; i < friendIdList.size(); i++) {
                String friendId = (String) friendIdList.get(i).get("friendId");
                boolean flag = userServiceImpl.GroupMove(userId, friendId, defGroupname);
                if (!flag)
                    return JsonResult.fail("删除好友分组失败");
            }
            boolean date = userServiceImpl.DelGroup(userId, groupname);
            if (date)
                return JsonResult.success("删除好友分组成功");
            else
                return JsonResult.fail("删除好友分组失败");
        }
    }

    /**
     * @api {get} ChangeNote 更改好友备注
     * @apiDescription  更改备注接口
     * @apiGroup 好友
     * @apiName 更改好友备注
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户Id
     * @apiParam {String} friendId 好友Id
     * @apiParam {String} note 备注
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 更改成功-示例:
     *     HTTP/1.1 200 OK
     *     {
     *       "code":1,
     *       "message": "success",
     *       "data": "更改好友备注成功"
     *     }
     *
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     *
     * @apiErrorExample {json} 更改失败-示例：
     *      HTTP/1.1 4xx
     *     {
     *       "code":0,
     *       "message": "更改好友备注失败"
     *       "data":null
     *     }
     */
    @CrossOrigin
    @GetMapping("/ChangeNote")
    public JsonResult ChangeNote(@RequestParam("userId") String userId,@RequestParam("friendId") String friendId,@RequestParam("note") String note)
    {
        boolean date = userServiceImpl.ChangeNote(userId,friendId,note);
        if(date)
            return JsonResult.success("更改好友备注成功");
        else
            return JsonResult.fail("更改好友备注失败");
    }
}
