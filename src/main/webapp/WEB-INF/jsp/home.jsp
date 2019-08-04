<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



        <body id="body">
            <jsp:include page="headerPage.jsp"></jsp:include>
            <jsp:include page="sidebar.jsp"></jsp:include>
            <div id="page-content-wrapper">
                <div class="row">
                    <div class="col">
                        <div id="card">
                            <h5>Conexiones ultima semana</h5><br>
                            <canvas id="chLine"></canvas>
                        </div>

                        <div id="card">
                            <h5>Crear nueva area de trabajo</h5>
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
                    <div class="col">
                        <div id="card">
                            <h5>Areas de trabajo disponibles</h5><br>
                            <table class="table table-striped" id="tableSesiones">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Fichero</th>
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
                                                <c:out value="${element.idFile}" />
                                            </td>
                                            <td>
                                                <c:out value="${element.idAlgoritmo}" />
                                            </td>
                                            <td>
                                                <fmt:formatDate value="${element.fechaCreacion}" pattern="dd-MMM-yyyy" />
                                            </td>
                                            <td>
                                                <fmt:formatDate value="${element.fechaActualizacion}" pattern="dd-MMM-yyyy" />
                                            </td>
                                            <td>
                                                <a class="btn btn-default" href="/activateSession/${element.idSesion}" aria-label="Settings">
                                                    <i class="fa fa fa-chevron-right " aria-hidden="true"></i>
                                                </a>
                                                <a class="btn btn-default" href="/deleteSession/${element.idSesion}" aria-label="Settings">
                                                    <i class="fa fa-trash-o fa-2x" aria-hidden="true"></i>
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
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

            <script>
                /* chart.js chart examples */

                // chart colors
                var colors = ['#007bff', '#28a745', '#333333', '#c3e6cb', '#dc3545', '#6c757d'];

                /* large line chart */
                var chLine = document.getElementById("chLine");
                var chartData = {
                    labels: ["Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"],
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
            </script>
        </body>

</html>