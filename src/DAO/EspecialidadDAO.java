package DAO;

import ConexionBD.ConexionBD;
import Entidades.Especialidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadDAO {
    
    private Connection conn;
    
    public Especialidad obtenerPorId(int id) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Especialidad especialidad = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT * FROM especialidad WHERE idespecial = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                especialidad = new Especialidad();
                especialidad.setIdEspecialidad(rs.getInt("idespecial"));
                especialidad.setNombre(rs.getString("nombres_esp"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return especialidad;
    }

    public void insertar(Especialidad especialidad) {
       
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "INSERT INTO especialidad (nombres_esp) VALUES (?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, especialidad.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void eliminar(int id) {

        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "DELETE FROM especialidad WHERE idespecial = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Especialidad> listarTodos() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Especialidad> especialidades = new ArrayList<>();

        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT * FROM especialidad";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Especialidad especialidad = new Especialidad();
                especialidad.setIdEspecialidad(rs.getInt("idespecial"));
                especialidad.setNombre(rs.getString("nombres_esp"));
                especialidades.add(especialidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return especialidades;
    }
}
