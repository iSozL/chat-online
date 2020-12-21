package com.example.chatonline.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Image {
    private  String userId;
    private  String friendId;
    private  String message;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date sendtime;
    private int flag;
}
