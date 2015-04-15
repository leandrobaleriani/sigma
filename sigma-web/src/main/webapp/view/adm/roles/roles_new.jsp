<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">

$(function(){
	
	$("#nroDocumento").keyup(function(){
		
		if($(this).val() == "33709399"){
			$("#nroDocumento_bloque").addClass("has-success");
			$("#nombreApellido").val("JUAN MANUEL VAZQUEZ");
		} else {
			$("#nroDocumento_bloque").addClass("has-error");
			$("#nombreApellido").val("");
		}
		
	});
	
});


	function guardarUsuario() {

		var options = {
			url : '<c:url value="/user/adm!save.action"/>',
			type : "json",
			success : function(data) {
				var mensaje = data.mensaje;
				if (eval(data.exito)) {
					showMsgInfo(data.mensaje, function(){cerrarModal();});
				} else {
					if(eval(data.validacion)){
						var mensajeValidacion = data.mensajeValidacion;
						$("#errorPanel").html(getMensajeValidacion(mensajeValidacion));	
						$("#errorPanel").show();
					} else {
						showMsgError(data.mensaje);
					}
				}
			}
		};

		$('#usuarioForm').ajaxForm(options);
		$('#usuarioForm').submit();
	}
</script>

<div class="modal-dialog modal-md">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h4 class="modal-title">
				<img alt="" height="32px" hspace="5" src="images/user2.png">
				<b>Alta de Usuario</b>
			</h4>
		</div>
		<div class="modal-body">
			<div class="alert alert-danger" style="display:none;" id="errorPanel" role="alert">
			</div>
			<form class="form-horizontal" id="usuarioForm">
				<fieldset>
					<div class="form-group" id="nroDocumento_bloque">
						<label class="col-lg-4 control-label control-label-left">DNI:</label>
						<div class="col-lg-4">
							<s:textfield name="user.dni" cssClass="form-control" id="nroDocumento" placeholder="DNI"/>	
						</div>
					</div>
					<div class="form-group" id="nombreApellido_bloque">
						<label class="col-lg-4 control-label control-label-left">APELLIDO/NOMBRE:</label>
						<div class="col-lg-6">
							<input class="form-control" id="nombreApellido"
								placeholder="" type="text" disabled="disabled">
						</div>
					</div>
					<div class="form-group" id="email_bloque">
						<label class="col-lg-4 control-label control-label-left">E-MAIL:</label>
						<div class="col-lg-6">
								<s:textfield name="selectedItem" cssClass="form-control" id="email" placeholder="EMAIL"/>	
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

