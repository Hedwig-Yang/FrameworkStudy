<%--
  Created by IntelliJ IDEA.
  User: KUN
  Date: 2021/4/28
  Time: 14:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Success Page</h1>
    <!--使用EL表达式获取模型数据
        默认依次从四个域对象（pageScope、requestScope、sessionScope、applicationScope）中查找指定的模型数据
        EL表达式支持级联的方式从指定的域对象中获取数据
    -->
    username:${requestScope.username}
    <br>
    <br>
    password:${requestScope.password}
    <br>
    <br>
    loginMsg:${requestScope.loginMsg}
</body>
</html>
