<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%
List<HashMap<String,Object>>brandList=
(List<HashMap<String,Object>>)
	request.getAttribute("brandList");
for(HashMap<String,Object>brand:brandList){
%>
<%=(String)brand.get("NAME")%>&nbsp;
<a href="<%=(String)brand.get("URL")%>">
	<%=rb.getString("manage.brand.url")%>
</a>&nbsp;
<a href="<%=(String)brand.get("LOGO_URL")%>">
	<%=rb.getString("manage.brand.logoUrl")%>
</a>&nbsp;
<br>
<%
}
%>