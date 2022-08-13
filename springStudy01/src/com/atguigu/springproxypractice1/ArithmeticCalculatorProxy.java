package com.atguigu.springproxypractice1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Author:KUN
 * @Data:2021/4/16 14:23
 * @Description: 这个类用于获取计算器的动态代理类，并不是动态代理类本身
 * @Version:1.0
 */

public class ArithmeticCalculatorProxy {

    //目标对象
    private ArithmeticCaculator target;

    //构造器使用目标对象作为参数创建代理对象生产类对象
    public ArithmeticCalculatorProxy(ArithmeticCaculator target) {
        this.target = target;
    }

    //获取代理对象
    public Object getProxy() {
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
                System.out.println("LoggingProxy==> The method " + methodName + " begin with " + Arrays.asList(args));
                //将方法调用转回目标对象
                Object result = method.invoke(target, args);
                //记录日志
                System.out.println("LoggingProxy==> The method " + methodName + " end with " + result);
                return result;
            }
        }
        InvocationHandler h = new MyInvocationHandler();
        //创建代理对象
        Object proxy = Proxy.newProxyInstance(loader, interfaces, h);
        //返回代理对象
        return proxy;
    }
}

/**
 * @description:模拟底层生成的代理类(仅供理解，不参与程序运行),代理对象一定继承了Proxy对象, 同时实现了目标对象的接口, 通过Proxy.newProxyInstance(loader, interfaces, h);方法生成代理对象，基本如下
 * @author: KUN
 * @date: 2021/4/19 10:41
 */
class $Proxy0 extends Proxy implements ArithmeticCaculator {

    //只有有参构造，因此必须提供InvocationHandler类的对象才能创建代理类对象
    protected $Proxy0(InvocationHandler h) {
        //创建代理对象时传入的InvocationHandler对象，被代理对象发送给父类
        super(h);
    }

    @Override
    public int add(int i, int j) {
        Object[] args = {i, j};
        int result = 0;
        try {
            //表面上调用代理类的add方法，实际上使用的时传入父类的InvocationHandler对象h的invoke方法
            //传入参数：1、代理对象 2、接口的当前方法对象 3、参数数组
            Class<?>[] interfaces = this.getClass().getInterfaces();
            Method interfaceAdd = interfaces[0].getMethod("add", int.class, int.class);
            result = (int) super.h.invoke(this, interfaceAdd, args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    @Override
    public int sub(int i, int j) {
        //同方法add
        return 0;
    }

    @Override
    public int mul(int i, int j) {
        //同方法add
        return 0;
    }

    @Override
    public int dvi(int i, int j) {
        //同方法add
        return 0;
    }
}

/*总结：动态代理的实现
            通过Proxy.newProxyInstance(loader,interfaces,h)方法获取代理类对象，传入类加载器（用
        于加载类）、原始类实现的接口（用于获取原始类中需要代理的方法）以及InvocationHandler的实现类
        对象h（用于实现代理方法）。
            创建的代理类，通过唯一的有参构造，将InvocationHandler的实现类对象h传递给父类Proxy，当
        调用代理类中的方法，比如add方法，本质是调用父类Proxy的InvocationHandler的实现类对象h的invoke
        方法：(int)super.h.invoke(this,interfaceAdd,args)，通过传入参数：this（代理类对象，一般
        不用）、interfaceAdd（接口方法对象）、args（方法参数）实现代理方法。
            代理方法的具体实现可以看，创建代理类前传入的InvocationHandler的实现类MyInvocationHandler
        中重写的invoke方法，通过method.invoke(target, args);实现需要被代理的原始方法，通过这个方
        法的上下，添加的方法，实现特殊的代理功能。

  过程：
        1、创建代理对象 --> 2、调用代理方法 --> 3、获取父类Proxy对象的属性InvocationHandler的实现类对象h
        --> 4、调用h的invoke方法 --> 5、实现特殊的代理方法（比如日志） --> 6、反射目标对象的实现原始方法

  动态代理的种类：
      1、基于接口做代理
         代理对象和目标对象实现相同接口，为所有接口内的方法都做代理。
      2、基于继承做代理
         目标对象作为子类，代理对象作为父类，为所有能从目标类继承的方法做代理。
* */



