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
    <!--使用REST框架实现CRUD-->
    <a href="emps">List All Emps</a>
    <br/>
    <br/>

    <!-- 在SpringMVC中处理JSON -->
    <a href="testJson">Test Json</a>
    <br/>
    <br/>

    <!-- 实现文件上传必须使用form表单，必须是post请求 -->
    <form action="upload" method="post" enctype="multipart/form-data">
      <br/>
        上传文件:<input type="file" name="uploadFile"/>
      <br/>
        文件描述:<input type="text" name="desc"/>
      <br/>
        <input type="submit" value="上传"/>
    </form>


    <!-- HttpMessageConverter_ResponseEntity完成下载操作 -->
    <a href="download">下载图片</a>
  </body>
</html>
