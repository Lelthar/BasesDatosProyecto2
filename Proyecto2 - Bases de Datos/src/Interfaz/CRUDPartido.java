/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import ClaseAux.Mensaje;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jeanca
 */
public class CRUDPartido extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    
    /**
     * Creates new form CRUDPartido
     */
    public CRUDPartido() {
        initComponents();
        tableModel = (DefaultTableModel) resultado_consulta_jTable.getModel();
        tableModel.setRowCount(0);
        tableModel.addColumn("NUMERO_PARTIDO");
        tableModel.addColumn("FASE");
        tableModel.addColumn("FECHA");
        tableModel.addColumn("HORA");
        tableModel.addColumn("CAN_AFICIONADOS");
        tableModel.addColumn("MIN_EXTRA_1_TIEMPO");
        tableModel.addColumn("MIN_EXTRA_2_TIEMPO");
        tableModel.addColumn("SEDE");
        tableModel.addColumn("ESTADIO");
        tableModel.addColumn("TIEMPOS_EXTRA");
        this.jLabel1.setForeground(Color.WHITE);
        this.jLabel2.setForeground(Color.WHITE);
        this.setDefaultCloseOperation(Principal.DO_NOTHING_ON_CLOSE);
        this.setBounds(250, 50, 850, 570);
        this.setTitle("CRUD");
        this.getContentPane().setBackground(new java.awt.Color(25, 30, 39));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        crear_jButton = new javax.swing.JButton();
        leer_jButton = new javax.swing.JButton();
        actualizar_jButton = new javax.swing.JButton();
        borrar_jButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultado_consulta_jTable = new javax.swing.JTable();
        regresar_jButton = new javax.swing.JButton();
        plantillas_jButton = new javax.swing.JButton();
        arbitros_jButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel1.setText("CRUD de Partidos");

        jLabel2.setText("Tabla para la función Leer");

        crear_jButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        crear_jButton.setText("Crear");
        crear_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_jButtonActionPerformed(evt);
            }
        });

        leer_jButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        leer_jButton.setText("Leer");
        leer_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leer_jButtonActionPerformed(evt);
            }
        });

        actualizar_jButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        actualizar_jButton.setText("Actualizar");
        actualizar_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizar_jButtonActionPerformed(evt);
            }
        });

        borrar_jButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        borrar_jButton.setText("Borrar");
        borrar_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrar_jButtonActionPerformed(evt);
            }
        });

        resultado_consulta_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(resultado_consulta_jTable);

        regresar_jButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        regresar_jButton.setText("Regresar");
        regresar_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresar_jButtonActionPerformed(evt);
            }
        });

        plantillas_jButton.setText("Plantillas");
        plantillas_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plantillas_jButtonActionPerformed(evt);
            }
        });

        arbitros_jButton.setText("Árbitros");
        arbitros_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arbitros_jButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(regresar_jButton)
                .addGap(132, 132, 132))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(crear_jButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(leer_jButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(borrar_jButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(actualizar_jButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(plantillas_jButton)
                            .addComponent(arbitros_jButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crear_jButton)
                        .addGap(18, 18, 18)
                        .addComponent(leer_jButton)
                        .addGap(18, 18, 18)
                        .addComponent(actualizar_jButton)
                        .addGap(18, 18, 18)
                        .addComponent(borrar_jButton)
                        .addGap(30, 30, 30)
                        .addComponent(plantillas_jButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arbitros_jButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(regresar_jButton)
                        .addGap(28, 28, 28))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crear_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_jButtonActionPerformed
        try {
            JTextField entradaNumPartido = new JTextField();
            JTextField entradaFase = new JTextField();
            JTextField entradaFechaPartido = new JTextField();
            JTextField entradaHoraPartido = new JTextField();
            JTextField entradaCantidadAficionados = new JTextField();
            JTextField entradaMinExtra1Tiempo = new JTextField();
            JTextField entradaMinExtra2Tiempo = new JTextField();
            JTextField entradaNombreSede = new JTextField();
            JTextField entradaNombreEstadio = new JTextField();
            JTextField entradaTiemposExtra = new JTextField();
            
            Object[] mensaje = { "Código de partido: ", entradaNumPartido,
                "Fase: ", entradaFase,
                "Fecha: ", entradaFechaPartido,
                "Hora: ", entradaHoraPartido,
                "Cantidad aficionados", entradaCantidadAficionados,
                "Min Extra Primer Tiempo", entradaMinExtra1Tiempo,
                "Min Extra Segundo Tiempo", entradaMinExtra2Tiempo, 
                "Sede", entradaNombreSede,
                "Estadio", entradaNombreEstadio,
                "Tiempos Extra: ", entradaTiemposExtra };
            int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Llene la siguiente información: ", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                String numPartido = entradaNumPartido.getText();
                String fase = entradaFase.getText();
                String fecha = entradaFechaPartido.getText();
                String hora = entradaHoraPartido.getText();
                String cantAficionados = entradaCantidadAficionados.getText();
                String minExtra1Tiempo = entradaMinExtra1Tiempo.getText();
                String minExtra2Tiempo = entradaMinExtra2Tiempo.getText();
                String sede = entradaNombreSede.getText();
                String estadio = entradaNombreSede.getText();
                String tiemposExtra = entradaTiemposExtra.getText();
                if (numPartido.equals("") || fase.equals("") || fecha.equals("") || hora.equals("") ||
                       cantAficionados.equals("") || minExtra1Tiempo.equals("") || minExtra2Tiempo.equals("") ||
                        sede.equals("") || estadio.equals("") || tiemposExtra.equals("")) {
                    Mensaje.entradaVacia();
                } else {
                    Conexion.Conexion.realizarConsulta("" +
                        "INSERT INTO partido VALUES (" +
                        "" + numPartido +
                        ",'" + fase +
                        "','" + fecha +
                        "','" + hora + 
                        "','" + cantAficionados + 
                        "','" + minExtra1Tiempo + 
                        "','" + minExtra2Tiempo + 
                        "','" + sede + 
                        "','" + estadio +
                        "','" + tiemposExtra + "')");
                    ClaseAux.Mensaje.mensajeCrearCRUD();
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ClaseAux.Mensaje.mensajeError(ex.toString());
        }
    }//GEN-LAST:event_crear_jButtonActionPerformed

    private void leer_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leer_jButtonActionPerformed
        try {
            ResultSet resultSet = Conexion.Conexion.realizarConsulta("SELECT * FROM vista_crud_partido");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            tableModel.setRowCount(0);
            int numColumnas = resultSetMetaData.getColumnCount();
            while(resultSet.next()){
                Vector vector = new Vector();
                for (int i = 1; i <= numColumnas; i++) {
                    vector.add(resultSet.getString(i));
                }
                tableModel.addRow(vector);
            }
            resultado_consulta_jTable.setModel(tableModel);
        } catch (SQLException | ClassNotFoundException ex) {
            ClaseAux.Mensaje.mensajeError(ex.toString());
        }
    }//GEN-LAST:event_leer_jButtonActionPerformed

    private void actualizar_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizar_jButtonActionPerformed
        try {
            JComboBox comboBox = new JComboBox();
            comboBox.addItem("numero_partido");
            comboBox.addItem("fase");
            comboBox.addItem("fecha_partido");
            comboBox.addItem("hora_partido");
            comboBox.addItem("cantidad_aficionados");
            comboBox.addItem("min_extra_primer_tiempo");
            comboBox.addItem("min_extra_segundo_tiempo");
            comboBox.addItem("nombre_sede");
            comboBox.addItem("nombre_estadio");
            comboBox.addItem("tiempos_extra");
            JTextField entradaNumPartido = new JTextField();
            JTextField nuevoValor = new JTextField();
            Object[] mensaje = { "Número de Partido: ", entradaNumPartido,
                "Atributo a actualizar: ", comboBox,
                "Nuevo valor del atributo: ", nuevoValor };
            int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Llene la siguiente información: ", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                String numPartido = entradaNumPartido.getText();
                String strNuevoValor = nuevoValor.getText();
                String atributo = (String) comboBox.getSelectedItem();
                if (numPartido.equals("") || strNuevoValor.equals("")) {
                    Mensaje.entradaVacia();
                } else {
                    Conexion.Conexion.realizarConsulta("" +
                        "UPDATE partido SET " + atributo + " = '" + strNuevoValor + "' WHERE numero_partido = " + numPartido);
                    ClaseAux.Mensaje.mensajeActualizarCRUD();
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ClaseAux.Mensaje.mensajeError(ex.toString());
        }
    }//GEN-LAST:event_actualizar_jButtonActionPerformed

    private void borrar_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrar_jButtonActionPerformed
        try {
            JTextField entradaNumPartido = new JTextField();
            Object[] mensaje = { "Número de Partido: ", entradaNumPartido };
            int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Llene la siguiente información: ", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                String numPartido = entradaNumPartido.getText();
                if (numPartido.equals("")) {
                    Mensaje.entradaVacia();
                } else {
                    Conexion.Conexion.realizarConsulta("" +
                        "DELETE FROM partido WHERE numero_partido = " + numPartido);
                    ClaseAux.Mensaje.mensajeBorrarCRUD();

                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ClaseAux.Mensaje.mensajeError(ex.toString());
        }
    }//GEN-LAST:event_borrar_jButtonActionPerformed

    private void regresar_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresar_jButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_regresar_jButtonActionPerformed

    private void plantillas_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plantillas_jButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plantillas_jButtonActionPerformed

    private void arbitros_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arbitros_jButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arbitros_jButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CRUDPartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CRUDPartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CRUDPartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CRUDPartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CRUDPartido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar_jButton;
    private javax.swing.JButton arbitros_jButton;
    private javax.swing.JButton borrar_jButton;
    private javax.swing.JButton crear_jButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leer_jButton;
    private javax.swing.JButton plantillas_jButton;
    private javax.swing.JButton regresar_jButton;
    private javax.swing.JTable resultado_consulta_jTable;
    // End of variables declaration//GEN-END:variables
}
