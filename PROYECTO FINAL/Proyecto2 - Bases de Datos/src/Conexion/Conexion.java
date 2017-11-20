/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Clase que se encarga de crear la conexión entre la BD de Oracle y Java NetBeans.
 * @author Jeanca
 */
public class Conexion {
    
    private static Connection conexion;
    
    /**
     * Este método se encarga de establecer una conexión a la Base de Datos mediante
     * una URL y el driver de Oracle.
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Connection establecerConexion() throws ClassNotFoundException, SQLException {
        Class.forName(ClaseAux.Constantes.ORACLE_DRIVER);
        conexion = DriverManager.getConnection(ClaseAux.Constantes.URL, ClaseAux.Variables.getUusario(), 
                ClaseAux.Variables.getPassword());
        return conexion;
    }
    
    /**
     * Este método se encarga de realizar una consulta SQL a la base de datos.
     * @param consulta
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ResultSet realizarConsulta(String consulta) throws SQLException, ClassNotFoundException {
        conexion = establecerConexion();
        Statement statement = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(consulta);
        return resultSet;
    }
    
    /**
     * Método que obtiene todos los objetos de la base de datos.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static Vector obtenerTablasBD() throws SQLException, ClassNotFoundException {
        conexion = establecerConexion();
        Vector vector = new Vector();
        Statement statement = conexion.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT TABLE_NAME FROM ALL_TABLES WHERE Owner = 'USER1'");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int index = resultSetMetaData.getColumnCount();
        while(resultSet.next()){ 
            for (int i = 1; i <= index; i++) {
                vector.add(resultSet.getString(i));
            }
        }
        return vector;
    }
}
