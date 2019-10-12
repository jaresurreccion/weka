<!DOCTYPE html>
<html lang="es">
<jsp:include page="header.jsp"></jsp:include>
<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="sidebar.jsp"></jsp:include>

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<jsp:include page="headerPage.jsp"></jsp:include>

			<div class="container-fluid">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">Bienvenido a WekaWeb</div>
							<div class="card-body">
							<p class="card-text">WekaWeb es una aplicaci�n web basada en
								la herramienta de mineria de datos Weka. Weka es una colecci�n
								de algoritmos de aprendizaje autom�tico para tareas de miner�a
								de datos. Contiene herramientas para la preparaci�n de datos,
								clasificaci�n, regresi�n, agrupamiento, miner�a de reglas de
								asociaci�n y visualizaci�n. Weka es un software de c�digo
								abierto emitido bajo la Licencia P�blica General de GNU. Hemos
								reunido varios cursos gratuitos en l�nea que ense�an el
								aprendizaje autom�tico y la miner�a de datos con Weka. Los
								videos de los cursos est�n disponibles en Youtube. �Weka apoya
								el aprendizaje profundo!</p>
						</div>
						</div>
					</div>

				</div>





			</div>
		</div>
	</div>



	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

</body>

</html>



