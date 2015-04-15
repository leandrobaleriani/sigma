<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="modal fade bs-example-modal-sm" id="myModal">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title">Recepcionar Paciente</h4>
			</div>
			<div class="modal-body">

				<form class="form-horizontal">
					<fieldset>
						<legend>Datos de la Recepción</legend>
						<div class="form-group">
							<label for="select"
								class="col-lg-4 control-label control-label-left">Tipo
								Atención</label>
							<div class="col-lg-8">
								<select class="form-control" id="select">
									<option selected="selected">[SELECCIONE]</option>
									<option>PEDIATRÍA</option>
									<option>CLÍNICA</option>
									<!-- 										<option>ENFERMERÍA</option> -->
								</select>
							</div>
						</div>
					</fieldset>
				</form>

			</div>
			<div class="modal-footer">
				<a href="javascript:cancelar();void(0);"
					class="btn btn-danger btn-sm"><span
					class="glyphicon glyphicon-remove-circle"></span> Cancelar</a> <a
					href="javascript:cancelar();void(0);"
					class="btn btn-success btn-sm"><span
					class="glyphicon glyphicon-ok-circle"></span> Recepcionar</a>
			</div>
		</div>
	</div>
</div>


<div class="modal fade bs-example-modal-sm" id="myModal2">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title">
					<img alt="" height="32px" hspace="5" src="images/paciente.png">Nuevo/Editar
					Paciente
				</h4>
			</div>
			<div class="modal-body">

				<form class="form-horizontal">
					<fieldset>
						<legend>Datos del Paciente</legend>
						<div class="form-group">
							<label class="col-lg-4 control-label control-label-left">DNI/LE/LC/CI</label>
							<div class="col-lg-8">
								<input class="form-control" id="inputEmail"
									placeholder="DNI/LE/LC/CI" type="text">
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
							<label for="select"
								class="col-lg-4 control-label control-label-left">Obra
								Social/Plan</label>
							<div class="col-lg-5">
								<select class="form-control" id="select">
									<option selected="selected">[SELECCIONE]</option>
									<option>OSDE</option>
									<option>SWISS MEDICAL</option>
									<option>OSECAC</option>
								</select>
							</div>
							<div class="col-lg-3">
								<input class="form-control" id="inputEmail" placeholder="PLAN"
									type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-4 control-label control-label-left">FECHA
								NACIMIENTO</label>
							<div class="col-lg-4">
								<input class="form-control" id="inputEmail"
									placeholder="DD/MM/YYYY" type="text">
							</div>

						</div>
						<div class="form-group">
							<label for="select"
								class="col-lg-4 control-label control-label-left">Sexo</label>
							<div class="col-lg-5">
								<label class="radio-inline"> <input type="radio"
									name="inlineRadioOptions" id="inlineRadio1" value="option1">
									Masculino
								</label> <label class="radio-inline"> <input type="radio"
									name="inlineRadioOptions" id="inlineRadio2" value="option2">
									Femenino
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-4 control-label control-label-left">DIRECCIÓN</label>
							<div class="col-lg-8">
								<input class="form-control" id="inputEmail"
									placeholder="DIRECCIÓN" type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-4 control-label control-label-left">TELÉFONO</label>
							<div class="col-lg-8">
								<input class="form-control" id="inputEmail"
									placeholder="TELÉFONO" type="text">
							</div>
						</div>
						<div class="form-group">
							<label for="select"
								class="col-lg-4 control-label control-label-left">Provincia</label>
							<div class="col-lg-8">
								<select class="form-control" id="select">
									<option>BUENOS AIRES</option>
									<option>SANTA FE</option>
									<option>CORDOBA</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="select"
								class="col-lg-4 control-label control-label-left">Localidad</label>
							<div class="col-lg-8">
								<select class="form-control" id="select">
									<option>ROJAS</option>
									<option>SALTO</option>
									<option>PERGAMINO</option>
								</select>
							</div>
						</div>
					</fieldset>
				</form>

			</div>
			<div class="modal-footer">
				<a href="javascript:cancelarNuevoEditar();void(0);"
					class="btn btn-danger btn-sm"><span
					class="glyphicon glyphicon-remove-circle"></span> Cancelar</a> <a
					href="javascript:cancelarNuevoEditar();void(0);"
					class="btn btn-success btn-sm"><span
					class="glyphicon glyphicon-ok-circle"></span> Guardar</a>
			</div>
		</div>
	</div>
</div>