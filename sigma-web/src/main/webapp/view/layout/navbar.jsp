<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">

	var timeoutObject;
	var intervals = [];
	
	function clearIntervals(){
		 for(var i = 0; i < intervals.length; i++){
			 clearInterval(intervals[i]);
		 }
		 intervals.splice(0, intervals.length);
	}

	function showPersonas() {
		clearIntervals();
		$.ajax({
			url : '<c:url value="/persona/adm.action"/>'
		}).done(function(data) {
			$("#menuPersona").addClass("active");
			$("#menuAtencion").removeClass("active");
			$("#container").html(data);
		});
	}
	
	function showAtenciones() {
		clearIntervals();
		$.ajax({
			url : '<c:url value="/persona/atencion.action"/>'
		}).done(function(data) {
			$("#menuAtencion").addClass("active");
			$("#menuPersona").removeClass("active");
			$("#container").html(data);
		});
	}
	
	function showInformacion(){
		clearIntervals();
		$.ajax({
			url : '<c:url value="/view/info.jsp"/>'
		}).done(function(data) {
			$("#modalDialogInfo").html(data);
			$('#modalDialogInfo').modal('show');
		});
	}
	
</script>

<div class="navbar navbar-inverse"
	style="margin-bottom: 0px !important;">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-responsive-collapse">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#" style="font-size: 12px !important;"><img
			alt="" src="images/sigma-blanco.png" width="80px"></a>
	</div>
	<div class="navbar-collapse collapse navbar-responsive-collapse">
		<ul class="nav navbar-nav">
			<li class="active" id="menuPersona"><a href="javascript:showPersonas();"> <img alt="" src="images/paciente.png" width="22px"> Recepción
			</a></li>
		</ul>
		<ul class="nav navbar-nav">
			<li  id="menuAtencion"><a href="javascript:showAtenciones();"> <img alt="" src="images/stethoscope.png" width="22px"> Atención
			</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span><b>
						<s:property value="#session.loggedUser" />
						(<s:property value="#session.lugarAtencion.nombre" />)
				</b><b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#"><span class="glyphicon glyphicon-pencil"></span>
							Editar Perfil</a></li>
					<li class="divider"></li>
					<li><a href='javascript:showInformacion();void(0);'>
							<span class="glyphicon glyphicon-info-sign"></span> Informaci&oacute;n
					</a></li>
					<li class="divider"></li>
					<li><a href='<c:url value="j_spring_security_logout" />'>
							<span class="glyphicon glyphicon-off"></span> Salir
					</a></li>
				</ul></li>
		</ul>
	</div>
</div>
<div class="modal fade bs-example-modal-sm" id="modalDialogInfo">

</div>
