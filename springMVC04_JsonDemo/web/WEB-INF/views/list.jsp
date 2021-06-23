<%--
  Created by IntelliJ IDEA.
  User: KUN
  Date: 2021/5/10
  Time: 15:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!--导入JSTL标签库-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>

    <!--引入JQuery
        Springmvc处理静态资源的问题
            静态资源：.js .css .html .txt .png .jpg .avi等
            动态资源：具体的一个请求、或者请求的jsp文件（需要通过运算资源）
            因为DispatcherServlet的<url-pattern>配置的是/，会匹配到除jsp请求以外的所有请求，
            由于.js文件是一个静态资源请求，交给DispatcherServlet后就会出现no mapping found问题。
        解决问题:
            1、修改<url-pattern>为后缀配置。但是不建议这么做，对REST支持不好。因为
               一个优秀的REST不希望请求URL带有任何后缀
            2、在springmvc.xml中加上一个配置：mvc:default-servlet-handler
               再补上一个 mvc:annotation-driven 标签防止@RequireMapping标签失效
    -->
    <script type="text/javascript" src="scripts/jquery-1.7.2.min.js"></script>

    <script type="text/javascript">
        //页面加载完毕直接执行
        $(function(){
            //给删除超链接<a>动态绑定事件
            $(".del").click(function(){
                //确认是否要删除
                var flag = window.confirm("是否要删除");
                if(!flag){
                    return false;
                }
                //获取超链接的href的值 (this表示当前正在点击的dom对象)
                var href = $(this).attr("href");
                //将href的值设置到表单的action上,并提交
                $("form").attr("action",href).submit();
                //取消超链<a>接默认行为
                return false;
            });
        });
    </script>
</head>
<body>

    <form action="" method="post">
        <input type="hidden" name="_method" value="DELETE">
    </form>

    <h1 align="center">员工信息列表</h1>
    <table border="1px" align="center" width="70%" cellspacing="opx">
        <tr>
            <!--设置列名-->
            <th>Id</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Gender</th>
            <th>DeptName</th>
            <th>Operation</th>
        </tr>
        <!--通过迭代数据生成表格-->
        <c:forEach items="${emps}" var="emp">
            <tr align="center">
                <td>${emp.id}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.gender==0?"女":"男"}</td>
                <td>${emp.department.departmentName}</td>
                <td>
                    <a href="emp/${emp.id}">Edit</a>
                    <!-- 修改操作的步骤：查询内容 -> 修改数据 -> 提交数据 -> 展示结果 -->
                    <a class="del" href="emp/${emp.id}">Delete</a>
                    <!--问题：
                            超链接默认发送Get请求，为了实现删除操作需要通过REST框架实现Delete请求，前提是能发送Post请求
                        解决思路：
                            给删除超链接绑定事件，当出发了点击事件，可以在事件处理函数中获取到要发送的请求URL,
                            再将获取到的请求URL设置到莫格表单的action属性上，再将表单提交，最后将,<a>的默认行为
                            取消掉。（默认行为：只要点击了链接，就一定会发送href请求）
                    -->
                </td>
            </tr>
        </c:forEach>
    </table>

    <!--
        由于添加页面需要用到部门数据，因此需要到Handler中线查询部门数据，再转发到添加页面，
        不能只进行简单的页面跳转
    -->
    <h2 align="center" >
        <a href="emp">Add New Emp</a>
    </h2>

    <br>
    <hr color="blue" width="60%" align="left" style="height: 5px"/>
    <br>



</body>
</html>
