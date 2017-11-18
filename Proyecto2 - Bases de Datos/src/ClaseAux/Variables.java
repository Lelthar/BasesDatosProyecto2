/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClaseAux;

import java.util.Vector;

/**
 * Clase para la administración de variables en el programa.
 * @author Jeanca
 */
public class Variables {
    private static String USUARIO;
    private static String PASSWORD;
    private static String CONSULTA_SQL;
    private static Vector VECTOR_TABLAS;
    
    /**
     * Métodos set.
     */
    public static void setUsuario(String str) {
        USUARIO = str;
    }
    
    public static void setPassword(String str) {
        PASSWORD = str;
    }
    
    public static void setConsultaSQL(String str) {
        CONSULTA_SQL = str;
    }
    
    public static void setVectorTablas(Vector v) {
        VECTOR_TABLAS = v;
    }
    
    /**
     * Métodos get.
     */
    public static String getUusario() {
        return USUARIO;
    }
    
    public static String getPassword() {
        return PASSWORD;
    }
    
    public static String getConsultaSQL() {
        return CONSULTA_SQL;
    }
    
    public static Vector getVectorTablas() {
        return VECTOR_TABLAS;
    }
}
