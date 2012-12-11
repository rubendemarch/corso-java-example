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
<!-- appena apre la pagina mette un attributo della servlet di manageBrand 
dal metodo process -->
<body onload="msg('<%=msg%>')">
<jsp:include page="../../common/menu/headerMenu.jsp"></jsp:include>
<form action="./ManageBrands" method="post">
<input type="hidden" name="<%=Common.ACTION%>" value="Inserisci">

<!-- Nel file baseLabel.properties ci sono i nomi delle label -->

<label><%=rb.getString("manage.brand.page.labelNome")%></label>
<input type="text" value="" name="name" maxlength="100" size="50">
<br>
<label><%=rb.getString("manage.brand.page.labelUrl")%></label>
<input type="text" value="" name="url" maxlength="150" size="60">
<br>
<label><%=rb.getString("manage.brand.page.labelLogo_url")%></label>
<input type="text" value="" name="logo_url" maxlength="150" size="60">
<br>
<input type="submit" value="<%=rb.getString("common.save")%><%=rb.getString("manage.brand.page.save")%>" align="middle">

 
</form>
</body>
</html>