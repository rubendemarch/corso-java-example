<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<title><s:text name="label.pageTitle"/></title>
<sx:head/>
</head>

<body>
	<h2>
	<s:text name="label.title"/>
	</h2>
	<s:actionerror />
	<s:form action="register.action" method="post">
		<s:textfield name="userName" key="userName" size="20" required="true"/>
		<s:textfield name="name" key="name" size="20" required="true"/>
		<s:textfield name="surName" key="surName" size="20" required="true"/>
		<s:password name="password" key="password" size="20" required="true"/>
		<s:password name="password2" key="password2" size="20" required="true"/>
		<s:textfield name="email" key="email" size="20" required="true"/>
		<s:textfield name="phone" key="phone" size="20"/>
		<s:textfield name="mobilePhone" key="mobilePhone" size="20"/>
		<sx:datetimepicker name="birthDay" displayFormat="%{getText('dateFormat')}" toggleType="explode" label="%{getText('birthDay')}"/>
		<s:submit key="register" align="center" />
	</s:form>
</body>
</html>