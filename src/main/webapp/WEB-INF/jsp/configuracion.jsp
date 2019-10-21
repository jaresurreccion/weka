<!DOCTYPE html>
<html lang="es">
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
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">Usuarios de la aplicacion</div>
                                    <div class="card-body table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">Id</th>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Primer apellido</th>
                                                    <th scope="col">Segundo apellido</th>
                                                    <th scope="col">Usuario</th>
                                                    <th scope="col">Administrador</th>
                                                    <th scope="col">Fecha de creacion</th>
                                                    <th scope="col">Ultimo acceso</th>
                                                    <th scope="col">Accion</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${listaUsuarios}" var="element" varStatus="loop">

                                                    <tr>
                                                        <th scope="row">${loop.count}</th>
                                                         <td>
                                                            <c:out value="${element.id}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${element.nombre}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${element.primerApellido}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${element.segundoApellido}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${element.username}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${element.flag}" />
                                                        </td>
                                                        <td>
                                                            <fmt:formatDate value="${element.creado}" pattern="dd-MMM-yyyy" />
                                                        </td>
                                                        <td>
                                                            <fmt:formatDate value="${element.last_access}" pattern="dd-MMM-yyyy" />
                                                        </td>
                                                        

                                                        <td>
                                                             <a class="btn btn-danger" href="/deleteUser/${element.id}" aria-label="Delete">
                                                                <i class="fa fa-trash-o" aria-hidden="true"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">Ficheros de la aplicacion</div>
                                    <div class="card-body table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Creado</th>
                                                    <th scope="col">Comentario</th>
                                                    <th scope="col">Clase</th>
                                                    <th scope="col">Numero de atributos</th>
                                                    <th scope="col">Numero de instancias</th>
                                                    <th scope="col">Accion</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${listaFicheros}" var="element" varStatus="loop">

                                                    <tr>
                                                        <th scope="row">${loop.count}</th>
                                                        <td>
                                                            <c:out value="${element.nombreFichero}" />
                                                        </td>
                                                        <td>
                                                            <fmt:formatDate value="${element.creado}" pattern="dd-MMM-yyyy" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${element.comentario}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${element.clase}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${element.nAtributos}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${element.numInstancias}" />
                                                        </td>
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
                                    </div>
                                </div>
                            </div>
                        </div>
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