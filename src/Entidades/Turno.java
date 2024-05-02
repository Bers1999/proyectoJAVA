package Entidades;

public class Turno {
    private int idTurno;
    private int medicoId;
    private int retCod;
    private int pacienteCod;
    private String fecha;
    private String cita;

    public Turno() {
    }

    public Turno(int idTurno, int medicoId, int retCod, int pacienteCod, String fecha, String cita) {
        this.idTurno = idTurno;
        this.medicoId = medicoId;
        this.retCod = retCod;
        this.pacienteCod = pacienteCod;
        this.fecha = fecha;
        this.cita = cita;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public int getRetCod() {
        return retCod;
    }

    public void setRetCod(int retCod) {
        this.retCod = retCod;
    }

    public int getPacienteCod() {
        return pacienteCod;
    }

    public void setPacienteCod(int pacienteCod) {
        this.pacienteCod = pacienteCod;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCita() {
        return cita;
    }

    public void setCita(String cita) {
        this.cita = cita;
    }
    
}
