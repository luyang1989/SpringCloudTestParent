package com.example.base.sys.config;

public enum HttpResultCodeEnum {

    SUCCESS("成功", 200), ERROR("系统内部错误", -1),PARAM("参数错误", -2),UNLISTED("未登录",-3);

    private String name;
    private int value;

    private HttpResultCodeEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}