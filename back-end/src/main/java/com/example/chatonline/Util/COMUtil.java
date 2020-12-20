package com.example.chatonline.Util;

public class COMUtil {
    public static int initUserId()
    {
        int rs;
        double Max= 1e8;
        double Min =1e7;
        rs = (int)((int)(Math.random()*Max)%(Max-Min+1)+Min);
        return rs;
    }

}
