<%@page import="it.eCommerce.util.constants.Common"%>
<%
String action=(String)request.getAttribute(Common.ACTION);
%>
<br>
<jsp:include page="menu/menu.jsp"/>
<%
if(Common.ADD.equals(action)){
%>
<jsp:include page="insertBrand.jsp"/>
<%
}else if(Common.LIST.equals(action)){
%>
<jsp:include page="brand_list.jsp"/>
<%
}else if(Common.DETAIL.equals(action)){
%>
	<jsp:include page="detailBrand.jsp"/>
<%
}else if(Common.MODIFY.equals(action)){
%>
	<jsp:include page="modifyBrand.jsp"/>
<%
}
%>