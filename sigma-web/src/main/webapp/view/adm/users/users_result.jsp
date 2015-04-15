<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<display:table name="users" class="table table-striped table-hover"
	uid="users">
	<display:column title="DNI" headerClass="header_column" property="dni"
		style="width: 10%;" />
	<display:column title="NOMBRE COMPLETO" headerClass="header_column"
		style="width: 30%;">
		<s:property value="#attr.users.apellido" />&nbsp;<s:property
			value="#attr.users.nombre" />
	</display:column>
	<display:column headerClass="header_column" property="mail" title="E-MAIL"
		style="width: 20%;" />
	<display:column headerClass="header_column" title="ACTIVO"
		style="width: 10%;">
		<s:if test="#attr.users.activo">
			<h4><span class="label label-success">SI</span></h4>
		</s:if>
		<s:else>
			<h4><span class="label label-danger">NO</span></h4>
		</s:else>
	</display:column>
	<display:column headerClass="header_column" title="BLOQUEADO"
		style="width: 10%;">
		<s:if test="#attr.users.locked">
			<h4><span class="label label-danger">SI</span></h4>
		</s:if>
		<s:else>
			<h4><span class="label label-success">NO</span></h4>
		</s:else>
	</display:column>
	<display:column headerClass="header_column" title=""
		style="width: 20%;">
		<a href="javascript:nuevoEditar(1);void(0);"
			class="btn btn-info btn-xs"><span
			class="glyphicon glyphicon-pencil"></span> Editar</a>
			
		<s:if test="#attr.users.locked">
			<a href="javascript:nuevoEditar(1);void(0);"
			class="btn btn-sucess btn-xs"><span
			class="glyphicon glyphicon-ok-circle"></span> Desbloquear</a>
		</s:if>
		
		
		<s:if test="#attr.users.activo">
			<a href="javascript:nuevoEditar(1);void(0);"
			class="btn btn-danger btn-xs"><span
			class="glyphicon glyphicon-thumbs-down"></span> Desactivar</a>
		</s:if>
		<s:else>
			<a href="javascript:nuevoEditar(1);void(0);"
			class="btn btn-success btn-xs"><span
			class="glyphicon glyphicon-thumbs-up"></span> Activar</a>
		</s:else>
	</display:column>
</display:table>