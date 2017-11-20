/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jeanca
 */
public class EquiposConfederacion extends javax.swing.JFrame {

    /**
     * Creates new form EquiposConfederacion
     */
    public EquiposConfederacion() {
        initComponents();
        this.resultado_jLabel.setForeground(Color.WHITE);
        this.setDefaultCloseOperation(Consulta.DO_NOTHING_ON_CLOSE);
        this.setBounds(250, 50, 850, 570);
        this.setTitle("Consulta");
        this.getContentPane().setBackground(new java.awt.Color(25, 30, 39));
        obtenerEquiposConfederacion();
    }
    
    /**
     * Método que muestra los equipos por confederación que participan.
     */
    private void obtenerEquiposConfederacion() {
        try {
            String[] strSalto = { };
            ResultSet resultSet = Conexion.Conexion.realizarConsulta("select * from equipo where afiliado_confederacion = 'CONCACAF'");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            DefaultTableModel tableModel = (DefaultTableModel) resultado_consulta_jTable.getModel();
            tableModel.setRowCount(0);
            int numColumnas = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= numColumnas; i++) {
                tableModel.addColumn(resultSetMetaData.getColumnLabel(i));
            }
            
            // CONCACAF
            String[] concacaf = { "CONCACAF" };
            tableModel.addRow(concacaf);
            while(resultSet.next()){
                Vector vector = new Vector();
                for (int i = 1; i <= numColumnas; i++) {
                    vector.add(resultSet.getString(i));
                }
                tableModel.addRow(vector);
            }
            tableModel.addRow(strSalto);
            tableModel.addRow(strSalto);
            
            // CAF
            resultSet = Conexion.Conexion.realizarConsulta("select * from equipo where afiliado_confederacion = 'CAF'");
            String[] caf = { "CAF" };
            tableModel.addRow(caf);
            while(resultSet.next()){
                Vector vector = new Vector();
                for (int i = 1; i <= numColumnas; i++) {
                    vector.add(resultSet.getString(i));
                }
                tableModel.addRow(vector);
            }
            tableModel.addRow(strSalto);
            tableModel.addRow(strSalto);
            
            // UEFA
            resultSet = Conexion.Conexion.realizarConsulta("select * from equipo where afiliado_confederacion = 'UEFA'");
            String[] uefa = { "UEFA" };
            tableModel.addRow(uefa);
            while(resultSet.next()){
                Vector vector = new Vector();
                for (int i = 1; i <= numColumnas; i++) {
                    vector.add(resultSet.getString(i));
                }
                tableModel.addRow(vector);
            }
            tableModel.addRow(strSalto);
            tableModel.addRow(strSalto);
            
            // CONMEBOL
            resultSet = Conexion.Conexion.realizarConsulta("select * from equipo where afiliado_confederacion = 'CONMEBOL'");
            String[] conmebol = { "CONMEBOL" };
            tableModel.addRow(conmebol);
            while(resultSet.next()){
                Vector vector = new Vector();
                for (int i = 1; i <= numColumnas; i++) {
                    vector.add(resultSet.getString(i));
                }
                tableModel.addRow(vector);
            }
            tableModel.addRow(strSalto);
            tableModel.addRow(strSalto);
            
            // AFC
            resultSet = Conexion.Conexion.realizarConsulta("select * from equipo where afiliado_confederacion = 'AFC'");
            String[] afc = { "AFC" };
            tableModel.addRow(afc);
            while(resultSet.next()){
                Vector vector = new Vector();
                for (int i = 1; i <= numColumnas; i++) {
                    vector.add(resultSet.getString(i));
                }
                tableModel.addRow(vector);
            }
            tableModel.addRow(strSalto);
            tableModel.addRow(strSalto);
            
            // OFC
            resultSet = Conexion.Conexion.realizarConsulta("select * from equipo where afiliado_confederacion = 'OFC'");
            String[] ofc = { "OFC" };
            tableModel.addRow(ofc);
            while(resultSet.next()){
                Vector vector = new Vector();
                for (int i = 1; i <= numColumnas; i++) {
                    vector.add(resultSet.getString(i));
                }
                tableModel.addRow(vector);
            }
            tableModel.addRow(strSalto);
            tableModel.addRow(strSalto);
            
            resultado_consulta_jTable.setModel(tableModel);
        } catch (SQLException | ClassNotFoundException ex) {
            ClaseAux.Mensaje.mensajeError(ex.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resultado_jLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultado_consulta_jTable = new javax.swing.JTable();
        regresar_jButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resultado_jLabel.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        resultado_jLabel.setText("Resultado");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultado_jLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(regresar_jButton)
                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(resultado_jLabel)
                .addGap(57, 57, 57)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(regresar_jButton)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regresar_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresar_jButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_regresar_jButtonActionPerformed

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
            java.util.logging.Logger.getLogger(EquiposConfederacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EquiposConfederacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EquiposConfederacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EquiposConfederacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EquiposConfederacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton regresar_jButton;
    private javax.swing.JTable resultado_consulta_jTable;
    private javax.swing.JLabel resultado_jLabel;
    // End of variables declaration//GEN-END:variables
}
