<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>

updateDisplayTagLinks("contenedorAjax");

</script>

<display:table name="personas" class="table table-bordered table-hover" 
	uid="personas" pagesize="10" requestURI="${appCtx}/persona/recepcion!search.action"
	partialList="true" size="${requestScope.totalResult}" sort="external" defaultsort="2" defaultorder="ascending">
	<display:column title="DOCUMENTO" headerClass="header_column"
		style="width: 10%;" sortable="true" sortName="doc">
		<s:property value="#attr.personas.doc" />&nbsp;(<s:property
			value="#attr.personas.tipoDoc" />)
		</display:column>
	<display:column title="NOMBRE COMPLETO" headerClass="header_column"
		style="width: 20%;" sortable="true" sortName="nombreApellido">
		<b><a href="javascript:editarPersona('<s:property value='#attr.personas.id' />');void(0);" ><s:property value="#attr.personas.apellido" />&nbsp;<s:property
			value="#attr.personas.nombre" /></a></b>
	</display:column>
	<display:column headerClass="header_column"
		title="F. NAC." style="width: 12%;" sortable="true" sortName="fechaNacimiento">
		<s:date name="#attr.personas.fechaNacimiento" format="dd/MM/yyyy" /> (<s:property value="#attr.personas.edad" />)
		</display:column>
	<display:column headerClass="header_column" property="sexo"
		title="SEXO" style="width: 10%;"  sortable="true"  sortName="sexo"/>
	<display:column headerClass="header_column" 
		title="LOCALIDAD" style="width: 10%;"  sortable="true"  sortName="localidad.nombre">
		<s:property value="#attr.personas.localidad.nombre" />&nbsp;(<s:property value="#attr.personas.codPostal" />)
		</display:column>
	<display:column headerClass="header_column" title=""
		style="width: 15%;" sortable="false"> 
		<a href="javascript:recepcionarPersona('<s:property value='#attr.personas.id' />');void(0);"
			class="btn btn-success btn-xs"><span
			class="glyphicon glyphicon-share-alt"></span> Recepcionar</a>
	</display:column>
</display:table>