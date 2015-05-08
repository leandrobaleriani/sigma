<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Sigma - Sistema de Guardia Médica</title>
</head>

<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/displayTagAjax.js"></script>
<script type="text/javascript" src="js/locales/bootstrap-datepicker.es.js"></script>



<link rel="stylesheet" href="css/bootstrap.css" media="screen">
<link rel="stylesheet" href="css/datepicker.css">
<link rel="stylesheet" href="css/global.css">

<body style="background-color: #F9F9F9!important;background-image: none!important;">
	<script type="text/javascript">
		function recepcionar(id) {
			$('#myModal').modal('show');
		}

		function cancelar() {
			$('#myModal').modal('hide');
		}

		function nuevoEditar(id) {
			$('#myModal2').modal('show');
		}

		function cancelarNuevoEditar() {
			$('#myModal2').modal('hide');
		}
		

		$(document).ready(function() {

			
			$.blockUI.defaults = $.extend($.blockUI.defaults, { 
				message: $("#progress"),
				css: { 
			        padding:        0, 
			        margin:         0, 
			        width:          '250px', 
			        height: 		'40px',
			        top:  			($(window).height() - 40) /2 + 'px', 
			        '-webkit-border-radius': '10px', 
				    '-moz-border-radius': '10px',
			        left:           ($(window).width() - 250) /2 + 'px',  
			        textAlign:      'center', 
			        color:          '#000', 
			        border:         '0px', 
			        backgroundColor:'#fff', 
			        cursor:         'wait',
			        zIndex: '5000'
			    },
			    overlayCSS: {
					backgroundColor: '#AAAAAA',
					opacity: 0.3
				}
			});
		
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
			
			$.ajaxSetup({ 
				type: 'post',
			    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			    cache: false
				});
			
			
			$("#textArea").keyup(function() {
				$(this).val($(this).val().toUpperCase());
			});
			
			$.ajax({
				url: '<c:url value="/persona/adm.action"/>'
				})
				.done(function( data ) {
					$("#container").html(data);
				});
			
		});
		
		modalOptions = {backdrop: false};
		
	</script>
	
	<c:set var="appCtx" value="${pageContext.request.contextPath}" scope="application" />

	<%@include file="layout/navbar.jsp"%>

	<div class="container-fluid" style="margin-top: 0px !important;">
		<div class="row" id="container">
		</div>
	</div>
	
	<%@include file="layout/loading.jsp"%>
	
	<%@include file="layout/mensajes.jsp"%>
	
</body>
</html>
