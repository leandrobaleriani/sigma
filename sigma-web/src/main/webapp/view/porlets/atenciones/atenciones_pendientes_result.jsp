<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="list-group">
	<s:iterator value="#request.atencionesPendientes" var="atencion">
		<a
			href="javascript:diagnosticar('<s:property value="id" />');void(0);"
			class="list-group-item"><span
			class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp; <b><s:property
					value="persona.apellido" />&nbsp;<s:property
					value="persona.nombre" /> (<s:property value="tipoAtencion" />)</b>
			<br /> RECEPCION: <b><s:date name="fechaRecepcion"
					format="dd/MM/yyyy HH:mm" /></b></a>
	</s:iterator>
</div>
