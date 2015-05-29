<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="mensajeInfo" class="modal fade">
	<div
		class="modal-dialog modal-vertical-centered modal-sm alert-success">
		<div class="modal-content">
			<div class="modal-body alert-success" style="text-align: center;">
				<div id="mensajeInfoDetalle"></div>
				<input class="btn btn-primary btn-sm" id="btnCerrarInfoDetalle"
					type="button" value="Aceptar">
			</div>
		</div>
	</div>
</div>

<div id="mensajeError" class="modal fade">
	<div
		class="modal-dialog modal-vertical-centered modal-sm alert-success">
		<div class="modal-content">
			<div class="modal-body alert-danger" style="text-align: center;">
				<div id="mensajeErrorDetalle"></div>
				<input class="btn btn-primary btn-sm" id="btnCerrarErrorDetalle"
					type="button" value="Aceptar">
			</div>
		</div>
	</div>
</div>