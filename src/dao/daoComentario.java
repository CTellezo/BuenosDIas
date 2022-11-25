package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Comentario;
import modelo.Usuario;
import modelo.Comentario;

public class daoComentario {
	conexion cx = null;

	public daoComentario() {
		cx = new conexion();
	}

	public boolean insertarComentario(Comentario user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO comentario VALUES(null,?,?)");
			ps.setString(1, user.getTexto());
			ps.setString(2, user.getUsuario());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Comentario> fetchComentarios() {
		ArrayList<Comentario> lista = new ArrayList<Comentario>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM comentario");
			rs = ps.executeQuery();
			while (rs.next()) {
				Comentario u = new Comentario();
				u.setId(rs.getInt("id"));
				u.setTexto(rs.getString("texto"));
				u.setUsuario(rs.getString("usuario"));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean EliminarComentario(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM comentario WHERE id=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarComentario(Comentario user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE comentario SET texto=?,usuarios=? WHERE id=?");
			ps.setString(1, user.getTexto());
			ps.setString(2, user.getUsuario());
			ps.setInt(3, user.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}