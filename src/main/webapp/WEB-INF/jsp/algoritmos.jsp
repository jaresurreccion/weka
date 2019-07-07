<!DOCTYPE html>
<html lang="es">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Dasboard</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

</head>

<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidebar-wrapper">
			<div class="sidebar-heading">Inicio</div>
			<div class="list-group list-group-flush">
				<a href="/home"
					class="list-group-item list-group-item-action bg-light">Dashboard</a>
				<a href="/algoritmos"
					class="list-group-item list-group-item-action bg-light">Algoritmos</a>
				<a href="/ficheros"
					class="list-group-item list-group-item-action bg-light">Datasets</a>
				<a href="/perfil"
					class="list-group-item list-group-item-action bg-light">Perfil</a>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<button class="btn btn-primary" id="menu-toggle">Toggle
					Menu</button>

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
						<li class="nav-item active"><a class="nav-link" href="#">Home
								<span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> Dropdown </a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="#">Action</a> <a
									class="dropdown-item" href="#">Another action</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">Something else here</a>
							</div></li>
					</ul>
				</div>
			</nav>

			<div class="container-fluid">
				<h2 class="mt-4">Weka</h2>
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
							<strong>�Error!</strong> No se ha podido realizar la acci�n requerida
						</div>
					</div>
				</c:if>
				<br> <br> <br>
				<h2>Lista de dataset</h2>
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Nombre</th>
							<th scope="col">Creado</th>
							<th scope="col">Comentario</th>
							<th scope="col">Acci�n</th>
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