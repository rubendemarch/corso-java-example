<%@page import="it.alfasoft.corso.java.util.constants.Request"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
ResourceBundle rb=
	(ResourceBundle)request.getAttribute(Request.ResourceBundle);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=rb.getString("title")%></title>
</head>
<body>

<jsp:include page="commons/include/comboLanguage.jsp"></jsp:include>
<br>
<table>
<tr>
<th><%=rb.getString("id_disp")%></th>
<th colspan="3"><%=rb.getString("ora")%></th>
</tr>
<%
SimpleDateFormat sdf =
	new SimpleDateFormat(
		"EEEEEEEEE d MMMMMMMMM YYYY H:m.s SSS");
Calendar nowHere = Calendar.getInstance();
Calendar c;
for(String id: TimeZone.getAvailableIDs()){
	c=new GregorianCalendar(TimeZone.getTimeZone(id));
	c.setTimeInMillis(nowHere.getTimeInMillis());
	sdf.setTimeZone(TimeZone.getTimeZone(id));
%>
<tr>
<th><%=id%></th>
<th><%=sdf.format(c.getTime())%></th>
<th><%=sdf.format(Calendar.getInstance().getTime())%></th>
<th><%=sdf.format(Calendar.getInstance(TimeZone.getTimeZone(id)).getTime())%></th>
</tr>
<%
}
%>
</table>
</body>
</html>