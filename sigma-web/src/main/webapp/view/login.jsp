<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sistema de Guardia Médica v1.0</title>

<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>


<link rel="stylesheet" href="css/bootstrap.css" media="screen">
<link rel="stylesheet" href="css/global.css">
<style type="text/css">
body {
	color: white;
}
</style>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {

			$("#j_username").focus();

		});

		$(function() {

			$("#btnIngresar").click(function() {
				$("#loginForm").submit();
			});
			
			$("#btnContrasenia").click(function() {
				alert("EVNIAR MAIL PASSWORD");
				//$("#loginForm").attr("action", "");
				//$("#loginForm").submit();
			});

		});
	</script>

	<div class="container"
		style="margin-top: 120px !important; max-width: none !important; width: 970px;">
		<div class="row row-centered">

			<c:url var="loginUrl" value="/j_spring_security_check"></c:url>
			<form class="form-horizontal" id="loginForm" action="${loginUrl}"
				method="POST">
				<fieldset>
					<div class="form-group">
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-centered"
							style="text-align: center;">
							<img alt="" src="images/user.gif" height="100px">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-centered"
							style="text-align: center;">
							<img alt="" src="images/sigma-blanco.png" width="250px">
						</div>
					</div>

					<s:if test='%{#session["SPRING_SECURITY_LAST_EXCEPTION"] != null}'>
						<div class="form-group has-error has-feedback">
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 col-centered"
								style="text-align: center;">
								<input class="form-control" id="j_username" name="j_username"
									placeholder="USUARIO" type="text"> <span
									class="glyphicon glyphicon-warning-sign form-control-feedback"
									aria-hidden="true"></span> <span id="inputWarning2Status"
									class="sr-only"></span>
							</div>
						</div>

					</s:if>
					<s:else>
						<div class="form-group">
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 col-centered"
								style="text-align: center;">
								<input class="form-control" id="j_username" name="j_username"
									placeholder="USUARIO" type="text">
							</div>
						</div>
					</s:else>

					<s:if test='%{#session["SPRING_SECURITY_LAST_EXCEPTION"] != null}'>
						<div class="form-group has-error has-feedback">
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 col-centered"
								style="text-align: center;">
								<input class="form-control" id="j_password" name="j_password"
									placeholder="PASSWORD" type="password">
									 <span
									class="glyphicon glyphicon-warning-sign form-control-feedback"
									aria-hidden="true"></span> <span id="inputWarning2Status"
									class="sr-only"></span>
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="form-group">
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 col-centered"
								style="text-align: center;">
								<input class="form-control" id="j_password" name="j_password"
									placeholder="PASSWORD" type="password">
							</div>
						</div>
					</s:else>

					<div class="form-group">
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg2 col-centered"
							style="text-align: left;">
							<a href="javascript:void(0);" id="btnContrasenia"
								class="btn btn-info btn-sm"><span
								class="glyphicon glyphicon-question-sign"></span> ¿Olvidó su Contraseña?</a> <input
								type="submit" style="display: none;">
						</div>
						<div class="col-xs-2 col-sm-2 col-md-2 col-lg2 col-centered"
							style="text-align: right;">
							<a href="javascript:void(0);" id="btnIngresar"
								class="btn btn-success btn-sm"><span
								class="glyphicon glyphicon-ok-circle"></span> Ingresar</a> <input
								type="submit" style="display: none;">
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-centered"
							style="text-align: center;"></div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="row row-centered">

			<s:if test='%{#session["SPRING_SECURITY_LAST_EXCEPTION"] != null}'>
				<div
					class="col-xs-4 col-sm-4 col-md-4 col-lg-4 alert alert-danger  col-centered"
					role="alert">
					<s:property
						value='#session["SPRING_SECURITY_LAST_EXCEPTION"].message' />
				</div>
			</s:if>
		</div>
	</div>
</body>
<%session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION"); %>
</html>