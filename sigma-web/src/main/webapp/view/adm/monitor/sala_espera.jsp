<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Sigma - Sistema de Guardia Médica</title>
</head>

<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>

<link rel="stylesheet" href="../css/bootstrap.css" media="screen">
<link rel="stylesheet" href="../css/global.css">
<link rel="stylesheet" href="../css/marquee.css">


<body scroll="no">
	<style>
span.tab {
	padding: 0 80px; /* Or desired space*/
}

.column_middle_grid1 {
	background: #0E93D4;
	margin: .4rem;
	padding-left: 20px;
	font-size: 18px;
}

.titulo {
	font-size: 60px !important;
}

.column_middle_grid3 {
	opacity: 0.5;
}

.column_middle_grid2 {
	background: #75B749;
	margin: .4rem;
	padding-left: 20px;
	font-size: 18px;
}

.profile_picture {
	position: relative;
}

.profile_picture span {
	position: absolute;
}

body {
	margin: 0;
	background: #000;
	overflow: hidden;
}

video {
	position: fixed;
	right: 0;
	bottom: 0;
	min-width: 100%;
	min-height: 100%;
	width: auto;
	height: auto;
	z-index: -100;
	background-size: cover;
	transition: 1s opacity;
}

.stopfade {
	opacity: .5;
}

#tablero {
	font-family: Myriad Pro, Regular;
	font-weight: 100;
	background: rgba(29, 79, 150, 0.5);
	color: white;
	width: 45%;
	height: 100vh;
	float: right;
	font-size: 1.2rem;
	text-align: center;
	overflow: hidden;
}

h1 {
	font-size: 3rem;
	text-transform: uppercase;
	margin-top: 20px;
	letter-spacing: .3rem;
}
</style>
<body>
	<script>
		$(document).ready(function() {
			init();
			loadSalaEspera();
		});

		var currentItem = 1;
		var totalItems = 0;
		var currentData = null;
		var currentDataUrg = null;

		function loadSalaEspera() {
			var options = {
				dataType : "json",
				success : function(data) {
					var refrescar = false;
					var refrescarUrg = false;
					var pacientes = data.data;
					var urgencias = data.urgencias;
					var inicio = false;

					if (currentData == null) {
						inicio = true;
						refrescar = true;
						currentData = pacientes;
					} else {
						refrescar = JSON.stringify(currentData) != JSON
								.stringify(pacientes);
						if (refrescar) {
							currentData = pacientes;
						}
					}

					if (urgencias == "") {
						currentDataUrg = "";
						$("#urgencias").html("");
						$("#label_urgencia").hide();
					} else {
						if (currentDataUrg == null) {
							refrescarUrg = true;
							currentDataUrg = urgencias;
						} else {
							refrescarUrg = JSON.stringify(currentDataUrg) != JSON
									.stringify(urgencias);
							if (refrescarUrg) {
								currentDataUrg = urgencias;
							}
						}
					}

					if (refrescarUrg) {
						$("#urgencias").html("");
						$.each(data.urgencias,
										function(i, val) {
											var atencion = new Urgencia(val);
											$("#urgencias").append(atencion.getHTML());
										});
						$("#label_urgencia").show();
					}

					if (refrescar) {
						if(!inicio){
							document.getElementById('multiaudio5').play();
						}
						
						$("[id^='espera_']").each(function(index) {
							$(this).remove();
						});
						$
								.each(
										data.data,
										function(i, val) {
											var atencion = new Atencion(val.nombre, eval(val.enEspera), val.tipoAtencion, i);
											$("#tablero").append(atencion.getHTML());
											$("#espera_" + i).hide();
										});
						currentItem = 0;
						totalItems = data.data.length;
						if (totalItems > 0) {
							$("#espera_" + currentItem).show("fold", options,
									500, function() {
										callback();
									});
						}
					}

					setTimeout(function() {
						loadSalaEspera();
					}, 10000);
				}
			};
			$('#salaEsperaForm').ajaxForm(options);
			$('#salaEsperaForm').submit();
		}

		function callback() {
			currentItem++;
			if (currentItem < totalItems) {
				$("#espera_" + currentItem).show("fold", {}, 500, function() {
					callback();
				});
			} else if (currentItem == totalItems) {
				$("#espera_" + currentItem).show("fold", {}, 500);
			}
		}

		var videoPlaying = 0;
		var videos = [ "video7.webm", "video6.webm", "video5.webm", "video4.webm", "video3.webm", "video2.webm" ];
		function init() {

			var vid = document.getElementById("bgvid");
			vid.volume = 0.9;
// 						vid.volume = 0.0;
			vid.src = '<c:url value="/videos/' + videos[videoPlaying] + '"/>';
			vid.load();
			vid.play();
			vid
					.addEventListener(
							'ended',
							function() {
								videoPlaying++;
								if (videoPlaying == videos.length) {
									videoPlaying = 0;
								}
								vid.src = '<c:url value="/videos/' + videos[videoPlaying] + '"/>';
								vid.load();
								vid.play();
							});
		}
		
		
		function Atencion(nombre, enEspera, tipoAtencion, index){
			this.nombre = nombre;
			this.estado = (enEspera) ? "En Espera" : "En Atención";
			this.tipoAtencion = tipoAtencion;
			this.index = index;
			this.cssEstado = (enEspera) ? "column_middle_grid1" : "column_middle_grid2";
			this.getHTML = function () {
				return '<div id="espera_'+ this.index +'" class="' + this.cssEstado + '" style="display: hidden;"><table width="100%"><tr><td colspan="2">'
				+ this.estado
				+ '</td></tr><tr><td width="70%" align="left"><h2>:: '
				+ this.nombre
				+ '</h2></td><td width="30%"><h3>'
				+ this.tipoAtencion
				+ '</h3></td></tr></table></div>';
		    }
		}
		
		
		function Urgencia(nombre){
			this.nombre = nombre;
			this.getHTML = function(){
				var url = '<c:url value="/images/emergencia.png"/>';
				return '<span class="tab"></span><img alt="" height="22px" hspace="5" src="' + url +'">' + this.nombre;
			}
		}
		
	</script>
	
	<audio id="multiaudio5" src="<c:url value="/sounds/alarm.wav"/>" preload="auto"></audio>

	<form action='<c:url value="/salaespera/show!showSalaEspera.action"/>'
		id="salaEsperaForm"></form>
	<video poster="" id="bgvid"> <source id="srcVideo"
		type="video/webm"></video>

	<div id="tablero">
		<h1 class="titulo">Guardia M&eacute;dica</h1>
		<div style="color: red !important; display: none;" id="label_urgencia">
			<div class="marquee">
			<h1><b>EN URGENCIA</b></h1>
				<h1>
					<marquee id="urgencias"></marquee>
				</h1>
			</div>
		</div>
		<hr />
	</div>

</body>
</html>