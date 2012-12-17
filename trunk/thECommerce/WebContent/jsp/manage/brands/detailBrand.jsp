<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.HashMap"%>
<%@page import="it.ecommerce.util.constants.Common"%>
<%@include file="../../common/props.jsp"%>
<%
HashMap<String,Object>brand=
	(HashMap<String,Object>)request.getAttribute("brand");
boolean isVisible=((BigDecimal)brand.get("IS_VISIBLE")).intValue()>0;
boolean isDeleted=((BigDecimal)brand.get("IS_DELETED")).intValue()>0;
%>
<label><%=rb.getString("manage.brand.page.labelNome")%></label>
&nbsp;<label><%=(String)brand.get("NAME")%></label>
<br>
<a href="<%=(String)brand.get("URL")%>"><%=rb.getString("manage.brand.page.labelUrl")%></a>
<br>
<a href="<%=(String)brand.get("LOGO_URL")%>"><%=rb.getString("manage.brand.page.labelLogoUrl")%></a>
<br>
<label><%=rb.getString("isVisible")%></label>
<input type="checkbox"<%if(isVisible){%> checked<%}%> disabled="disabled">
<br>
<label><%=rb.getString("isDeleted")%></label>
<input type="checkbox"<%if(isDeleted){%> checked<%}%> disabled="disabled">