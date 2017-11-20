/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClaseAux;

import javax.swing.JOptionPane;

/**
 * Clase para administrar los mensajes al usuario
 * @author Jeanca
 */
public class Mensaje {
    
    /**
     * Mensajes de excepciones.
     * @param error 
     */
    public static void mensajeError(String error) {
        JOptionPane.showMessageDialog(null, error, "", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Mensaje Log In exitoso.
     */
    public static void mensajeConexionExitosa() {
        JOptionPane.showMessageDialog(null, ClaseAux.Constantes.CONEXION);
    }
    
    /**
     * Mensaje para entrada de usuario vacía.
     */
    public static void entradaVacia() {
        JOptionPane.showMessageDialog(null, ClaseAux.Constantes.ENTRADA_VACÍA, "", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Mensaje creacion correcta en CRUD.
     */
    public static void mensajeCrearCRUD() {
        JOptionPane.showMessageDialog(null, ClaseAux.Constantes.CREAR_CRUD);
    }
    
    /**
     * Mensaje eliminacion correcta en CRUD.
     */
    public static void mensajeBorrarCRUD() {
        JOptionPane.showMessageDialog(null, ClaseAux.Constantes.BORRAR_CRUD);
    }
    
    /**
     * Mensaje actualizacion correcta en CRUD.
     */
    public static void mensajeActualizarCRUD() {
        JOptionPane.showMessageDialog(null, ClaseAux.Constantes.MODIFICAR_CRUD);
    }
}
