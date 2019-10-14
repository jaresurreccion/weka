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

			<div class="container-fluid">
				<div class="row">
					<div class="col-6">
						<div class="card">
							<div class="card-header">Mi perfil</div>
							<div class="card-body">
								<form class="form-container" action="/updateUser/${id}"
									method="POST">
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-lg">Nombre</span>
										</div>
										<input type="text" class="form-control" aria-label="Default"
											aria-describedby="inputGroup-sizing-lg" name="nombre"
											value="${nombre}">
									</div>
									<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-lg">Primer
											apellido</span>
									</div>
									<input type="text" class="form-control" aria-label="Default"
										aria-describedby="inputGroup-sizing-lg" name="apellido1"
										value="${apellido1}">
								</div>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-lg">Segundo
											apellido</span>
									</div>
									<input type="text"
										class="form-control" aria-label="Default"
										aria-describedby="inputGroup-sizing-lg" name="apellido2"
										value="${apellido2}">
								</div>
								<input type="hidden" name="id" value="${id}">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-lg">Activo</span>
									</div>
									 <select type="text"
										class="form-control" aria-label="Default"
										aria-describedby="inputGroup-sizing-default" name="activo"
										value="${activo}">
										<option value="1">Activo</option>
										<option value="0">No activo</option>
									</select> 
								</div>
									 <br> <br>
									<button type="submit" class="btn btn-outline-success  btn-sm">Actualizar</button>
								</form>
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



