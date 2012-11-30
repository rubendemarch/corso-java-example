<%@page import="it.alfasoft.corso.java.util.constants.Request"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%
List<Locale> managedLanguages=
	(List<Locale>)request.getAttribute(Request.managedLanguages);
Locale locale=
(Locale)request.getAttribute(Request.LOCALE);
%>
<form
	name="chooseLanguage"
	action="./ManageLanguage"
	method="post">
<select name="language" onchange="submit()">
<%
for(Locale l:managedLanguages){
%>
	<option
		value="<%=l%>"
		class="lang_<%=l%>"
		<%if(l.equals(locale)){%>selected="selected"<%}%>>
		<%=l.getDisplayLanguage()%>
	</option>
<%
}
%>
</select>
</form>