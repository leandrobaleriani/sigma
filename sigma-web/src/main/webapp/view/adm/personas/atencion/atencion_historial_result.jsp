<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
updateDisplayTagLinks("contenedorAjax_historial");
</script>

<display:table name="sessionScope.historialAtenciones" class="table table-bordered table-hover"
	uid="historial" pagesize="2"
	requestURI="${appCtx}/persona/atencion!paginarHistorial.action" sort="list"
	defaultsort="2" defaultorder="ascending">
	<display:column title="F. ATENCIÓN" headerClass="header_column"
		style="width: 40%;" property="finAtencion" sortable="false" format="{0,date,MM-dd-yyyy HH:mm}" />
	<display:column title="DIAGNÓSTICO" headerClass="header_column"
		style="width: 60%;" sortable="false" property="diagnostico"/>
</display:table>