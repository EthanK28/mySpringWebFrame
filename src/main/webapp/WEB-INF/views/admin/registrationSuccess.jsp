<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Success</title>
</head>
<body>
    <div align="center">
        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>Registration Succeeded!</h2></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <h3>Thank you for registering! Here's the review of your details:</h3>
                </td>
            </tr>
            <tr>
                <td>User ID:</td>
                <td>${userForm.id}</td>
            </tr>
            <tr>
                <td>User Name:</td>
                <td>${userForm.name}</td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td>${userForm.email}</td>
            </tr>
            <tr>
                <td>Profession:</td>
                <td>${userForm.profession}</td>
            </tr>
        </table>
    </div>
</body>
</html>