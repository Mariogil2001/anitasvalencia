package p2;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetallePedido
 */
@WebServlet("/detallepedido.html")
public class DetallePedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetallePedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int pedido = Integer.parseInt(request.getParameter("pedido"));
		
		AccesoBD bd = AccesoBD.getInstance();
		
		List<DetalleBD> detalles = bd.obtenerDetallePedido(pedido);
		List<ProductoBD> productos = AccesoBD.getInstance().obtenerProductosBD();
		
        request.setAttribute("listado-productos",productos);
		request.setAttribute("detalles", detalles);
		
        RequestDispatcher rd = request.getRequestDispatcher("./html/detalle.jsp");
        rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
