package com.atguigu.tx.annotation.exception;

/**
 * @Author:KUN
 * @Data:2021/4/23 09:41
 * @Description: 库存不足异常
 * @Version:1.0
 */
public class BookStockException extends RuntimeException{
    //创建构造器

    public BookStockException() {
    }

    public BookStockException(String message) {
        super(message);
    }

    public BookStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookStockException(Throwable cause) {
        super(cause);
    }

    public BookStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
