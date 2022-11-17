package modelo;

public class Tienda {
int ID;
String Descrip;
double Cantidad;
String Provedor;
double Precio;
public Tienda() {
	
}
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public String getDescrip() {
	return Descrip;
}
public void setDescrip(String descrip) {
	Descrip = descrip;
}
public double getCantidad() {
	return  Cantidad;
}
public void setCantidad(double cantidad) {
	Cantidad = cantidad;
}
public String getProvedor() {
	return Provedor;
}
public void setProvedor(String provedor) {
	Provedor = provedor;
}
public double getPrecio() {
	return Precio;
}
public void setPrecio(double precio) {
	Precio = precio;
}

}
