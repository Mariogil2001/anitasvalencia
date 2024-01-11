package p2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/zonausuario.html")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuario() {
        super();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession sesion = request.getSession(false);
    	int codigo = (int)sesion.getAttribute("usuario");
		AccesoBD acceso = AccesoBD.getInstance();
		
		UsuarioBD usuario = acceso.obtenerUsuario(codigo);
		request.setAttribute("user", usuario);
		
		List<PedidoBD> pedido = acceso.obtenerPedidos(codigo);
		request.setAttribute("pedido", pedido);
		
		ArrayList<ProductoBD> carritoJSON = (ArrayList<ProductoBD>) sesion.getAttribute("listaproductos");
		request.setAttribute("productos", carritoJSON);
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("./html/zona_usuario.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		int codigo = (int) session.getAttribute("usuario");
        String clave = request.getParameter("password");
        String domicilio = request.getParameter("dir");
        String cp = request.getParameter("cod");
        String telf = request.getParameter("tel");


        // Para poner enteros int telf = Integer.parseInt(request.getParameter("tel"));
        AccesoBD bd = AccesoBD.getInstance();
        boolean acualizado = bd.actualizaUsuarioBD (codigo, clave, domicilio, cp, telf);


        if (acualizado) {
			RequestDispatcher rd = request.getRequestDispatcher("./html/inicio.html");
			rd.forward(request, response);
			
        } else {
			request.setAttribute("mensaje", "Error al actualizar");
			RequestDispatcher rd = request.getRequestDispatcher("./html/zona_usuario.jsp");
			rd.forward(request, response);
        }

    }
	

}
