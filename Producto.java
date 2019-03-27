
public class Producto {
	
	String codigo;
	int precio;
	String nombre;
	
	public Producto() {}
	public Producto(String codigo, int precio, String nombre) {
		super();
		this.codigo = codigo;
		this.precio = precio;
		this.nombre = nombre;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
