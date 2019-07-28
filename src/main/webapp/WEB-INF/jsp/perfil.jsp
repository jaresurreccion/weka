<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



        <body id="body">
            <jsp:include page="headerPage.jsp"></jsp:include>
            <jsp:include page="sidebar.jsp"></jsp:include>
            <div id="page-content-wrapper">
                <h4 class="mt-4">Mi Perfil</h4>



                <div id="card">
                    <form class="form-container" action="/updateUser/${id}" method="POST">
                        <div class="row">

                            <div class="col">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroup-sizing-lg">Nombre</span>
                                    </div>
                                    <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-lg" name="nombre" value="${nombre}">
                                </div>

                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroup-sizing-lg">Primer apellido</span>
                                    </div>
                                    <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-lg" name="apellido1" value="${apellido1}">
                                </div>
                            </div>

                            <div class="col">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroup-sizing-lg">Segundo apellido</span>
                                    </div>
                                    <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-lg" name="apellido2" value="${apellido2}">
                                </div>
                                <input type="hidden" name="id" value="${id}">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroup-sizing-lg">Activo</span>
                                    </div>
                                    <select type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" name="activo" value="${activo}">
                                        <option value="1" >Activo</option>
                                        <option value="0" >No activo</option>
                                        </select>
                                </div>

                            </div>
                        </div>

                        <button type="submit" class="btn btn-outline-success  btn-sm">Actualizar</button>
                    </form>
                </div>

            </div>
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>


        </body>

</html>