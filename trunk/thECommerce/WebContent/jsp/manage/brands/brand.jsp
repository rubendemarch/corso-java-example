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
String action=(String)request.getAttribute(Common.ACTION);
%>
<body onload="msg('<%=msg%>')">
<jsp:include page="../../common/menu/headerMenu.jsp"/>
<br>
<jsp:include page="menu/menu.jsp"/>
<%
if(Common.ADD.equals(action)){
%>
<jsp:include page="insertBrand.jsp"/>
<%
}else if(Common.LIST.equals(action)){
%>
<jsp:include page="brandList.jsp"/>
<%
}
%>
</body>
</html>