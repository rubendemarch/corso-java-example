<%@page import="it.ecommerce.util.constants.Common"%>
<table>
	<tr>
		<th>
		<button onclick="dispatchPage('subMenu','./ManageBrands','<%=Common.ACTION%>','<%=Common.ADD%>')">
		Inserisci Marca
		</button>
		</th>
		<th>
		<button onclick="dispatchPage('subMenu','./ManageBrands','<%=Common.ACTION%>','<%=Common.LIST%>')">
		Lista marche
		</button>
		</th>
	</tr>
</table>
<jsp:include page="../../../common/form/form.jsp">
	<jsp:param value="subMenu" name="FORM_ID"/>
</jsp:include>