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
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-header">Algoritmos de seleccion de
								atributos</div>
							<div class="card-body">

								<form action="/seleccionAtributos" method="GET" id="formHidden">
									<div class="radio">
										<label><input type="radio" name="optradio" value="1">BestFirst</label>
									</div>
									<div class="radio">
										<label><input type="radio" name="optradio" value="2">ScatterSearch/GreedyStepwise</label>
									</div>
									<div class="radio">
										<label><input type="radio" name="optradio" value="3">RankSearch</label>
									</div>
									<button type="submit"
										class="btn btn-md btn-outline-secondary m-0 px-3 py-2 z-depth-0 waves-effect">Utilizar
										algoritmo</button>
								</form>
							</div>
						</div>
					</div>
					<br> <br>
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-header">Informacion</div>
							<div class="card-body">
								Utilizando los algoritmos de seleccion de atributos, obtiene una
								recomendacion para utilziar los atributos más representativos
								del conjutos de datos. <br>
								<c:if test="${listaAtributosFiltrosBol}">
									<div id="filtroActivo" class="alert alert-warning" role="alert">
										Atributos recomendados:
										<c:forEach items="${sessionScope.listaAtributosFiltros}"
											var="element" varStatus="loop">
											<c:out value="${element}"></c:out>
										</c:forEach>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-header">Seleccion de atributos</div>
							<div class="card-body table-responsive">
								<table class="table table-striped" id="tableSesiones">
									<thead>
										<tr>
											<th scope="col">#</th>
											<th scope="col">Atributo</th>
											<th scope="col">Tipo</th>
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${sessionScope.atributos}" var="element"
											varStatus="loop">
											<tr id="${loop.count}">
												<th scope="row"><input type="checkbox" name="atributes"
													value="${loop.count}"></th>
												<td><c:out value="${element[0]}"></c:out></td>
												<td><c:out value="${element[1]}"></c:out></td>
												<td><i class="fa fa-minus"  style="color: rgb(135, 0, 0)"aria-hidden="true" onclick="eliminar(${loop.count})"></i></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-header">Seleccion de filtros</div>
							<div class="card-body">
								<div class="radio">
									<label><input type="radio" name="filtro" value="1">Supervisado</label>
								</div>
								<div class="radio">
									<label><input type="radio" name="filtro" value="2">No
										supervisado</label>
								</div>

							</div>
						</div>
						<br>
						<div class="card">
							<div class="card-header">Configuración seleccionada:</div>
							<div class="card-body">
								<form action="/saveFilters" method="POST">

								<textarea disabled class="form-control z-depth-1" id="paramsArea" rows="10">
								</textarea>
										<br>
									<button type="submit"
										class="btn btn-md btn-outline-secondary m-0 px-3 py-2 z-depth-0 waves-effect">Guardar
										filtros</button>
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

	function eliminar(value){
		var dom = document.getElementById(value);
		var name = dom.cells[1].textContent 
		var inputArea = document.getElementById("paramsArea");
		inputArea.value = inputArea.value.trim() +";"+name;
		dom.remove()
				}	
	</script>
</body>

</html>