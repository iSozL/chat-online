package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 消息实体类
 */
public class Message {
    private String sender;//发送方
    private String receiver;//接收方

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyy-MM-dd HH:mm:ss")
    private Date time;//发送时间

    private String message;//消息内容
    private int status;//消息是否已读

    public Message(String sender, String receiver, Date time, String message, int status) {
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
        this.message = message;
        this.status = status;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public Date getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
