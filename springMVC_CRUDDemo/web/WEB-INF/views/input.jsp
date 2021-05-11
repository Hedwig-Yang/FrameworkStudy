<%--
  Created by IntelliJ IDEA.
  User: KUN
  Date: 2021/5/10
  Time: 17:47
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 导入Springmvc表单标签库 -->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!--springMVC的表单标签
            1、可以快速地开发表单
            2、可以更加方便的回显数据
        Springmvc表单标签遇到的问题：
            Neither BindingResult nor plain target object
            for bean name 'command' available as request attribute
        问题原因：Springmvc的表单标签默认必须要进行数据的回显，会使用"command"这个key到request中
            查询回显数据，如果找不到则抛出异常。
         解决方法：让springmvc可以通过"command"从request中找到要回显的数据。
            还可以通过modleAttribute来指定一个key替换默认的command
    -->
    <form:form action="emp" method="post" modelAttribute="employee">
    <%-- HTML标签写法：<input type="text" name="lastName"/>
    在springmvc表单标签中path就相当于HTML中input标签的name属性
    --%>
        lastName:<form:input path="lastName"/>
        <br/>
        Email：<form:input path="email"/>
        <br/>
        <!--radiobuttons 可以根据数据来生成单选框-->
        Gender:<form:radiobuttons path="gender" items="${genders}"/>
        <br/>
        <%--HTML下拉标签写法
        <select name="department.id">
            <option value="1">开发部</option>
            <option value="2">测试部</option>
        </select>--%>
        deptName:<form:select path="department.id" items="${depts}" itemLabel="departmentName" itemValue="id"></form:select>
        <br/>
        <input type="submit" value="ADD"/>
    </form:form>
</body>
</html>
