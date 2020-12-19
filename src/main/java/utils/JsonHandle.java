package utils;

import net.sf.json.JSONObject;



/**
 * 处理客户端发来的json数据
 */
public class JsonHandle {
    private final JSONObject jsonObject;

    public JsonHandle(String clientData) {
        this.jsonObject = JSONObject.fromObject(clientData);
    }

    /**
     * 获取消息接收方
     *
     * @return
     */
    public String getReceiver() {
        return jsonObject.getString("receiver");
    }

    /**
     * 获取发送消息内容
     *
     * @return
     */
    public String getMessage() {
        return jsonObject.getString("message");
    }
}
