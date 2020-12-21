package com.example.chatonline.controller;

import com.example.chatonline.Config.DateConverterConfig;
import com.example.chatonline.Model.JsonResult;
import com.example.chatonline.Model.Message;
import com.example.chatonline.Model.Image;
import com.example.chatonline.Service.MessageService;
import com.example.chatonline.Service.UserService;
import com.example.chatonline.Util.JWTUtil;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    @Autowired
    private DateConverterConfig dateConverterConfig;


    /**
     * @api {Get} addImage 发送添加好友印象
     * @apiDescription  添加好友印象请求接口
     * @apiGroup 消息
     * @apiName 添加好友印象
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     * @apiParam {String} friendId 好友ID
     * @apiParam {String} mes 好友映像
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，留言的时候才存在
     *
     * @apiSuccessExample {json} 留言成功-示例:
     *    HTTP/1.1 200 OK
     *    {
     *     "message": "success",
     *     "data": "留言成功",
     *     "code": 1
     * }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 留言失败-示例：
     *  HTTP/1.1 500
     *       {
     *            "code":0,
     *            "message": "留言失败"
     *          "data":null
     *     }
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
    /**
     * @api {Get} DelImage 删除自己发送的好友印象
     * @apiDescription  删除自己发送的好友印象接口
     * @apiGroup 消息
     * @apiName 删除自己发送的好友印象
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     * @apiParam {String} friendId 好友ID
     * @apiParam {String} time 留言时间
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，留言的时候才存在
     *
     * @apiSuccessExample {json} 删除成功-示例:
     *    HTTP/1.1 200 OK
     *    {
     *     "message": "success",
     *     "data": "删除成功",
     *     "code": 1
     * }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json}删除失败-示例：
     *  HTTP/1.1 200 OK
     *  {
     *     "message": "删除失败",
     *     "data": null,
     *     "code": 0
     * }
     */
    @CrossOrigin
    @GetMapping("/DelImage")
    public JsonResult DelImage(@RequestParam("userId") String userId,@RequestParam("friendId") String friendId,@RequestParam("time") String time){
        Message message = new Message();
        message.setSendid(userId);
        message.setReciveid(friendId);
        //Date date=dateConverterConfig.convert(sendtime);
        message.setSendtime(dateConverterConfig.convert(time));
        boolean i = messageService.DelImage(message);
        if (i)
            return jsonResult.success("删除成功");
        else
            return JsonResult.fail("删除失败");
    }
    /**
     * @api {Get} DelReceiveImage 删除接收的好友印象
     * @apiDescription  删除接收的好友印象接口
     * @apiGroup 消息
     * @apiName 删除接收的好友印象
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     * @apiParam {String} friendId 好友ID
     * @apiParam {String} time 留言时间
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，留言的时候才存在
     *
     * @apiSuccessExample {json} 删除成功-示例:
     *    HTTP/1.1 200 OK
     *    {
     *     "message": "success",
     *     "data": "删除成功",
     *     "code": 1
     * }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 删除失败-示例：
     *  HTTP/1.1 200 Ok
     *  {
     *     "message": "删除失败",
     *     "data": null,
     *     "code": 0
     * }
     */
    @CrossOrigin
    @GetMapping("/DelReceiveImage")
    public JsonResult DelReceiveImage(@RequestParam("userId") String userId,@RequestParam("friendId") String friendId,@RequestParam("time") String time){
        Message message = new Message();
        message.setSendid(friendId);
        message.setReciveid(userId);
        //Date date=dateConverterConfig.convert(sendtime);
        message.setSendtime(dateConverterConfig.convert(time));
        boolean i = messageService.DelImage(message);
        if (i)
            return jsonResult.success("删除成功");
        else
            return JsonResult.fail("删除失败");
    }
    /**
     * @api {Get} ShowImage 显示接收的好友印象
     * @apiDescription  显示接收的好友印象接口
     * @apiGroup 消息
     * @apiName 显示接收的好友印象
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 有好友印象-示例:
     *     HTTP/1.1 200 OK
     *     {
     *     "message": "success",
     *     "data": [
     *         {
     *             "userId": "0",
     *             "friendId": "101",
     *             "message": "123",
     *             "sendtime": "2020-12-21 12:06:26",
     *             "flag": 0
     *         },
     *         {
     *             "userId": "0",
     *             "friendId": "101",
     *             "message": "2020/12/21/12/06/25",
     *             "sendtime": "2020-12-21 12:07:09",
     *             "flag": 0
     *         },
     *         {
     *             "userId": "0",
     *             "friendId": "101",
     *             "message": "2020/12/21/12/06/25",
     *             "sendtime": "2020-12-21 13:35:03",
     *             "flag": 0
     *         }
     *     ],
     *     "code": 1
     * }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 无好友印象-示例：
     *    HTTP/1.1 200 OK
     *    {
     *     "message": "暂无印象",
     *     "data": null,
     *     "code": 0
     *    }
     */
    @CrossOrigin
    @GetMapping("/ShowImage")
    public  JsonResult ShowImage(@RequestParam("userId") String userId)
    {

        String receiveId = userId;
        ArrayList<Image> images = messageService.ShowImage(receiveId);
        if(images.size()!=0)
        {
            return JsonResult.success(images);
        }
        else
            return JsonResult.fail("暂无印象");
    }
    /**
     * @api {Get} ChangMark 更改印象观看权限
     * @apiDescription  更改印象观看权限接口
     * @apiGroup 用户
     * @apiName 更改印象观看权限
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户ID
     * @apiParam {String} note 权限标记
     *
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 有好友印象-示例:
     *     HTTP/1.1 200 OK
     *     {
     *     "message": "success",
     *     "data": "更改印象权限成功",
     *     "code": 1
     * }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 无好友印象-示例：
     *    HTTP/1.1 200 OK
     *    {
     *     "message": "更改印象权限失败",
     *     "data": null,
     *     "code": 0
     * }
     */
    @CrossOrigin
    @GetMapping("/ChangMark")
    public JsonResult ChangMark(@RequestParam("userId") String userId,@RequestParam("note") String note)
    {
        boolean date = messageService.ChangMark(userId,note);
        if(date)
            return JsonResult.success("更改好印象权限成功");
        else
            return JsonResult.fail("更改好友印象权限失败");
    }
    /**
     * @api {Get} ShowFriendImage 显示好友收到的印象
     * @apiDescription  显示好友收到的印象接口
     * @apiGroup 消息
     * @apiName 显示好友收到的印象
     * @apiversion 0.1.0
     *
     * @apiParam {String} friendId 好友ID
     * @apiSuccess {int} status 响应状态码
     * @apiSuccess {String} message 响应描述
     * @apiSuccess {String} data 返回相关信息，成功的时候才存在
     *
     * @apiSuccessExample {json} 有印象权限-示例1:
     *     HTTP/1.1 200 OK
     *     {
     *     "message": "success",
     *     "data": [
     *         {
     *             "userId": "0",
     *             "friendId": "4",
     *             "message": "123",
     *             "sendtime": "2020-12-21 11:50:03",
     *             "flag": 0
     *         }
     *     ],
     *     "code": 1
     * }
     * @apiSuccessExample {json} 有印象权限-示例2:
     * HTTP/1.1 200 OK
     * {
     *     "message": "暂无印象",
     *     "data": null,
     *     "code": 0
     * }
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     * @apiErrorExample {json} 无印象权限-示例：
     * HTTP/1.1 200 OK
     * {
     *     "message": "未获得观看好友印象权限",
     *     "data": null,
     *     "code": 0
     * }
     */
    @CrossOrigin
    @GetMapping("/ShowFriendImage")
    public  JsonResult ShowFriendImage(@RequestParam("friendId") String friendId)
    {
        Integer integer = messageService.GetImageMark(friendId);
        if(integer == 0){
            String receiveId = friendId;
            ArrayList<Image> images = messageService.ShowImage(receiveId);
            if(images.size()!=0)
            {
                return JsonResult.success(images);
            }
            else
                return JsonResult.fail("暂无印象");
        }
       else
           return JsonResult.fail("未获得观看好友印象权限");
    }

}


