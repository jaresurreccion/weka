<!DOCTYPE html>
<html lang="es">
<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

        <body>
            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Bienvenido a miWekaWeb</h4>
                        </div>
                        <div class="modal-body">
                            <p>Para poder utilizar la aplicacion, debera seleccionar una sesion ya creada o, crear una nueva.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>

            <div class="d-flex" id="wrapper">

                <!-- Sidebar -->
                <jsp:include page="sidebar.jsp"></jsp:include>

                <!-- Page Content -->
                <div id="page-content-wrapper">

                    <jsp:include page="headerPage.jsp"></jsp:include>

                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-10 col-sm-12 col-xs-12">
                                <div class="card">
                                    <div class="card-header">Areas de trabajo disponibles</div>
                                    <div class="card-body table-responsive">
                                        <table class="table table-striped" id="tableSesiones">
                                            <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Fichero</th>
                                                    <th scope="col">Filtro</th>
                                                    <th scope="col">Algoritmo</th>
                                                    <th scope="col">fechaCreacion</th>
                                                    <th scope="col">fechaActualizacion</th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${sessionScope.trabajo}" var="element" varStatus="loop">
                                                    <tr>
                                                        <th scope="row">${loop.count}</th>
                                                        <td>
                                                            <c:out value="${element.nombre}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${element.nombreFile}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${element.nombreFiltros}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${element.nombreAlg}" />
                                                        </td>
                                                        <td>
                                                            <fmt:formatDate value="${element.fechaCreacion}" pattern="dd-MMM-yyyy" />
                                                        </td>
                                                        <td>
                                                            <fmt:formatDate value="${element.fechaActualizacion}" pattern="dd-MMM-yyyy" />
                                                        </td>
                                                        <td>
                                                            <a class="btn btn-default" href="/activateSession/${element.idSesion}" aria-label="Settings"> <i class="fa fa-chevron-right " aria-hidden="true"></i>
                                                            </a>
                                                            <a class="btn btn-default" href="/deleteSession/${element.idSesion}" aria-label="Settings"> <i class="fa fa-trash-o " aria-hidden="true"></i>
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
                        <div class="col-md-4 col-sm-12 col-xs-12">
                                <div class="card ">
                                    <div class="card-header">Crear nueva area de trabajo</div>
                                    <div class="card-body">
                                        <form class="form-container" action="/crearSesion" method="POST" id="crearSesion">
                                            <div class="input-group mb-3">
                                                <div class="input-group-prepend" id="button-addon3">
                                                    <button class="btn btn-md btn-outline-secondary m-0 px-3 py-2 z-depth-0 waves-effect" type="submit">
													  <i class="fa fa-plus" aria-hidden="true"></i>
													</button>
                                                </div>
                                                <input type="text" name="nombre" class="form-control" placeholder="Introduzca el nombre" aria-label="Example text with two button addons" aria-describedby="button-addon3">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        
                            <div class="col-md-4 col-sm-12 col-xs-12">
                                <div class="card">
                                    <div class="card-header">Conexiones ultima semana</div>
                                    <div class="card-body">
                                        <canvas id="chLine"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>





                    </div>
                </div>



                <!-- Bootstrap core JavaScript -->
                <script src="vendor/jquery/jquery.min.js"></script>
                <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>


                <!-- Menu Toggle Script -->
                <script>
                    $("#menu-toggle").click(function(e) {
                        e.preventDefault();
                        $("#wrapper").toggleClass("toggled");
                    });

                    function charts() {
                        /* chart.js chart examples */

                        // chart colors
                        var colors = ['#007bff', '#28a745', '#333333', '#c3e6cb',
                            '#dc3545', '#6c757d'
                        ];

                        /* large line chart */
                        var chLine = document.getElementById("chLine");
                        var chartData = {
                            labels: ["Lunes", "Martes", "Miercoles", "Jueves", "Viernes",
                                "Sabado", "Domingo"
                            ],
                            datasets: [{
                                data: [2, 3, 8, 15, 22, 2, 0],
                                backgroundColor: 'transparent',
                                borderColor: colors[0],
                                borderWidth: 4,
                                pointBackgroundColor: colors[0]
                            }]
                        };
                        if (chLine) {
                            new Chart(chLine, {
                                type: 'line',
                                data: chartData,
                                options: {
                                    scales: {
                                        xAxes: [{
                                            ticks: {
                                                beginAtZero: false
                                            }
                                        }]
                                    },
                                    legend: {
                                        display: false
                                    },
                                    responsive: true
                                }
                            });
                        }
                    }

                    $(document).ready(function() {
                        var sesionActiva = "<c:out value='${sesionActiva}'/>";
                        if (!sesionActiva) {
                            $("#myModal").modal();
                            charts();
                        } else {
                            charts();
                        }
                    });
                </script>

        </body>

</html>