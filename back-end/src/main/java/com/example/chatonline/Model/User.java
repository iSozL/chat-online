package com.example.chatonline.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;

@Data
public class User {
    private  String userId;
    private  String password;
    private  String nickname;
    private  String sex;
    private  ArrayList<Group> groups;
    private int age;
    private String address;
    private String phone;
    private String signature;
    private Integer imageMark;


}
