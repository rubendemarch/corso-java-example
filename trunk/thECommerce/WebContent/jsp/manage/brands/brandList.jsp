<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
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
List<HashMap<String,Object>>brandList=
(List<HashMap<String,Object>>)
	request.getAttribute("brandList");
%>
<body onload="msg('<%=msg%>')">
<jsp:include page="../../common/menu/headerMenu.jsp"/>
<%
for(HashMap<String,Object>brand:brandList){
%>
<%=(String)brand.get("URL")%>&nbsp;
<%=(String)brand.get("LOGO_URL")%>&nbsp;
<%=(String)brand.get("NAME")%>&nbsp;
<br>
<%
}
%>
</body>
</html>