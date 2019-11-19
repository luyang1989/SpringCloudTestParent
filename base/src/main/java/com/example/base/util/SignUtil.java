package com.example.base.util;

import java.security.MessageDigest;

public class SignUtil {

    public static String getMD5(String message) throws Exception {
        if (message == null)
            return null;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(message.getBytes("utf-8"));
        return byte2hexString(b);
    }

    public static final String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xFF) < 16) {
                buf.append("0");
            }
            buf.append(Long.toString(bytes[i] & 0xFF, 16));
        }
        return buf.toString();
    }
}
