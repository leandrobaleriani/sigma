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

	});
	
	
	function imprimirPlanilla(){
		
		var fechaDesde = $.trim($("#field_fechaDesde").val());
		var horaDesde = $.trim($("#field_horaDesde").val());
		var fechaHasta = $.trim($("#field_fechaHasta").val());
		var horaHasta = $.trim($("#field_horaHasta").val());
			
		if(fechaDesde != ""){
			if(horaDesde != ''){
				if(fechaHasta != ''){
					if(horaHasta != ''){
						 var f = document.getElementById('FormPlanilla');
						  window.open('', 'FormPlanillaTarget');
						  f.submit();
						  cerrarModal_planilla();
					} else {
						
					}
				} else {
					
				}
			} else {
				
			}
		} else {
			
		}
	}
	
</script>

<form
	action='<c:url value="/reportes/atencion!imprimirPlanilla.action"/>' method="post" id="FormPlanilla" target="FormPlanillaTarget">
	<div class="modal-dialog modal-md">
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
					<div class="col-md-12">

						<label class="col-lg-4 control-label control-label-left">FECHA/HORA
							DESDE:</label>
						<div class="col-lg-3" id="wrapper_fechaDesde">
							<s:textfield id="field_fechaDesde" cssClass="form-control"
								placeholder="DD/MM/YYYY" name="desde">
							</s:textfield>
						</div>
						<div class="col-lg-2">
							<s:textfield id="field_horaDesde" cssClass="form-control" placeholder="HH:MM"
								name="desdeHora">
							</s:textfield>
						</div>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-md-12">

						<label class="col-lg-4 control-label control-label-left">FECHA/HORA
							HASTA:</label>
						<div class="col-lg-3">
							<s:textfield id="field_fechaHasta" cssClass="form-control"
								placeholder="DD/MM/YYYY" name="hasta">
							</s:textfield>
						</div>
						<div class="col-lg-2">
							<s:textfield id="field_horaHasta" cssClass="form-control" placeholder="HH:MM"
								name="hastaHora">
							</s:textfield>
						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<a href="javascript:cerrarModal_planilla();void(0);"
					class="btn btn-danger btn-sm"><span
					class="glyphicon glyphicon-remove-circle"></span> Cancelar</a> <a
					href="javascript:imprimirPlanilla();void(0);"
					class="btn btn-success btn-sm"><span
					class="glyphicon glyphicon-ok-circle"></span> Imprimir</a>
			</div>
		</div>
	</div>
</form>