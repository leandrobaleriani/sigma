<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

.divHover:HOVER {
	opacity: 0.9 !important;
	filter: alpha(opacity = 90) !important;
}
</style>
</head>
<body>
	<script type="text/javascript">
		
	</script>

	<div class="container"
		style="margin-top: 120px !important; max-width: none !important; width: 970px;">
		<div class="row row-centered">

			<form class="form-horizontal" id="loginForm" method="POST">
				<fieldset>

					<div class="form-group">
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-centered"
							style="text-align: center;">
							<img alt="" src="images/user.gif" height="100px"><img
								alt="" src="images/sigma-blanco.png" width="250px">
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-centered"
							style="text-align: center;">SELECCIONE UN LUGAR DE ATENCIÓN
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg4 col-centered">
							<s:iterator value="#session.lstLugarAtencion" status="rowstatus">
								<a href='<c:url value="/seleccionarLugarAtencion.action"/>?seleccionLugarAtencion=<s:property value="id" />'> 
									<s:if test="#rowstatus.odd == true">
										<div class="alert alert-dismissible alert-success divHover">
									</s:if> <s:else>
										<div class="alert alert-dismissible alert-info divHover">
									</s:else>
									<table width="100%" border="0">
										<tr>
											<td width="20%"><img alt="" src="images/clinic.png">
											</td>
											<td>
												<h3>
													<s:property value="nombre" />
												</h3>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center"><label><s:property
														value="direccion" /></label></td>
										</tr>
									</table>
						</div>
						</a>
						</s:iterator>
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
<%
	session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
%>
</html>