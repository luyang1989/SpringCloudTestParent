package com.example.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 发短信工具
 */
public class SendMsg {


    public static final String MSG_URL = "";
    public static final String APPSCRETKEY = "";
    public static final String APPACCOUNT = "";
    public static final String ELECT_TYPE  = "";
    public static final String ELECT_SOURCE  = "";


    /**
     * 发送短信
     * 参考fesco接口文档
     * @param mobile
     * @param content
     * @param
     * @return
     */
    public static boolean send(String mobile, String content){
        boolean flag = false;
        String appscret = APPSCRETKEY;
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("appaccount", APPACCOUNT);
        map.put("IsNeedTry", "1");
        map.put("PhoneNum", mobile);
        map.put("PhoneContent", content);
        map.put("ElectType", ELECT_TYPE);
        map.put("ElectSource", ELECT_SOURCE);
        map.put("IsNowSend", "1");
//		map.put("SendTime", date);
        map.put("noncestr", UUID.randomUUID().toString());
        map.put("timestamp", System.currentTimeMillis() / 1000);
        String[] array = map.keySet().toArray(new String[] {});
        Arrays.sort(array,String.CASE_INSENSITIVE_ORDER);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(map.get(array[i]));
        }
        String sign = "";
        try {
            sign = SignUtil.getMD5(appscret+sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.put("sign", sign);
        json.put("appaccount", map.get("appaccount"));
        json.put("noncestr", map.get("noncestr"));
        json.put("timestamp", map.get("timestamp"));
        json.put("jsonParam", "{'IsNeedTry':1,'PhoneNum':'"+mobile+"','PhoneContent':'"+content+"','ElectType':'"+ELECT_TYPE+"','ElectSource':'"+ELECT_SOURCE+"','IsNowSend':1}");
        String restlt = HttpsUtils.sendPostJson(MSG_URL, json.toString(), "UTF-8");
        System.out.println("验证码返回结果："+restlt);
        if(restlt != null){
            JSONObject resultMap = JSON.parseObject(restlt);
            String result = resultMap.get("Success").toString();
            if(result.equals("true")){
                flag = true;
            }
        }
        return flag;
    }
}
