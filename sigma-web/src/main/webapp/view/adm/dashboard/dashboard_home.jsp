<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	var ctx = document.getElementById("myChart").getContext("2d");
	var data = [ {
		value : 300,
		color : "#F7464A",
		highlight : "#FF5A5E",
		label : "Red"
	}, {
		value : 50,
		color : "#46BFBD",
		highlight : "#5AD3D1",
		label : "Green"
	}, {
		value : 100,
		color : "#FDB45C",
		highlight : "#FFC870",
		label : "Yellow"
	} ];

	var options = {
		//Boolean - Whether we should show a stroke on each segment
		segmentShowStroke : false,

		//String - The colour of each segment stroke
		segmentStrokeColor : "#fff",

		//Number - The width of each segment stroke
		segmentStrokeWidth : 1,

		//Number - The percentage of the chart that we cut out of the middle
		percentageInnerCutout : 0, // This is 0 for Pie charts

		//Number - Amount of animation steps
		animationSteps : 100,

		//String - Animation easing effect
		animationEasing : "easeOutBounce",

		//Boolean - Whether we animate the rotation of the Doughnut
		animateRotate : true,

		//Boolean - Whether we animate scaling the Doughnut from the centre
		animateScale : false
	};

	var myLineChart = new Chart(ctx).Pie(data, options);
	
	
	$(document).ready(function(){
		requestData_dashboard(false);
	});

	var interval_dashboard = null;

	function requestData_dashboard(initialize_dashboard){
		$.ajax({
			url : '<c:url value="/dashboard/show!showDashboard.action"/>',
			dataType: 'json',
			global : false
		}).done(function(data) {
			if(!initialize_dashboard){
				interval_dashboard = setInterval(function(){requestData_dashboard(true);}, 10000);
				intervals.push(interval_dashboard);
			}
			populateData(data.data);
		});
	}

	function resetRequestData_dashboard(){
		clearInterval(interval_dashboard);
		var index = intervals.indexOf(interval_dashboard);
		intervals.splice(index, 1);
		requestData_dashboard(false);
	}
	
	function populateData(data){
		$("#enEspera_label").html();
// 		alert(data.enEspera);
// 		alert(data.enAtencion);
	}
	
</script>
<div class="container-fluid">
	<div class="row">
		<div
			class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-md-offset-4 col-xs-offset-4 col-sm-offset-4 col-lg-offset-4">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;
						Sala de Espera
					</h3>
					En Espera&nbsp;&nbsp;&nbsp;<span class="badge" id="enEspera_label"></span>&nbsp;&nbsp;&nbsp;
					En Atención&nbsp;&nbsp;&nbsp;<span class="badge" id="enAtencion_label"></span>
				</div>
				<div class="panel-body" style="text-align: center;">
					<canvas id="myChart" width="200" height="200"></canvas>
					<br />
					<br />
					<br />
					<br />
					<table class="table table-striped table-bordered ">
						<thead>
							<tr>
								<th>#</th>
								<th>Column heading</th>
								<th>Column heading</th>
								<th>Column heading</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>Column content</td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

</div>
