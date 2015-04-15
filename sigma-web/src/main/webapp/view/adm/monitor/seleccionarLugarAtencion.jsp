<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	<script>
	function seleccionarLugarAtencion(idLugarAtencion){
		var url = '<c:url value="/salaespera/lugarAtencion!seleccionarLugarAtencion.action"/>?seleccionLugarAtencion=' + idLugarAtencion;
		
		$.ajax({
			  dataType: "json",
			  url: url,
			  success: function(data){
				  if(data.exito){
					  loadSalaEspera();
				  }
			  }
			});
		
		
	}
	</script>
<hr/>
			<form class="form-horizontal" id="loginForm" method="POST">
				<fieldset>

					<div class="form-group">
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg6 col-centered">
							<s:iterator value="#session.lstLugaresAtencion" status="rowstatus">
								<a href='javascript:seleccionarLugarAtencion(<s:property value="id" />);void(0);'> 
									<s:if test="#rowstatus.odd == true">
										<div class="alert alert-dismissible alert-success divHover">
									</s:if> <s:else>
										<div class="alert alert-dismissible alert-info divHover">
									</s:else>
									<table width="100%" border="0">
										<tr>
											<td width="20%"><img alt="" src="../images/clinic.png">
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
		</fieldset>
		</form>
