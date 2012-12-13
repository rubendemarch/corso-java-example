<%@page import="it.ecommerce.util.constants.Common"%>
<table>
<tr>
<th>
<button onclick="dispatchPage('menu','./ManageLanguages','<%=Common.ACTION%>','')">
Lingue
</button>
</th>
<th>
<button onclick="dispatchPage('menu','./ManageBrands','<%=Common.ACTION%>','<%=Common.LIST%>')">
Marche
</button>
</th>
</tr>
</table>
<jsp:include page="../form/form.jsp">
<jsp:param value="menu" name="FORM_ID"/>
</jsp:include>