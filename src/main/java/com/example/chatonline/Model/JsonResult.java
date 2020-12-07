package com.example.chatonline.Model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component("jsonResult")
public class JsonResult extends HashMap<String, Object> implements Serializable {
    public static final long serialVersionUID = 1L;

    public static final int SUCCESS = 1;
    public static final int FAIL = 0;
    public static final int ERROR = -1;
    public static final int LOGOUT = 1001;

    public JsonResult()
    {

    }

    public JsonResult(int code, String msg, Object data) {
        super(3);  //继承自Map，设置初始容量
        this.put("status", code); //状态码，code=1表示成功
        this.put("msg", msg);   //提示消息
        this.put("data", data); //数据体
    }

    //一般返回code、msg和data这三个即可
    //添加额外的返回值
    public JsonResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    //快速返回请求成功
    public static JsonResult success(Object data) {
        return new JsonResult(SUCCESS, "ok", data);
    }

    //快速返回请求失败
    public static JsonResult fail(String msg) {
        return new JsonResult(FAIL, msg, null);
    }

    public static JsonResult error(String msg, Object data) {
        return new JsonResult(ERROR, msg, data);
    }

    public static JsonResult logout() {
        return new JsonResult(LOGOUT, "未登录", null);
    }

    //快速生成一个Map键值对
    public static Map<String, Object> fastMap(String key, Object value) {
        Map<String, Object> data = new HashMap<>(1);
        data.put(key, value);
        return data;
    }
}
