<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
	$(document).ready(function() {
		$("#campoBusqueda").focus();
	});

	function search() {

		if (!$("#contenedorAjax").is(":visible")) {
			$("#contenedorAjax").show();
		}

		var options = {
			url : '<c:url value="/persona/recepcion!search.action"/>',
			target : '#contenedorAjax'
		};

		$('#busquedaForm').ajaxForm(options);
		$('#busquedaForm').submit();
		$("#campoBusqueda").focus();
	}

	function nuevaPersona() {

		var options = {
			url : '<c:url value="/persona/recepcion!showNew.action"/>',
			target : '#modalDialogContainer',
			success : function() {
				$('#modalDialogContainer').modal(modalOptions).modal("show");

				$('#modalDialogContainer').on('shown.bs.modal', function(e) {
					$("#nroDocumento").focus();
				});
			}
		};

		$('#busquedaForm').ajaxForm(options);
		$('#busquedaForm').submit();
	}

	function editarPersona(id) {

		$("#selectedItem").val(id);

		var options = {
			url : '<c:url value="/persona/recepcion!showEdit.action"/>',
			target : '#modalDialogContainer',
			success : function() {
				$('#modalDialogContainer').modal(modalOptions).modal("show");
			}
		};

		$('#busquedaForm').ajaxForm(options);
		$('#busquedaForm').submit();
	}

	function recepcionarPersona(id) {

		$("#selectedItem").val(id);

		var options = {
			url : '<c:url value="/persona/recepcion!showRecepcionar.action"/>',
			target : '#modalDialogContainer',
			success : function() {
				$('#modalDialogContainer').modal(modalOptions).modal("show");
			}
		};

		$('#busquedaForm').ajaxForm(options);
		$('#busquedaForm').submit();
	}

	function clearForm() {
		$("#campoBusqueda").val("");
		$("#campoBusqueda").focus();
	}

	function cerrarModal() {
		$('#modalDialogContainer').modal('hide');
		$('#modalDialogContainer').html("");
	}

	$(function() {

		$("#campoBusqueda").bind('keyup', function(e) {
			$("#campoBusqueda").val(($("#campoBusqueda").val()).toUpperCase());
		});

		$("#campoBusqueda").keypress(function(e) {
			if (e.which == 13) {
				search();
			}
		});

	});
</script>


<form action="" id="atencionForm">
	<s:hidden id="atencionHidden" name="idAtencion" />
</form>


<div class="container-fluid">
	<div class="row">
		<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-paperclip"></span>&nbsp;&nbsp;
						Búsqueda de Pacientes
					</h3>
				</div>
				<div class="panel-body">
					<form class="form-horizontal" id="busquedaForm" method="post">
						<s:hidden name="selectedItem" id="selectedItem" />
						<fieldset>
							<legend> </legend>
							<div class="form-group">
								<label for="inputEmail" class="col-lg-4 control-label">DNI/NOMBRE
									O APELLIDO</label>
								<div class="col-lg-4">
									<s:textfield id="campoBusqueda"
										name="personaFilter.campoBusqueda" cssClass="form-control"
										placeholder="DNI/NOMBRE O APELLIDO" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-12" style="text-align: right;">
									<a href="javascript:search();void(0);"
										class="btn btn-primary btn-sm"><span
										class="glyphicon glyphicon-search"></span> Buscar</a> <a
										href="javascript:clearForm();void(0);"
										class="btn btn-primary btn-sm"><span
										class="glyphicon glyphicon-zoom-out"></span> Limpiar</a> <a
										href="javascript:nuevaPersona();void(0);"
										class="btn btn-success btn-sm"><span
										class="glyphicon glyphicon-plus-sign"></span> Nuevo</a>
								</div>
							</div>
						</fieldset>
					</form>
					<div
						class="col-xs-12 col-sm-12 col-md-12 col-lg-12 well bs-component"
						id="contenedorAjax">
						<%@include file="personas_result.jsp"%>
					</div>
				</div>
			</div>
		</div>

		<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
			
			<%@include file="../../porlets/salaespera/sala_espera.jsp"%>
			
			<%@include file="../../porlets/urgencias/urgencias.jsp"%>
			
		</div>
	</div>
</div>

<div class="modal fade bs-example-modal-sm" id="modalDialogContainer">

</div>