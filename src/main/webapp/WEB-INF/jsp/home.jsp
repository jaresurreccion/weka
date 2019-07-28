<!DOCTYPE html>
<html>

<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body id="body">
	<jsp:include page="headerPage.jsp"></jsp:include>
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div id="page-content-wrapper">
		<div id="card">
			<form class="form-container" action="/crearSesion" method="POST"
				id="crearSesion">
				<div class="input-group mb-3">
					<div class="input-group-prepend" id="button-addon3">
						<button
							class="btn btn-md btn-outline-secondary m-0 px-3 py-2 z-depth-0 waves-effect"
							type="button">
							<i class="fa fa-plus" aria-hidden="true"></i>
						</button>
					</div>
					<input type="text" class="form-control" placeholder="Introduzca el nombre"
						aria-label="Example text with two button addons"
						aria-describedby="button-addon3">
				</div>
			</form>

		</div>
		<div id="card">
			<table class="table table-striped" id="tableSesiones">

				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Nombre</th>
						<th scope="col">Fichero</th>
						<th scope="col">Algoritmo</th>
						<th scope="col">fechaCreacion</th>
						<th scope="col">fechaActualizacion</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sessionScope.trabajo}" var="element"
						varStatus="loop">
						<tr>
							<th scope="row">${loop.count}</th>
							<td><c:out value="${element.nombre}" /></td>
							<td><c:out value="${element.idFile}" /></td>
							<td><c:out value="${element.idAlgoritmo}" /></td>
							<td><fmt:formatDate value="${element.fechaCreacion}"
									pattern="dd-MMM-yyyy" /></td>
							<td><fmt:formatDate value="${element.fechaActualizacion}"
									pattern="dd-MMM-yyyy" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>