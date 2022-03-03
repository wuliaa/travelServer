package com.example.travelserver.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
     * 获取MD5加密
     */
    public static String getPwd(String pwd){
        try{
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] bs = digest.digest(pwd.getBytes());
            StringBuilder hexString = new StringBuilder();
            for(byte b :bs){
                int temp = b&255;
                if(temp<16&&temp>=0){
                    hexString.append("0").append(Integer.toHexString(temp));
                }else{
                    hexString.append(Integer.toHexString(temp));
                }
            }
            return hexString.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }
}
