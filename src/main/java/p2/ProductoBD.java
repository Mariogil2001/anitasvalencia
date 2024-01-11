package p2;

public class ProductoBD {
	
	private int codigo;
	private String descripcion;//Nombre
	private float precio;
	private int stock;//Cantidad
	private String imagen;
	private String explicacionproducto;
	

	// Settters y Getters
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getExplicacion() {
		return explicacionproducto;
	}
	public void setExplicacion(String explicacionproducto) {
		this.explicacionproducto = explicacionproducto;
	}
	
}
