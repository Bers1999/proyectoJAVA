package DAO;

import ConexionBD.ConexionBD;
import Entidades.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAO {
    public Turno obtenerPorId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Turno turno = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT * FROM turno WHERE idturno = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                turno = new Turno();
                turno.setIdTurno(rs.getInt("idturno"));
                turno.setMedicoId(rs.getInt("med_id"));
                turno.setRetCod(rs.getInt("ret_cod"));
                turno.setPacienteCod(rs.getInt("pac_cod"));
                turno.setFecha(rs.getString("tur_Fecha"));
                turno.setCita(rs.getString("tur_Cita"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return turno;
    }

    public void insertar(Turno turno) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "INSERT INTO turno (med_id, ret_cod, pac_cod, tur_Fecha, tur_Cita VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, turno.getMedicoId());
            stmt.setInt(2, turno.getRetCod());
            stmt.setInt(3, turno.getPacienteCod());
            stmt.setString(4, turno.getFecha());
            stmt.setString(5, turno.getCita());

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

    public void eliminar(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "DELETE FROM turno WHERE idturno = ?";
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

    public List<Turno> listarTodos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Turno> turnos = new ArrayList<>();

        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT * FROM turno";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Turno turno = new Turno();
                turno.setIdTurno(rs.getInt("idturno"));
                turno.setMedicoId(rs.getInt("med_id"));
                turno.setRetCod(rs.getInt("ret_cod"));
                turno.setPacienteCod(rs.getInt("pac_cod"));
                turno.setFecha(rs.getString("tur_Fecha"));
                turno.setCita(rs.getString("tur_Cita"));
                turnos.add(turno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return turnos;
    }
}
