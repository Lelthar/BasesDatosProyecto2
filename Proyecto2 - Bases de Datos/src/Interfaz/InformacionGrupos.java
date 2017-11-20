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
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jeanca
 */
public class InformacionGrupos extends javax.swing.JFrame {
    
    private String[] GRUPOS ={"A","B","C","D","E","F","G","H"};
    private int TOTAL = 8;
    private int ACTUAL = 0;
    private boolean TODOS = false;
    
    /**
     * Creates new form EquiposConfederacion
     */
    public InformacionGrupos(String grupo) {
        initComponents();
        this.resultado_jLabel.setForeground(Color.WHITE);
        this.resultado_jLabelG.setForeground(Color.WHITE);
        this.setDefaultCloseOperation(Consulta.DO_NOTHING_ON_CLOSE);
        //this.setBounds(250, 50, 850, 570);
        this.setLocationRelativeTo(null);
        this.setTitle("Consulta");
        this.getContentPane().setBackground(new java.awt.Color(25, 30, 39));
        if(grupo.equals("TODOS")){
            TODOS=true;
            posicionamientoGrupos(GRUPOS[ACTUAL]);
            //ACTUAL+=1;
        }else{
            posicionamientoGrupos(grupo);
        }
    }
    
    /**
     * Método que muestra los equipos por confederación que participan.
     */
    private void posicionamientoGrupos(String grupo) {
        try {
            resultado_jLabelG.setText(grupo);
            String[] strSalto = { };
            ResultSet resultSet = Conexion.Conexion.realizarConsulta("select e.nombre_pais,e.codigo_equipo from equipo e where grupo_inicio_campeonato='"+grupo+"'  order by 1 asc");
            DefaultTableModel tableModel = (DefaultTableModel) resultado_consulta_jTable.getModel();
            tableModel.setRowCount(0);
            
            Vector paises = new Vector();
            Vector codPais = new Vector();
            Vector jugados = new Vector();
            Vector puntos = new Vector();
            Vector ganadosl = new Vector();
            Vector empatesl = new Vector();
            Vector perdidosl = new Vector();
            Vector golesPos = new Vector();
            Vector golesNeg = new Vector();
            
            resultSet.next();
            paises.add(resultSet.getString(1));
            codPais.add(resultSet.getString(2));
            resultSet.next();
            paises.add(resultSet.getString(1));
            codPais.add(resultSet.getString(2));
            resultSet.next();
            paises.add(resultSet.getString(1));
            codPais.add(resultSet.getString(2));
            resultSet.next();
            paises.add(resultSet.getString(1));
            codPais.add(resultSet.getString(2));
            
            String consulta = "";
            for(int i=0;i<codPais.size();i++){
                int cant = 0;
                consulta = "select count(p.numero_partido) from partido p inner join capitan_local cl on p.numero_partido=cl.numero_partido\n" +
                    "where cl.codigo_equipo='"+codPais.get(i)+"'";
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                resultSet.next();
                cant += Integer.valueOf(resultSet.getString(1));
                consulta = "select count(p.numero_partido) from partido p inner join capitan_visita cl on p.numero_partido=cl.numero_partido\n" +
                    "where cl.codigo_equipo='"+codPais.get(i)+"'";
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                resultSet.next();
                cant += Integer.valueOf(resultSet.getString(1));
                jugados.add(cant);
            }
            puntos.add(0); puntos.add(0); puntos.add(0); puntos.add(0);
            for(int i=0;i<4;i++){
                int punt = 0;
                int punt1 = 0;
                String cod1= (String) codPais.get(i);
                for(int j=0;j<4;j++){
                    String cod2= (String) codPais.get(j);
                    if(cod1!=cod2){
                        int p = partidoJugado(cod1,cod2);
                        if(p!=-1){
                            switch (p) {
                                case 0:
                                    System.out.println("Equipo1:"+cod1+" Equipo2"+cod2+" Empate");
                                    int a1 = (int) puntos.get(i)+1;
                                    puntos.set(i,a1);
                                    int b1 = (int) puntos.get(j)+1;
                                    puntos.set(j,b1);
                                    break;
                                case 1:
                                    System.out.println("Equipo1:"+cod1+" Equipo2"+cod2+" Gano");
                                    int a2 = (int) puntos.get(i)+3;
                                    puntos.set(i,a2);
                                    break;
                                default:
                                    System.out.println("Equipo1:"+cod1+" Equipo2"+cod2+" Derrota");
                                    int b2 = (int) puntos.get(j)+3;
                                    puntos.set(j,b2);
                                    break;
                            }
                        }
                        
                    }                    
                }
            }

            
            
            for(int i=0;i<4;i++){
                int valor = (int) puntos.get(i);
                int ganados = valor/3;
                int empates = valor%3;
                int juga = (int) jugados.get(i);
                int perdidos = juga-ganados-empates;
                ganadosl.add(ganados);
                empatesl.add(empates);
                perdidosl.add(perdidos);
                //System.out.println(codPais.get(i)+" Ganados: "+ganados+" Empates: "+empates+" Perdidos: "+perdidos);
            }
            
            golesPos.add(0); golesPos.add(0); golesPos.add(0); golesPos.add(0);
            golesNeg.add(0); golesNeg.add(0); golesNeg.add(0); golesNeg.add(0);
            for(int i=0;i<4;i++){
                String cod1= (String) codPais.get(i);
                for(int j=0;j<4;j++){
                    String cod2= (String) codPais.get(j);
                    if(cod1!=cod2){
                        Vector vec = golesAnotados(cod1,cod2);
                        int pos = (int) vec.get(0);
                        int neg = (int) vec.get(1);
                        if(pos!=-1 && neg!=-1){
                            int p1 = (int) golesPos.get(i);
                        int n1 = (int) golesNeg.get(i);
                        p1+=pos;
                        n1+=neg;
                        golesPos.set(i, p1);
                        golesNeg.set(i, n1);
                        
                        int p2 = (int) golesPos.get(j);
                        int n2 = (int) golesNeg.get(j);
                        p2+=neg;
                        n2+=pos;
                        golesPos.set(j, p2);
                        golesNeg.set(j, n2);
                        }
                    }                    
                }
            }
            
            Vector resultFin = new Vector();
            for(int i=0;i<4;i++){
                Vector vector = new Vector();
                vector.add((i+1));
                vector.add(paises.get(i));
                vector.add(puntos.get(i));
                vector.add(jugados.get(i));
                vector.add(ganadosl.get(i));
                vector.add(empatesl.get(i));
                vector.add(perdidosl.get(i));
                vector.add(golesPos.get(i));
                vector.add(golesNeg.get(i));
                int a = (int) golesPos.get(i);
                int b = (int) golesNeg.get(i);
                int c = a-b;
                vector.add(c);
                resultFin.add(vector);
                tableModel.addRow(vector);
            }
            
            /*RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);*/           
            /*
            System.out.println("Paises: "+paises);
            System.out.println("Codigos:"+codPais);
            System.out.println("Puntuación:"+puntos);
            System.out.println("Jugados:"+jugados);
            System.out.println("Ganados:"+ganadosl);
            System.out.println("Empates:"+empatesl);
            System.out.println("Perdidos:"+perdidosl);
            System.out.println("Anotados:"+golesPos);
            System.out.println("Encontra:"+golesNeg);*/
            resultado_consulta_jTable.setModel(tableModel);
            /*resultado_consulta_jTable.setRowSorter(sorter);
            resultado_consulta_jTable.getRowSorter().toggleSortOrder(0);
            resultado_consulta_jTable.getRowSorter().toggleSortOrder(2);
            resultado_consulta_jTable.getRowSorter().toggleSortOrder(2);*/
        } catch (SQLException | ClassNotFoundException ex) {
            ClaseAux.Mensaje.mensajeError(ex.toString());
        }
    }
    
    private int partidoJugado(String equipo1,String equipo2){
        try {
            String consulta = "select p.numero_partido from partido p inner join capitan_local cl on p.numero_partido=cl.numero_partido inner join capitan_visita cv on p.numero_partido=cv.numero_partido\n" +
            "where cl.codigo_equipo='"+equipo1+"' and cv.codigo_equipo='"+equipo2+"'";
            ResultSet resultSet = Conexion.Conexion.realizarConsulta(consulta);
            if(resultSet.next()){
                String numPartido = resultSet.getString(1);
                consulta = "select g.codigo_equipo from partido p inner join goles_por_partido g on p.numero_partido=g.numero_partido\n" +
                "where p.numero_partido="+numPartido;
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                int golesE1 = 0;
                int golesE2 = 0;
                while(resultSet.next()){
                    String aux = resultSet.getString(1);
                    if(aux.equals(equipo1)){
                        golesE1+=1;
                    }else{
                        golesE2+=1;
                    }
                }
                if(golesE1==golesE2){
                    return 0;
                }else if(golesE1>golesE2){
                    return 1;
                }else{
                    return 2;
                }
            }else{
                return -1;
            }            
        } catch (SQLException | ClassNotFoundException ex) {
            ClaseAux.Mensaje.mensajeError(ex.toString());
            return -1;
        }
        
    }
    
    private Vector golesAnotados(String equipo1,String equipo2){
        try {
            Vector result = new Vector();
            String consulta = "select p.numero_partido from partido p inner join capitan_local cl on p.numero_partido=cl.numero_partido inner join capitan_visita cv on p.numero_partido=cv.numero_partido\n" +
            "where cl.codigo_equipo='"+equipo1+"' and cv.codigo_equipo='"+equipo2+"'";
            ResultSet resultSet = Conexion.Conexion.realizarConsulta(consulta);
            if(resultSet.next()){
                String numPartido = resultSet.getString(1);
                consulta = "select g.codigo_equipo from partido p inner join goles_por_partido g on p.numero_partido=g.numero_partido\n" +
                "where p.numero_partido="+numPartido;
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                int golesE1 = 0;
                int golesE2 = 0;
                while(resultSet.next()){
                    String aux = resultSet.getString(1);
                    if(aux.equals(equipo1)){
                        golesE1+=1;
                    }else{
                        golesE2+=1;
                    }
                }
                result.add(golesE1);
                result.add(golesE2);
                return result;
            }else{
                result.add(-1);
                result.add(-1);
                return result;
            }            
        } catch (SQLException | ClassNotFoundException ex) {
            ClaseAux.Mensaje.mensajeError(ex.toString());
            return null;
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
        resultado_jLabelG = new javax.swing.JLabel();
        jButtonAnt = new javax.swing.JButton();
        jButtonSig = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resultado_jLabel.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        resultado_jLabel.setText("Grupo:");

        resultado_consulta_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posición", "Equipo", "Puntos", "PJ", "PG", "PE", "PP", "GP", "GC", "Dif"
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

        resultado_jLabelG.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N

        jButtonAnt.setText("Anterior");
        jButtonAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAntActionPerformed(evt);
            }
        });

        jButtonSig.setText("Siguiente");
        jButtonSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resultado_jLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resultado_jLabelG, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addComponent(jButtonAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSig)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
                        .addComponent(regresar_jButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resultado_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultado_jLabelG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(regresar_jButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonAnt)
                        .addComponent(jButtonSig)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regresar_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresar_jButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_regresar_jButtonActionPerformed

    private void jButtonAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAntActionPerformed
        // TODO add your handling code here:
        if(TODOS==true){
            if(ACTUAL>0){
                ACTUAL-=1;
                posicionamientoGrupos(GRUPOS[ACTUAL]);
            }else{
                JOptionPane.showMessageDialog(this, "No existen mas grupos anteriores"
                                            , "Error: grupo 0", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonAntActionPerformed

    private void jButtonSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSigActionPerformed
        // TODO add your handling code here:
        if(TODOS==true){
            System.out.println("Actual:"+ACTUAL+" TOTAL:"+TOTAL);
            if(ACTUAL<TOTAL){
                ACTUAL+=1;
                posicionamientoGrupos(GRUPOS[ACTUAL]);
            }else{
                JOptionPane.showMessageDialog(this, "No existen mas grupos siguientes"
                                            , "Error: No existen mas grupos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonSigActionPerformed

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
            java.util.logging.Logger.getLogger(InformacionGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformacionGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformacionGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacionGrupos(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnt;
    private javax.swing.JButton jButtonSig;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton regresar_jButton;
    private javax.swing.JTable resultado_consulta_jTable;
    private javax.swing.JLabel resultado_jLabel;
    private javax.swing.JLabel resultado_jLabelG;
    // End of variables declaration//GEN-END:variables
}
