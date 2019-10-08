<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body id="body">
	<jsp:include page="headerPage.jsp"></jsp:include>
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div id="page-content-wrapper">
		<form method="POST" action="/saveAlgoritmo">
			<c:choose>
				<c:when test="${filtroActivoTipo == 'Supervisado'}">
					<div class="row">
						<div class="col">
							<div id="card">
								<h4>Agrupamiento</h4>
								<div class="radio">
									<label><input type="radio" name="optradio" value="4">simpleKmeans</label>
								</div>
								<div class="radio">
									<label><input type="radio" name="optradio" value="5">Clustering
										Conceptual (COBWEB)</label>
								</div>
								<div class="radio">
									<label><input type="radio" name="optradio" value="6">Clustering
										Probabilístico (EM)</label>
								</div>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>

					<div class="row">
						<div class="col">
							<div id="card">
								<h4>Clasificacion</h4>
								<div class="radio">
									<label><input type="radio" name="optradio" value="7">NaivesBayes</label>
								</div>
								<div class="radio">
									<label><input type="radio" name="optradio" value="8">J48</label>
								</div>
								<div class="radio">
									<label><input type="radio" name="optradio" value="9">SVM</label>
								</div>
							</div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>

			<div class="mt-3">
				<button type="submit" class="btn btn-primary"
					id="botonSeleccionarAlgoritmo">Seleccionar algoritmo</button>
			</div>
		</form>

		<c:if test="${algoritmoActivo}">
			<div id="filtroActivo" class="alert alert-success" role="alert">
				Algoritmo seleccionado: ${algoritmoActivoNombre}</div>
		</c:if>
	</div>
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>