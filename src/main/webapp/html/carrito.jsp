<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List,p2.*" pageEncoding="UTF8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Ana Domingo | Carrito</title>
</head>
<body>

	<section class="h-100 h-custom">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col">
					<div class="card card-shopping-cart">
						<div class="card-body p-4">

							<div class="row">

								<div class="col-lg-7">
									<h5 class="mb-3">
										<a href="#" class="text-body"
											onclick="Cargar('productos.html','cuerpo')"><i
											class="fas fa-long-arrow-alt-left me-2"></i>Continuar
											comprando</a>
									</h5>
									<hr>

									<div id="TablaCarrito"></div>
									<div class="col-lg-5">

										<div class="card bg-primary text-white rounded-3">

											<%
											if ((session.getAttribute("usuario") == null) || ((Integer) session.getAttribute("usuario") <= 0)) { // Si no hay usuario o el usuario no es valido
											%>
											<button type="button"
												onclick="Cargar('login.html', 'cuerpo')"
												class="btn btn-info btn-block btn-lg">
												<span>Iniciar Sesión <i
													class="fas fa-long-arrow-alt-right ms-2"></i></span>
											</button>
											
											<%
											} else {
											%>
											<!-- Para formalizar el pedido los datos del carrito de la compra se enviarán a un servlet
											(RecogerCarrito) a través de una llamada ajax EnviarCarrito(url, capa, valores). -->
											<button id="recogerCarrito" type="submit" onclick="EnviarCarrito('recogercarrito.html','cuerpo', carrito)"
												class="btn btn-info btn-block btn-lg">
												<span>Procesar Pago <i
													class="fas fa-long-arrow-alt-right ms-2"></i></span>
											</button>
											<%
											}
											%>
											</div>
									</div>

								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script>
	pintaTabla();
</script>
</html>