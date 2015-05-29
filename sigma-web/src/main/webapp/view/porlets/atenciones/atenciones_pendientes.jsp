<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<script>


$(document).ready(function(){
	requestData_atencionpendientes(false);
});

var interval_atencionpendientes = null;
var previous_data_atencionpendientes = null;

function requestData_atencionpendientes(initialize_atencionpendientes){
	$.ajax({
		url :'<c:url value="/porlet/atencionpendiente!showAtencionesPendientes.action"/>',
		global : false
	}).done(function(data) {
		if(!initialize_atencionpendientes){
			interval_atencionpendientes = setInterval(function(){requestData_atencionpendientes(true);}, 10000);
			intervals.push(interval_atencionpendientes);
		}
		if(null == previous_data_atencionpendientes){
			previous_data_atencionpendientes = data;
			$("#contenedorAjax_atencionpendientes").html(data);
		} else {
			if(previous_data_atencionpendientes != data){
				previous_data_atencionpendientes = data;
				$("#contenedorAjax_atencionpendientes").html(data);	
			}
		}
	});
}

</script>
<div class="panel panel-warning">
	<div class="panel-heading">
		<h3 class="panel-title">
			<span class="glyphicon glyphicon-exclamation-sign"></span>&nbsp;&nbsp;
			Atenciones sin Finalizar
		</h3>
	</div>
	<div class="panel-body"  id="contenedorAjax_atencionpendientes">
	</div>
</div>
