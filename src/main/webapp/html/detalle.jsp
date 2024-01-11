<%@ page language="java" contentType="text/html charset=UTF-8"
	import="p2.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Detalles de los pedidos</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
	<section class="h-100 h-custom">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="container text-center">
					<div class="card card-shopping-cart">
						<div class="row">

							<div class="col">
								<p class="fw-bold fs-1">Producto</p>
<hr/>
							</div>
							<div class="col">
								<p class="fw-bold fs-1">Unidades</p>
								<hr/>
							</div>
							<div class="col">
								<p class="fw-bold fs-1">Precio</p>
<hr/>
							</div>
							 
						</div>

						<%
						List<ProductoBD> productos = (List<ProductoBD>) request.getAttribute("listado-productos");
						List<DetalleBD> detalle = (List<DetalleBD>) request.getAttribute("detalles");
						for (int i = 0; detalle != null && i < detalle.size(); i++) {
							DetalleBD det = detalle.get(i);
							for (int j = 0; productos != null && j < productos.size(); j++){
								ProductoBD prod = productos.get(j);
								if(prod.getCodigo() == det.getCodigo_producto()){
							
						%>
						<div class="row align-items-start">

							<div class="col">
								<p class="fs-4"><%=prod.getDescripcion()%></p><hr/></div>
							<div class="col">
								<p class="fs-4"><%=det.getUnidades()%></p><hr/></div>
							<div class="col">
								<p class="fs-4"><%=det.getPrecio_unitario()%> &euro;</p><hr/></div>

						</div>
						<%
								}
							}
						}
						%>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>