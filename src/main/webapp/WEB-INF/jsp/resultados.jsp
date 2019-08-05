<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<body id="body">
	<jsp:include page="headerPage.jsp"></jsp:include>
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div id="page-content-wrapper">
		<div class="row">
			<div class="col">
				<div id="card">
				<h4>Algoritmo seleccionado</h4>
				<div class="form-group">
						<label for="algoritmo">${algoritmoActivoNombre}</label>
					</div>
				</div>
			</div>
			<div class="col">
				<div id="card">
				<h4>Filtros seleccionado</h4>
				<label for="remove">${filtroActivoRemove}</label>
				<label for="tipo">${filtroActivoTipo}</label>
				</div>
			</div>
			<div class="col">
			<div id="card">
					<div class="form-group">
						<label for="nombreUsuario">Conjunto test</label> 
						<input class="form-control" name="test" > 
					</div>
					<div class="form-group">
						<label for="nombreUsuario">Conjunto train</label> 
						<input class="form-control" name="train" > 
					</div>
					<button type="button" class="btn btn-primary btn-block" id="create">Aplicar</button>
			</div>
			</div>
		</div>
	</div>
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</body>

</html>