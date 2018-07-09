package com.example.test.utils;


public class GovernCenterException extends RuntimeException {


    private Integer code;

    public GovernCenterException(EnumsUtils enumsUtils) {
        super(enumsUtils.getMsg());
        this.code = enumsUtils.getCode();
    }

    public GovernCenterException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }


}
