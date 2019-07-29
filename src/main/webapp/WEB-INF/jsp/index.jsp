<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>
<body class="bg">
	<section class="container-fluid">
		<section class="row justify-content-center">
			
			<section class="col-12 col-sm-6 col-md-3">
			<c:if test="${error}">
				<div class="alert alert-danger" role="alert">
  				 El usuario / contraseña son incorrectos o, no existe ese usuario"
				</div>
			</c:if>
				<form class="form-container" action="/home" method="POST">
					<div class="form-group">
						<label for="exampleInputEmail1">Email address</label> <input
							class="form-control" name="user" aria-describedby="emailHelp"
							placeholder="Enter user">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" class="form-control" name="pass"
							placeholder="Password">
					</div>
					<button type="submit" class="btn btn-primary btn-block">Submit</button>
					<a href="/nuevoUsuario">¿No tienes usuario? Pincha aquí</a>
				</form>
			</section>
		</section>
	</section>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
		integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o"
		crossorigin="anonymous"></script>
</body>
</html>
