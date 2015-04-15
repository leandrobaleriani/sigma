<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="modal-dialog modal-md">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h4 class="modal-title">
				<img alt="" height="32px" hspace="5" src="images/paciente.png">
				<s:if test="%{user == null}">
					ALTA
				</s:if>
				<s:else>
					EDICION
				</s:else>
			</h4>
		</div>
		<div class="modal-body">

			<form class="form-horizontal">
				<fieldset>
					<legend>Datos del Paciente</legend>
					<div class="form-group">
						<label class="col-lg-4 control-label control-label-left">DNI</label>
						<div class="col-lg-8">
							<input class="form-control" id="nroDocumento"
								placeholder="DNI" type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-4 control-label control-label-left">APELLIDO
							Y NOMBRE</label>
						<div class="col-lg-8">
							<input class="form-control" id="inputEmail"
								placeholder="APELLIDO Y NOMBRE" type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-4 control-label control-label-left">E-MAIL</label>
						<div class="col-lg-8">
							<input class="form-control" id="inputEmail"
								placeholder="EMAIL" type="text">
						</div>
					</div>
				</fieldset>
			</form>

		</div>
		<div class="modal-footer">
			<a href="javascript:cerrarModal();void(0);"
				class="btn btn-danger btn-sm"><span
				class="glyphicon glyphicon-remove-circle"></span> Cancelar</a> <a
				href="javascript:guardarUsuario();void(0);"
				class="btn btn-success btn-sm"><span
				class="glyphicon glyphicon-ok-circle"></span> Guardar</a>
		</div>
	</div>
</div>
