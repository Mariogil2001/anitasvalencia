package p2;

import java.sql.Date;

public class PedidoBD {
int codigo;
int persona;
Date fecha;
float importe;
int estado;
int unidades;
int precio_unitario;

public int getCodigo() {
	return codigo;
}
public void setCodigo(int codigo) {
	this.codigo = codigo;
}
public int getPersona() {
	return persona;
}
public void setPersona(int persona) {
	this.persona = persona;
}
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fecha) {
	this.fecha = fecha;
}
public float getImporte() {
	return importe;
}
public void setImporte(float importe) {
	this.importe = importe;
}
public int getEstado() {
	return estado;
}
public void setEstado(int estado) {
	this.estado = estado;
}
public int getUnidades() {
	return unidades;
}
public void setUnidades(int unidades) {
	this.unidades = unidades;
}
public int getPrecio_unitario() {
	return precio_unitario;
}
public void setPrecio_unitario(int precio_unitario) {
	this.precio_unitario = precio_unitario;
}

}
