<%--
  Created by IntelliJ IDEA.
  User: KUN
  Date: 2021/5/10
  Time: 14:08

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <!--通过超链接（默认GET请求）显示所有员工信息
    过程：
    1、请求
    2、前端控制器：DispatcherServlet接收请求并转发到Handler
    3、Handler对应的方法中查询数据 -> 保存到域对象 -> 转发到jsp页面展示
    -->
    <a href="emps">List All Emps</a>
  </body>
</html>
