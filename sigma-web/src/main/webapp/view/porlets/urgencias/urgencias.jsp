<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>

$(document).ready(function(){
	requestData_urgencias(false);
});

var interval_urgencias = null;
var previous_data_urgencias = null;

function requestData_urgencias(initialize_urgencias){
	$.ajax({
		url : '<c:url value="/porlet/urgencia!showEnUrgencias.action"/>',
		global : false
	}).done(function(data) {
		if(!initialize_urgencias){
			interval_urgencias = setInterval(function(){requestData_urgencias(true);}, 10000);
			intervals.push(interval_urgencias);
		}
		if(null == previous_data_urgencias){
			previous_data_urgencias = data;
			$("#contenedorAjax_urgencias").html(data);
		} else {
			if(previous_data_urgencias != data){
				previous_data_urgencias = data;
				$("#contenedorAjax_urgencias").html(data);	
			}
		}
		
	});
}

function resetRequestData_urgencias(){
	clearInterval(interval_urgencias);
	var index = intervals.indexOf(interval_urgencias);
	intervals.splice(index, 1);
	requestData_urgencias(false);
}

function closeDialog_urgencias(){
	$('#modalDialogContainer_urgencias').modal('hide');
	$('#modalDialogContainer_urgencias').html("");
}


function nuevaUrgencia() {

	var options = {
		url : '<c:url value="/porlet/urgencia!showAgregarUrgencia.action"/>',
		target : '#modalDialogContainer_urgencias',
		success : function() {
			$('#modalDialogContainer_urgencias').modal(modalOptions).modal("show");
		}
	};

	$('#urgenciaForm').ajaxForm(options);
	$('#urgenciaForm').submit();
}

function finalizarUrgencia(id) {

	$("#selectedItems").val(id);

	var options = {
		url : '<c:url value="/porlet/urgencia!finalizarUrgencia.action"/>',
		target : '#modalDialogContainer',
		success : function() {
			resetRequestData_urgencias();
		}
	};

	$('#urgenciaForm').ajaxForm(options);
	$('#urgenciaForm').submit();
}
</script>


<form action="" id="urgenciaForm">
	<s:hidden id="selectedItems" name="selectedItems" />
</form>

<div class="panel panel-danger">
	<div class="panel-heading">
		<h3 class="panel-title">
			<span class="glyphicon glyphicon-plus"></span> En Urgencia
		</h3>
	</div>
	<div class="panel-body" id="contenedorAjax_urgencias">
		<%@include file="urgencias_result.jsp"%>		
	</div>
</div>

<div class="modal fade bs-example-modal-sm" id="modalDialogContainer_urgencias">

</div>