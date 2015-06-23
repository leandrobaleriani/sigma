<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
	$(document).ready(function() {
		requestData_atenciones(false);
	});

	var interval_atenciones = null;
	var previous_data_atenciones = null;

	function requestData_atenciones(initialize_atenciones) {
		$.ajax({
			url : '<c:url value="/persona/atencion!showEspera.action"/>',
			global : false
		}).done(function(data) {
			if (!initialize_atenciones) {
				interval_atenciones = setInterval(function() {
					requestData_atenciones(true);
				}, 10000);
				intervals.push(interval_atenciones);
			}
			if (null == previous_data_atenciones) {
				previous_data_atenciones = data;
				$("#atencion_container").html(data);
			} else {
				if (previous_data_atenciones != data) {
					previous_data_atenciones = data;
					$("#atencion_container").html(data);
				}
			}
		});
	}

	function atender(idAtencion) {
		$("#atencionHidden").val(idAtencion);
		var options = {
			url : '<c:url value="/persona/atencion!atender.action"/>',
			dataType : "json",
			success : function(data) {
				var mensaje = data.mensaje;
				if (eval(data.exito)) {
					resetRequestData_atenciones();
					diagnosticar(idAtencion);
				} else {
					if (eval(data.validacion)) {
						showErrors(data.mensajesValidacion);
					} else {
						showMsgError(data.mensaje);
					}
				}
			}
		};

		$('#atencionForm').ajaxForm(options);
		$('#atencionForm').submit();
	}

	function resetRequestData_atenciones() {
		clearInterval(interval_atenciones);
		var index = intervals.indexOf(interval_atenciones);
		intervals.splice(index, 1);
		requestData_atenciones(false);
	}

	function cancelarAtencion(idAtencion) {
		$("#atencionHidden").val(idAtencion);
		var options = {
			url : '<c:url value="/persona/atencion!finalizarAusencia.action"/>',
			dataType : "json",
			success : function(data) {
				var mensaje = data.mensaje;
				if (eval(data.exito)) {
					resetRequestData_atenciones();
				} else {
					if (eval(data.validacion)) {
						showErrors(data.mensajesValidacion);
					} else {
						showMsgError(data.mensaje);
					}
				}
			}
		};

		$('#atencionForm').ajaxForm(options);
		$('#atencionForm').submit();
	}

	function diagnosticar(idAtencion) {
		$("#atencionHidden").val(idAtencion);
		var options = {
			url : '<c:url value="/persona/atencion!diagnosticar.action"/>',
			target : '#modalDialogContainer_atencion',
			success : function() {
				$('#modalDialogContainer_atencion').modal(modalOptions).modal(
						"show");
				$('#modalDialogContainer_atencion').on('shown.bs.modal',
						function(e) {
							$("#nroDocumento").focus();
						});
			}
		};
		$('#atencionForm').ajaxForm(options);
		$('#atencionForm').submit();
	}

	function cerrarModal() {
		$('#modalDialogContainer_atencion').modal('hide');
		$('#modalDialogContainer_atencion').html("");
	}
	
	function cerrarModal_planilla(){
		$('#modalDialogContainer_planilla').modal('hide');
		$('#modalDialogContainer_planilla').html("");
	}
	
	function showFechaPlanilla(){
		var options = {
				url : '<c:url value="/reportes/atencion!showImprimirPlanilla.action"/>',
				target : '#modalDialogContainer_planilla',
				success : function() {
					$('#modalDialogContainer_planilla').modal(modalOptions).modal(
							"show");
				}
			};
			$('#atencionForm').ajaxForm(options);
			$('#atencionForm').submit();
	}
	

	
</script>

<br />

<form action="" id="atencionForm">
	<s:hidden id="atencionHidden" name="idAtencion" />
</form>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-4">

			<a href="javascript:showFechaPlanilla();void(0);" class="btn btn-primary btn-sm">
				<span class="glyphicon glyphicon-print"></span>&nbsp;&nbsp;IMPRIMIR
				PLANILLA 
			</a>
			<br /> <br />

			<%@include
				file="../../../porlets/atenciones/atenciones_pendientes.jsp"%>

			<%@include file="../../../porlets/atenciones/atenciones_ultimas.jsp"%>

		</div>
		<div class="col-md-8" id="atencion_container"></div>
	</div>
</div>

<div class="modal fade bs-example-modal-lg"
	id="modalDialogContainer_atencion"></div>
	
	<div class="modal fade bs-example-modal-lg"
	id="modalDialogContainer_planilla">
	</div>