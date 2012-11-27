<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
<th>Id Disponibili</th>
<th>ora in quel posto</th>
</tr>
<%
SimpleDateFormat sdf =
	new SimpleDateFormat(
		"EEEEEEEEE d MMMMMMMMM YYYY H:m.s SSS",
		Locale.ITALIAN);
for(String id: TimeZone.getAvailableIDs()){
%>
<tr>
<th><%=id%></th>
<th>
<%=sdf.format(
		Calendar.getInstance(
			TimeZone.getTimeZone(id)
		).getTime())%></th>
</tr>
<%
}
%>
</table>
</body>
</html>