<%@page import="java.util.HashMap"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="it.ecommerce.util.constants.Common"%>
<%@include file="../../common/props.jsp"%>
<%
String action=(String)request.getAttribute(Common.ACTION);
String disable=(Common.DETAIL.equals(action))?" disabled='disabled'":"";
HashMap<String,Object>brand=
	(HashMap<String,Object>)request.getAttribute("brand");
boolean isVisible=false;
boolean isDeleted=false;
if(!brand.isEmpty()){
	isVisible=((BigDecimal)brand.get("IS_VISIBLE")).intValue()>0;
	isDeleted=((BigDecimal)brand.get("IS_DELETED")).intValue()>0;
}
%>
<form action="./ManageBrands" method="post" enctype="multipart/form-data" name="brand">
<input 
	type="hidden" 
	name="<%=Common.COMMON_ACTION%>" 
	value="<%=action%>">
<input 
	type="hidden" 
	name="commonId"
	value="<%=(String)brand.get("ID_BRAND")%>">
<input 
	type="hidden" 
	name="<%=Common.CUSTOM_ACTION%>" 
	value="<%=Common.SAVE%>">
<input type="hidden" name="ext" value="" id="ext">
<input
	type="hidden"
	name="idColumValue"
	value="<%=(String)brand.get("ID_BRAND")%>">
<input
	type="hidden"
	name="oldLogoUrl"
	value="<%=(String)brand.get("LOGO_URL")%>">
<label><%=rb.getString("manage.brand.page.labelNome")%></label>
<input
	type="text"
	name="name"
	value="<%=brand.get("NAME")!=null?(String)brand.get("NAME"):""%>"
	maxlength="100"
	size="20"
	onkeypress="validateText(event)"
	onblur="cleanText(this)"
<%=disable%>>
<br>
<%
if(Common.DETAIL.equals(action)){
%>
<a href="<%=(String)brand.get("URL")%>">
<%=rb.getString("manage.brand.page.labelUrl")%>
</a>
<%
}else{
%>
<label><%=rb.getString("manage.brand.page.labelUrl")%></label>
<input
	type="url"
	name="url"
	value="<%=brand.get("URL")!=null?(String)brand.get("URL"):""%>"
	maxlength="150"
	size="30"
	onkeypress="validateUrl(event)"
	onblur="cleanUrl(this)">
<%
}
%>
<br>
<%
if(Common.DETAIL.equals(action)){
%>
<a href="<%=(String)brand.get("LOGO_URL")%>">
<%=rb.getString("manage.brand.page.labelLogoUrl")%>
</a>
<%
}else{
%>
<input
	type="radio"
	name="radioLogoUrl"
	checked="checked"
	onchange="manageRadio('logoUrl','logoImg')"
	value="url">
<label><%=rb.getString("manage.brand.page.labelLogoUrl")%></label>
<input
	type="url"
	name="logoUrl"
	value="<%=brand.get("LOGO_URL")!=null?(String)brand.get("LOGO_URL"):""%>"
	maxlength="150"
	size="30"
	id="logoUrl"
	onkeypress="validateUrl(event)"
	onblur="cleanUrl(this)">
<br>
<input
	type="radio"
	name="radioLogoUrl"
	onchange="manageRadio('logoImg','logoUrl')"
	value="image">
<input
	type="file"
	name="logoImg"
	id="logoImg"
	accept="image/*"
	disabled="disabled">
<%
}
%>
<%
if(!Common.ADD.equals(action)){
%>
<br>
<%=rb.getString("isVisible")%>
&nbsp;
<%=rb.getString("common.no")%>
<input
type="radio"
name="isVisible"
value="0"<%if(!isVisible){%> checked="checked"<%}%>
<%=disable%>>
&nbsp;<%=rb.getString("common.yes")%>
<input
type="radio"
name="isVisible"
value="1"<%if(isVisible){%> checked="checked"<%}%>
<%=disable%>>
<br>
<%=rb.getString("isDeleted")%>
&nbsp;
<%=rb.getString("common.no")%>
<input
type="radio"
name="isDeleted"
value="0"<%if(!isDeleted){%> checked="checked"<%}%>
<%=disable%>>
&nbsp;<%=rb.getString("common.yes")%>
<input
type="radio"
name="isDeleted"
value="1"<%if(isDeleted){%> checked="checked"<%}%>
<%=disable%>>
<%
}else{
%>
<input type="hidden" name="isVisible" value="1">
<input type="hidden" name="isDeleted" value="0">
<%
}
%>
<br>
<%
if(!Common.DETAIL.equals(action)){
%>
<input
id="buttonSubmit"
type="button"
value="<%=rb.getString("common.save")%><%=rb.getString("manage.brand.page.save")%>"
onmouseup="copyValue('logoImg','ext')"
onclick="validateBrand('<%=rb.getString("file.size.limit.exceeded")%>',<%=(String)request.getAttribute(Common.maxImageSize)%>,'buttonSubmit')">
<%
}else{
%>
<button onclick="loadAll('./ManageBrands','<%=Common.MODIFY%>','<%=(String)brand.get("ID_BRAND")%>')">
<%=rb.getString("manage.brand.page.modify")%>
</button>
<%
}
%>
</form>