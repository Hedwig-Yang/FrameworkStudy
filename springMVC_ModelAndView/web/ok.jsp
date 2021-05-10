<%--
  Created by IntelliJ IDEA.
  User: KUN
  Date: 2021/5/10
  Time: 10:03
  测试redirect: 由于/WEB-INF/views/下的资源只能通过内部转发进行访问，不对浏览器开放，
    因此重定向时，需要重新设定访问的资源：在WEB目录下创建ok.jsp
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>OK Page.</h1>
</body>
</html>
