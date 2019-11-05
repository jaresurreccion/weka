
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
			<form action="/wekaLine" action="GET">
				<div class="row">
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-header">WekaWeb Linea de comandos</div>
							<div class="card-body">En esta pantalla podrá configurar de
								forma manual las propiedades para usar sobre el fichero que vaya
								a seleccionar. A continuación, una vez ejecute esa
								configuración, se le mostrara los resultados.</div>
						</div>
					</div>
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-header">Seleccione el fichero que desea
								utilizar</div>
							<div class="card-body">
								<select name="idFichero">
									<c:forEach items="${listaFicheros}" var="fichero">
										<option value="${fichero.idFichero}">${fichero.nombreFichero}</option>
							<</c:forEach>
								</select>
							</div>
						</div>
					</div>

				</div>
				<br><br>
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-header">Introduzca los parametros manuales</div>
							<div class="card-body">
							<input name="params" type="text" value="" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<button type="submit" class="btn btn-primary btn-block">Aplicar</button>
				<br><br>
				<div class="row">
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="card">

										<div class="card-header">Resultado</div>
										<div class="card-body">
											<textarea class="form-control" name="resultados" 
												disabled="disabled" rows="30" id="resultados">${resultCommand}</textarea>												
										</div>
									</div>
								</div>
							</div>
				</form>
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
	</script>

</body>

</html>



