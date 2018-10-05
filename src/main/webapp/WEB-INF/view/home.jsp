<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Home Page</title>
	</head>
	<body>
		<h2>Home Page</h2>
		<hr>
		<p>
			Welcome to the home page!
		</p>
		<hr>
			User: <security:authentication property="principal.username"/>
			<br>
			Role(s): <security:authentication property="principal.authorities"/>
		<hr>
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout"/>
		</form:form>
		
	</body>
</html>