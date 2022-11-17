package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Conexion.conexion;
import modelo.Tienda;
import modelo.Usuario;

public class daoTienda {
	conexion cx = null;

	public daoTienda() {
		cx = new conexion();
	}

	public boolean insertarCompra(Tienda user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO producto VALUES(null,?,?,?,?,?)");
			ps.setInt(1, user.getID());
			ps.setString(2, user.getDescrip());
			ps.setDouble(2, user.getCantidad());
			ps.setString(3, user.getProvedor());
			ps.setDouble(3, user.getPrecio());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Tienda> fetchproducto() {
		ArrayList<Tienda> lista = new ArrayList<Tienda>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM producto");
			rs = ps.executeQuery();
			while (rs.next()) {
				Tienda u = new Tienda();
				u.setID(rs.getInt("ID"));
				u.setDescrip(rs.getString("Descripcion"));
				u.setCantidad( rs.getDouble("Cantidad"));
				u.setProvedor(rs.getString("Provedor"));
				u.setPrecio(rs.getDouble("Precio"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}


	public boolean EliminarProducto(int ID) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM producto WHERE ID=?");
			ps.setInt(1, ID);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarCompra(Tienda user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE producto SET Descripcion=?,Cantidad=?,Provedor=?,Precio=? WHERE ID=?");
			ps.setInt(1, user.getID());
			ps.setString(3, user.getDescrip());
			ps.setDouble(4, user.getCantidad());
			ps.setString(4, user.getProvedor());
			ps.setDouble(4, user.getPrecio());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}