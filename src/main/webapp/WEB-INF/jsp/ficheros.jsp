<!DOCTYPE html>
<html lang="es">
<jsp:include page="header.jsp"></jsp:include>
<body>
	<div class="d-flex" id="wrapper">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div id="page-content-wrapper">
		<jsp:include page="nav.jsp"></jsp:include>
			<div class="container-fluid">
				<h2 class="mt-4">Añadir dataset</h2>
				<div>
					<form method="POST" action="/uploadFile"
						enctype="multipart/form-data">
						<div class="custom-file mb-3">
							<input type="file" class="custom-file-input" id="file"
								name="file"> <label class="custom-file-label"
								for="customFile">Elegir dataset</label>
						</div>
						<div class="form-group">
							<label for="comment">Comentario:</label>
							<textarea class="form-control" rows="5" id="comment"
								name="comentario"></textarea>
						</div>
						<div class="mt-3">
							<button type="submit" class="btn btn-primary">Añadir</button>
						</div>
					</form>
				</div>
				<br> <br> <br>
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
								<td>
								<a class="btn btn-primary" href="/downloadFile/${element.idFichero}" aria-label="Delete"> 
								<i class="fa fa-download" aria-hidden="true"></i>
								</a>
								<a class="btn btn-danger" href="/deleteFile/${element.idFichero}" aria-label="Delete"> 
								<i class="fa fa-trash-o" aria-hidden="true"></i>
								</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<script>
					// Add the following code if you want the name of the file appear on select
					$(".custom-file-input").on(
							"change",
							function() {
								var fileName = $(this).val().split("\\").pop();
								$(this).siblings(".custom-file-label")
										.addClass("selected").html(fileName);
							});
				</script>


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