<%--
  User: KUN
  Date: 2021/4/27
  Time: 14:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <!--
      相对路径：不以/开头的路径，相对于当前路径发出请求
      href="hello" 表示访问路径 http://localhost:8080/springMVCPractice_HelloWorld/hello
      绝对路径：以 / 开头的路径，直接在http:// localhost:8080后面拼接
      href="/hello"表示访问路径 http://localhost:8080/hello
    -->
    <a href="hello">Hello SpringMVC</a>
    <br>
    <br>
    <a href="springmvc/testRequestMapping">Test RequestMapping</a>
    <br>
    <br>
    <a href="testRequestMappingMethod">Test RequestMapping Method</a>
    <br>
    <br>
    <a href="testRequestMappingParamsAndHeaders?username=tom&age=22">Test RequestMapping Params Headers</a>
    <br>
    <br>
    <a href="testPathVariable/Admin/1001">Test testPathVariable</a>
    <br>
    <br>
    <!--模拟发送post请求测试RequestMappingMethod-->
    <form action = "testRequestMappingMethod" method="post">
      <input type="submit" value="POST"/>
    </form>
    <hr color="blue" width="60%" align="left" style="height: 5px"/>
    <br>
    <br>
    <br>
    <br>




    <!--Rest POST 增加一个新的订单-->
    <form action = "order" method="post">
      <input type="submit" value="REST POST"/>
    </form>

    <br>
    <!--Rest POST 删除id为1001的订单-->
    <form action = "order/1001" method="post">
      <!--隐藏域-->
      <input type="hidden" name="_method" value="DELETE"/>
      <input type="submit" value="REST DELETE"/>
    </form>

    <br>
    <!--Rest POST 修改一个订单-->
    <form action = "order" method="post">
      <input type="hidden" name="_method" value="PUT"/>
      <input type="submit" value="REST PUT"/>
    </form>

    <br>
    <!--Rest Get 查询id为1001的订单-->
    <a href="order/1001">REST GET</a>
    <hr color="blue" width="60%" align="left" style="height: 5px"/>
    <br>
    <br>
    <br>
    <br>



    <!--测试标签：@RequestParam,获取请求参数-->
    <a href="testRequestParam?username=Tom&age=22">Test RequestParam</a>
    <br>
    <br>

    <!--测试标签@RequestHeader,获取请求头信息-->
    <a href="testRequestHeader">Test RequestHeader</a>
    <br>
    <br>

    <!--测试标签@CookieValue,获取cookie中的参数-->
    <a href="testCookieValue">Test CookieValue</a>
    <hr color="blue" width="60%" align="left" style="height: 5px"/>
    <br>
    <br>
    <br>
    <br>


    <!--模拟注册操作，测试POJO对象接收请求参数
        要求：1、请求参数名称和POJO属性名称一致；2、POJO对象必须提供set方法
    -->
  <form action="testPOJO" method="post">
    用户名称：<input type="text" name="username"/>
    <br>
    用户密码：<input type="password" name="password"/>
    <br>
    用户邮箱：<input type="text" name="email"/>
    <br>
    用户性别：男<input type="radio" name="gender" value="1"/>
             女<input type="radio" name="gender" value="0"/>
    <br>
    <!--支持级联-->
    用户省份：<input type="text" name="address.provice"/>
    用户城市：<input type="text" name="address.city"/>
    <br>
    <input type="submit" value="注册"/>
  </form>

    <hr color="blue" width="60%" align="left" style="height: 5px"/>
    <br>
    <br>
    <br>
    <br>


  <!--测试在SpringMVC中使用原生ServletAPI-->
  <a href="testServletAPI">Test Servlet API</a>




  </body>
</html>

<!--
  注意：超链接默认使用GET请求方式
  由于超链接默认使用GET请求，为了能够发DELETE请求，需要使用form表单提交POST请求，
  经过过滤器HiddenHttpMethodFilter，获取设定的paramValue参数，将POST请求转化为Delete请求
  同理发送PUT请求，
-->
