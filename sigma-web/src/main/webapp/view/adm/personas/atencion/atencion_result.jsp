<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:iterator value="#request.atenciones" var="atencion">
	<div class="row row-centered">
		<div class="col-md-12 col-centered">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp; <b><s:property
								value="persona.apellido" />&nbsp;<s:property
								value="persona.nombre" /> (<s:property value="tipoAtencion" />)</b>
						- RECEPCION: <b><s:date name="fechaRecepcion"
								format="dd/MM/yyyy HH:mm" /></b>
					</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-12 modal-footer">
						<a href="javascript:cerrarModal();void(0);"
							class="btn btn-danger btn-sm"><span
							class="glyphicon glyphicon-remove-circle"></span> Finalizar</a> <a
							href="javascript:atender('<s:property value="id" />');void(0);"
							class="btn btn-success btn-sm"><span
							class="glyphicon glyphicon-ok-circle"></span> Atender</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:iterator>
