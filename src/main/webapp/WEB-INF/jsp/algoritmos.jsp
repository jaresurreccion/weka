<!DOCTYPE html>
<html lang="es">

<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<body>
	<div class="d-flex" id="wrapper">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div id="page-content-wrapper">
		<jsp:include page="nav.jsp"></jsp:include>	
			<div class="container-fluid">
				<h2 class="mt-4">Weka</h2>
								<h2>Lista de dataset</h2>
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Nombre</th>
							<th scope="col">Creado</th>
							<th scope="col">Comentario</th>
							<th scope="col">Acción</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ListaFicheros}" var="element" varStatus="loop">

							<tr>
								<th scope="row">${loop.count}</th>
								<td><c:out value="${element.nombreFichero}" /></td>
								<td><fmt:formatDate value="${element.creado}"
										pattern="dd-MMM-yyyy" /></td>
								<td><c:out value="${element.comentario}" /></td>
								<td><a class="btn btn-primary"
									href="/weka/${element.idFichero}" aria-label="Delete"> <i
										class="fa fa-cog" aria-hidden="true"></i>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br><br>
				<div>
					<div class="input-group">
						<span class="input-group-text">J48</span> <input id="msg"
							type="text" class="form-control" name="msg"
							placeholder="Additional Info" value="${J48}">
					</div>
					<div class="input-group">
						<span class="input-group-text">PART</span> <input id="msg"
							type="text" class="form-control" name="msg"
							placeholder="Additional Info" value="${PART}">
					</div>
					<div class="input-group">
						<span class="input-group-text">TABLE</span> <input id="msg"
							type="text" class="form-control" name="msg"
							placeholder="Additional Info" value="${TABLE}">
					</div>
					<div class="input-group">
						<span class="input-group-text">DECISION</span> <input id="msg"
							type="text" class="form-control" name="msg"
							placeholder="Additional Info" value="${DECISION}">
					</div>
				</div>
				<br>
				<br>
				<c:if test="${error}">
					<div class="container">
						<div class="alert alert-danger">
							<strong>¡Error!</strong> No se ha podido realizar la acción requerida
						</div>
					</div>
				</c:if>
				<br> <br> <br>
				<h2>Lista de atributos</h2>
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Atributo</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${lista}" var="element" varStatus="loop">

							<tr>
								<th scope="row">${loop.count}</th>
								<td><c:out value="${element}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
	<div class="input-group">
						<span class="input-group-text">clase</span> <input id="msg"
							type="text" class="form-control" name="msg"
							placeholder="Additional Info" value="${clase}">
					</div>

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