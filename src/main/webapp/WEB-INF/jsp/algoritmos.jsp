<!DOCTYPE html>
<html lang="es">
<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
						<form method="POST" action="/saveAlgoritmo">

							<div class="card">
								<div class="card-header">Seleccion de atributos</div>
								<div class="card-body">
									<div class="radio">
										<label><input type="radio" name="optradio" value="1">BestFirst</label>
									</div>
									<div class="radio">
										<label><input type="radio" name="optradio" value="2">ScatterSearch</label>
									</div>
									<div class="radio">
										<label><input type="radio" name="optradio" value="3">RankSearch</label>
									</div>
								</div>
							</div>
							<c:choose>
								<c:when test="${filtroActivoTipo == 'Supervisado'}">
									<div id="card">
										<div class="card-header">Clasificacion</div>
										<div class="card-body">
											<div class="radio">
												<label><input type="radio" name="optradio" value="7">NaivesBayes</label>
											</div>
											<div class="radio">
												<label><input type="radio" name="optradio" value="8">J48</label>
											</div>
											<div class="radio">
												<label><input type="radio" name="optradio" value="9">SVM</label>
											</div>
										</div>
									</div>

								</c:when>
								<c:otherwise>
									<div class="card">
										<div class="card-header">Agrupamiento</div>
										<div class="card-body">
											<div class="radio">
												<label><input type="radio" name="optradio" value="4">simpleKmeans</label>
											</div>
											<div class="radio">
												<label><input type="radio" name="optradio" value="5">Clustering
													Conceptual (COBWEB)</label>
											</div>
											<div class="radio">
												<label><input type="radio" name="optradio" value="6">Clustering
													Probabilistico (EM)</label>
											</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="mt-3">
								<button type="submit" class="btn btn-primary"
									id="botonSeleccionarAlgoritmo">Seleccionar algoritmo</button>
							</div>
						</form>
						<c:if test="${algoritmoActivo}">
							<div id="filtroActivo" class="alert alert-success" role="alert">
								Algoritmo seleccionado: ${algoritmoActivoNombre}</div>
						</c:if>
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