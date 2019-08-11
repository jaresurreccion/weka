<!DOCTYPE html>
<html>

<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<body id="body">
	<jsp:include page="headerPage.jsp"></jsp:include>
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div id="page-content-wrapper">
		<form action="/saveFilters" method="POST" id="formHidden">

			<div id="card-large">
				<h4>Seleccion de atributos</h4>
				<table class="table table-striped" id="tableSesiones">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Atributo</th>
							<th scope="col">Tipo</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sessionScope.atributos}" var="element"
							varStatus="loop">
							<tr>
								<th scope="row"><input type="checkbox" name="atributes"
									value="${loop.count}"></th>
								<td><c:out value="${element[0]}"></c:out></td>
								<td><c:out value="${element[1]}"></c:out></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		
			<div class="row">
				<div class="col">
					<div id="card">
						<h4>Seleccion de filtro</h4>
						<div class="radio">
							<label><input type="radio" name="filtro" value="1">Supervisado</label>
						</div>
						<div class="radio">
							<label><input type="radio" name="filtro" value="2">No
								supervisado</label>
						</div>
						<button type="submit"
							class="btn btn-md btn-outline-secondary m-0 px-3 py-2 z-depth-0 waves-effect">Guardar
							filtros</button>
					</div>
				</div>
			</div>
		</form>
		<c:if test="${filtroActivo}">
			<div id="filtroActivo" class="alert alert-success" role="alert">
				Filtros seleccionados: Remove: ${filtroActivoRemove} Tipo:
				${filtroActivoTipo}</div>
		</c:if>
			<div class="lineVertical">
		<div class="row">
			<div class="col">
				<div id="card">
				<form action="/seleccionAtributos" method="GET" id="formHidden">
					<h4>Seleccion de atributos</h4>
					<div class="radio">
						<label><input type="radio" name="optradio" value="1">BestFirst</label>
					</div>
					<div class="radio">
						<label><input type="radio" name="optradio" value="2">ScatterSearch</label>
					</div>
					<div class="radio">
						<label><input type="radio" name="optradio" value="3">RankSearch</label>
					</div>
					<button type="submit"
							class="btn btn-md btn-outline-secondary m-0 px-3 py-2 z-depth-0 waves-effect">Utilizar algoritmo</button>
					</form>
				</div>
				<c:if test="${listaAtributosFiltrosBol}">
			<div id="filtroActivo" class="alert alert-success" role="alert">
				Atributos recomendados:
				<c:forEach items="${sessionScope.listaAtributosFiltros}" var="element"
							varStatus="loop">
								<c:out value="${element}"></c:out>					
						</c:forEach>
				</div>
		</c:if>
			</div>
		</div>
		
	</div>
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</body>

</html>