
<%@page language="java" contentType="text/html charset=UTF-8"
	import="p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>&nbsp;</title>
</head>

<body>

	<main class="d-flex align-items-center min-vh-100 py-3 py-md-0">
		<div class="container">
			<div class="card login-card">
				<div class="row no-gutters">
					<div class="col-md-5">
						<img src="./images/login.png" alt="login" class="card-img adjust">
					</div>
					<div class="col-md-7">
						<div class="card-body">

							<p class="login-card-description">Iniciar sesión</p>
							<form method="post"
								onsubmit="ProcesarForm(this,'login.html','cuerpo'); return false">
								<div class="form-group">
									<label for="email" class="sr-only">Correo electrónico</label> <input
										type="email" name="email" id="email" class="form-control"
										placeholder="correo@ejemplo.com">
								</div>
								<div class="form-group mb-4">
									<label for="password" class="sr-only">Contraseña</label> <input
										type="password" name="password" id="password"
										class="form-control" placeholder="***********">
								</div>
								<%
								//Utilizamos una variable en la sesión para informar de los mensajes de Error
								String mensaje = (String) request.getAttribute("mensaje");
								if (mensaje != null) { //Eliminamos el mensaje consumido
									request.removeAttribute("mensaje");
								%>
								<p>
									<%=mensaje%>
								</p>
								<%
								}
								%>
								<input name="login" id="login"
									class="btn btn-block login-btn mb-4 boton-login" type="submit"
									value="Login">
							</form>
							<a href="#" class="forgot-password-link">¿Olvidó su
								contraseña?</a>
							<p class="login-card-footer-text">
								¿No tienes cuenta? <a href="#!" class="text-reset"
									onclick="Cargar('./html/registro.jsp','cuerpo')">Registrese
									aquí</a>
							</p>
							<nav class="login-card-footer-nav">
								<a href="#">Terminos de uso</a> <a href="#">Politica de
									privacidad</a>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>

</body>
</html>