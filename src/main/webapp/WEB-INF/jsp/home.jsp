<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<body>
	<div class="d-flex" id="wrapper">
 	<jsp:include page="sidebar.jsp"></jsp:include>
	<div id="page-content-wrapper">
		<jsp:include page="nav.jsp"></jsp:include>
      <div class="container-fluid">
      
      <form class="form-container" action="/crearSesion" method="POST">
      <div class="form-group">
						<label for="nombre">Nombre</label> 
						<input class="form-control" name="nombre" aria-describedby="nombreHelp" > 
	</div>
	<button type="submit" class="btn btn-primary btn-block" id="create" >Crear sesión</button>
     </form>
        
        <h2>Lista de sesiones de trabajo</h2>
				<table class="table table-striped">
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
						<c:forEach items="${sessionScope.trabajo}" var="element" varStatus="loop">
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