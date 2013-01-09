<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title><s:text name="label.pageTitle"/></title>
<s:head/>
</head>

<body>
	<h2>
	<s:text name="label.title"/>
	</h2>
	<s:actionerror />
	<s:form action="login.action" method="post">
		<s:textfield name="userName" key="userName" size="20" required="true"/>
		<s:password name="password" key="password" size="20" required="true"/>
		<s:submit key="label.login" align="center" />
	</s:form>
	<s:a href="Register.jsp"><s:text name="register"/></s:a>
</body>
</html>