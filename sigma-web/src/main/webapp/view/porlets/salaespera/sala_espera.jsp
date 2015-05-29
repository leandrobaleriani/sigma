<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
	$(document).ready(function() {
		requestData_salaespera(false);
	});

	var interval_salaespera = null;
	var previous_data_salaespera = null;

	function requestData_salaespera(initialize_salaespera){
		$.ajax({
			url : '<c:url value="/porlet/salaespera!getSalaEspera.action"/>',
			global : false
		}).done(function(data) {
			if(!initialize_salaespera){ 
				interval_salaespera = setInterval(function(){requestData_salaespera(true);}, 10000);
				intervals.push(interval_salaespera);
			}
			if(null == previous_data_salaespera){
				previous_data_salaespera = data;
				$("#contenedorAjax_salaespera").html(data);
			} else {
				if(previous_data_salaespera != data){
					previous_data_salaespera = data;
					$("#contenedorAjax_salaespera").html(data);	
				}
			}
			
		});
	}
	
	function resetRequestData_salaespera(){
		clearInterval(interval_salaespera);
		var index = intervals.indexOf(interval_salaespera);
		intervals.splice(index, 1);
		requestData_salaespera(false);
	}
	
	function cancelarAtencion_salaespera(idAtencion) {
 		$.ajax({
			url : '<c:url value="/persona/atencion!finalizarAusencia.action"/>?idAtencion=' + idAtencion,
			global : false,
			dataType : "json"
		}).done(function(data) {
			var mensaje = data.mensaje;
			if (eval(data.exito)) {
				resetRequestData_salaespera();
			}
		});
	}
	
	
	function finalizarAtencion_salaespera(idAtencion) {
 		$.ajax({
			url : '<c:url value="/persona/atencion!finalizarAtencion.action"/>?idAtencion=' + idAtencion,
			global : false,
			dataType : "json"
		}).done(function(data) {
			var mensaje = data.mensaje;
			if (eval(data.exito)) {
				resetRequestData_salaespera();
			}
		});
	}
	
</script>

<div class="panel panel-primary" id="contenedorAjax_salaespera"></div>