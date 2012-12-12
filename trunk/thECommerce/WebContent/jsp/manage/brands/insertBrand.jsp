<%@page import="it.ecommerce.util.constants.Common"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../common/props.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=rb.getString("manage.brand.page.title")%></title>
<script type="text/javascript" src="js/common/message.js"></script>
<script type="text/javascript" src="js/common/radio.js"></script>
<script type="text/javascript" src="js/common/button.js"></script>
</head>
<%
String msg = (String)request.getAttribute("msg");
%>
<body onload="msg('<%=msg%>')">
<jsp:include page="../../common/menu/headerMenu.jsp"/>
<form action="./ManageBrands" method="post" enctype="multipart/form-data">
<input 
type="hidden" 
name="<%=Common.ACTION%>" 
value="inserisci">
<input type="hidden" name="ext" value="" id="ext">
<label><%=rb.getString("manage.brand.page.labelNome")%></label>
<input type="text" value="" name="name" maxlength="100" size="20">
<br>
<label><%=rb.getString("manage.brand.page.labelUrl")%></label>
<input type="text" value="" name="url" maxlength="150" size="30">
<br>
<input
	type="radio"
	name="radioLogoUrl"
	checked="checked"
	onchange="manageRadio('logoUrl','logoImg')"
	value="url">
<label><%=rb.getString("manage.brand.page.labelLogoUrl")%></label>
<input type="text" value="" name="logoUrl" maxlength="150" size="30" id="logoUrl">
<br>
<input
	type="radio"
	name="radioLogoUrl"
	onchange="manageRadio('logoImg','logoUrl')"
	value="image">
<input type="file" name="logoImg" id="logoImg" accept="image/*" disabled="disabled">
<br>
<input
type="submit"
value="<%=rb.getString("common.save")%><%=rb.getString("manage.brand.page.save")%>"
onmouseup="copyValue('logoImg','ext')"> 
</form>
</body>
</html>