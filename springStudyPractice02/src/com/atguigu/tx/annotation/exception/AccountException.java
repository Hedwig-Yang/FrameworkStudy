package com.atguigu.tx.annotation.exception;

/**
 * @Author:KUN
 * @Data:2021/4/23 10:03
 * @Description: 用户余额不足异常
 * @Version:1.0
 */
public class AccountException extends RuntimeException{
    //创建构造器

    public AccountException() {
    }

    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountException(Throwable cause) {
        super(cause);
    }

    public AccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
