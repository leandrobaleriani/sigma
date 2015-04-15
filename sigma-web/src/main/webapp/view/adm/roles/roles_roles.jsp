<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	
</script>


<b>Permisos de Aplicaciones:</b>

<display:table name="roles" class="table table-bordered" uid="roles">
	<display:column title="# APLICACIÓN" headerClass="header_column"
		property="aplicacion.nombre" style="width: 20%;" group="1" />
	<display:column title="PERMISO" headerClass="header_column"
		style="width: 30%;">
		<table cellpadding="0" cellspacing="0" style="">
			<tr>
				<s:if test="#attr.roles.id in rolesUsuario">
					<s:checkbox name="user.roles.id" id="rolesUsuario_%{#attr.roles.id}" fieldValue="%{#attr.roles.id}"
					checked="checked"
 					label="%{#attr.roles.codigo}" cssStyle="font-weight: normal!important;" />
				</s:if>
				<s:else>
					<s:checkbox name="user.roles.id" id="rolesUsuario_%{#attr.roles.id}" fieldValue="%{#attr.roles.id}"
 					label="%{#attr.roles.codigo}" cssStyle="font-weight: normal!important;" />
				</s:else>
			</tr>
		</table>
	</display:column>
	<display:column headerClass="header_column" property="descripcion"
		style="width: 50%;" title="DESCRIPCIÓN" />
</display:table>