
package Entidades;

/**
 *
 * @author Laptop
 */
public class Trabajador {
    private int id;
    private int idEspecialidad;
    private int idMedico;

    public Trabajador() {
    }

    public Trabajador(int id, int idEspecialidad, int idMedico) {
        this.id = id;
        this.idEspecialidad = idEspecialidad;
        this.idMedico = idMedico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }
    
    
    
}
