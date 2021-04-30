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


  </body>
</html>

<!--
  注意：超链接默认使用GET请求方式
-->
