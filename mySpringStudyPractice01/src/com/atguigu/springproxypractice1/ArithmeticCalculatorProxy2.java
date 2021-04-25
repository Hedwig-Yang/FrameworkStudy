package com.atguigu.springproxypractice1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Author:KUN
 * @Data:2021/4/20 14:07
 * @Description: 使用Proxy.getProxyClass(loader,interface)的方法创建代理类，相比获取代理对象的方法，
 *               更直观理解InvocationHandler对象是怎样传入代理对象
 * @Version:1.0
 */
public class ArithmeticCalculatorProxy2 {
    //目标对象
    private ArithmeticCaculator target;

    //构造器使用目标对象作为参数创建代理对象生产类对象
    public  ArithmeticCalculatorProxy2(ArithmeticCaculator target){
        this.target = target;
    }

    //获取代理对象
    public Object getProxy() throws Exception {
        //1、类加载器对象用于动态生成的代理类的加载
        ClassLoader loader = target.getClass().getClassLoader();
        //2、提供目标对象实现的所有接口，使代理对象能实现（拥有）目标对象实现的所有方法（功能）
        Class<?>[] interfaces = target.getClass().getInterfaces();
        //3、InvokationHandaler接口的对象，用于代理功能的实现,也就是创建代理方法（invoke方法）
        class MyInvocationHandler implements InvocationHandler {
            //proxy：代理对象，在invoke方法中一般不使用；method：正在被调用的方法；args：正在被调用的方法参数
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //获取方法名字
                String methodName = method.getName();
                //记录日志
                System.out.println("LoggingProxy2==> The method "+methodName+" begin with "+ Arrays.asList(args) );
                //将方法调用转回目标对象
                Object result = method.invoke(target, args);
                //记录日志
                System.out.println("LoggingProxy2==> The method "+methodName+" end with "+ result );
                return result;
            }
        }
        InvocationHandler h = new MyInvocationHandler();
        
        //创建代理类
        Class proxyClass = Proxy.getProxyClass(loader,interfaces);
        //无法直接newInstance来创建对象，因为没有无参构造，因此现需要获取构造器
        Constructor con = proxyClass.getDeclaredConstructor(InvocationHandler.class);
        //使用构造器创建代理对象
        Object proxy = con.newInstance(h);
        //返回代理对象
        return proxy;
    }
}
