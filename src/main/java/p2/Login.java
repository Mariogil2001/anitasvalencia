package p2;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.html")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession sesion = request.getSession(false);
		
		if (sesion != null && sesion.getAttribute("usuario")!=null) {
			response.sendRedirect("zonausuario.html");
			
		} else {
			
			RequestDispatcher rd = request.getRequestDispatcher("./html/login.jsp");
			rd.forward(request, response);
		}
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usr = request.getParameter("email");
		String clave = request.getParameter("password");
				
		AccesoBD acceso = AccesoBD.getInstance();
		
		int codigo = acceso.comprobarUsuarioBD(usr, clave);
		
		if (codigo > 0) {
			UsuarioBD usuario = acceso.obtenerUsuario(codigo);
			//añadido .getSession()
			request.setAttribute("user", usuario);
			// Usuario autenticado y por lo tanto sesión creada.
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuario", codigo);

			RequestDispatcher rd = request.getRequestDispatcher("./html/zona_usuario.jsp");
			rd.forward(request, response);

		} else {
			
			request.setAttribute("mensaje", "Usuario y/o contraseña incorrectos");
			RequestDispatcher rd = request.getRequestDispatcher("./html/login.jsp");
			rd.forward(request, response);
		}
	}

}
