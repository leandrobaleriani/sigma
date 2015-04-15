<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
function search(){

	var options = { 
		        url: '<c:url value="/user/adm!search.action"/>',
		        target: '#contenedorAjax'
		}; 
	
	$('#busquedaForm').ajaxForm(options); 
	$('#busquedaForm').submit();
	$("#campoBusqueda").focus();
}

function nuevoUsuario(){
	
	var options = { 
	        url: '<c:url value="/user/adm!showNew.action"/>',
	        target: '#modalDialogContainer',
	        success: function(){
	        	$('#modalDialogContainer').modal(modalOptions).modal("show");

	        	$('#modalDialogContainer').on('shown.bs.modal', function (e) {
	        		$("#nroDocumento").focus();
        		});
	        }
	}; 

	$('#busquedaForm').ajaxForm(options); 
	$('#busquedaForm').submit();
}

function clearForm(){
	$("#campoBusqueda").val("");
	$("#campoBusqueda").focus();
}

function cerrarModal(){
	$('#modalDialogContainer').modal('hide');
	$('#modalDialogContainer').html("");
}

$(function(){
	
	$("#campoBusqueda").bind('keyup', function (e) {
	    $("#campoBusqueda").val(($("#campoBusqueda").val()).toUpperCase());
	});
	
});

</script>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 well bs-component">
	<form class="form-horizontal" id="busquedaForm">
		<fieldset>
			<legend> </legend>
			<div class="form-group">
				<label for="inputEmail" class="col-lg-4 control-label">DNI/NOMBRE
					O APELLIDO</label>
				<div class="col-lg-4">
						<s:textfield  id="campoBusqueda" name="userFilter.campoBusqueda" cssClass="form-control" placeholder="DNI/NOMBRE O APELLIDO"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-12" style="text-align: right;">
					<a href="javascript:clearForm();void(0);" class="btn btn-primary btn-sm"><span
						class="glyphicon glyphicon-zoom-out"></span> Limpiar</a> <a href="javascript:search();void(0);"
						class="btn btn-primary btn-sm"><span
						class="glyphicon glyphicon-search"></span> Buscar</a> <a
						href="javascript:nuevoUsuario();void(0);"
						class="btn btn-success btn-sm"><span
						class="glyphicon glyphicon-plus-sign"></span> Nuevo</a>
				</div>
			</div>
		</fieldset>
	</form>
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 well bs-component" id="contenedorAjax">
	</div>
</div>

<div class="modal fade bs-example-modal-sm" id="modalDialogContainer">

</div>