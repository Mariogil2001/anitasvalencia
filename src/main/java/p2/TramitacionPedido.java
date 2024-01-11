package p2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TramitacionPedido
 */
@WebServlet("/resguardo.html")
public class TramitacionPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TramitacionPedido() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		int productosinstock = 0;
		ArrayList<ProductoBD> carritoJSON = (ArrayList<ProductoBD>) session.getAttribute("listaproductos");
		productosinstock = (int) session.getAttribute("productoNOstock");
		int codigo = (int) session.getAttribute("usuario");
		String clave = request.getParameter("password");
		String domicilio = request.getParameter("dir");
		String cp = request.getParameter("cod");
		String telf = request.getParameter("tel");

		// Para poner enteros int telf = Integer.parseInt(request.getParameter("tel"));
		AccesoBD bd = AccesoBD.getInstance();
		bd.actualizaUsuarioBD(codigo, clave, domicilio, cp, telf);
		// Si productosinstock es mayor que 1 significa que hay algun producto que no tiene stock
		if (productosinstock >= 1) {
			session.setAttribute("advertencia", "El pedido no se ha podido realizar por falta de existencias.");
			RequestDispatcher rt = request.getRequestDispatcher("./html/pedidofinalizado.jsp");
			rt.forward(request, response);
		} else {
			// Si hay stock se sube con la función de añadirDetallePedido
			bd.añadirDetallePedido(carritoJSON, codigo);
			for (int i = 0; i < carritoJSON.size(); i++) {
				bd.añadirPedido(carritoJSON.get(i));
			}
			RequestDispatcher rd = request.getRequestDispatcher("./html/pedidofinalizado.jsp");
			rd.forward(request, response);
		}

	}
}