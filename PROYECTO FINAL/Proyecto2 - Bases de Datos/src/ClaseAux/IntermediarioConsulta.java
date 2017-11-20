/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClaseAux;

import ClaseAux.Mensaje;
import Interfaz.Consulta;
import Interfaz.InformacionEquipos;
import Interfaz.InformacionGrupos;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JComboBox;
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
                ClaseAux.Variables.setConsultaSQL("select * from equipo where afiliado_confederacion = '" + confederacion + "'");
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
        Object[] mensaje = { "Partido(TODOS): ", entradaPartido };
        int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Llene la siguiente información: ", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            String partido = entradaPartido.getText();
            if (partido.equals("")) {
                Mensaje.entradaVacia();
            } else {
                //ClaseAux.Variables.setConsultaSQL("");
                InformacionEquipos informacionEquipos = new InformacionEquipos(partido);
                informacionEquipos.setVisible(true);
            }
        } else {
            return;
        }
    }
    
    /**
     * Para la consulta de estadísticas de un partido.
     */
    @SuppressWarnings("UnnecessaryReturnStatement")
    public static void verEstadisticaGrupos() {
        JTextField entradaPartido = new JTextField();
        Object[] mensaje = { "Grupo(TODOS): ", entradaPartido };
        int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Llene la siguiente información: ", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            String partido = entradaPartido.getText();
            if (partido.equals("")) {
                Mensaje.entradaVacia();
            } else {
                //ClaseAux.Variables.setConsultaSQL("");
                InformacionGrupos informacionEquipos = new InformacionGrupos(partido);
                informacionEquipos.setVisible(true);
            }
        } else {
            return;
        }
    }
    
    @SuppressWarnings("UnnecessaryReturnStatement")
    public static void verEstadio() {
        try {
            JComboBox comboBoxSedes = new JComboBox();
            ResultSet resultSet = Conexion.Conexion.realizarConsulta(ClaseAux.Variables.getConsultaSQL());
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numColumnas = resultSetMetaData.getColumnCount();
            while(resultSet.next()){
                for (int i = 1; i <= numColumnas; i++) {
                    comboBoxSedes.addItem(resultSet.getString(i));
                }
            }
            Object[] mensaje = { "Estadio: ", comboBoxSedes };
            int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Seleccione una opción: ", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                String nombreEstadio = (String) comboBoxSedes.getSelectedItem();
                resultSet = Conexion.Conexion.realizarConsulta("select link_estadio from sede where nombre_estadio = '" + nombreEstadio + "'");
                resultSetMetaData = resultSet.getMetaData();
                numColumnas = resultSetMetaData.getColumnCount();
                String link = "";
                while(resultSet.next()){
                    for (int i = 1; i <= numColumnas; i++) {
                        link = resultSet.getString(i);
                    }
                }
                Desktop.getDesktop().browse(new URI(link));
            } else {
                return;
            }
        } catch (SQLException | ClassNotFoundException | IOException | URISyntaxException ex) {
            ClaseAux.Mensaje.mensajeError(ex.toString());
        }
    }
    
    /**
     * Para las consultas que no requieren entradas.
     */
    public static void cargarConsulta() {
        consulta = new Consulta();
        consulta.setVisible(true);
    }
}
