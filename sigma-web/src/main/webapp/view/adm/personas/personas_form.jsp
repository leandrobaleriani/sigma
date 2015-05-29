<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	$(function() {
		$('#field_fechaNac').datepicker({
			autoclose : true,
			language : "es"
		});
		
		$('#cmbProvincia').change(function(){
			var idProvincia = $(this).val();
			getPartidosByProvincia(idProvincia);
		});
		
		$('#cmbPartido').change(function(){
			var idPartido = $(this).val();
			getLocalidadesByPartido(idPartido);
		});
		
		$("input").bind('keyup', function (e) {
			if($(this).attr("id") != 'field_mail'){
				
			    var start = this.selectionStart;
			    var end = this.selectionEnd;

			    $(this).val( $(this).val().toUpperCase() );
			    this.setSelectionRange(start, end);
			}
		});
		
	});
	
	$(document).ready(function() {
		<s:if test="%{persona.id != null}"> 
		$("#cmbProvincia option").filter(function() {
       	    return $(this).val() == '<s:text name="persona.localidad.partido.idProvincia"/>'; 
       		}).prop('selected', true);
		$("#cmbProvincia").trigger("change");
		</s:if>
	});
	
	function getPartidosByProvincia(idProvincia){
		$.ajax
	    ({    	
	        url: '<c:url value="/parametrico/ajax!getPartidosByProvincia.action"/>?idProvincia=' + idProvincia,
	        dataType:  'html',
	        async: true,
	        success: function(resp){
	           $("#cmbPartido").html(resp);
	           <s:if test="%{persona.id != null}"> 
	           $("#cmbPartido option").filter(function() {
	       	    //may want to use $.trim in here
	       	    return $(this).val() == '<s:text name="persona.localidad.idPartido"/>'; 
	       		}).prop('selected', true);
	           $("#cmbPartido").trigger("change");
	           </s:if>
	        }
	    });
	}
	
	
	function getLocalidadesByPartido(idPartido){
		$.ajax
	    ({    	
	        url: '<c:url value="/parametrico/ajax!getLocalidadesByPartido.action"/>?idPartido=' + idPartido,
	        dataType:  'html',
	        async: true,
	        success: function(resp){
	           $("#cmbLocalidad").html(resp);
	           <s:if test="%{persona.id != null}"> 
	           $("#cmbLocalidad option").filter(function() {
	       	    //may want to use $.trim in here
	       	    return $(this).val() == '<s:text name="persona.idLocalidad"/>'; 
	       		}).prop('selected', true);
	           </s:if>
	        }
	    });
	}

	function guardarPersona() {

		var options = {
			dataType : "json",
			success : function(data) {
				var mensaje = data.mensaje;
				if (eval(data.exito)) {
					cerrarModal();
					search();
				} else {
					if (eval(data.validacion)) {
						showErrors(data.mensajesValidacion);
					} else {
						showMsgError(data.mensaje);
					}
				}
			}
		};

		$('#personaForm').ajaxForm(options);
		$('#personaForm').submit();
	}
	
	function guardarRecepcionarPersona() {

		var url = '<c:url value="/persona/adm!recepcionar.action"/>';
		
		var options = {
			url : url,	
			dataType : "json",
			success : function(data) {
				var mensaje = data.mensaje;
				if (eval(data.exito)) {
					cerrarModal();
					search();
				} else {
					if (eval(data.validacion)) {
						showErrors(data.mensajesValidacion);
					} else {
						showMsgError(data.mensaje);
					}
				}
			}
		};

		$('#personaForm').ajaxForm(options);
		$('#personaForm').submit();
	}
	
</script>

<div class="modal-dialog modal-md">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h4 class="modal-title">
				<img alt="" height="32px" hspace="5" src="images/paciente.png">
				<s:if test="%{persona.id == null}">
					<b>Nueva Persona</b>
				</s:if>
				<s:else>
					<b>Editar Persona</b>
				</s:else>
			</h4>
		</div>
		<div class="modal-body">
			<div class="alert alert-danger" style="display: none;"
				id="errorPanel" role="alert"></div>
			<form class="form-horizontal" id="personaForm"
				action='<c:url value="/persona/adm!save.action"/>' method="post">
				<s:hidden cssClass="form-control" name="persona.id"
					value="%{persona.id}" />
				<s:hidden cssClass="form-control" name="persona.paciente"
					value="%{persona.paciente}" />
				<fieldset>
					<div class="form-group">
						<label class="col-lg-3 control-label control-label-left">DOCUMENTO:</label>
						<div class="col-lg-2">
							<s:select list="#request.lstTiposDoc" cssClass="form-control"
								id="cmb_tipoDoc" name="persona.tipoDoc"></s:select>
						</div>
						<div class="col-lg-3" id="wrapper_persona_documento">
							<s:textfield id="field_persona_documento" cssClass="form-control"
								name="persona.doc" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label control-label-left">NOMBRE/APELLIDO:</label>
						<div class="col-lg-4" id="wrapper_persona_nombre">
							<s:textfield id="field_persona_nombre" cssClass="form-control"
								placeholder="NOMBRE" name="persona.nombre" />
						</div>
						<div class="col-lg-4" id="wrapper_persona_apellido">
							<s:textfield id="field_persona_apellido" cssClass="form-control"
								placeholder="APELLIDO" name="persona.apellido" />
						</div>
					</div>
					<div class="form-group" id="fnac_bloque">
						<label class="col-lg-3 control-label control-label-left">F.
							NACIMIENTO:</label>
						<div class="col-lg-3" id="wrapper_persona_fecha_nacimiento">
							<s:textfield id="field_fechaNac" cssClass="form-control"
								placeholder="DD/MM/YYYY" name="persona.fechaNacimiento">
								<s:param name="value">
									<s:date name="persona.fechaNacimiento" format="dd/MM/yyyy" />
								</s:param>
							</s:textfield>
						</div>
						<label class="col-lg-2 control-label control-label-left">SEXO:</label>
						<div class="col-lg-3">
							<s:select list="#request.lstSexo" cssClass="form-control"
								name="persona.sexo" listKey="name()" listValue="descripcion"></s:select>
						</div>
					</div>

					<div class="clearfix"></div>
					<br />
					<div class="form-group" id="fnac_bloque">
						<label class="col-lg-3 control-label control-label-left">OBRA
							SOCIAL:</label>
						<div class="col-lg-3">
							<s:select list="#request.lstObraSocial" cssClass="form-control"
								name="persona.datosMedico.idObraSocial" listKey="id"
								listValue="nombre"></s:select>
						</div>
						<label class="col-lg-2 control-label control-label-left">PLAN:</label>
						<div class="col-lg-3">
							<s:textfield id="field_barrio" cssClass="form-control"
								name="persona.datosMedico.planObraSocial" />
						</div>
					</div>
					<div class="form-group" id="fnac_bloque">
						<label class="col-lg-3 control-label control-label-left">NRO.
							CARNET:</label>
						<div class="col-lg-3">
							<s:textfield id="field_nroCarnet" cssClass="form-control"
								name="persona.datosMedico.nroCarnet" />
						</div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group">
						<label class="col-lg-3 control-label control-label-left">DIRECCI&Oacute;N:</label>
						<div class="col-lg-6" id="wrapper_persona_direccion">
							<s:textfield id="field_direccion" cssClass="form-control"
								name="persona.direccion" />
						</div>
					</div>
					<div class="form-group" id="wrapper_persona_barrio">
						<label class="col-lg-3 control-label control-label-left">BARRIO:</label>
						<div class="col-lg-4">
							<s:select list="#request.lstBarrio" cssClass="form-control"
								name="persona.idBarrio" listKey="id" listValue="nombre"></s:select>
						</div>
						<label class="col-lg-1 control-label control-label-left">OTRO:</label>
						<div class="col-lg-4">
							<s:textfield id="field_codPostal" cssClass="form-control"
								name="persona.observacionBarrio" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label control-label-left">TELÉFONO:</label>
						<div class="col-lg-3">
							<s:textfield id="field_telefono" cssClass="form-control"
								name="persona.telefono" />
						</div>
						<label class="col-lg-1 control-label control-label-left">MAIL:</label>
						<div class="col-lg-5">
							<s:textfield id="field_mail"
								cssClass="form-control input-lowercase" name="persona.mail" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label control-label-left">PROVINCIA:</label>
						<div class="col-lg-4">
							<s:select list="#request.lstProvincia" listKey="id"
								headerValue="- SELECCIONE -" headerKey="-1" id="cmbProvincia"
								listValue="nombre" cssClass="form-control"></s:select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label control-label-left">PARTIDO:</label>
						<div class="col-lg-4" id="wrapper_persona_partido">
							<select class="form-control" id="cmbPartido">
								<option value="">- SELECCIONE -</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label control-label-left">LOCALIDAD:</label>
						<div class="col-lg-4" id="wrapper_persona_localidad">
							<select class="form-control" name="persona.idLocalidad"
								id="cmbLocalidad">
								<option value="">- SELECCIONE -</option>
							</select>
						</div>
						<label class="col-lg-1 control-label control-label-left">CP:</label>
						<div class="col-lg-3" id="wrapper_persona_cp">
							<s:textfield id="field_codPostal" cssClass="form-control"
								name="persona.codPostal" />
						</div>
					</div>
					<s:if test="%{persona.id == null}">
					<br /> 
					<div class="form-group has-success">
						<label class="col-lg-3 control-label control-label-left">TIPO
							DE ANTENCIÓN:</label>
						<div class="col-lg-4">
							<s:select list="#request.lstTipoAtencion" cssClass="form-control"
								name="tipoAtencion" listKey="name()" listValue="descripcion"></s:select>
						</div>
					</div>
					</s:if>
				</fieldset>
			</form>


		</div>

		<div class="modal-footer">
			<a href="javascript:cerrarModal();void(0);"
				class="btn btn-danger btn-sm"><span
				class="glyphicon glyphicon-remove-circle"></span> Cancelar</a>
			<a href="javascript:guardarPersona();void(0);"
				class="btn btn-success btn-sm"><span
				class="glyphicon glyphicon-ok-circle"></span> Guardar</a>
				<s:if test="%{persona.id == null}">
					 <a
				href="javascript:guardarRecepcionarPersona();void(0);"
				class="btn btn-success btn-sm"><span
				class="glyphicon glyphicon-ok-circle"></span> Guardar y Recepcionar</a>
				</s:if>
		</div>
	</div>

</div>

