package DAO;

import ConexionBD.ConexionBD;
import Entidades.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {
    
    private Connection conn;
    public MedicoDAO() {
        conn = ConexionBD.getConnection();
    }
    
    public Medico obtenerPorId(int id) {
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Medico medico = null;

        try {
            
            String query = "SELECT * FROM medico WHERE idmedico = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                medico = new Medico();
                medico.setIdMedico(rs.getInt("idmedico"));
                medico.setDni(rs.getString("dni"));
                medico.setNombres(rs.getString("nombres_med"));
                medico.setApellidoPaterno(rs.getString("paterno_med"));
                medico.setApellidoMaterno(rs.getString("materno_med"));
                medico.setCelular(rs.getString("celular_med"));
                medico.setTelefono(rs.getString("telefono_med"));
                medico.setSexo(rs.getString("sexo_med"));
                medico.setHoraEntrada(rs.getString("horentra_med"));
                medico.setHoraSalida(rs.getString("horsalid_med"));
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

        return medico;
    }

    public void insertar(Medico medico) {
        
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "INSERT INTO medico (dni, nombres_med, paterno_med, materno_med, celular_med, telefono_med, sexo_med, horentra_med, horsalid_med) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, medico.getDni());
            stmt.setString(2, medico.getNombres());
            stmt.setString(3, medico.getApellidoPaterno());
            stmt.setString(4, medico.getApellidoMaterno());
            stmt.setString(5, medico.getCelular());
            stmt.setString(6, medico.getTelefono());
            stmt.setString(7, medico.getSexo());
            stmt.setString(8, medico.getHoraEntrada());
            stmt.setString(9, medico.getHoraSalida());
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
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "DELETE FROM medico WHERE idmedico = ?";
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

    public List <Medico> listarTodos() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List <Medico> medicos = new ArrayList<>();

        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT * FROM medico";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setIdMedico(rs.getInt("idmedico"));
                medico.setDni(rs.getString("dni"));
                medico.setNombres(rs.getString("nombres_med"));
                medico.setApellidoPaterno(rs.getString("paterno_med"));
                medico.setApellidoMaterno(rs.getString("materno_med"));
                medico.setCelular(rs.getString("celular_med"));
                medico.setTelefono(rs.getString("telefono_med"));
                medico.setSexo(rs.getString("sexo_med"));
                medico.setHoraEntrada(rs.getString("horentra_med"));
                medico.setHoraSalida(rs.getString("horsalid_med"));
                medicos.add(medico);
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

        return medicos;
    }
    public int obtenerIdPorNombreMedico(String nombreMedico) {

    PreparedStatement stmt = null;
    ResultSet rs = null;
    int idMedico = -1; // Default value if not found

    try {
        conn = ConexionBD.getConnection();
        String query = "SELECT idmedico FROM medico WHERE nombres_med = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, nombreMedico);
        rs = stmt.executeQuery();

        if (rs.next()) {
            idMedico = rs.getInt("idmedico");
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

    return idMedico;
}
}


