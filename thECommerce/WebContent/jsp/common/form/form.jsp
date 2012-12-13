<%@page import="it.ecommerce.util.constants.Common"%>
<%
String formId=request.getParameter(Common.FORM_ID);
%>
<form
name="<%=formId%>"
action=""
id="<%=formId%>"
method="post">
	<input
		type="hidden"
		name="<%=Common.ACTION%>"
		value=""
		id="<%=formId%>_<%=Common.ACTION%>">
</form>