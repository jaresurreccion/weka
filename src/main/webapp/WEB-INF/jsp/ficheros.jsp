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
                            <div class="col-4">
                                <div class="card">
                                    <div class="card-header">Nuevo dataset</div>
                                    <div class="card-body">
                                        <form method="POST" action="/uploadFile" enctype="multipart/form-data">
                                            <div class="custom-file mb-3">
                                                <input type="file" class="custom-file-input" id="file" name="file"> <label class="custom-file-label" for="customFile">Elegir dataset</label>
                                            </div>
                                            <div class="form-group">
                                                <label for="comment">Comentario:</label>
                                                <textarea class="form-control" rows="1" col="5" id="comment" name="comentario"></textarea>
                                            </div>
                                            <div class="mt-3">
                                                <button type="submit" class="btn btn-primary">Aniadir</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">Dataset seleccionado</div>
                                    <div class="card-body table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Creado</th>
                                                    <th scope="col">Comentario</th>
                                                    <th scope="col">Clase</th>
                                                    <th scope="col">Numero de instancias</th>
                                                    <th scope="col">Numero de atributos</th>
                                                    <th scope="col">Accion</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${ListaFicheros}" var="element" varStatus="loop">

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