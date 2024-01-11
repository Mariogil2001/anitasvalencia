<%@page language="java" contentType="text/html charset=UTF-8"
	import="p2.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>Zona Usuario</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
	<%
	UsuarioBD user = (UsuarioBD) request.getAttribute("user");
	List<PedidoBD> pedidos = (List<PedidoBD>) request.getAttribute("pedido");
	%>
	<div class="card-body mt-5">

		<div class="container">

			<div class="card mb-3">

				<div class="row g-0">
				<div class="card-body">
					<h3>Zona de Usuario</h3>
					<p>Aqui podras modificar y añadir tus datos, para cualquier
						tipo de duda puede ponerse en contacto con info@anitasvalencia.es.
						Si hay dudas sobre el proyecto solicitado o su cancelación deberá
						poner bien sus datos ya que nos pondremos en contacto a través de
						estos.</p>
						</div>
					<div class="col-md-4">
						<img src="./images/avatar.jpg" class="img-fluid rounded-start"
							width="256px" height="256px" alt="Avatar">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title">Informacion Personal</h5>
							<form action="#"
								onsubmit="ProcesarForm(this,'zonausuario.html','cuerpo'); return false">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span> <input id="email"
										type="text" class="form-control" name="email"
										value=<%=user.getUsuario()%> disabled>
								</div>
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-lock"></i></span> <input id="password"
										type="password" class="form-control" name="password"
										value="<%=user.getClave()%>" required>
								</div>
								<br>
								<div class="input-group">
									<span class="input-group-addon">Telefono</span> <input id="tel"
										type="tel" class="form-control" name="tel"
										value=<%=user.getTelefono()%> required>
								</div>
								<div class="input-group">
									<span class="input-group-addon">Direccion</span> <input
										id="dir" type="text" class="form-control" name="dir"
										value="<%=user.getDomicilio()%>" required> <span
										class="input-group-addon">Codigo Postal</span> <input id="cod"
										type="number" class="form-control" name="cod"
										value="<%=user.getCp()%>" required>
								</div>
								<div class="btn-group">

									<input name="enviar" id="enviar"
										class="btn btn-primary me-md-2" type="submit"
										value="Actualizar">
								</div>

							</form>
							<form action="#"
								onsubmit="ProcesarForm(this,'CerrarSesion','cuerpo'); return false">
								<div class="btn-group">
									<input name="logout" id="logout"
										class="btn btn-primary me-md-2" type="submit"
										value="Cerrar Sesion">
								</div>
							</form>
						</div>
					</div>

				</div>
				<div class="container text-center">
					<div class="row">
						<div class="col">
							<h2>Fecha de pedido</h2>
						</div>
						<div class="col">
							<h2>Estado de pedido</h2>
						</div>
						<div class="col">
							<h2>Precio Pedido</h2>
						</div>
					</div>
					<%
					for (int i = 0; pedidos != null && i < pedidos.size(); i++) {
						PedidoBD ped = pedidos.get(i);
					%>
					<div class="row align-items-start">
						<div class="col">
							<%=ped.getFecha()%>
						</div>
						<%
						if (ped.getEstado() == 1) {
						%>
						<div class="col">
							<p>En Proceso</p>
							<button type="button" onclick="Cargar('cancelarpedido.html?pedido=<%=ped.getCodigo() %>','cuerpo')" class="btn btn-danger">Cancelar pedido</button>
							<button type="button" onclick="Cargar('detallepedido.html?pedido=<%=ped.getCodigo() %>','cuerpo')"class="btn btn-dark"> Ver detalle del pedido</button>
						</div>
						<%
						}
						%><%
						if (ped.getEstado() == 2) {
						%>
						<div class="col">
							<p>En Proceso</p>
							<button type="button" class="btn btn-secondary" disabled>En Proceso</button>
							<button type="button" onclick="Cargar('detallepedido.html?pedido=<%=ped.getCodigo() %>','cuerpo')"class="btn btn-dark"> Ver detalle del pedido</button>
						</div>
						<%
						}
						%>
						<%
						if (ped.getEstado() == 3) {
						%>
						<div class="col">
							<p>Recibido</p>
							<button type="button" class="btn btn-secondary" disabled>Recibido</button>
							<button type="button" onclick="Cargar('detallepedido.html?pedido=<%=ped.getCodigo() %>','cuerpo')"class="btn btn-dark"> Ver detalle del pedido</button>
						</div>
						<%
						}
						%>
						<%
						if (ped.getEstado() == 4) {
						%>
						<div class="col">
							<p>Pedido cancelado</p>
							<button type="button" class="btn btn-secondary" disabled>Cancelado</button>
							<button type="button" onclick="Cargar('detallepedido.html?pedido=<%=ped.getCodigo() %>','cuerpo')"class="btn btn-dark"> Ver detalle del pedido</button>
						</div>
						<%
						}
						%>
						<div class="col">
							<%=ped.getPrecio_unitario()%>
						</div>
					</div>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

