package p2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Registro
 */
@WebServlet("/registro.html")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("email");
        String clave = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String domicilio = request.getParameter("domicilio");
        String cp = request.getParameter("cp");
        String telf = request.getParameter("telefono");
        
        AccesoBD bd = AccesoBD.getInstance();
        boolean registrado = bd.registrarUsuarioBD(usuario, clave, nombre, apellidos, domicilio, cp, telf);


        if (registrado) {
			RequestDispatcher rd = request.getRequestDispatcher("./html/login.jsp");
			rd.forward(request, response);
			
        } else {
			request.setAttribute("mensaje", "Usuario incorrecto");
			RequestDispatcher rd = request.getRequestDispatcher("./html/registro.jsp");
			rd.forward(request, response);
        }

    }
}

