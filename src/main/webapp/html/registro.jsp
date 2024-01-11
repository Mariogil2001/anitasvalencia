<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Anitas Valencia | Registro</title>
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

							<p class="login-card-description">Registrese aquí</p>
							<form
								onsubmit="ProcesarForm(this,'registro.html','cuerpo'); return false">
								<div class="form-group">
									<label for="nombre" class="sr-only">Nombre</label> <input
										type="text" name="nombre" id="nombre" class="form-control"
										placeholder="Juanito" maxlength="50" required autofocus>
										
									<label for="apellidos" class="sr-only">Apellidos</label> <input
										type="text" name="apellidos" id="apellidos"
										class="form-control" placeholder="Perez Garcia" maxlength="50"
										required>
								</div>
								<div class="form-group">
									<label for="email" class="sr-only">Correo electrónico</label> <input
										type="email" name="email" id="email" class="form-control"
										placeholder="correo@ejemplo.com" maxlength="50" required>
								</div>
								<div class="form-group mb-4">
									<label for="password" class="sr-only">Contraseña</label> <input
										type="password" name="password" id="password"
										class="form-control" placeholder="***********" minlength="5"
										required>
								</div>
								<div class="form-group mb-4">
									<label for="domicilio" class="sr-only">Domicilio</label> <input
										type="text" name="domicilio" id="domicilio"
										class="form-control" placeholder="C/ Cristo Rey 5" required>
								</div>
								<div class="form-group mb-4">
									<label for="cp" class="sr-only">Codigo Postal</label> <input
										type="number" name="cp" id="cp" class="form-control"
										placeholder="46000" required>
								</div>
								<div class="form-group mb-4">
									<label for="telefono" class="sr-only">Telefono</label> <input
										type="number" name="telefono" id="telefono"
										class="form-control" placeholder="612 34 56 78" required>
								</div>
								<input name="login" id="login"
									class="btn btn-block login-btn mb-4 boton-login" type="submit"
									value="Registrarse">
							</form>
							<a href="#!" class="text-reset"
								onclick="Cargar('./html/login.jsp','cuerpo')">Volver </a>

						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>

</html>