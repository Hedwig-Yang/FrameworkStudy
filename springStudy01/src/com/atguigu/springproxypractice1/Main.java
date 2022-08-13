package com.atguigu.springproxypractice1;


import org.junit.Test;

/**
 * @Author:KUN
 * @Data:2021/4/16 17:02
 * @Description: main
 * @Version:1.0
 */
public class Main {
    public static void main(String[] args) {
        //获取代理对象
        Object object = new ArithmeticCalculatorProxy(new ArithmeticCaculatorImpl()).getProxy();
        //回转具体对象，因为目标对象和代理兑现都实现了相同的ArithmeticCaculator接口
        ArithmeticCaculator proxy = (ArithmeticCaculator) object;
        //查看代理类名称
        System.out.println(proxy.getClass().getName());
        int result = proxy.add(1, 1);
        System.out.println("Main Result : " + result);
    }

    @Test
    public void test() throws Exception {
        //获取代理对象
        Object object = new ArithmeticCalculatorProxy2(new ArithmeticCaculatorImpl()).getProxy();
        //回转具体对象，因为目标对象和代理兑现都实现了相同的ArithmeticCaculator接口
        ArithmeticCaculator proxy = (ArithmeticCaculator) object;
        //查看代理类名称
        System.out.println(proxy.getClass().getName());
        int result = proxy.add(1, 1);
        System.out.println("Main Result : " + result);
    }
}
