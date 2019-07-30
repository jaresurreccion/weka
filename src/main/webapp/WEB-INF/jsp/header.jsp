<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Dasboard</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
	
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
					'Rellene todos lo campos!',
					'error'
				)
			return false;
		}else{
			return true;	
			}
		}

	
</script>

<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${index}">
<link rel="stylesheet" type="text/css" href="./css/global.css">
</c:if>

</head>