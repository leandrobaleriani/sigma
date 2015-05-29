function showMsgInfo(mensaje, callback) {
	$('#mensajeInfoDetalle').html("<b><H4>" + mensaje + "</H4></b>");

	$('#btnCerrarInfoDetalle').unbind("click");
	if (callback) {
		newCallback = function() {
			callback();
			$('#mensajeInfo').modal("hide");
		}
		$('#btnCerrarInfoDetalle').bind("click", newCallback);
	}
	$('#mensajeInfo').modal(modalOptions).modal("show");
}

function showMsgError(mensaje) {
	$('#mensajeErrorDetalle').html("<b><H4>" + mensaje + "</H4></b>");

	$('#btnCerrarErrorDetalle').unbind("click");
//	if (callback) {
		newCallback = function() {
			callback();
			$('#mensajeError').modal("hide");
		}
		$('#btnCerrarErrorDetalle').bind("click", newCallback);
//	}
	$('#mensajeError').modal(modalOptions).modal("show");
}

function showErrors(mensajesValidacion){
	var mensajeHtml = getMensajeValidacion(mensajesValidacion);
	$("#errorPanel").html(mensajeHtml);
	showErrorComponents(mensajesValidacion);
	$("#errorPanel").show();
}

function getMensajeValidacion(mensajesValidacion){
	var mensajeValidacion = "";
	for(var i = 0; i < mensajesValidacion.length; i++){
		mensajeValidacion += '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> ' + mensajesValidacion[i].mensaje + '<br/>';
	}
	return mensajeValidacion;
}

function showErrorComponents(mensajesValidacion){
	$("[id^='wrapper_']").each(function( index ) {
		$( this ).removeClass("has-error");
	});
	for(var i = 0; i < mensajesValidacion.length; i++){
		$("#wrapper_" + mensajesValidacion[i].key).addClass("has-error");
	}
}