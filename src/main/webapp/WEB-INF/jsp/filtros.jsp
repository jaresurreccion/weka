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

			<div class="modal fade" id="myModalfiltroActivo" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header"
							style="background-color: rgb(22, 154, 24);">
							<h4 class="modal-title">Filtro guardado</h4>
						</div>
						<div class="modal-body" style="word-wrap: break-word;">
							<p>
								Resultado: <br>
								<c:out value='${filtroResultado}' />
							</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cerrar</button>
						</div>
					</div>

				</div>
			</div>

			<div class="container-fluid">
				<h3>
					<span class="badge badge-secondary">Selecion automatica</span>
				</h3>
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

					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-header">Informacion</div>
							<div class="card-body">
								Utilizando los algoritmos de seleccion de atributos, obtiene una
								recomendacion para utilziar los atributos más representativos
								del conjutos de datos. <br> <br>
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
				<h3>
					<span class="badge badge-secondary">Seleccion manual</span>
				</h3>
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
												<td><i class="fa fa-minus"
													style="color: rgb(135, 0, 0)" aria-hidden="true"
													onclick="eliminar(${loop.count})"></i></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div id="accordion">
							<div class="card">
								<div class="card-header" id="headingOne">
									<h5 class="mb-0">
										<button class="btn btn-link" data-toggle="collapse"
											data-target="#collapseOne" aria-expanded="true"
											aria-controls="collapseOne">Supervisado</button>
									</h5>
								</div>

								<div id="collapseOne" class="collapse show"
									aria-labelledby="headingOne" data-parent="#accordion">
									<div class="card-body">
										<div class="radio">
											<label><input type="radio" value="1" name="filter"
												onClick="addFiltro(this.value);">Discretize</label>
										</div>
										<div class="radio">
											<label><input type="radio" value="2" name="filter"
												onClick="addFiltro(this.value);">AttributeSelection</label>
										</div>
										<div class="radio">
											<label><input type="radio" value="3" name="filter"
												onClick="addFiltro(this.value);">Resample</label>
										</div>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-header" id="headingTwo">
									<h5 class="mb-0">
										<button class="btn btn-link collapsed" data-toggle="collapse"
											data-target="#collapseTwo" aria-expanded="false"
											aria-controls="collapseTwo">No supervisado</button>
									</h5>
								</div>
								<div id="collapseTwo" class="collapse"
									aria-labelledby="headingTwo" data-parent="#accordion">
									<div class="card-body">
									<div class="radio">
											<label><input type="radio" value="4" name="filter"
												onClick="addFiltro(this.value);">Normalize</label>
										</div>
										<div class="radio">
											<label><input type="radio" value="5" name="filter"
												onClick="addFiltro(this.value);">Discretize</label>
										</div>
										<div class="radio">
											<label><input type="radio" value="6" name="filter"
												onClick="addFiltro(this.value);">Resample</label>
										</div>
									</div>
								</div>
							</div>
						</div>
						<br>
						<div class="card">
							<div class="card-header">Configuración seleccionada:</div>
							<div class="card-body">
								<form action="/saveFilters" method="POST">

									<textarea disabled class="form-control z-depth-1"
										id="paramsArea" rows="10">
									</textarea>
									<input id="params" name="params" type="hidden" value="">
									<input id="filtroSelec" name="filtro" type="hidden" value="">
									<input id="optsArea" name="optsArea" type="hidden" value="">
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
		var inputHidden = document.getElementById("params");
		var optsArea =  document.getElementById("optsArea");
		var name = dom.cells[1].textContent;
		var inputArea = document.getElementById("paramsArea");
		inputArea.value = inputArea.value.trim() +name + ";";
		optsArea.value = inputArea.value.trim() +name + ";";
		inputHidden.value = inputHidden.value.trim() +value+",";
		dom.remove()
				}	

	function addFiltro(value){
	var dom = document.getElementById("filtroSelec");
	dom.value = value;
		}

	$(document).ready(function() {
        var filtroActivo = "<c:out value='${filtroActivo}'/>";
        if (filtroActivo) {
            $("#myModalfiltroActivo").modal();
            var inputArea = document.getElementById("paramsArea");
            inputArea.value = "<c:out value='${filtroActivoRemoveName}'/>" + "<c:out value='${filtroActivoTipo}'/> ";
            
        }
    });
	</script>
</body>

</html>