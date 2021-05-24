<%--
  Created by IntelliJ IDEA.
  User: KUN
  Date: 2021/5/10
  Time: 17:47
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 导入Springmvc表单标签库 -->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 导入JSTL标签库 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!--springMVC的表单标签
            1、可以快速地开发表单
            2、可以更加方便的回显数据
        Springmvc表单标签遇到的问题 1：
            Neither BindingResult nor plain target object
            for bean name 'command' available as request attribute
        问题原因：Springmvc的表单标签默认必须要进行数据的回显，会使用"command"这个key到request中
            查询回显数据，如果找不到则抛出异常。
         解决方法：让springmvc可以通过"command"从request中找到要回显的数据。
            还可以通过modleAttribute来指定一个key替换默认的command
    -->
    <%--<form:form action="emp" method="post" modelAttribute="employee">--%>
    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
    <%-- HTML标签写法：<input type="text" name="lastName"/>
    在springmvc表单标签中path就相当于HTML中input标签的name属性
    --%>

        <!-- 判断是添加操作还是修改操作：
            根据回显Employee对象的id值来判断：如果有id就是修改，如果没有id就是添加
        -->
        <c:if test="${!empty employee.id}" var="flag">
            <!--修改操作-->
            <form:hidden path="id"/>
            <!--隐藏put请求-->
            <%--<form:hidden path="_method" value="PUT"/>
            此处如果使用表单标签就一定会回显数据，也就是会拿_method作为key去会先数据中找，找不到就会报错因此使用原始的隐藏域
            --%>
            <input type="hidden" name="_method" value="PUT"/>
        </c:if>

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
        deptName:<form:select path="department.id" items="${depts}" itemLabel="departmentName" itemValue="id"/>
        <br/>
        <c:if test="${flag}">
            <input type="submit" value="Edit"/>
        </c:if>
        <c:if test="${!flag}">
            <input type="submit" value="Add"/>
        </c:if>

    </form:form>
</body>

</html>

<!--
出现问题 2：
    点击input页面点击Edit提交后，显示Request method 'PUT' not supported
原因：http://localhost:8080/springMVC_CRUDDemo/emp/emp，在list页面点击Edit后请求URL：http://localhost:8080/springMVC_CRUDDemo/emp/1001
    因此当前路径为：http://localhost:8080/springMVC_CRUDDemo/emp/，在点击Edit提交修改结果时，表单请求连接action="emp"，由于相对路径为emp，
    即相对当前路径发送url为emp的请求，所以总的请求URL等于http://localhost:8080/springMVC_CRUDDemo/emp/emp，在后台找不到对应处理方式，因此报错。
解决方法：
    使用绝对路径：action="springMVC_CRUDDemo/emp",但是为了避免硬编码，应该动态的获取项目路径：action="${pageContext.request.contextPath}/emp"
-->
