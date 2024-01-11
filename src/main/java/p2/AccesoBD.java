package p2;

import java.sql.*;
import java.util.*;
import java.util.Date;


/**
 * @author Mario Gil Domingo
 *
 */
public class AccesoBD {
	
	protected static String DB_URI = "jdbc:mysql://localhost:3306/prueba";
    // root y sin clave es el usuario por defecto que crea XAMPP.
	protected static String DB_USER = "root";
	protected static String DB_PASSWORD = "";
	
	private static AccesoBD instanciaUnica = null;
	private Connection conexionBD = null;
	
	
	private AccesoBD() {
		
	}
	 public static AccesoBD getInstance() {
		 if (instanciaUnica == null)
			 	instanciaUnica = new AccesoBD();
		 
		 return instanciaUnica;
	 }
	 
	 
	 void abrirConexion() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexionBD = DriverManager.getConnection(DB_URI, DB_USER, DB_PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 
	 void cerrarConexion() {
		 try {
			 conexionBD.close();
			 conexionBD = null;
		 } catch(Exception e) {
			 System.err.println("No se ha podido desconectar de la base de datos");
			 System.err.println(e.getMessage());
		 }
	 }
	 
	 public boolean comprobarAcceso() {
		 abrirConexion();
		 boolean correcto = (conexionBD!=null);
		 cerrarConexion();
		 return correcto;
	 }
	 
	 public List<ProductoBD> obtenerProductosBD(){
		 abrirConexion();
		 
		 ArrayList<ProductoBD> productos = new ArrayList<>();
		 
		 try {
			// hay que tener en cuenta las columnas de vuestra tabla de productos
			// también se puede utilizar una consulta del tipo:
			// con = "SELECT * FROM productos";
			String con = "SELECT codigo,descripcion,precio,existencias,imagen,explicacionproducto FROM productos";
			Statement s = conexionBD.createStatement();
			ResultSet resultado = s.executeQuery(con);
			while(resultado.next()){
			ProductoBD producto = new ProductoBD();
			producto.setCodigo(resultado.getInt("codigo"));
			producto.setDescripcion(resultado.getString("descripcion"));
			producto.setPrecio(resultado.getFloat("precio"));
			producto.setStock(resultado.getInt("existencias"));
			producto.setImagen(resultado.getString("imagen"));
			producto.setExplicacion(resultado.getString("explicacionproducto"));
			productos.add(producto);
			}
			}
			catch(Exception e) {
			System.err.println("Error ejecutando la consulta a la base de datos");
			System.err.println(e.getMessage());
			}
		 return productos;
	 }

	 
		public int comprobarUsuarioBD(String usuario, String clave) {

			abrirConexion();

			try {
				String con = "SELECT codigo FROM usuarios WHERE usuario=? AND clave=? AND activo=1";
				
				PreparedStatement s = conexionBD.prepareStatement(con);
				s.setString(1, usuario);
				s.setString(2, clave);
				ResultSet resultado = s.executeQuery();
				
				if (resultado.next()) { // El usuario/clave se encuentra en la BD 
					return resultado.getInt("codigo");
				}
			} catch (Exception e) { 
				e.printStackTrace();
			}
			
			cerrarConexion();
			
			return -1;
		}
		public boolean registrarUsuarioBD(String usuario, String clave, String nombre, String apellidos, String domicilio, String cp, String telefono) {
		    abrirConexion();
		    try {
		        String con = "INSERT INTO usuarios (usuario, clave, nombre, apellidos, domicilio, cp,telefono, activo) VALUES (?, ?, ?, ?, ?, ?, ?,1)";
		        PreparedStatement s = conexionBD.prepareStatement(con);
		        s.setString(1, usuario);
		        s.setString(2, clave);
		        s.setString(3, nombre);
		        s.setString(4, apellidos);
		        s.setString(5, domicilio);
		        s.setString(6, cp);
		        s.setString(7, telefono);
		        int filasAfectadas = s.executeUpdate();
		        return filasAfectadas == 1; // se espera que solo se afecte una fila
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        cerrarConexion();
		    }
		    return false;
		}


	    public UsuarioBD obtenerUsuario(int codigo) {
	        abrirConexion();
	        
	        UsuarioBD usuario = new UsuarioBD();
	        
	        try {
	    	    /**
	    	     * Sentencia SQL para obtener un usuario de la base de datos a partir de su codigo
	    	     */
	    	 
	        	String SQL = "SELECT codigo,usuario,clave,nombre,apellidos,domicilio,cp,telefono FROM usuarios WHERE codigo = ? ";
	            PreparedStatement ps = conexionBD.prepareStatement(SQL);
	            ps.setInt(1,codigo);
	            ResultSet resultado = ps.executeQuery();
	            
	            if (resultado.next()) {
	                usuario.setCodigo(resultado.getInt("codigo"));
	                usuario.setUsuario(resultado.getString("usuario"));
	                usuario.setClave(resultado.getString("clave"));
	                usuario.setNombre(resultado.getString("nombre"));
	                usuario.setApellidos(resultado.getString("apellidos"));
	                usuario.setDomicilio(resultado.getString("domicilio"));
	                usuario.setCp(resultado.getString("cp"));
	                usuario.setTelefono(resultado.getString("telefono"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        cerrarConexion();
	        
	        return usuario;
	    }
	    

	    public boolean actualizaUsuarioBD(int codigo, String clave, String domicilio, String cp, String telefono) {
	        boolean actualizado = false;
	        
	        abrirConexion();
	         String SQL = "UPDATE usuarios SET clave = ?, domicilio = ?, cp = ?, telefono = ? WHERE codigo = ? ";
	        
	        try {

	            PreparedStatement ps = conexionBD.prepareStatement(SQL);
	            ps.setString(1, clave);
	            ps.setString(2, domicilio);
	            ps.setString(3, cp);
	            ps.setString(4, telefono);
	            ps.setInt(5,codigo);

	            ps.execute();
	            
	            // En las inserciones y actualizaciones podemos obtener el 
	            // número de cambios en la base de datos
	            actualizado = (ps.getUpdateCount() > 0);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        cerrarConexion();
	        System.out.println("Actualizado usuario");
	        return actualizado;
	    }

	            
	    /**
	     * Sentencia SQL para obtener un producto de la base de datos a partir de su identificador
	     */
	    private final static String SQL_OBTENER_PRODUCTO = "SELECT codigo,descripcion,precio,existencias,imagen FROM productos WHERE codigo = ?"; 
	            
	    /**
	     * Obtiene un producto a partir de su identificador.
	     * En caso de no existir o de error en el acceso a la base de datos devuelven <i>null</i>
	     * 
	     * @param id Identificador del producto
	     * @return Producto o <i>null</i>
	     */
	    public int obtenerStockPorId(Integer id) {
	        abrirConexion();
	        
	        ProductoBD producto = null;
	        
	        try {

	            PreparedStatement ps = conexionBD.prepareStatement(SQL_OBTENER_PRODUCTO);
	            
	            ps.setInt(1, id);
	            
	            ResultSet resultado = ps.executeQuery();
	            
	            if (resultado.next()) {
	                producto = new ProductoBD();
	                producto.setCodigo(0);
	                producto.setDescripcion(resultado.getString("descripcion"));
	                producto.setPrecio(resultado.getFloat("precio"));
	                producto.setStock(resultado.getInt("existencias"));
	                producto.setImagen(resultado.getString("imagen"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        cerrarConexion();
	        System.out.println(producto.getStock());

	        return producto.getStock();
	    }
	    
	    /**
	     * Añade producto a la lista de pedidos y se lo asigna a un usuario
	     * @param producto
	     * @param usuario
	     * @return
	     */
	    public boolean añadirDetallePedido(ArrayList<ProductoBD> producto, int usuario) {
		    abrirConexion();
		    float precioTotal = 0;
		    try {
		        String con = "INSERT INTO pedidos ( persona, fecha, importe, estado) VALUES ( ?, ?, ?,?)";
		        PreparedStatement s = conexionBD.prepareStatement(con);
		        for (int i = 0; i < producto.size(); i++) {
		        	precioTotal += producto.get(i).getStock() * producto.get(i).getPrecio();
		        	
		        }
		        s.setInt(1, usuario);
		        s.setDate(2, new java.sql.Date(new Date().getTime()));
		        s.setFloat(3, precioTotal);
		        s.setInt(4, 1);

		        int filasAfectadas = s.executeUpdate();
		        return filasAfectadas == 1; // se espera que solo se afecte una fila
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		    	System.out.println("Añadido correctamente");
		        cerrarConexion();
		    }
		    return false;
		}
	    
	    /**
	     * Añade un pedido a la tabla de detalle
	     * @param producto
	     * @return
	     */

	    public boolean añadirPedido(ProductoBD producto) {
		    abrirConexion();
		    int codigo = 0;
		    try {
		        String con = "INSERT INTO detalle (codigo_pedido, codigo_producto, unidades, precio_unitario) VALUES (? , ?, ?, ?)";
		        PreparedStatement s = conexionBD.prepareStatement(con);
		        ResultSet result = s.executeQuery("SELECT MAX(codigo) from pedidos"); // Como es autoincremental el ultimo pedido es el mas alto vaya
		        if(result.next()) {
		        	codigo = result.getInt(1);
		        }
		        s.setInt(1, codigo);
		        s.setInt(2, producto.getCodigo());
		        s.setFloat(3, producto.getStock());
		        s.setFloat(4, producto.getPrecio());

		        int filasAfectadas = s.executeUpdate();
		        return filasAfectadas == 1; // se espera que solo se afecte una fila
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		    	System.out.println("Añadido detalle de producto");
		        cerrarConexion();
		    }
		    return false;
	    }
	    
	    /**
	     * Obtiene una lista con todos los pedidos de una persona
	     * @param persona
	     * @return
	     */
	    public ArrayList<PedidoBD> obtenerPedidos(int persona) {
	        abrirConexion();
	        
	        ArrayList<PedidoBD> pedidos = new ArrayList<>();
	        
	        try {
	    	    /**
	    	     * Sentencia SQL para obtener un usuario de la base de datos a partir de su codigo
	    	     */
	    	 
	        	String SQL = "SELECT persona,codigo,estado,fecha,importe FROM pedidos WHERE persona = ? ";
	            PreparedStatement ps = conexionBD.prepareStatement(SQL);
	            ps.setInt(1,persona);
	            ResultSet resultado = ps.executeQuery();
	            
	           while (resultado.next()) {
	        	   	PedidoBD pedido = new PedidoBD();
	            	pedido.setFecha(resultado.getDate("fecha"));
	            	pedido.setEstado(resultado.getInt("estado"));
	            	pedido.setPersona(resultado.getInt("persona"));
	                pedido.setCodigo(resultado.getInt("codigo"));
	                pedido.setPrecio_unitario(resultado.getInt("importe"));
	                pedidos.add(pedido);
	                //System.out.println(pedido.getUnidades());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        cerrarConexion();
	        
	        return pedidos;
	    }
	    
	    /**
	     * Para ver el detalle del pedido, le pasamos el codigo del pedido y obtenemos el producto su cantidad y precio unitario
	     * @param codigo_pedido
	     * @return detalles, el pedido entero
	     */
	    public ArrayList<DetalleBD> obtenerDetallePedido(int codigo_pedido){
	    	abrirConexion();
	    	
	    	ArrayList<DetalleBD> detalles = new ArrayList<>();
	    	 
	    	try {
	    		
		    	String SQL = "SELECT codigo_producto,unidades,precio_unitario FROM detalle WHERE codigo_pedido = ? ";
		    	PreparedStatement ps = conexionBD.prepareStatement(SQL);
		    	ps.setInt(1, codigo_pedido);
		    	ResultSet resultado = ps.executeQuery();
		    	
		    	while(resultado.next()) {
		    		DetalleBD detalle = new DetalleBD();
		    		detalle.setCodigo_producto(resultado.getInt("codigo_producto"));
		    		detalle.setUnidades(resultado.getInt("unidades"));
		    		detalle.setPrecio_unitario(resultado.getFloat("precio_unitario"));
		    		detalles.add(detalle);
		    	}
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	cerrarConexion();
	    	return detalles;
	    	
	    }
	    
	    /**
	     * Funcion que se le pasa el codigo del pedido y cambia su estado a 4 (Cancelado)
	     * @param codigo_pedido
	     * @return si se ha podido cancelar el pedido o no
	     */
	    public boolean cancelarPedido(int codigo_pedido) {
	    	boolean actualizado = false;
	        
	        abrirConexion();
	         String SQL = "UPDATE pedidos SET estado = 4 WHERE codigo = ? ";
	        
	        try {

	            PreparedStatement ps = conexionBD.prepareStatement(SQL);
	            ps.setInt(1,codigo_pedido);

	            // Si en vez de string fueran int, setInt
	            ps.execute();
	            
	            // En las inserciones y actualizaciones podemos obtener el 
	            // número de cambios en la base de datos
	            actualizado = (ps.getUpdateCount() > 0);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        cerrarConexion();
	        System.out.println("Pedido cancelado");
	        return actualizado;
	    }

}
