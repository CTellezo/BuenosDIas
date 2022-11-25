package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Conexion.conexion;
import modelo.Abastecer;
import modelo.Categoria;
import modelo.Usuario;

public class daoComandos {
	conexion cx = null;

	public daoComandos() {
		cx = new conexion();
	}

	public boolean insertarProducto(Abastecer A) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO inventario VALUES(null,?,?)");
			ps.setString(1, A.getDescripcion());
			ps.setInt(1, A.getCantidad());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Abastecer> fetchAbastecer() {
		ArrayList<Abastecer> lista = new ArrayList<Abastecer>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM inventario");
			rs = ps.executeQuery();
			while (rs.next()) {
				Abastecer u = new Abastecer();
				u.setId(rs.getInt("id"));
				u.setDescripcion(rs.getString("descripcion"));
				u.setCantidad(rs.getInt("cantidad"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public boolean EliminarProducto(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM inventario WHERE id=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarProducto(Abastecer user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE inventario SET descripcion=?,cantidad=? WHERE id=?");
			ps.setInt(1, user.getId());
			ps.setString(2, user.getDescripcion());
			ps.setInt(3, user.getCantidad());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
}