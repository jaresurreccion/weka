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
					<h2 class="mt-4">Aniadir dataset</h2>
					<form method="POST" action="/uploadFile"
						enctype="multipart/form-data">
						<div class="custom-file mb-3">
							<input type="file" class="custom-file-input" id="file"
								name="file"> <label class="custom-file-label"
								for="customFile">Elegir dataset</label>
						</div>
						<div class="form-group">
							<label for="comment">Comentario:</label>
							<textarea class="form-control" rows="1" col="5" id="comment"
								name="comentario"></textarea>
						</div>
						<div class="mt-3">
							<button type="submit" class="btn btn-primary">Aniadir</button>
						</div>
					</form>

				</div>

				<div id="card">
					<h2>Dataset seleccionado</h2>
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Nombre</th>
								<th scope="col">Creado</th>
								<th scope="col">Comentario</th>
								<th scope="col">Clase</th>
								<th scope="col">Numero de atributos</th>
								<th scope="col">Numero de instancias</th>
								<th scope="col">Accion</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ListaFicheros}" var="element"
								varStatus="loop">

								<tr>
									<th scope="row">${loop.count}</th>
									<td><c:out value="${element.nombreFichero}" /></td>
									<td><fmt:formatDate value="${element.creado}"
											pattern="dd-MMM-yyyy" /></td>
									<td><c:out value="${element.comentario}" /></td>
									<td><c:out value="${element.clase}" /></td>
									<td><c:out value="${element.nAtributos}" /></td>
									<td><c:out value="${element.numInstancias}" /></td>
									<td><a class="btn btn-primary"
										href="/downloadFile/${element.idFichero}" aria-label="Delete">
											<i class="fa fa-download" aria-hidden="true"></i>
									</a> <a class="btn btn-danger"
										href="/deleteFile/${element.idFichero}" aria-label="Delete">
											<i class="fa fa-trash-o" aria-hidden="true"></i>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</dic>
			</div>

		</div>
	</div>
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
	<script>
		// Add the following code if you want the name of the file appear on select
		$(".custom-file-input").on(
				"change",
				function() {
					var fileName = $(this).val().split("\\").pop();
					$(this).siblings(".custom-file-label").addClass("selected")
							.html(fileName);
				});
	</script>

</body>

</html>