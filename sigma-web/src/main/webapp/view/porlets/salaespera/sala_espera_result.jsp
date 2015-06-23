<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="panel-heading">
	<h3 class="panel-title">
		<span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp; Sala de
		Espera
	</h3>
	En Espera&nbsp;&nbsp;&nbsp;<span class="badge"><s:property value="#request.enEspera"/> </span>&nbsp;&nbsp;&nbsp;
	En Atención&nbsp;&nbsp;&nbsp;<span class="badge"><s:property value="#request.enAtencion"/> </span>
</div>
<div class="panel-body" style="overflow: auto; height: 400px;">
	<table id="tablero" class="table table-bordered table-hover ">
		<tbody id="cuerpo_tabla">
			<s:iterator value="#request.atenciones">
				<tr class="active">
					<td><h4>
							<b><s:property value="nombre" /></b>
						</h4> <span class="glyphicon glyphicon-unchecked"></span>&nbsp;Tipo
						Atención:&nbsp;<b><s:property value="tipoAtencion" /></b><br />
						<span class="glyphicon glyphicon-log-in"></span>&nbsp;Ingreso:&nbsp;<b><s:property
								value="fechaRecepcion" /> </b><br> <span
						class="glyphicon glyphicon-log-out"></span>&nbsp;Atención:&nbsp;<b><s:property
								value="fechaAtencion" /> </b><br> <br> <a role="button"
						href="javascript:imprimirCabo_salaespera('<s:property value="id" />');void(0);"
						class="btn btn-info btn-xs"><span
							class="glyphicon glyphicon-print"></span>&nbsp;&nbsp;Imprimir
							Cabo</a>&nbsp;&nbsp; <s:if test="enEspera">
							<a class="btn btn-danger btn-xs"
								href="javascript:cancelarAtencion_salaespera('<s:property value="id" />');void(0);"
								role="button"><span class="glyphicon glyphicon-minus"></span>&nbsp;&nbsp;Ausente</a>
							<br />
						</s:if> <s:else>
							<a class="btn btn-danger btn-xs"
								href="javascript:finalizarAtencion_salaespera('<s:property value="id" />');void(0);"
								role="button"><span class="glyphicon glyphicon-minus"></span>&nbsp;&nbsp;Finalizar</a>
						</s:else></td>
					<td><h4>
							<s:if test="enEspera">
								<span class="label label-primary">En Espera</span>
							</s:if>
							<s:else>
								<span class="label label-success">En Atención</span>
							</s:else>
						</h4></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>