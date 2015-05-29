<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>


$(document).ready(function(){
	requestData_atencionultimas(false);
});

var interval_atencionultimas = null;
var previous_data_atencionultimas = null;

function requestData_atencionultimas(initialize_atencionultimas){
	$.ajax({
		url :'<c:url value="/porlet/atencionultimas!showAtencionesUltimas.action"/>',
		global : false
	}).done(function(data) {
		if(!initialize_atencionultimas){
			interval_atencionultimas = setInterval(function(){requestData_atencionultimas(true);}, 10000);
			intervals.push(interval_atencionultimas);
		}
		if(null == previous_data_atencionultimas){
			previous_data_atencionultimas = data;
			$("#contenedorAjax_atencionultimas").html(data);
		} else {
			if(previous_data_atencionultimas != data){
				previous_data_atencionultimas = data;
				$("#contenedorAjax_atencionultimas").html(data);	
			}
		}
	});
}

</script>
<div class="panel panel-info">
<div class="panel-heading">
	<h3 class="panel-title">
		<span class="glyphicon glyphicon-check"></span>&nbsp;&nbsp;Últimas
		Atenciones
	</h3>
</div>
<div class="panel-body"  id="contenedorAjax_atencionultimas">
</div>
</div>
