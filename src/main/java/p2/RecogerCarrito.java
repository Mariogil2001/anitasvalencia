package p2;

import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.json.*;

/**
 * Servlet implementation class RecogerCarrito
 */
@WebServlet("/recogercarrito.html")
public class RecogerCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecogerCarrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*El servlet RecogerCarrito, se encargará de comprobar que los datos sean correctos (que haya
		stock disponible de los productos comprados, por ejemplo).
		~Sirve para procesar la cadena JSON que contiene el carrito en un servlet. */

		RequestDispatcher rd = request.getRequestDispatcher("./html/carrito.jsp");
		rd.forward(request, response);
	}

	/**
	 * @param carrito 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		/*El servlet RecogerCarrito, redirigirá la petición a un documento
		JSP (resguardo) que mostrará los datos de la compra y los datos de envío y facturación, junto con
		dos botones, uno para llevar a cabo la compra y otro para cancelarla*/
		HttpSession session = request.getSession(false);
		int codigo = (int) session.getAttribute("usuario");
		AccesoBD acceso = AccesoBD.getInstance();
		UsuarioBD usuario = acceso.obtenerUsuario(codigo);
		request.setAttribute("user", usuario);
		int productoNOstock = 0;
		
		ArrayList<ProductoBD> carritoJSON = new ArrayList<ProductoBD>();

		JsonReader jsonReader = Json.createReader(new InputStreamReader(request.getInputStream()));
		JsonArray jobj = jsonReader.readArray();
		for (int i = 0; i < jobj.size(); i++) {
			JsonObject prod = jobj.getJsonObject(i);
			ProductoBD nuevo = new ProductoBD();
			nuevo.setCodigo(Integer.parseInt(prod.getString("codigo")));
			nuevo.setDescripcion(prod.getString("descripcion"));
			nuevo.setPrecio(Float.parseFloat(prod.get("precio").toString()));
			nuevo.setStock(prod.getInt("cantidad"));
			if(acceso.obtenerStockPorId(nuevo.getCodigo()) > nuevo.getStock()) {
				carritoJSON.add(nuevo);
			} else {
				productoNOstock++;
			}
			System.out.println(nuevo);
		}
		session.setAttribute("listaproductos", carritoJSON);
		session.setAttribute("productoNOstock", productoNOstock);
    	RequestDispatcher rd2 = request.getRequestDispatcher("./html/resguardo.jsp");
		rd2.forward(request, response);
	}


}
