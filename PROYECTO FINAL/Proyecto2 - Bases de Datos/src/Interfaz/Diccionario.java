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
 * Clase que se encarga de mostrar el diccionario de datos.
 * @author Jeanca
 */
public class Diccionario extends javax.swing.JFrame {

    /**
     * Creates new form Diccionario
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Diccionario() {
        initComponents();
        this.diccionario_jLabel.setForeground(Color.WHITE);
        this.setDefaultCloseOperation(Consulta.DO_NOTHING_ON_CLOSE);
        this.setBounds(250, 50, 850, 570);
        this.setTitle("Diccionario");
        this.getContentPane().setBackground(new java.awt.Color(25, 30, 39));
        obtenerDiccionario();
    }
    
    /**
     * Método que muestra el diccionario de datos.
     */
    private void obtenerDiccionario() {
        try {
            String[] strSalto = { };
            Vector vector = ClaseAux.Variables.getVectorTablas();
            DefaultTableModel tableModel = (DefaultTableModel) diccionario_jTable.getModel();
            tableModel.setRowCount(0);
            
            // Establece un número de columnas por default.
            for (int i = 1; i <= 6; i++) {
                tableModel.addColumn("Columna " + i);
            }
            
            // Recorre el arreglo de tablas pata hacer una consulta por cada i elemento del vector.
            for (int i = 0; i < vector.size(); i++) {
                ResultSet resultSet = Conexion.Conexion.realizarConsulta("SELECT * FROM " + vector.get(i));
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int numColumnas = resultSetMetaData.getColumnCount();
                Vector vectorColumnas = new Vector();
                
                // Ciclos internos que se encargan de obtener y mostrar los datos de cada tabla.
                for (int j = 1; j <= numColumnas; j++) {
                    vectorColumnas.add(resultSetMetaData.getColumnLabel(j));
                }
                String[] tabla = { vector.get(i).toString().toUpperCase() };
                tableModel.addRow(tabla);
                tableModel.addRow(vectorColumnas);
                while(resultSet.next()){
                    Vector vector2 = new Vector();
                    for (int k = 1; k <= numColumnas; k++) {
                        vector2.add(resultSet.getString(k));
                    }
                    tableModel.addRow(vector2);
                }
                tableModel.addRow(strSalto);
                tableModel.addRow(strSalto);

            }
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

        diccionario_jLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        diccionario_jTable = new javax.swing.JTable();
        regresar_jButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        diccionario_jLabel.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        diccionario_jLabel.setText("Diccionario");

        diccionario_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(diccionario_jTable);

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
                    .addComponent(diccionario_jLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(regresar_jButton)
                .addGap(135, 135, 135))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(diccionario_jLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(regresar_jButton)
                .addContainerGap(91, Short.MAX_VALUE))
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Diccionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Diccionario().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel diccionario_jLabel;
    private javax.swing.JTable diccionario_jTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton regresar_jButton;
    // End of variables declaration//GEN-END:variables
}
