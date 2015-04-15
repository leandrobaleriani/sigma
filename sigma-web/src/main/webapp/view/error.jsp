<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>


<link rel="stylesheet" href="css/bootstrap.css" media="screen">
<link rel="stylesheet" href="css/global.css">
</head>

<body>

	<div class="container"
		style="margin-top: 200px !important; max-width: none !important; width: 970px;">
		<div class="row">
			<div class="panel panel-danger">
				<div class="panel-heading">
					<h3 class="panel-title">ERROR GENERAL EN EL SISTEMA</h3>
				</div>
				<div class="panel-body row-centered">
				<p>Se há producido un error en el Sistema.</p>
					<p>Por favor contacte al Soporte Técnico con la siguiente
						información :</p>
					<h4 style="color: red;font-weight: bold;">
						Código de Error:
						<s:property value="exception" />
					</h4>
					<br /> <a href="javascript:window.history.back()"
						class="btn btn-danger btn-md" role="button">Volver</a>
				</div>
			</div>
		</div>
	</div>

	<!-- 	<div -->
	<!-- 		class="col-xs-offset-3 col-sm-offset-3 col-md-offset-3 col-lg-offset-3 col-xs-6 col-sm-6 col-md-6 col-lg-6 alert alert-info" -->
	<!-- 		style="margin-top: 15%;"  -->
	<!-- 		role="alert"> -->
	<!-- 		<table> -->
	<!-- 			<tr> -->
	<!-- 				<td><img alt="" height="120px" src="images/error.png"></td> -->
	<!-- 				<td></td> -->
	<!-- 			</tr> -->
	<!-- 			<tr> -->
	<!-- 				<td colspan="2" align="center"><a -->
	<!-- 					href="javascript:window.history.back()" -->
	<!-- 					class="btn btn-danger btn-lg" role="button">Volver</a></td> -->
	<!-- 			</tr> -->
	<!-- 		</table> -->
	<!-- 	</div> -->
</body>
</html>