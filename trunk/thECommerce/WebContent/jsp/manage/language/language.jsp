<%@page import="java.math.BigDecimal"%>
<%@page import="it.ecommerce.util.constants.Common"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="it.ecommerce.util.constants.Request"%>
<%@include file="../../common/props.jsp"%>
<%
List<HashMap<String,Object>>managedLanguages=
	(List<HashMap<String,Object>>)
		request.getAttribute(
			Request.MANAGED_LANGUAGES);
List<Locale>toManage=
	(List<Locale>)
		request.getAttribute(
			Request.TO_MANAGE_LANGUAGES);
%>
<form action="./ManageLanguages" name="language" method="post">
<input type="hidden" name="<%=Common.ACTION%>" value="save">
<%
String lang;
boolean isVisibile;
for(HashMap<String,Object>managedLanguage:managedLanguages){
	lang=(String)managedLanguage.get("ID_LANGUAGE");
	isVisibile=((BigDecimal)managedLanguage.get("IS_VISIBLE")).intValue()>0;
%>
<input type="checkbox" name="<%=lang%>"<%if(isVisibile){%> checked<%}%>>
<%=new Locale(lang).getDisplayLanguage(inLocale)%>
<br>
<%
}
%>
<select name="toManage">
	<option value="0000">
		<%=rb.getString("common.sel")%>
	</option>
<%
for(Locale locale:toManage){
%>
	<option value="<%=locale.getLanguage()%>">
		<%=locale.getDisplayLanguage(inLocale)%>
	</option>
<%
}
%>
</select>
<input type="submit" value="<%=rb.getString("common.save")%>">
</form>