<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	All Users  
</h1>

<div>
<table border="1">
<tr>
<th>ID</th><th>Name</th><th>Email</th><th>Level</th><th>#Login</th><th>#Recommend</th>
</tr>

<c:forEach var="user" items="${users}">
<tr>
<td>${user.id}</td><td>${user.name}</td><td>${user.email}</td><td>${user.level}</td><td>${user.login}</td><td>${user.recommend}</td>
</tr> 
</c:forEach>
</table> 
</div>

</body>
</html>
