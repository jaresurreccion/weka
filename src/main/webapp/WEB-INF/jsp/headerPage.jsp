<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
    
 <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom" style="margin-bottom: 15px;">
        <button class="btn  btn-default btn-sm" id="menu-toggle"><i class="fa fa-expand" aria-hidden="true"></i></button>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <c:if test="${sesionActiva}">
	    <span class="badge badge-success">Sesion activa: ${sesionActivaNombre}</span>
	    </c:if>
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item active">
              <a class="nav-link" href="/perfil">Mi perfil
 			<span class="sr-only"></span></a>
            </li>
            <c:if test="${rolUser}">
            <li class="nav-item active">
              <a class="nav-link" href="/configuracion">Configuracion
 			<span class="sr-only"></span></a>
            </li>
            </c:if>
            <li class="nav-item active">
              <a class="nav-link" href="/">Cerrar sesion
 			<span class="sr-only"></span></a>
            </li>
          </ul>
        </div>
      </nav>