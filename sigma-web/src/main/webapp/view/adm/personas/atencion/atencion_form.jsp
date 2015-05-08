<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript">
	$(function() {
		$("textarea").bind('keyup', function(e) {
			if ($(this).attr("id") != 'field_mail') {

				var start = this.selectionStart;
				var end = this.selectionEnd;

				$(this).val($(this).val().toUpperCase());
				this.setSelectionRange(start, end);
			}
		});

	});

	$(document).ready(function() {
		setTimeout(function() {
			$("#field_diagnostico").focus();
		}, 1);
	});

	function finalizar() {
		var options = {
			url : '<c:url value="/persona/atencion!finalizar.action"/>',
			dataType : "json",
			success : function(data) {
				var mensaje = data.mensaje;
				if (eval(data.exito)) {
					showMsgInfo(data.mensaje, function() {
						cerrarModal();
						refreshSalaEspera();
					});
				} else {
					if (eval(data.validacion)) {
						showErrors(data.mensajesValidacion);
					} else {
						showMsgError(data.mensaje, function() {
						});
					}
				}
			}
		};

		$('#finalizarForm').ajaxForm(options);
		$('#finalizarForm').submit();
	}
</script>

<form id="finalizarForm">
	<s:hidden name="idAtencion" value="%{atencion.id}" id="atencion_hidden" />
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title">
					<img alt="" height="32px" hspace="5" src="images/stethoscope.png">
					<s:if test="%{atencion.finAtencion == null}">
						<b>Atención Médica</b>
					</s:if>
					<s:else>
						<b>Consulta de Atención</b>
					</s:else>
				</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp; <b><s:property
									value="%{atencion.persona.apellido}" />&nbsp;<s:property
									value="%{atencion.persona.nombre}" /> (<s:property
									value="%{atencion.tipoAtencion}" />)</b>
						</h4>
						<span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;RECEPCION:
						<b><s:date name="%{atencion.fechaRecepcion}"
								format="dd/MM/yyyy HH:mm" /></b> <br /> <span
							class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;INICIO
						ATENCIÓN: <b><s:date name="%{atencion.inicioAtencion}"
								format="dd/MM/yyyy HH:mm" /></b>
						<s:if test="%{atencion.finAtencion != null}">
							<br />
							<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;FIN
						ATENCIÓN: <b><s:date name="%{atencion.finAtencion}"
									format="dd/MM/yyyy HH:mm" /></b>
						</s:if>
						<br /> <br />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-paperclip"></span>&nbsp;&nbsp;Datos
								Personales
							</div>
							<div class="panel-body">
								<p>
									Edad:<b><s:property value="%{atencion.persona.edad}" /></b>
								<p></p>
								Sexo: <b><s:property value="%{atencion.persona.sexo}" /></b>
								<p></p>
								Localidad: <b><s:property
										value="%{atencion.persona.localidad.nombre}" /></b>
								<p></p>
								<p>
									Barrio: <b><s:property
											value="%{atencion.persona.barrio.nombre}" /></b>
									<s:if test="%{atencion.persona.observacionBarrio} != ''">
											- <b><s:property
												value="%{atencion.persona.observacionBarrio}" /></b>
									</s:if>
								</p>
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-sm-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-folder-open"></span>&nbsp;&nbsp;Historial
								de Guardia
							</div>
							<div class="panel-body" id="contenedorAjax_historial">
								<%@include file="atencion_historial_result.jsp"%>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-paperclip"></span>&nbsp;&nbsp;Datos
								Médicos
							</div>
							<div class="panel-body">
								<p>
									Obra Social: <b><s:property
											value="%{atencion.persona.datosMedico.obraSocial.nombre}" /></b>
								</p>

								<p>
									Plan: <b><s:property
											value="%{atencion.persona.datosMedico.planObraSocial}" /></b>
								</p>
								<p>
									Carnet: <b><s:property
											value="%{atencion.persona.datosMedico.nroCarnet}" /></b>
								</p>
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-sm-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Diagnóstico
							</div>
							<div class="panel-body">
								<s:if test="%{atencion.finAtencion == null}">
									<textarea id="field_diagnostico" name="diagnostico"
										class="form-control" rows="5"></textarea>
								</s:if>
								<s:else>
									<b><s:property value="%{atencion.diagnostico}" /></b>
								</s:else>
							</div>
						</div>
					</div>
				</div>

			</div>

			<div class="modal-footer">
				<a href="javascript:cerrarModal();void(0);"
					class="btn btn-danger btn-sm"><span
					class="glyphicon glyphicon-remove-circle"></span> Cancelar</a>
				<s:if test="%{atencion.finAtencion == null}">
					<a href="javascript:finalizar();void(0);"
						class="btn btn-success btn-sm"><span
						class="glyphicon glyphicon-ok-circle"></span> Finalizar Caso</a>
				</s:if>
			</div>
		</div>
	</div>
</form>