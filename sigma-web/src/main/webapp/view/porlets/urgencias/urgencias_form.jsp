<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<form id="urgenciaForm">
<s:hidden name="selectedItems" id="selectedItems"></s:hidden>
</form>

<div class="modal-dialog modal-md">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h4 class="modal-title">
				<img alt="" height="32px" hspace="5" src="images/paciente.png">
				<b>Urgencia Médica</b>
			</h4>
		</div>
		<div class="modal-body" id="contenedorAjax_usuarios">
			<%@include file="urgencias_seleccion_result.jsp"%>
		</div>

		<div class="modal-footer">
			<a href="javascript:closeDialog_urgencias();void(0);"
				class="btn btn-danger btn-sm"><span
				class="glyphicon glyphicon-remove-circle"></span> Cancelar</a>
		</div>
	</div>
</div>
