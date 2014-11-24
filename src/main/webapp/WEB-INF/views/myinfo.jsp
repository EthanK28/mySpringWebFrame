<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Success</title>
</head>
<body>
<sec:authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')">
    <div align="center">
        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>My Information</h2></td>
            </tr>
            <tr>
                <td>User ID:</td>
                <td>${user.id}</td>
            </tr>
            <tr>
                <td>User Name:</td>
                <td>${user.name}</td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td>${user.email}</td>
            </tr>
        </table>
    </div>
</sec:authorize>

<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
You need to login 
<br/>
<a href="login">Log in</a>
</sec:authorize>
</body>
</html>