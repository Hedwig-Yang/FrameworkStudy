<%--
  Created by IntelliJ IDEA.
  User: KUN
  Date: 2021/5/10
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!--导入JSTL标签库-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
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
                    <a href="#">Edit</a>
                    &nbsp;&nbsp;
                    <a href="#">Delete</a>
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
</body>
</html>
