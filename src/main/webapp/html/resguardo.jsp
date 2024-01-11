<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List,p2.*" pageEncoding="UTF8"%>
<!DOCTYPE html>
<html>
<head>
<title>Resguardo del carrito</title>
</head>
<body>
	<%
	UsuarioBD user = (UsuarioBD) request.getAttribute("user");
	
	%>
	<section class="h-100 h-custom">
		<div class="container py-10 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col">
					<div id="TablaCarrito"></div>
					<div class="d-flex justify-content-between align-items-center mb-4">
					</div>

					<div class="col-md-7 col-lg-8">
						<h5 class="mb-0 text-black">Detalles Tarjeta</h5>
						<form class="needs-validation" action="#"
							onsubmit="ProcesarForm(this,'resguardo.html','cuerpo'); return false">
							<div class="row g-3">
								<div class="row g-3">

									<div class="col-6">
										<label for="firstName" class="form-label">Nombre</label> <input
											type="text" class="form-control" id="nombre" name="nombre"
											value="<%=user.getNombre()%>" disabled>
									</div>

									<div class="col-6">
										<label for="lastName" class="form-label">Apellidos</label> <input
											type="text" class="form-control" id="apellidos"
											name="apellidos" value="<%=user.getApellidos()%>" disabled>
									</div>
								</div>
								<div class="row g-3">
									<div class="col-6">
										<label for="email" class="form-label">Email </label> <input
											type="email" class="form-control" id="email" name="email"
											value="<%=user.getUsuario()%>" disabled>
									</div>
									<div class="col-4">
										<label for="password" class="form-label">Contraseña </label> <input
											type="password" class="form-control" id="password"
											name="password" value="<%=user.getClave()%>" disabled>
									</div>
									<div class="col-6">
										<label for="telf" class="form-label">Telefono </label> <input
											type="number" class="form-control" id="tel" name="tel"
											value="<%=user.getTelefono()%>" required>
									</div>

								</div>

								<div class="row g-3">
									<div class="col-6">
										<label for="address" class="form-label">Direccion</label> <input
											type="text" class="form-control" id="dir" name="dir"
											value="<%=user.getDomicilio()%>" required>
									</div>



									<div class="col-3">
										<label for="zip" class="form-label">Codigo Postal</label> <input
											type="text" class="form-control" id="cod" name="cod"
											value="<%=user.getCp()%>" required>
									</div>
								</div>
							</div>

							<hr class="my-4">

							<h4 class="mb-3">Metodo de pago</h4>

							<div class="my-3">
								<div class="form-check">
									<input id="credit" name="paymentMethod" type="radio"
										class="form-check-input" checked required> <label
										class="form-check-label" for="credit">Tarjeta de
										credito</label>
								</div>
								<div class="form-check">
									<input id="debit" name="paymentMethod" type="radio"
										class="form-check-input" required> <label
										class="form-check-label" for="debit">Tarjeta de debito</label>
								</div>
								<div class="form-check">
									<input id="paypal" name="paymentMethod" type="radio"
										class="form-check-input" required> <label
										class="form-check-label" for="paypal">PayPal</label>
								</div>
							</div>

							<div class="row gy-3">
								<div class="col-md-6">
									<label for="cc-name" class="form-label">Nombre en la
										tarjeta</label> <input type="text" class="form-control" id="cc-name"
										value="<%=user.getNombre() + " " + user.getApellidos()%>">
									<small class="text-body-secondary">Nombre que aparece en la tarjeta</small>
								</div>

								<div class="col-md-6">
									<label for="cc-number" class="form-label">Numero
										tarjeta</label> <input type="text" class="form-control" id="cc-number"
										placeholder="">
								</div>

								<div class="col-md-3">
									<label for="cc-expiration" class="form-label">Expiration</label>
									<input type="text" class="form-control" id="cc-expiration"
										placeholder="">
								</div>

								<div class="col-md-3">
									<label for="cc-cvv" class="form-label">CVV</label> <input
										type="text" class="form-control" id="cc-cvv" placeholder="">
								</div>
							</div>

							<hr class="my-4">

							<button class="w-100 btn btn-primary btn-lg" type="submit">Realizar
								pedido</button>

						</form>
					</div>
				</div>
			</div>

		</div>

	</section>
	<script>
		pintaTabla();
	</script>

</body>
</html>
