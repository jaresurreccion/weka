<link href="css/simple-sidebar.css" rel="stylesheet">
<aside id="aside" class="navigation" onload="load()">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <img src="../images/weka-icon.png" id="image-weka">
    <ul class="mainmenu">
        <li><a href="/home">Dashboard</a></li>
        <li><a href="/perfil">Mi Perfil</a></li>
        <li><a href="">Aprendizaje automatico</a>
            <ul class="submenu">
                <li><a href="/ficheros" id="ficheros" class="disabled">Dataset</a></li>
                <li><a href="/filtro" id="filtro" class="disabled">Filtro</a></li>
                <li><a href="/algoritmos" id="algoritmos" class="disabled">Algoritmos</a></li>
                <li><a href="/resultados" id="resultados" class="disabled">Resultados</a></li>
            </ul>
        </li>
        <li><a href="/perfil">About</a></li>
    </ul>
</aside>

<script>
window.onload = function load(){
    var sesionActiva = '<c:out value="${sesionActiva}"/>';
    if (sesionActiva == 'true') {
        var ficheros = document.getElementById('ficheros');
        ficheros.classList.remove('disabled');
        
        var idFile = '<c:out value="${sesionActivaIdFile}"/>';
        if(idFile != 0 && idFile != null && idFile != ""){
	       	var filtro = document.getElementById('filtro');
	       	filtro.classList.remove('disabled');
            }
        
        var idfiltros = '<c:out value="${listaAtributosFiltrosBol}"/>';
        if(idfiltros != 0 && idfiltros != null && idfiltros != "" ){
            var algoritmos = document.getElementById('algoritmos');
            algoritmos.classList.remove('disabled');
            }
        
        var idAlgoritmo = '<c:out value="${sesionActivaIdAlgoritmo}"/>';
        if(idAlgoritmo != 0 && idAlgoritmo != null && idAlgoritmo != ""){
            var resultados = document.getElementById('resultados');
            resultados.classList.remove('disabled');
            }
        

    }
}
</script>