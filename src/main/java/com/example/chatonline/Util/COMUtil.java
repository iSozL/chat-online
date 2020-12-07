package com.example.chatonline.Util;

public class COMUtil {
    public static int initUserId()
    {
        int rs;
        rs = (int)(Math.random()*1e7);
        return rs;
    }

}
