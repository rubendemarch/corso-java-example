<%@page import="it.ecommerce.util.constants.Common"%>
<%
String action=(String)request.getAttribute(Common.ACTION);
%>
<br>
<jsp:include page="menu/menu.jsp"/>
<%
if(	Common.ADD.equals(action)||
		Common.DETAIL.equals(action)||
		Common.MODIFY.equals(action)){
%>
<jsp:include page="brand.jsp"/>
<%
}else if(Common.LIST.equals(action)){
%>
<jsp:include page="brandList.jsp"/>
<%
}
%>