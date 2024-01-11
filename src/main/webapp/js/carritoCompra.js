/**
 * JavaScript para el manejo del carrito de la compra
 */
	var carrito = []; // Array que contendrá los objetos.
	
	
function insertarCarrito(codigo, descripcion, precio){

	var producto = new Object(); // Objeto que deseamos almacenar
	// Asignamos valores a los atributos del objeto
	producto.codigo = codigo; // Identificador único del objeto
	producto.descripcion = descripcion; // Cantidad que tenemos en el carrito (1 por ejemplo)
	producto.precio = parseInt(precio); // Precio unitario de cada elemento (100.0 por ejemplo)
	// Guardamos el producto en el array.
	producto.cantidad = 1;
	let bool = true;
		for (let i = 0; i < carrito.length && bool == true; i++){
			if (producto.codigo == carrito[i].codigo){
				carrito[i].cantidad++;
				alert("Ahora tiene " +  carrito[i].cantidad + " de " + carrito[i].descripcion);
				bool = false;
			}
		}
	if(bool){
		carrito.push(producto);
		alert(producto.descripcion + " añadido al carrito");
	}
	localStorage.setItem('carrito', JSON.stringify(carrito));	
}

function pintaTabla() {
	carrito = JSON.parse(localStorage.getItem("carrito")) || [];
    var table = document.getElementById("TablaCarrito");
    for (producto of carrito) {
		let para = document.createElement("div");
		para.innerHTML =`<div id ="tabla" class= "col-md-7 col-lg-8">
		<div class="card-body">
						<div class="d-flex justify-content-between">
                      <div class="d-flex flex-row align-items-center">
                        <div class="ms-3">
                          <h5>${producto.descripcion}</h5>
                        </div>
                      </div>
                      <div class="d-flex flex-row align-items-center">
                        <div class="recuadro-1">
                          <h5 class="fw-normal mb-0">${producto.cantidad}</h5>
                        </div>
                        <div class="recuadro-2">
                          <h5 class="mb-0">${producto.precio}€</h5>
                        </div>
                        <a href="#"><i class="fas fa-trash-alt"></i></a>
                      </div>
                      <button class="btn btn-primary me-md-2" onclick="borrarProducto(${producto.codigo})">Aniquilar</button>
                      <button class="btn btn-primary me-md-2" onclick="decrementarProducto(${producto.codigo})">Decrementar</button>
                      <button class="btn btn-primary me-md-2" onclick="incrementarProducto(${producto.codigo})">Aumentar</button>
                    </div>
                  </div>
                  </div>`;
                  table.appendChild(para);
    }

}


function incrementarProducto(codigo) {
	let booleano = true;
for (let i = 0; i < carrito.length && booleano; i++) {
		if (carrito[i].codigo == codigo) {
			carrito[i].cantidad++;
			booleano = false;
		}
	}
	localStorage.setItem('carrito', JSON.stringify(carrito));
	TablaCarrito.innerHTML = ``; //Evitar duplicaciones
    pintaTabla();
}

function decrementarProducto(codigo) {
	let booleano = true;
for (let i = 0; i < carrito.length && booleano; i++) {
		if (carrito[i].codigo == codigo) {
			carrito[i].cantidad--;
			if(carrito[i].cantidad == 0)
				borrarProducto(codigo);
			booleano = false;
		}
	}
	localStorage.setItem('carrito', JSON.stringify(carrito));
	TablaCarrito.innerHTML = ``; //Evitar duplicaciones
    pintaTabla();
}

function borrarProducto(codigo) {
	let booleano = true;
	for (let i = 0; i < carrito.length && booleano; i++) {
		if (carrito[i].codigo == codigo) {
			carrito.splice(i,1);
			booleano = false;
		}
	}
	localStorage.setItem('carrito', JSON.stringify(carrito));
	TablaCarrito.innerHTML = ``;
	pintaTabla();	
}

//Borrar carrito por completo
function borrarCarritoEntero(){
	carrito.splice(0,carrito.length);
	localStorage.setItem('carrito',JSON.stringify(carrito));
}

function generaMenu(url,titulo){
	
	let li = document.getElementById("MenuSesion");
	let a ="<a class='nav-link texto' href='#' onclick=\"Cargar('"+url+"'),'cuerpo')\">"+titulo+"</a>"
	li.innerHTML = a;
}

