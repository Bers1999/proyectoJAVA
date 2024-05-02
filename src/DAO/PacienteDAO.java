package DAO;

import ConexionBD.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entidades.Paciente;

public class PacienteDAO{
    
    private Connection conn;

    public PacienteDAO() {
        // Initialize the connection when creating a PacienteDAO object
        conn = ConexionBD.getConnection();
    }
    
    public Paciente obtenerPorId(int id) {
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Paciente paciente = null;

        try {
            
            String query = "SELECT * FROM paciente WHERE idpaciente = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idpaciente"));
                paciente.setDni(rs.getString("dni"));
                paciente.setNombres(rs.getString("nombres_pac"));
                paciente.setApellidoPaterno(rs.getString("paterno_pac"));
                paciente.setApellidoMaterno(rs.getString("materno_pac"));
                paciente.setFechaNacimiento(rs.getString("fecnac_pac"));
                paciente.setDireccion(rs.getString("direccion_pac"));
                paciente.setCelular(rs.getString("celular_pac"));
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

        return paciente;
    }

    public void insertar(Paciente paciente) {
        
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "INSERT INTO paciente (dni, nombres_pac, paterno_pac, materno_pac, fecnac_pac, direccion_pac, celular_pac) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, paciente.getDni());
            stmt.setString(2, paciente.getNombres());
            stmt.setString(3, paciente.getApellidoPaterno());
            stmt.setString(4, paciente.getApellidoMaterno());
            stmt.setString(5, paciente.getFechaNacimiento());
            stmt.setString(6, paciente.getDireccion());
            stmt.setString(7, paciente.getCelular());
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
            String query = "DELETE FROM paciente WHERE idpaciente = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Paciente> listarTodos() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT * FROM paciente";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idpaciente"));
                paciente.setDni(rs.getString("dni"));
                paciente.setNombres(rs.getString("nombres_pac"));
                paciente.setApellidoPaterno(rs.getString("paterno_pac"));
                paciente.setApellidoMaterno(rs.getString("materno_pac"));
                paciente.setFechaNacimiento(rs.getString("fecnac_pac"));
                paciente.setDireccion(rs.getString("direccion_pac"));
                paciente.setCelular(rs.getString("celular_pac"));
                pacientes.add(paciente);
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

        return pacientes;
    }
    
    public void closeConnection() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
