<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户详情</title>
</head>
<body>
	<center>
		<table border="1">
			<tr>
				<td>标签</td>
				<td>值</td>
			</tr>
			<tr>
				<td>用户编号:</td>
				<td><c:out value="${user.id}"></c:out></td>
			</tr>
			<tr>
				<td>用户名称:</td>
				<td><c:out value="${user.userName}"></c:out></td>
			</tr>
			<tr>
				<td>用户备注:</td>
				<td><c:out value="${user.note}"></c:out></td>
			</tr>
		</table>
	</center>
</body>
</html>