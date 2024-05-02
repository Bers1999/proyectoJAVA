package Interfaces;

import ConexionBD.ConexionBD;
import java.sql.Connection;


public class Main {

    public static void main(String[] args) {
       ConexionBD conexion=new ConexionBD();
       Connection conn = null;
       conn = ConexionBD.getConnection();
       Login log=new Login();
       log.setVisible(true);
    }
    
}
