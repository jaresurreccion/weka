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
				<div id="card-large">
					<table class="table table-striped" id="tableSesiones">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Atributo</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sessionScope.atributos}" var="element"
								varStatus="loop">
								<tr>
									<th scope="row"><input type="checkbox"
										value="${loop.count}"></th>
									<td><c:out value="${element}"></c:out></td>
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
</body>

</html>