<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<display:table name="requestScope.users"
	class="table table-bordered table-hover" uid="users" pagesize="2"
	requestURI="${appCtx}/persona/atencion!paginarHistorial.action"
	sort="list" defaultsort="2" defaultorder="ascending">
	<display:column title="MÉDICO" headerClass="header_column"
		style="width: 60%;" sortable="true">
		<s:if test="#attr.users.persona.sexo.id == 1">
			Dr.
		</s:if>
		<s:else>
			Dra.
		</s:else>
		<b><s:property value="#attr.users.persona.apellido" />&nbsp;<s:property
				value="#attr.users.persona.nombre" /></b>
	</display:column> 
	<display:column title="" headerClass="header_column"
		style="width: 40%;" sortable="false">
		<a
			href="javascript:finalizarUrgencia('<s:property value="#attr.users.id" />');void(0);"
			class="btn btn-danger btn-xs"><span
			class="glyphicon glyphicon-plus"></span> Finalizar Urgencia</a>
	</display:column>
	<display:footer>
		<tr>
			<td colspan="2"><a href="javascript:nuevaUrgencia();void(0);"
				class="btn btn-primary btn-xs">Agregar Médico</a></td>
		</tr>
	</display:footer>
</display:table>


