package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Alumno;
import modelo.Usuario;

public class daoAlumno {
	conexion ca = null;

	public daoAlumno() {
		ca = new conexion();
	}

	public boolean insertarAlumno(Alumno userA) {
		PreparedStatement ps = null;
		try {
			ps = ca.conectar().prepareStatement("INSERT INTO alumno VALUES(null,?,?,?,?)");
			ps.setString(1, userA.getNombre());
			ps.setInt(2, userA.getGrupo());
			ps.setString(3, userA.getCarrera());
			ps.setString(4, userA.getMunicipio());
			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Alumno> consultaAlumnos() {
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = ca.conectar().prepareStatement("SELECT * FROM alumno");
			rs = ps.executeQuery();
			while (rs.next()) {
				Alumno userA = new Alumno();
				userA.setId(rs.getInt("id"));
				userA.setNombre(rs.getString("nombre"));
				userA.setGrupo(rs.getInt("grupo"));
				userA.setCarrera(rs.getString("carrera"));
				userA.setMunicipio(rs.getString("municipio"));
				lista.add(userA);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	public boolean EliminarAlumno(int Id) {
		PreparedStatement ps=null;
		try {
			ps=ca.conectar().prepareStatement("DELETE FROM alumno WHERE id=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean editarAlumno(Alumno user) {
		PreparedStatement ps=null;
		try {
			ps=ca.conectar().prepareStatement("UPDATE alumno SET nombre=?,grupo=?,carrera=?,municipio=? WHERE id=?");
			ps.setString(1,  user.getNombre());
			ps.setInt(2,  user.getGrupo());
			ps.setString(3,  user.getCarrera());
			ps.setString(4,user.getMunicipio());
			ps.setInt(5,user.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}

	}


}
