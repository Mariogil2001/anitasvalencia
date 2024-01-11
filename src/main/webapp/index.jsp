<%@ page language="java"
	contentType="text/html; charset=UTF-8" import="p2.*" pageEncoding="UTF8"%>

<!-- Esto sirve para escribir comentarios -->
<!-- <etiqueta atributo="valor"></etiqueta> sirve para crear un objeto -->
<!DOCTYPE html>
<html lang="es">
<head>
<title>Anitas Valencia</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="./js/libCapas2223.js"></script>
<link rel="icon" href="./images/icon.png">
<!-- Bootstrap para los estilos -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/BarraNavegacion.css">
<link rel="stylesheet" href="css/styleregistro.css">
<link rel="stylesheet" href="css/styletienda.css">
<link rel="stylesheet" href="css/stylecontacto.css" />
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="css/about.css" />
<link rel="stylesheet" href="css/shopping_cart.css" />

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="js/carritoCompra.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<!-- Navbar  -->
	<nav class="navbar fixed-top navbar-expand-lg navbar-dark p-md-3">
		<div class="container-fluid">
			<img src="./images/logonegro.png" class="img-fluid"
				alt="Logo Empresa">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<div class="mx-auto"></div>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link texto" href="#"
						onclick="Cargar('./html/inicio.html','cuerpo')">Inicio</a></li>
					<li class="nav-item"><a class="nav-link texto" href="#"
						onclick="Cargar('./html/empresa.html','cuerpo')">Empresa</a></li>
					<li class="nav-item"><a class="nav-link texto" href="#"
						onclick="Cargar('./html/contacto.html','cuerpo')">Contacto</a></li>
					<li class="nav-item"><a class="nav-link texto" href="#"
						onclick="Cargar('productos.html','cuerpo')">Tienda</a></li>
					<% if ((session.getAttribute("usuario") == null) || ((Integer)session.getAttribute("usuario") <=0 ))
					{	// Si no hay usuario o el usuario no es valido%>
					
					<li class="nav-item"><a class="nav-link texto" href="#"
						onclick="Cargar('login.html','cuerpo')">Login</a></li>
						<%}else{ // Si ya se ha iniciado sesión accedemos directamente a la zona de usuario%>
					<li class="nav-item"><a class="nav-link texto" href="#"
						onclick="Cargar('zonausuario.html','cuerpo')">Zona Usuario</a></li>
						<%} %>
					<li class="nav-item"><a class="navbar-brand" href="#"
						onclick="Cargar('recogercarrito.html','cuerpo')"><img
							class="shopping_cart" src="./images/shopping_cart.svg"
							alt="Shopping cart"></a></li>
				</ul>
			</div>
		</div>
	</nav>


	<div id="cuerpo"></div>
	<!-- JQUERY -->
	<script src="js/jquery-3.3.1.min.js"></script>

</body>
</html>