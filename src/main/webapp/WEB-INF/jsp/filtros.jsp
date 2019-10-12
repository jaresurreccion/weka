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
                            <form action="/saveFilters" method="POST" id="formHidden">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="card">
                                        <div class="card-header">Seleccion de atributos</div>
                                        <div class="card-body table-responsive">
                                            <table class="table table-striped" id="tableSesiones">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">#</th>
                                                        <th scope="col">Atributo</th>
                                                        <th scope="col">Tipo</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${sessionScope.atributos}" var="element" varStatus="loop">
                                                        <tr>
                                                            <th scope="row"><input type="checkbox" name="atributes" value="${loop.count}"></th>
                                                            <td>
                                                                <c:out value="${element[0]}"></c:out>
                                                            </td>
                                                            <td>
                                                                <c:out value="${element[1]}"></c:out>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-8 col-sm-12 col-xs-12">
                                    <div class="card">
                                        <div class="card-header">Seleccion de filtros</div>
                                        <div class="card-body">
                                            <div class="radio">
                                                <label><input type="radio" name="filtro" value="1">Supervisado</label>
                                            </div>
                                            <div class="radio">
                                                <label><input type="radio" name="filtro" value="2">No
                supervisado</label>
                                            </div>
                                            <button type="submit" class="btn btn-md btn-outline-secondary m-0 px-3 py-2 z-depth-0 waves-effect">Guardar
              filtros</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <c:if test="${filtroActivo}">
                            <div id="filtroActivo" class="alert alert-success" role="alert">
                                Filtros seleccionados: Remove: ${filtroActivoRemove} Tipo: ${filtroActivoTipo}
                            </div>
                        </c:if>

                        <div class="row">
                            <div class="col-md-4 col-sm-12 col-xs-12">
                                <div class="card">
                                    <div class="card-header">Seleccion de filtros</div>
                                    <div class="card-body">

                                        <form action="/seleccionAtributos" method="GET" id="formHidden">
                                            <h4>Seleccion de atributos</h4>
                                            <div class="radio">
                                                <label><input type="radio" name="optradio" value="1">BestFirst</label>
                                            </div>
                                            <div class="radio">
                                                <label><input type="radio" name="optradio" value="2">ScatterSearch/GreedyStepwise</label>
                                            </div>
                                            <div class="radio">
                                                <label><input type="radio" name="optradio" value="3">RankSearch</label>
                                            </div>
                                            <button type="submit" class="btn btn-md btn-outline-secondary m-0 px-3 py-2 z-depth-0 waves-effect">Utilizar algoritmo</button>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <c:if test="${listaAtributosFiltrosBol}">
                        <div id="filtroActivo" class="alert alert-success" role="alert">
                            Atributos recomendados:
                            <c:forEach items="${sessionScope.listaAtributosFiltros}" var="element" varStatus="loop">
                                <c:out value="${element}"></c:out>
                            </c:forEach>
                        </div>
                    </c:if>

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