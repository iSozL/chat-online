package com.example.chatonline.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class Message {
    private String sendid;
    private String reciveid;
    @DateTimeFormat(pattern="yyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date sendtime;
    private String messagetext;
    private String texttype;

}
