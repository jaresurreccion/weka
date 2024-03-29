<link href="css/simple-sidebar.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading wekaweb">WekaWeb</div>
      <div class="list-group list-group-flush">
        <a href="/home" class="list-group-item list-group-item-action bg-light">Dashboard</a>
        <a href="/perfil" class="list-group-item list-group-item-action bg-light">Mi Perfil</a>
        <a href="/opendata" class="list-group-item list-group-item-action bg-light">Datos Abiertos</a>
        <a href="/wekaCommand" class="list-group-item list-group-item-action bg-light">Weka Line</a>
        <a href="/ficheros" id="ficheros" class="list-group-item list-group-item-action bg-light disabled">Dataset</a>
        <a href="/filtro" id="filtro" class="list-group-item list-group-item-action bg-light disabled">Filtro</a>
        <a href="/algoritmos" id="algoritmos" class="list-group-item list-group-item-action bg-light disabled">Algoritmos</a>
        <a href="/resultados" id="resultados" class="list-group-item list-group-item-action bg-light disabled">Resultados</a>
        <a href="/informacion" class="list-group-item list-group-item-action bg-light">Informacion</a>
        
      </div>
    </div>
    <!-- /#sidebar-wrapper -->

<script>
	window.onload = function load() {
		var sesionActiva = '<c:out value="${sesionActiva}"/>';
		if (sesionActiva == 'true') {
			var ficheros = document.getElementById('ficheros');
			ficheros.classList.remove('disabled');

			var idFile = '<c:out value="${sesionActivaIdFile}"/>';
			if (idFile != 0 && idFile != null && idFile != "") {
				var filtro = document.getElementById('filtro');
				filtro.classList.remove('disabled');
			}

			var idfiltros = '<c:out value="${filtroActivoTipo}"/>';
			if (idfiltros != 0 && idfiltros != null && idfiltros != "") {
				var algoritmos = document.getElementById('algoritmos');
				algoritmos.classList.remove('disabled');
			}

			var idAlgoritmo = '<c:out value="${sesionActivaIdAlgoritmo}"/>';
			if (idAlgoritmo != 0 && idAlgoritmo != null && idAlgoritmo != "") {
				var resultados = document.getElementById('resultados');
				resultados.classList.remove('disabled');
			}

		}
	}
</script>