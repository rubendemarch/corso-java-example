<%@page import="it.ecommerce.util.constants.Common"%>
<%@include file="../../common/props.jsp"%>
<form action="./ManageBrands" method="post" enctype="multipart/form-data" name="brand">
<input 
type="hidden" 
name="<%=Common.COMMON_ACTION%>" 
value="<%=Common.ADD%>">
<input 
type="hidden" 
name="<%=Common.CUSTOM_ACTION%>" 
value="<%=Common.SAVE%>">
<input type="hidden" name="ext" value="" id="ext">
<input type="hidden" name="isVisible" value="1">
<input type="hidden" name="isDeleted" value="0">
<label><%=rb.getString("manage.brand.page.labelNome")%></label>
<input type="text" value="" name="name" maxlength="100" size="20" onkeypress="validate(event)">
<br>
<label><%=rb.getString("manage.brand.page.labelUrl")%></label>
<input type="url" value="" name="url" maxlength="150" size="30" onkeypress="validate(event)">
<br>
<input
	type="radio"
	name="radioLogoUrl"
	checked="checked"
	onchange="manageRadio('logoUrl','logoImg')"
	value="url">
<label><%=rb.getString("manage.brand.page.labelLogoUrl")%></label>
<input type="url" value="" name="logoUrl" maxlength="150" size="30" id="logoUrl" onkeypress="validate(event)">
<br>
<input
	type="radio"
	name="radioLogoUrl"
	onchange="manageRadio('logoImg','logoUrl')"
	value="image">
<input type="file" name="logoImg" id="logoImg" accept="image/*" disabled="disabled">
<br>
<input
type="submit"
value="<%=rb.getString("common.save")%><%=rb.getString("manage.brand.page.save")%>"
onmouseup="copyValue('logoImg','ext')"> 
</form>