package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Conexion.conexion;
import modelo.Categoria;
import modelo.Usuario;

public class daoCategoria {
	conexion cx = null;

	public daoCategoria() {
		cx = new conexion();
	}

	public boolean insertarCategoria(Categoria user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO categoria VALUES(null,?)");
			ps.setInt(1, user.getID());
			ps.setString(2, user.getCategoria());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Categoria> fetchCategoria() {
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM categoria");
			rs = ps.executeQuery();
			while (rs.next()) {
				Categoria u = new Categoria();
				u.setID(rs.getInt("id"));
				u.setCategoria(rs.getString("categoria"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public boolean EliminarCategoria(int ID) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM categoria WHERE ID=?");
			ps.setInt(1, ID);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarUsuario(Categoria user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE usuario SET user=?,password=?,nombre=? WHERE id=?");
			ps.setInt(1, user.getID());
			ps.setString(2, user.getCategoria());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
}