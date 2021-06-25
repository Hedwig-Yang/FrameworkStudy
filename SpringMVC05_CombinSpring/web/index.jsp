<%--
  Created by IntelliJ IDEA.
  User: KUN
  Date: 2021/5/26
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <!-- 测试IOC容器和SpringMVC容器分离 -->
  <a href="HelloServlet">Test HelloServlet</a>
  <br/>
  <br/>

  <!-- 测试MVC容器中的Controller跨容器，从IOC容器中获取Bean对象 -->
  <a href="hello">Test UserHandler</a>
  </body>
</html>
