/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConexionBD.ConexionBD;
import Entidades.Trabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAO {
    
    private Connection conn;
    public Trabajador obtenerPorId(int id) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Trabajador trabajador = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT * FROM trabajador WHERE idmedesp = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                trabajador = new Trabajador();
                trabajador.setId(rs.getInt("idmedesp"));
                trabajador.setIdEspecialidad(rs.getInt("#Esp_Cod"));
                trabajador.setIdMedico(rs.getInt("#Med_Cod"));
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

        return trabajador;
    }

    public void insertar(Trabajador trabajador) {

        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "INSERT INTO trabajador (Esp_Cod, Med_Cod) VALUES (?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, trabajador.getIdEspecialidad());
            stmt.setInt(2, trabajador.getIdMedico());
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
            String query = "DELETE FROM trabajador WHERE idmedesp = ?";
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

    public List <Trabajador> listarTodos() {
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Trabajador> trabajadores = new ArrayList<>();

        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT * FROM trabajador";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Trabajador trabajador = new Trabajador();
                trabajador.setId(rs.getInt("idmedesp"));
                trabajador.setIdEspecialidad(rs.getInt("#Esp_Cod"));
                trabajador.setIdMedico(rs.getInt("#Med_Cod"));
                trabajadores.add(trabajador);
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

        return trabajadores;
    }
    
    public int obtenerIdPorNombreEspecialidad(String nombreEspecialidad) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idEspecialidad = -1; // Default value if not found

        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT idespecial FROM especialidad WHERE nombres_esp = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nombreEspecialidad);
            rs = stmt.executeQuery();

            if (rs.next()) {
                idEspecialidad = rs.getInt("idespecial");
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

        return idEspecialidad;
    }
}

