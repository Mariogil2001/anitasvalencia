<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List,p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Pagina enhorabuena ha sido timado</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
	<section class="h-100 h-custom">
		<div class="container py-10 h-100">
			<div class="card-body">
				<%
				String advertencia = (String) session.getAttribute("advertencia");
				if (advertencia != null) { //Eliminamos el mensaje consumido
					session.removeAttribute("advertencia");
				%>

				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col-9">
								<div class="card-body">
									<h4 class="card-title">Pedido Realizado</h4>
									<h5 class="card-title">Gracias por confiar en nosotros.</h5>
									<p class="card-text"><%=advertencia%></p>
									<a href="#" class="btn btn-primary"
										onclick="Cargar('./html/inicio.html','cuerpo')">Ir inicio</a>
								</div>
							</div>
							<div class="col"><img src="./images/pedidofinalizado.png" alt="Compra Realizada" class="rounded img-fluid float-end" width="200" height="200"></div>
						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>
		</div>

	</section>

	<script>
		borrarCarritoEntero();
	</script>
</body>
</html>