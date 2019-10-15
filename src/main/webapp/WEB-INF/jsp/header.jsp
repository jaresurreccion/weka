<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Wekaweb</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/simple-sidebar.css" rel="stylesheet">
    <script>
        function validarPassword() {
            var nombre = document.getElementsByName("nombre")[0].value;
            var apellido1 = document.getElementsByName("apellido1")[0].value;
            var apellido2 = document.getElementsByName("apellido2")[0].value;
            var username = document.getElementsByName("username")[0].value;
            var pass = document.getElementsByName("pass")[0].value;
            var pass2 = document.getElementsByName("pass2")[0].value;
            if (pass != pass2 || nombre == '' || apellido1 == '' || apellido2 == '' || username == '') {

                swal(
                    'Error!',
                    'Rellene todos lo campos!',
                    'error'
                )
                return false;
            } else {
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