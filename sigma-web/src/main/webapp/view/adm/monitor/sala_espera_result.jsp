<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:iterator step="index" value="#request.atenciones">
	<s:if test="enEspera">
		<div style="display: block;" class="column_middle_grid1" id="espera_0">
		<s:property value="index"/>
	</s:if>
	<s:else>
		<div style="display: block;" class="column_middle_grid2" id="espera_0">
	</s:else>
	<table width="100%">
		<tbody>
			<tr>
				<s:if test="enEspera">
					<td colspan="2">En Espera</td>
				</s:if>
				<s:else>
					<td colspan="2">En Atención</td>
				</s:else>
			</tr>
			<tr>
				<td width="70%" align="left"><h2>
						::
						<s:property value="nombre" />
					</h2></td>
				<td width="30%"><h3>
						<s:property value="tipoAtencion" />
					</h3></td>
			</tr>
		</tbody>
	</table>
	</div>
</s:iterator>