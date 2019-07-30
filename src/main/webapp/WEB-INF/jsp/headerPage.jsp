<link href="css/style.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header id="header">
	<div id="listaIconos">
		<a href="/perfil"><i class="fa fa-user-circle-o "
			aria-hidden="true"> ${username} </i></a> <a href="/guardarSesion"><i
			class="fa fa-floppy-o " aria-hidden="true"></i></a> <a href="/"><i
			class="fa fa-sign-out " aria-hidden="true"></i></a>
	</div>


	<c:choose>
		<c:when test="${sesionActiva}">
			<div id="sesionActiva" class="alert alert-success" role="alert">
			Área de trabajo seleccionada: ${sesionActivaDesc}
			</div>
		</c:when>
		<c:otherwise>
			<div id="sesionNoActiva" class="alert alert-warning" role="alert">	
			No tiene ninguna área de trabajo activa	
			</div>
		</c:otherwise>
	</c:choose>

</header>