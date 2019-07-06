<!DOCTYPE html>
<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="./css/global.css">
	  <!-- SweetAlert2 -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
<title>Login Form</title>

<script>
	function validarPassword(){
		var nombre = document.getElementsByName("nombre")[0].value;
		var apellido1= document.getElementsByName("apellido1")[0].value;
		var apellido2 = document.getElementsByName("apellido2")[0].value;
		var username = document.getElementsByName("username")[0].value;
		var pass = document.getElementsByName("pass")[0].value;
		var pass2 = document.getElementsByName("pass2")[0].value;
		if(pass != pass2 || nombre =='' || apellido1 =='' || apellido2 ==''|| username ==''){
			
			swal(
					'Error!',
					'¡Rellene todos lo campos!',
					'error'
				)
			return false;
		}else{
			return true;	

			}
		}
	
</script>
</head>
<body class="bg">

	<section class="container-fluid">
	
		<section class="row justify-content-center">
			<section class="col-12 col-sm-6 col-md-3">
				<form class="form-container" action="/createUsuario" method="POST">
					<div class="form-group">
						<label for="nombreUsuario">Nombre</label> 
						<input class="form-control" name="nombre" aria-describedby="nombreHelp" > 
					</div>
					<div class="form-group">
						<label for="apellido1">Primer Apellido</label> 
						<input class="form-control" name="apellido1" aria-describedby="apellidoHelp" > 
					</div>
					<div class="form-group">
						<label for="apellido">Segundo Apellido</label> 
						<input class="form-control" name="apellido2" aria-describedby="apellido2Help" > 
					</div>
					<div class="form-group">
						<label for="nombreUsuario">Nombre de usuario</label> 
						<input class="form-control" name="username" aria-describedby="usernameHelp" > 
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Contraseña</label> 
						<input type="password" class="form-control" name="pass" >
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Confirma la contraseña</label> 
						<input type="password" class="form-control" name="pass2">
					</div>
					
					<button type="submit" class="btn btn-primary btn-block" id="create" onclick="return validarPassword()">Crear usuario</button>
			
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
