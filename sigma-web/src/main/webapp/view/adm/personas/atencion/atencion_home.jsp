<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
	refreshSalaEspera();

	function refreshSalaEspera() {

		$.ajax({
			url : '<c:url value="/persona/atencion!showEspera.action"/>',
			global : false
		}).done(function(data) {
			refreshAtencionesPendientes();
			$("#atencion_container").html(data);
		});
	}
	
	function refreshAtencionesPendientes() {

		$.ajax({
			url : '<c:url value="/persona/atencion!showAtencionesPendientes.action"/>',
			global : false
		}).done(function(data) {
			refreshAtencionesUltimas();
			$("#atencion_pendiente_container").html(data);
		});
	}
	
	function refreshAtencionesUltimas() {

		$.ajax({
			url : '<c:url value="/persona/atencion!showAtencionesUltimas.action"/>',
			global : false
		}).done(function(data) {
			$("#atencion_ultima_container").html(data);
						setTimeout(function(){ 
							refreshSalaEspera(); 
							}, 10000);
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

	function diagnosticar(idAtencion){
		$("#atencionHidden").val(idAtencion);
		var options = {
				url : '<c:url value="/persona/atencion!diagnosticar.action"/>',
				target : '#modalDialogContainer',
				success : function() {
					$('#modalDialogContainer').modal(modalOptions).modal("show");
					$('#modalDialogContainer').on('shown.bs.modal', function(e) {
						$("#nroDocumento").focus();
					});
				}
			};
		$('#atencionForm').ajaxForm(options);
		$('#atencionForm').submit();
	}
	
	function cerrarModal() {
		$('#modalDialogContainer').modal('hide');
		$('#modalDialogContainer').html("");
	}

	$(function() {

	});
</script>

<br />

<form action="" id="atencionForm">
<s:hidden id="atencionHidden" name="idAtencion"/>
</form>

<div class="container">
	<div class="row">
		<div class="col-md-4">
			<div class="panel panel-warning" id="atencion_pendiente_container">
			</div>
			<div class="panel panel-info"  id="atencion_ultima_container">
			</div>
		</div>
		<div class="col-md-8" id="atencion_container"></div>
	</div>
</div>

<div class="modal fade bs-example-modal-lg" id="modalDialogContainer">
</div>