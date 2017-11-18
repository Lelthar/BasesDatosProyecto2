/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClaseAux;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 * Clase que crea un listener para los labels de la interfaz del frame Principal.
 * Simula botones.
 * @author Jeanca
 */
public class LabelListener {
    
    public static void LabelListener(JLabel jLabel, int opcion) {
        JLabel jLabelListener = jLabel;
        jLabelListener.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) { 
                jLabelListener.setForeground(new java.awt.Color(210, 10, 17));
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                jLabelListener.setForeground(new java.awt.Color(210, 10, 17));
                switch (opcion) {
                    case 1:
                        IntermediarioConsulta.verParticipantesPorConferencia();
                        break;
                    case 2:
                        ClaseAux.Variables.setConsultaSQL("SELECT * FROM prueba");
                        IntermediarioConsulta.cargarConsulta();
                        break;
                    case 3:
                        IntermediarioConsulta.verEstadisticaPartidos();
                        break;
                    case 4:
                        ClaseAux.Variables.setConsultaSQL("SELECT * FROM ELEMENTOS");
                        IntermediarioConsulta.cargarConsulta();
                        break;
                    case 5:
                        ClaseAux.Variables.setConsultaSQL("SELECT * FROM ELEMENTOS");
                        IntermediarioConsulta.cargarConsulta();
                        break;
                    case 6:
                        ClaseAux.Variables.setConsultaSQL("SELECT * FROM ELEMENTOS");
                        IntermediarioConsulta.cargarConsulta();
                        break;
                    case 7:
                        ClaseAux.Variables.setConsultaSQL("select nombre_estadio from sede");
                        IntermediarioConsulta.verEstadio();
                    default:
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                jLabelListener.setForeground(Color.WHITE);
            }

            @Override
            public void mouseEntered(MouseEvent e) { 
                jLabelListener.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) { 
                jLabelListener.setForeground(Color.WHITE);
            }
        });
    }
    
    
}