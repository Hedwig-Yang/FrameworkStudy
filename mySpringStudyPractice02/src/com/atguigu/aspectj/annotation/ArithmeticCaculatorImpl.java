package com.atguigu.aspectj.annotation;


import org.springframework.stereotype.Component;

/**
 * @Author:KUN
 * @Data:2021/4/15 18:00
 * @Description: 计算器实现类
 * @Version:1.0
 */

@Component
public class ArithmeticCaculatorImpl implements ArithmeticCaculator {
    @Override
    public int add(int i, int j) {

        return i+j;
    }

    @Override
    public int sub(int i, int j) {
        return i-j;
    }

    @Override
    public int mul(int i, int j) {
        return i*j;
    }

    @Override
    public int dvi(int i, int j) {
        return i/j;
    }
}
