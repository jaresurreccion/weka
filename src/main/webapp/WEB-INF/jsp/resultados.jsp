<!DOCTYPE html>
<%@page
	import="org.springframework.web.servlet.mvc.support.RedirectAttributes"%>
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
				<form action="/weka" method="GET">
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
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
					<br>
					<div class="row">
						<div class="col-md-4 col-sm-12 col-xs-12">
							<div class="card">
								<div class="card-header">Conjunto de entrenamiento</div>
								<div class="card-body">
									<h4>Numero de instancias totales: ${numInstancias}</h4>
									<div class="form-group">
										<label for="nombreUsuario">Conjunto test</label> <input
											class="form-control" name="test" type="number" value="0">
									</div>
									<div class="form-group">
										<label for="nombreUsuario">Conjunto train</label> <input
											class="form-control" name="train" type="number" value="0">
									</div>
									<br>
									<button type="submit" class="btn btn-primary btn-block"
										id="create" onClick="comprobarInstancias()">Aplicar</button>
								</div>
							</div>
						</div>
						<div class="col-md-4 col-sm-12 col-xs-12">
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
					</div>
					</form>
					<c:if test="${resutadosBool}">
						<form action="/generatePDF" method="POST" >
							<div class="row">
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="card">

										<div class="card-header">Resultado</div>
										<div class="card-body">
											<button type="submit" class="btn btn-secondary">
												<i class="fa fa-file-pdf-o" aria-hidden="true"> Exportar
													a PDF</i>
											</button>
											<br>
											<br>
											<textarea class="form-control" name="resultados" 
												disabled="disabled" rows="30" id="resultados">${resultado}</textarea>
												<input type="hidden" value="${resultado}" name="resultadosHidden">
										</div>
									</div>
								</div>
							</div>
						</form>
					</c:if>
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

		function comprobarInstancias() {
		var test = document.getElementsByName("test");
		var train = document.getElementsByName("train");
		if (parseInt(test[0].value) + parseInt(train[0].value) > ${numInstancias}){
				alert("Seleccione el conjunto de test y train correcto");
				return false;
			}
		else {
			return true;
			}
		
			}
	</script>

</body>

</html>



