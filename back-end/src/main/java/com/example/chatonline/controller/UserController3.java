package com.example.chatonline.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController3 {
    /**
     * @api {post} groupMove 好友移动
     * @apiDescription  好友移动接口
     * @apiGroup 好友
     * @apiName 好友移动
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户Id
     * @apiParam {String} preGroupName 移动前分组名
     * @apiParam {String} postGroupName 移动后分组名
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
    @GetMapping("/groupMove")
    @CrossOrigin
    public static void groupMove(String[] args) {

    }

    /**
     * @api {post} groupfriends 好友列表
     * @apiDescription  好友列表接口
     * @apiGroup 好友
     * @apiName 好友列表
     * @apiversion 0.1.0
     *
     * @apiParam {String} userId 用户Id
     * @apiParam {String} groupName 分组名
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
     *       "data": 好友列表,
     *     }
     *
     * @apiError {int} status 响应状态码
     * @apiError {String} message 响应描述
     *
     * @apiErrorExample {json} 查询失败-示例：
     *      HTTP/1.1 200 OK
     *     {
     *       "code":0,
     *       "message": "未查询到该用户好友信息",
     *       "data":null,
     *     }
     */
    @GetMapping("/groupfriends")
    @CrossOrigin
    public static void groupfriends(String[] args) {

    }


}
