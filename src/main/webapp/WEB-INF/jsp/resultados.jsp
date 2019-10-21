<!DOCTYPE html>
<html lang="es">
<jsp:include page="header.jsp"></jsp:include>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="sidebar.jsp"></jsp:include>

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<jsp:include page="headerPage.jsp"></jsp:include>
		<form action="/weka" method="GET" >
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-header">Algoritmo seleccionado</div>
							<div class="card-body">

								<div class="form-group">
									<label for="algoritmo">${algoritmoActivoNombre}</label>
								</div>
							</div>
						</div>
						<br>
					</div>
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-header">Filtros seleccionado</div>
							<div class="card-body">
								Atributos a eliminar: ${filtroActivoRemoveName} <br> <br>
								Tipo de filtros: ${filtroActivoTipo}
							</div>
						</div>
					</div>
					<br>
					</div>
					<div class="row">
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-header">Conjunto de entrenamiento</div>
							<div class="card-body">

								<div class="form-group">
									<label for="nombreUsuario">Conjunto test</label> <input
										class="form-control" name="test">
								</div>
								<div class="form-group">
									<label for="nombreUsuario">Conjunto train</label> <input
										class="form-control" name="train">
								</div>
								<br>
								<button type="submit" class="btn btn-primary btn-block"
									id="create">Aplicar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			</form>
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



