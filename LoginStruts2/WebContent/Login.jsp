<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title><s:text name="label.pageTitle"/></title>
</head>

<body>
	<h2>
	<s:text name="label.title"/>
	</h2>
	<s:actionerror />
	<s:form action="login.action" method="post">
		<s:textfield name="userName" key="userName" size="20" />
		<s:password name="password" key="password" size="20" />
		<s:submit key="label.login" align="center" />
	</s:form>
</body>
</html>