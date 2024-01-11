<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List,p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="container-tienda">
	<div class="row row-cols-1 row-cols-md-2 g-4">
	<%
	List<ProductoBD> productos =(List<ProductoBD>) request.getAttribute("listado-productos");

	for (int i = 0; productos != null && i < productos.size(); i++) {
		ProductoBD prod = productos.get(i);
	
	%>
	<div class="col">
	<div class="card">
	<div class="hovereffect">
			<img src="<%=prod.getImagen()%>" class="card-img-top" alt="Producto">
			<div class="overlay">
		<h2 class="tipografia"><%=prod.getDescripcion()%></h2>
		<% if(prod.getStock() > 0){%>
		<a onclick="insertarCarrito('<%=prod.getCodigo() %>', '<%=prod.getDescripcion() %>','<%=prod.getPrecio() %>')" class="info tipografia" href="#"><%=prod.getPrecio()%>€</a> <%}else{ %>
		<a class ="info tipografia" href="#"> Fuera de servicio</a><%} %>
		</div>
		</div>
		<div class="card-body">
		<p class="card-text tipografia"><%=prod.getExplicacion() %></p>
		</div>
		</div>
	</div>
	<%
	}
	%>
	</div>
	</div>
</body>
</html>