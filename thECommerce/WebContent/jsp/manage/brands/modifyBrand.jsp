<%@page import="java.util.HashMap"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="it.ecommerce.util.constants.Common"%>
<%@include file="../../common/props.jsp"%>
<%
HashMap<String,Object>brand=
	(HashMap<String,Object>)request.getAttribute("brand");
boolean isVisible=((BigDecimal)brand.get("IS_VISIBLE")).intValue()>0;
boolean isDeleted=((BigDecimal)brand.get("IS_DELETED")).intValue()>0;
%>
<form action="./ManageBrands" method="post" enctype="multipart/form-data" name="brand">
<input 
type="hidden" 
name="<%=Common.COMMON_ACTION%>" 
value="<%=Common.MODIFY%>">
<input 
type="hidden" 
name="commonId" 
value="<%=(String)brand.get("ID_BRAND")%>">
<input 
type="hidden" 
name="<%=Common.CUSTOM_ACTION%>" 
value="<%=Common.SAVE%>">
<input type="hidden" name="ext" value="" id="ext">
<input type="hidden" name="idColumValue" value="<%=(String)brand.get("ID_BRAND")%>">
<input type="hidden" value="<%=(String)brand.get("LOGO_URL")%>" name="oldLogoUrl">
<label><%=rb.getString("manage.brand.page.labelNome")%></label>
<input type="text" value="<%=(String)brand.get("NAME")%>" name="name" maxlength="100" size="20">
<br>
<label><%=rb.getString("manage.brand.page.labelUrl")%></label>
<input type="text" value="<%=brand.get("URL")!=null?(String)brand.get("URL"):""%>" name="url" maxlength="150" size="30">
<br>
<input
	type="radio"
	name="radioLogoUrl"
	checked="checked"
	onchange="manageRadio('logoUrl','logoImg')"
	value="url">
<label><%=rb.getString("manage.brand.page.labelLogoUrl")%></label>
<input type="text" value="<%=(String)brand.get("LOGO_URL")%>" name="logoUrl" maxlength="150" size="30" id="logoUrl">
<br>
<input
	type="radio"
	name="radioLogoUrl"
	onchange="manageRadio('logoImg','logoUrl')"
	value="image">
<input type="file" name="logoImg" id="logoImg" accept="image/*" disabled="disabled">

<br>
<%=rb.getString("isVisible")%>
&nbsp;
<%=rb.getString("common.no")%>
<input type="radio" name="isVisible" value="0"<%if(!isVisible){%> checked="checked"<%}%>>
&nbsp;<%=rb.getString("common.yes")%>
<input type="radio" name="isVisible" value="1"<%if(isVisible){%> checked="checked"<%}%>>

<br>
<%=rb.getString("isDeleted")%>
&nbsp;
<%=rb.getString("common.no")%>
<input type="radio" name="isDeleted" value="0"<%if(!isDeleted){%> checked="checked"<%}%>>
&nbsp;<%=rb.getString("common.yes")%>
<input type="radio" name="isDeleted" value="1"<%if(isDeleted){%> checked="checked"<%}%>>

<br>
<input
type="submit"
value="<%=rb.getString("common.save")%><%=rb.getString("manage.brand.page.save")%>"
onmouseup="copyValue('logoImg','ext')">
</form>