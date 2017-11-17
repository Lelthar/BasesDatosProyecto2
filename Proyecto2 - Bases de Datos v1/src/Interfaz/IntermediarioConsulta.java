/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import ClaseAux.Mensaje;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Clase que funciona como intermediario entre el Menú Principal y la consulta SQL.
 * @author Jeanca
 */
public class IntermediarioConsulta {
    
    private static Consulta consulta;
    
    /**
     * Para la consulta de equipos de una conferencia.
     */
    @SuppressWarnings("UnnecessaryReturnStatement")
    public static void verParticipantesPorConferencia() {
        JTextField entradaConfederacion = new JTextField();
        Object[] mensaje = { "Confederación a consultar: ", entradaConfederacion };
        int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Llene la siguiente información: ", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            String confederacion = entradaConfederacion.getText();
            if (confederacion.equals("")) {
                Mensaje.entradaVacia();
            } else {
                ClaseAux.Variables.setConsultaSQL("SELECT * FROM ELEMENTOS");
                consulta = new Consulta();
                consulta.setVisible(true);
            }
        } else {
            return;
        }
    }
    
    /**
     * Para la consulta de estadísticas de un partido.
     */
    @SuppressWarnings("UnnecessaryReturnStatement")
    public static void verEstadisticaPartidos() {
        JTextField entradaPartido = new JTextField();
        Object[] mensaje = { "Partido: ", entradaPartido };
        int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Llene la siguiente información: ", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            String partido = entradaPartido.getText();
            if (partido.equals("")) {
                Mensaje.entradaVacia();
            } else {
                ClaseAux.Variables.setConsultaSQL("SELECT * FROM ELEMENTOS");
                consulta = new Consulta();
                consulta.setVisible(true);
            }
        } else {
            return;
        }
    }
    
    /**
     * Para las consultas que no requieren entradas.
     */
    public static void cargarConsulta() {
        ClaseAux.Variables.setConsultaSQL("SELECT * FROM ELEMENTOS");
        consulta = new Consulta();
        consulta.setVisible(true);
    }
}
