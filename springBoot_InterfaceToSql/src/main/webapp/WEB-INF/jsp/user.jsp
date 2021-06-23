<%--
  Created by IntelliJ IDEA.
  User: KUN
  Date: 2021/6/7
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>username</td>
        <td>birthday</td>
        <td>sex</td>
        <td>address</td>
    </tr>
    <c:forEach items="${users}" var="u" varStatus="st">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.birthday}</td>
            <td>${u.sex}</td>
            <td>${u.address}</td>
        </tr>
    </c:forEach>
</table>

