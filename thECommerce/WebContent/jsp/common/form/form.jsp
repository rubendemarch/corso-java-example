<%@page import="it.ecommerce.util.constants.Common"%>
<form
name="<%=request.getParameter(Common.FORM_ID)%>"
action=""
id="<%=request.getParameter(Common.FORM_ID)%>"
method="post">
	<input
		type="hidden"
		name="<%=Common.ACTION%>"
		value=""
		id="<%=Common.ACTION%>">
</form>