package com.example.word_contentprovider;

import java.util.UUID;

public class GUID {
    public static String getGUID(){
        //创建GUID对象
        UUID uuid = UUID.randomUUID();
        //得到对象产生的ID
        String a = uuid.toString();
        a = a.toUpperCase(); //替换为大写
        a = a.replaceAll("-","");
        return a;
    }
}
