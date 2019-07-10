<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">
		<jsp:include page="header.jsp"></jsp:include>
<body>
	<div class="d-flex" id="wrapper">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div id="page-content-wrapper">
		<jsp:include page="nav.jsp"></jsp:include>
			<div class="container-fluid">
				<h1 class="mt-4">Mi Perfil</h1>
				<form class="form-container" action="/updateUser/${id}" method="POST">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="inputGroup-sizing-default">Nombre</span>
					</div>
					<input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" name="nombre" value="${nombre}">
				</div>
				
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="inputGroup-sizing-default">Primer apellido</span>
					</div>
					<input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" name="apellido1" value="${apellido1}">
				</div>			
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="inputGroup-sizing-default">Segundo apellido</span>
					</div>
					<input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" name="apellido2" value="${apellido2}">
				</div>
				<input type="hidden" name="id" value="${id}" > 		
				<div class="input-group mb-3"> 
 					<div class="input-group-prepend"> 
 						<span class="input-group-text" id="inputGroup-sizing-default">Activo</span> 
 					</div> 
					<select type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" name="activo" value="${activo}">
					<option value="1" >Activo</option>
    				<option value="0" >No activo</option>
 					</select> 
			    </div> 
				<button type="submit" class="btn btn-outline-success  btn-sm">Actualizar</button>
			</form>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

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