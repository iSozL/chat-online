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
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthday;
    private String address;
    private String phone;


}
