<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript">
	$(function() {
		
		$('#field_fechaDesde').datepicker({
			autoclose : true,
			language : "es"
		});
		
		$('#field_fechaHasta').datepicker({
			autoclose : true,
			language : "es"
		});
		
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
</script>

<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h4 class="modal-title">
<!-- 				<img alt="" height="32px" hspace="5" src="images/stethoscope.png"> -->
					<b>Imprimir Planilla</b>
			</h4>
		</div>
		<div class="modal-body">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
				
					<div class="form-group" id="fnac_bloque">
						<label class="col-lg-3 control-label control-label-left">FECHA DESDE:</label>
						<div class="col-lg-3" id="wrapper_persona_fecha_nacimiento">
							<s:textfield id="field_fechaDesde" cssClass="form-control"
								placeholder="DD/MM/YYYY" name="persona.fechaNacimiento">
								<s:param name="value">
									<s:date name="persona.fechaNacimiento" format="dd/MM/yyyy" />
								</s:param>
							</s:textfield>
						</div>
						<label class="col-lg-3 control-label control-label-left">FECHA HASTA:</label>
						<div class="col-lg-3" id="wrapper_persona_fecha_nacimiento">
							<s:textfield id="field_fechaHasta" cssClass="form-control"
								placeholder="DD/MM/YYYY" name="persona.fechaNacimiento">
								<s:param name="value">
									<s:date name="persona.fechaNacimiento" format="dd/MM/yyyy" />
								</s:param>
							</s:textfield>
						</div>
					</div>
				
					<br /> <br />
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
