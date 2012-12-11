<%@page import="it.eCommerce.util.constants.Common"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- include il file props nella cartella commons con la dichiarazione del resource bundle e
locale -->
<%@include file="../../common/props.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=rb.getString("manage.brand.page.title")%></title>
<script type="text/javascript" src="js/common/message.js"></script>
</head>
<%
String msg = (String)request.getAttribute("msg");
%>
<body onload="msg('<%=msg%>')">
<form action="./ManageBrands" method="post">
<input 
type="hidden" 
name="<%=Common.ACTION%>" 
value="inserisci">
<label><%=rb.getString("manage.brand.page.labelNome")%></label>
<input type="text" value="" name="name" maxlength="100" size="50">
<input type="submit" value="<%=rb.getString("common.save")%><%=rb.getString("manage.brand.page.save")%>"> 
</form>
</body>
</html>