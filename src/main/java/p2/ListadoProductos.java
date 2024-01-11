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
 * Servlet implementation class ListadoProductos
 */
@WebServlet({ "/productos.html", "/products.html", "/productes.html" })
public class ListadoProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoProductos() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProductoBD> productos = AccesoBD.getInstance().obtenerProductosBD();
        
        request.setAttribute("listado-productos",productos);
        
        RequestDispatcher rd = request.getRequestDispatcher("./html/productos.jsp");
        rd.forward(request, response);
	}

}
