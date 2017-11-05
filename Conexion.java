/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jeanca
 */
public class Conexion {
    
    private String url = "jdbc:oracle:thin:@localhost:1521:XE";
    private String driver = "oracle.jdbc.OracleDriver";
    private Connection conexion;
    private boolean estadoConexion = false;
    private String usuario;
    private String password;
    
    /**
     * Método que establece una conexión entre NETBEANS y la base de datos de ORACLE.
     * @return 
     */
    public Connection getConexion() {
        try {
            Class.forName(this.driver);
            try {
                this.conexion = DriverManager.getConnection(this.url, this.usuario, this.password);
                JOptionPane.showMessageDialog(null, "Conexión exitosa.");
                estadoConexion = true;
                return this.conexion;
            } catch (SQLException e) {
                System.out.println(e + "\nCodigo = " + e.getErrorCode());
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "ERROR: NO SE ENCUENTRA EL DRIVER oracle.jdbc.OracleDriver.", "", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    /**
     * Método booleano que se utiliza para verificar el estado de conexión.
     * La variable contiene TRUE si la conexión se realizó correctamente, FALSE en caso contrario.
     * @return
     */
    public boolean getEstadoConexion() {
        return this.estadoConexion;
    }
    
    /**
     * Método que establece el ususario para la conexión en la clase Conexion.
     * @param usuario 
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Método que establece el password para la conexión en la clase Conexion.
     * @param usuario 
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
