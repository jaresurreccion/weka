<!DOCTYPE html>
<html lang="es">
<jsp:include page="header.jsp"></jsp:include>
<body>

  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
	<jsp:include page="sidebar.jsp"></jsp:include>

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <jsp:include page="headerPage.jsp"></jsp:include>

      <div class="container-fluid">
        <h1 class="mt-4">Bienvenido a WekaWeb</h1>
        WekaWeb es una aplicación web basada en la herramienta de mineria de
				datos Weka. Weka es una colección de algoritmos de aprendizaje
				automático para tareas de minería de datos. Contiene herramientas
				para la preparación de datos, clasificación, regresión,
				agrupamiento, minería de reglas de asociación y visualización. Weka
				es un software de código abierto emitido bajo la Licencia Pública
				General de GNU. Hemos reunido varios cursos gratuitos en línea que
				enseñan el aprendizaje automático y la minería de datos con Weka.
				Los videos de los cursos están disponibles en Youtube. ¡Weka apoya
				el aprendizaje profundo!</div>
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

				
				
