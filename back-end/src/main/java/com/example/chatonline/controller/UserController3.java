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
public class UserController3 {
    @Autowired
    private UserService userService;
    @Autowired
    private JsonResult jsonResult;
    @Autowired
    private MessageService messageService;
    /**
     * @api {get} groupMove 好友移动
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
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "好友分组移动失败"
     *       "data":null
     *     }
     *
     */
    @CrossOrigin
    @GetMapping("/groupMove")
    public JsonResult groupMove(@RequestParam("userId") String userId,@RequestParam("friendId") String friendId,@RequestParam("preGroupname") String preGroupname,@RequestParam("postGroupname") String postGroupname)
    {
        
        if( userService.groupMove(userId,friendId,postGroupname) && userService.preGroupnum(userId,preGroupname) && userService.postGroupnum(userId,postGroupname))
            return  JsonResult.success("移动好友分组成功");
        else
            return JsonResult.fail("好友分组移动失败");
    }

    /**
     * @api {get} groupfriends 好友列表
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
     *             "evaluate": "起飞"         好友印象
     *         },
     *     }
     *
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     *
     * @apiErrorExample {json} 查询错误-示例：
     *      HTTP/1.1 200 OK
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
        ArrayList<Map<String,Object>> users = userService.GroupFriends(userId);

        if(users!=null)
            return  JsonResult.success(users);
        else
            return JsonResult.error("未查询到该用户好友信息",null);
    }
}
