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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jeanca
 */
public class InformacionEquipos extends javax.swing.JFrame {
    
    private int ACTUAL = 1;
    private boolean TODOS = false;
    private int TOTAL = 0;
    /**
     * Creates new form EquiposConfederacion
     */
    public InformacionEquipos(String partido) {
        initComponents();
        this.resultado_jLabel.setForeground(Color.WHITE);
        this.jLabelResultCA.setForeground(Color.WHITE);
        this.jLabelResultES.setForeground(Color.WHITE);
        this.jLabelResultET.setForeground(Color.WHITE);
        this.jLabelResultFE.setForeground(Color.WHITE);
        this.jLabelResultHO.setForeground(Color.WHITE);
        this.jLabelResultNP.setForeground(Color.WHITE);
        this.jLabelResult6.setForeground(Color.WHITE);
        this.jLabelResult7.setForeground(Color.WHITE);
        this.jLabel1.setForeground(Color.WHITE);
        this.jLabel2.setForeground(Color.WHITE);
        this.jLabel3.setForeground(Color.WHITE);
        this.jLabel4.setForeground(Color.WHITE);
        this.jLabel5.setForeground(Color.WHITE);
        this.jLabel6.setForeground(Color.WHITE);
        this.jLabel7.setForeground(Color.WHITE);
        this.jLabel8.setForeground(Color.WHITE);
        this.jLabel9.setForeground(Color.WHITE);
        this.jLabelGL.setForeground(Color.WHITE);
        this.jLabelGL1.setForeground(Color.WHITE);
        this.jLabelGL2.setForeground(Color.WHITE);
        this.jLabelGL3.setForeground(Color.WHITE);
        this.jLabelGL4.setForeground(Color.WHITE);
        this.jLabelGL5.setForeground(Color.WHITE);
        this.jLabelGL6.setForeground(Color.WHITE);
        this.jLabelE1.setForeground(Color.WHITE);
        this.jLabelE2.setForeground(Color.WHITE);
        this.jLabelTE.setForeground(Color.WHITE);
        this.setDefaultCloseOperation(Consulta.DO_NOTHING_ON_CLOSE);
        //this.setBounds(250, 50, 850, 570);
        this.setTitle("Consulta");
        this.getContentPane().setBackground(new java.awt.Color(25, 30, 39));
        if("TODOS".equals(partido)){
                TODOS=true;
                TOTAL = totalTuplas();
                System.out.println("Total:"+TOTAL);
                String aux = String.valueOf(ACTUAL);
                obtenerEquiposConfederacion(aux);
                //ACTUAL+=1;
         }else{
            obtenerEquiposConfederacion(partido);
        }
    }
    
    private int totalTuplas(){
        int result = 0;
        try {
            ResultSet resultSet = Conexion.Conexion.realizarConsulta("select count(numero_partido) from partido");
            resultSet.next();
            result = Integer.valueOf(resultSet.getString(1));
            return result;
        } catch (SQLException | ClassNotFoundException ex) {
            ClaseAux.Mensaje.mensajeError(ex.toString());
        }
        
        return result;
    }
    
    /**
     * Método que muestra los equipos por confederación que participan.
     */
    private void obtenerEquiposConfederacion(String partido) {
        try {
            
                System.out.println(partido);
                String[] strSalto = { };
                ResultSet resultSet = Conexion.Conexion.realizarConsulta("select * from partido where numero_partido="+partido);
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int numColumnas = resultSetMetaData.getColumnCount();
                
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    this.jLabelResultNP.setText(partido);
                    this.jLabelResultET.setText((String) vector.get(1));
                    this.jLabelResultES.setText((String) vector.get(8));
                    this.jLabelResultFE.setText((String) vector.get(2));
                    this.jLabelResultHO.setText((String) vector.get(3));
                    this.jLabelResultCA.setText((String) vector.get(4));
                }
                ResultSet cLocal = Conexion.Conexion.realizarConsulta("select codigo_equipo from pantilla_titular_equipo_local where numero_partido="+partido);
                ResultSet cVisita = Conexion.Conexion.realizarConsulta("select codigo_equipo from pantilla_titular_equipo_visita where numero_partido="+partido);
                cLocal.next(); cVisita.next();
                String codLocal = cLocal.getString("codigo_equipo");
                String codVisita = cVisita.getString("codigo_equipo");
                System.out.println("Local: "+codLocal+" Visita: "+codVisita);
                ResultSet nLocal = Conexion.Conexion.realizarConsulta("select nombre_pais from equipo where codigo_equipo='"+codLocal+"'");
                ResultSet nVisita = Conexion.Conexion.realizarConsulta("select nombre_pais from equipo where codigo_equipo='"+codVisita+"'");
                nVisita.next(); nLocal.next();
                String nomLocal = nLocal.getString("nombre_pais");
                String nomVisita = nVisita.getString("nombre_pais");
                this.jLabelResult6.setText(nomLocal);
                this.jLabelResult7.setText(nomVisita);
                /*
                DefaultTableModel t1 = (DefaultTableModel) resultado_consulta_jTable.getModel();
                DefaultTableModel t2= (DefaultTableModel) resultado_consulta_jTablevisita.getModel();
                DefaultTableModel t3 = (DefaultTableModel) tabla_cambios.getModel();
                DefaultTableModel t4 = (DefaultTableModel) resultado_consulta_jTableGoles.getModel();
                DefaultTableModel t5 = (DefaultTableModel) tabla_penales.getModel();
                DefaultTableModel t6 = (DefaultTableModel) Tabla_tarjetas.getModel();
                */
                
                String consulta = "select j.nombre, j.apellido1, j.apellido2 from pantilla_titular_equipo_local p inner join jugador j on p.NUMERO_PASAPORTE_JUGADOR=j.numero_pasaporte where p.NUMERO_PARTIDO=";
                resultSet = Conexion.Conexion.realizarConsulta(consulta+partido);
                resultSetMetaData = resultSet.getMetaData();
                DefaultTableModel tableModel = (DefaultTableModel) resultado_consulta_jTable.getModel();
                tableModel.setRowCount(0);
                numColumnas = resultSetMetaData.getColumnCount();
                

                String[] concacaf = { "JUGADORES" };
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
                
                consulta = "select j.nombre, j.apellido1, j.apellido2 from  capitan_local c inner join jugador j on c.numero_pasaporte_jugador=j.numero_pasaporte where c.numero_partido=";
                resultSet = Conexion.Conexion.realizarConsulta(consulta+partido);
                String[] capitan = {"CAPITAN"};
                tableModel.addRow(capitan);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModel.addRow(vector);
                }
                tableModel.addRow(strSalto);
                tableModel.addRow(strSalto);
                
                consulta = "(select j1.nombre, j1.apellido1, j1.apellido2 from jugador j1 where j1.codigo_equipo='"+codLocal+"')MINUS";
                consulta += "(select j.nombre, j.apellido1, j.apellido2 from pantilla_titular_equipo_local p inner join jugador j on p.NUMERO_PASAPORTE_JUGADOR=j.numero_pasaporte where p.NUMERO_PARTIDO=";
                resultSet = Conexion.Conexion.realizarConsulta(consulta+partido+")");
                String[] sustitutos = {"SUPLENTES"};
                tableModel.addRow(sustitutos);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModel.addRow(vector);
                }
                tableModel.addRow(strSalto);
                tableModel.addRow(strSalto);
                
                consulta = "select nombre, apellido1, apellido2 from entrenador where equipo='";
                resultSet = Conexion.Conexion.realizarConsulta(consulta+codLocal+"'");
                String[] entrenador = {"ENTRENADOR"};
                tableModel.addRow(entrenador);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModel.addRow(vector);
                }
                tableModel.addRow(strSalto);
                tableModel.addRow(strSalto);
                
                consulta = "(select a.nombre, a.apellido1, a.apellido2\n" +
                            "from asistente a inner join cuerpo_tecnico_equipo_local c on a.numero_pasaporte=c.numero_pasaporte_asistente1 where c.numero_partido="+partido+")\n" +
                            "UNION\n" +
                            "(select a.nombre, a.apellido1, a.apellido2\n" +
                            "from asistente a inner join cuerpo_tecnico_equipo_local c on a.numero_pasaporte=c.numero_pasaporte_asistente2 where c.numero_partido="+partido+")";
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                String[] asistentes = {"ASISTENTES"};
                tableModel.addRow(asistentes);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModel.addRow(vector);
                }
                tableModel.addRow(strSalto);
                tableModel.addRow(strSalto);
                
                consulta = "select f.nombre, f.apellido1, f.apellido2\n" +
                "from cuerpo_tecnico_equipo_local c inner join federativo f on f.numero_pasaporte=c.numero_pasaporte_delegado where c.numero_partido=";
                resultSet = Conexion.Conexion.realizarConsulta(consulta+partido);
                String[] delegado = {"DELEGADO"};
                tableModel.addRow(delegado);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModel.addRow(vector);
                }
                tableModel.addRow(strSalto);
                tableModel.addRow(strSalto);
                
                consulta = "select a.nombre, a.apellido1, a.apellido2\n" +
                "from arbitros_por_partido ar inner join arbitro a on a.numero_pasaporte=ar.numero_pasaporte_arbitro where ar.numero_partido=";
                resultSet = Conexion.Conexion.realizarConsulta(consulta+partido);
                String[] arbitro = {"ARBITROS"};
                tableModel.addRow(arbitro);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModel.addRow(vector);
                }
                tableModel.addRow(strSalto);
                tableModel.addRow(strSalto);
                
                consulta = "select j.nombre, j.apellido1, j.apellido2 from pantilla_titular_equipo_visita p inner join jugador j on p.NUMERO_PASAPORTE_JUGADOR=j.numero_pasaporte where p.NUMERO_PARTIDO=";
                resultSet = Conexion.Conexion.realizarConsulta(consulta+partido);
                resultSetMetaData = resultSet.getMetaData();
                DefaultTableModel tableModelVisita = (DefaultTableModel) resultado_consulta_jTablevisita.getModel();
                tableModelVisita.setRowCount(0);
                numColumnas = resultSetMetaData.getColumnCount();
                
                String[] concacaf1 = { "JUGADORES" };
                tableModelVisita.addRow(concacaf1);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModelVisita.addRow(vector);
                }
                tableModelVisita.addRow(strSalto);
                tableModelVisita.addRow(strSalto);
                
                consulta = "select j.nombre, j.apellido1, j.apellido2 from  capitan_visita c inner join jugador j on c.numero_pasaporte_jugador=j.numero_pasaporte where c.numero_partido=";
                resultSet = Conexion.Conexion.realizarConsulta(consulta+partido);
                String[] capitan1 = {"CAPITAN"};
                tableModelVisita.addRow(capitan1);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModelVisita.addRow(vector);
                }
                tableModelVisita.addRow(strSalto);
                tableModelVisita.addRow(strSalto);
                
                consulta = "(select j1.nombre, j1.apellido1, j1.apellido2 from jugador j1 where j1.codigo_equipo='"+codVisita+"')MINUS";
                consulta += "(select j.nombre, j.apellido1, j.apellido2 from pantilla_titular_equipo_visita p inner join jugador j on p.NUMERO_PASAPORTE_JUGADOR=j.numero_pasaporte where p.NUMERO_PARTIDO=";
                resultSet = Conexion.Conexion.realizarConsulta(consulta+partido+")");
                String[] sustitutos1 = {"SUPLENTES"};
                tableModelVisita.addRow(sustitutos1);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModelVisita.addRow(vector);
                }
                tableModelVisita.addRow(strSalto);
                tableModelVisita.addRow(strSalto);
                
                consulta = "select nombre, apellido1, apellido2 from entrenador where equipo='";
                resultSet = Conexion.Conexion.realizarConsulta(consulta+codVisita+"'");
                String[] entrenador1 = {"ENTRENADOR"};
                tableModelVisita.addRow(entrenador1);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModelVisita.addRow(vector);
                }
                tableModelVisita.addRow(strSalto);
                tableModelVisita.addRow(strSalto);
                
                consulta = "(select a.nombre, a.apellido1, a.apellido2\n" +
                            "from asistente a inner join cuerpo_tecnico_equipo_visita c on a.numero_pasaporte=c.numero_pasaporte_asistente1 where c.numero_partido="+partido+")\n" +
                            "UNION\n" +
                            "(select a.nombre, a.apellido1, a.apellido2\n" +
                            "from asistente a inner join cuerpo_tecnico_equipo_visita c on a.numero_pasaporte=c.numero_pasaporte_asistente2 where c.numero_partido="+partido+")";
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                String[] asistentes1 = {"ASISTENTES"};
                tableModelVisita.addRow(asistentes1);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModelVisita.addRow(vector);
                }
                tableModelVisita.addRow(strSalto);
                tableModelVisita.addRow(strSalto);
                
                consulta = "select f.nombre, f.apellido1, f.apellido2\n" +
                "from cuerpo_tecnico_equipo_visita c inner join federativo f on f.numero_pasaporte=c.numero_pasaporte_delegado where c.numero_partido=";
                resultSet = Conexion.Conexion.realizarConsulta(consulta+partido);
                String[] delegado1 = {"DELEGADO"};
                tableModelVisita.addRow(delegado1);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModelVisita.addRow(vector);
                }
                tableModelVisita.addRow(strSalto);
                tableModelVisita.addRow(strSalto);
                
                consulta = "select a.nombre, a.apellido1, a.apellido2\n" +
                "from arbitros_por_partido ar inner join arbitro a on a.numero_pasaporte=ar.numero_pasaporte_arbitro where ar.numero_partido=";
                resultSet = Conexion.Conexion.realizarConsulta(consulta+partido);
                String[] arbitro1 = {"ARBITROS"};
                tableModelVisita.addRow(arbitro1);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= numColumnas; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    tableModelVisita.addRow(vector);
                }
                tableModelVisita.addRow(strSalto);
                tableModelVisita.addRow(strSalto);
                
                DefaultTableModel tableModelGoles = (DefaultTableModel) resultado_consulta_jTableGoles.getModel();
                tableModelGoles.setRowCount(0);
                consulta = "select j.nombre,j.apellido1,g.minuto_gol from goles_por_partido g inner join jugador j on g.numero_pasaporte_jugador=j.numero_pasaporte \n" +
                "where numero_partido="+partido+" and g.codigo_equipo='"+codLocal+"'";
                //System.out.println(consulta);
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                String[] Goles_local = {"GOLES_local"};
                tableModelGoles.addRow(Goles_local);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= 3; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    vector.add(0);
                    tableModelGoles.addRow(vector);
                }
                tableModelGoles.addRow(strSalto);
                tableModelGoles.addRow(strSalto);
                
                consulta = "select j.nombre,j.apellido1,g.minuto_gol from goles_por_partido g inner join jugador j on g.numero_pasaporte_jugador=j.numero_pasaporte \n" +
                "where numero_partido="+partido+" and g.codigo_equipo='"+codVisita+"'";
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                String[] Goles_visita = {"GOLES_Visita"};
                tableModelGoles.addRow(Goles_visita);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= 3; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    vector.add(0);
                    tableModelGoles.addRow(vector);
                }
                tableModelGoles.addRow(strSalto);
                tableModelGoles.addRow(strSalto);
                
                DefaultTableModel tableModelTarge = (DefaultTableModel) Tabla_tarjetas.getModel();
                tableModelTarge.setRowCount(0);
                consulta = "select j.nombre, j.apellido1,t.minuto_tarjeta from tarjetas_por_partido t inner join jugador j on t.numero_pasaporte_jugador=j.numero_pasaporte\n" +
                "where numero_partido="+partido+" AND tipo_tarjeta='AMARILLA'";
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                String[] tarjeta_ama = {"AMARILLAS"};
                tableModelTarge.addRow(tarjeta_ama);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= 3; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    vector.add(0);
                    tableModelTarge.addRow(vector);
                }
                tableModelTarge.addRow(strSalto);
                tableModelTarge.addRow(strSalto);
                
                consulta = "select j.nombre, j.apellido1,t.minuto_tarjeta from tarjetas_por_partido t inner join jugador j on t.numero_pasaporte_jugador=j.numero_pasaporte\n" +
                "where numero_partido="+partido+" AND tipo_tarjeta='ROJA'";
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                String[] tarjeta_roj= {"ROJAS"};
                tableModelTarge.addRow(tarjeta_roj);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= 3; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    vector.add(0);
                    tableModelTarge.addRow(vector);
                }
                tableModelTarge.addRow(strSalto);
                tableModelTarge.addRow(strSalto);
                
                DefaultTableModel tableModelCambios = (DefaultTableModel) tabla_cambios.getModel();
                tableModelCambios.setRowCount(0);
                consulta = "select j.NOMBRE,j.APELLIDO1,j1.nombre,j1.apellido1,c.minuto_cambio from cambios_equipo_local c inner join jugador j on c.numero_pasaporte_jugador_entra=j.numero_pasaporte\n" +
                "inner join jugador j1 on c.numero_pasaporte_jugador_sale=j1.numero_pasaporte where numero_partido="+partido;
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                String[] camb_local = {"LOCAL"};
                tableModelCambios.addRow(camb_local);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= 5; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    vector.add(0);
                    tableModelCambios.addRow(vector);
                }
                tableModelCambios.addRow(strSalto);
                tableModelCambios.addRow(strSalto);
                
                consulta = "select j.NOMBRE,j.APELLIDO1,j1.nombre,j1.apellido1,c.minuto_cambio from cambios_equipo_visita c inner join jugador j on c.numero_pasaporte_jugador_entra=j.numero_pasaporte\n" +
                "inner join jugador j1 on c.numero_pasaporte_jugador_sale=j1.numero_pasaporte where numero_partido="+partido;
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                String[] camb_visita = {"VISITA"};
                tableModelCambios.addRow(camb_visita);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= 5; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    vector.add(0);
                    tableModelCambios.addRow(vector);
                }
                tableModelCambios.addRow(strSalto);
                tableModelCambios.addRow(strSalto);
                
                consulta = "select * from partido where numero_partido="+partido;
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                resultSet.next();
                this.jLabelE1.setText(resultSet.getString("min_extra_primer_tiempo"));
                this.jLabelE2.setText(resultSet.getString("min_extra_segundo_tiempo"));
                this.jLabelTE.setText(resultSet.getString("tiempos_extra"));
                
                DefaultTableModel tableModelPenal = (DefaultTableModel) tabla_penales.getModel();
                tableModelPenal.setRowCount(0);
                consulta = "select p.numero_penal,j.nombre,j.apellido1,p.anotado from penales_por_partido p inner join jugador j on p.jugador_que_cobra=j.numero_pasaporte\n" +
                "where numero_partido="+partido+" AND j.codigo_equipo='"+codLocal+"'";
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                String[] penaleslocal = {"LOCAL"};
                tableModelPenal.addRow(penaleslocal);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= 5; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    vector.add(0);
                    tableModelPenal.addRow(vector);
                }
                tableModelPenal.addRow(strSalto);
                tableModelPenal.addRow(strSalto);
                
                consulta = "select p.numero_penal,j.nombre,j.apellido1,p.anotado from penales_por_partido p inner join jugador j on p.jugador_que_cobra=j.numero_pasaporte\n" +
                "where numero_partido="+partido+" AND j.codigo_equipo='"+codVisita+"'";
                resultSet = Conexion.Conexion.realizarConsulta(consulta);
                String[] penalesVisita = {"VISITA"};
                tableModelPenal.addRow(penalesVisita);
                while(resultSet.next()){
                    Vector vector = new Vector();
                    for (int i = 1; i <= 5; i++) {
                        vector.add(resultSet.getString(i));
                    }
                    vector.add(0);
                    tableModelPenal.addRow(vector);
                }
                
                resultado_consulta_jTable.setModel(tableModel);
                resultado_consulta_jTablevisita.setModel(tableModelVisita);
                resultado_consulta_jTableGoles.setModel(tableModelGoles);
                Tabla_tarjetas.setModel(tableModelTarge);
                tabla_cambios.setModel(tableModelCambios);
                tabla_penales.setModel(tableModelPenal);
            /*
                ResultSet resultSet = Conexion.Conexion.realizarConsulta("select * from partido where numero_partido="+partido);
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                DefaultTableModel tableModel = (DefaultTableModel) resultado_consulta_jTable.getModel();
                tableModel.setRowCount(0);
                int numColumnas = resultSetMetaData.getColumnCount();
                
                for (int i = 1; i <= numColumnas; i++) {
                    tableModel.addColumn(resultSetMetaData.getColumnLabel(i));
                }
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
                */
            
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelResultNP = new javax.swing.JLabel();
        jLabelResultET = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelResultFE = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelResultHO = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelResultES = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelResultCA = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelResult6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelResult7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultado_consulta_jTableGoles = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        resultado_consulta_jTablevisita = new javax.swing.JTable();
        jLabelGL = new javax.swing.JLabel();
        jLabelGL1 = new javax.swing.JLabel();
        resultado_tablaTarjetas = new javax.swing.JScrollPane();
        Tabla_tarjetas = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_cambios = new javax.swing.JTable();
        jLabelGL2 = new javax.swing.JLabel();
        jLabelGL3 = new javax.swing.JLabel();
        jLabelE1 = new javax.swing.JLabel();
        jLabelE2 = new javax.swing.JLabel();
        jLabelGL4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla_penales = new javax.swing.JTable();
        jLabelGL5 = new javax.swing.JLabel();
        jLabelGL6 = new javax.swing.JLabel();
        jLabelTE = new javax.swing.JLabel();
        jButtonAnterior = new javax.swing.JButton();
        jButtonSiguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resultado_jLabel.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        resultado_jLabel.setText("Resultado");

        resultado_consulta_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Primer Apellido", "Segundo Apellido"
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Información General:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Número Partido:");

        jLabelResultNP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResultNP.setText(" ");

        jLabelResultET.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResultET.setText(" ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Etapa:");

        jLabelResultFE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResultFE.setText(" ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Fecha:");

        jLabelResultHO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResultHO.setText(" ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Hora");

        jLabelResultES.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResultES.setText(" ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Estadio:");

        jLabelResultCA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResultCA.setText(" ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Cantidad Aficionados:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Equipo local:");

        jLabelResult6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResult6.setText(" ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Equipo visita:");

        jLabelResult7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResult7.setText(" ");

        resultado_consulta_jTableGoles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Minuto", "Segundo"
            }
        ));
        jScrollPane2.setViewportView(resultado_consulta_jTableGoles);

        resultado_consulta_jTablevisita.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Primer Apellido", "Segundo Apellido"
            }
        ));
        jScrollPane3.setViewportView(resultado_consulta_jTablevisita);

        jLabelGL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelGL.setText("Tarjetas:");

        jLabelGL1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelGL1.setText("Goles:");

        Tabla_tarjetas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Minuto", "Segundo"
            }
        ));
        resultado_tablaTarjetas.setViewportView(Tabla_tarjetas);

        tabla_cambios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre_entra", "Apellido_entra", "Nombre_sale", "Nombre_sale", "Minuto", "Segundo"
            }
        ));
        jScrollPane4.setViewportView(tabla_cambios);

        jLabelGL2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelGL2.setText("Cambios:");

        jLabelGL3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelGL3.setText("Penales:");

        jLabelE1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelE1.setText(" ");

        jLabelE2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelE2.setText(" ");

        jLabelGL4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelGL4.setText("Extra segundo tiempo:");

        tabla_penales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero_penal", "Nombre", "Apellido", "Anotado"
            }
        ));
        jScrollPane5.setViewportView(tabla_penales);

        jLabelGL5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelGL5.setText("Extra primer tiempo:");

        jLabelGL6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelGL6.setText("Tiempos Extra:");

        jLabelTE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTE.setText(" ");

        jButtonAnterior.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAnterior.setText("Anterior");
        jButtonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnteriorActionPerformed(evt);
            }
        });

        jButtonSiguiente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonSiguiente.setText("Siguiente");
        jButtonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSiguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(resultado_jLabel)
                                .addGap(217, 217, 217)
                                .addComponent(jButtonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSiguiente))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel3)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabelResultET, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel2)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabelResultNP, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabelResultES, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(117, 117, 117)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabelResultHO, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                                    .addComponent(jLabelResultCA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabelResultFE, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabelResult6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(134, 134, 134)
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabelResult7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(50, 50, 50))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelGL6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelTE, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelGL2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelGL)
                        .addGap(398, 398, 398))
                    .addComponent(resultado_tablaTarjetas)
                    .addComponent(jScrollPane5)
                    .addComponent(regresar_jButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelGL1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabelGL5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelE1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabelGL4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelE2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelGL3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(resultado_jLabel)
                        .addComponent(jButtonAnterior)
                        .addComponent(jButtonSiguiente))
                    .addComponent(jLabelGL1))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabelResultNP)
                            .addComponent(jLabel4)
                            .addComponent(jLabelResultFE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabelResultET)
                            .addComponent(jLabel5)
                            .addComponent(jLabelResultHO))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabelResultES)
                            .addComponent(jLabel7)
                            .addComponent(jLabelResultCA))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabelResult6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabelResult7))
                                .addGap(10, 10, 10)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabelGL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resultado_tablaTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelE1)
                                    .addComponent(jLabelGL4)
                                    .addComponent(jLabelE2)
                                    .addComponent(jLabelGL5)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelGL2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelGL3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(regresar_jButton))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelGL6)
                        .addComponent(jLabelTE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regresar_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresar_jButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_regresar_jButtonActionPerformed

    private void jButtonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnteriorActionPerformed
        // TODO add your handling code here:
        if(TODOS==true){
            if(ACTUAL>1){
                ACTUAL-=1;
                String aux = String.valueOf(ACTUAL);
                obtenerEquiposConfederacion(aux);
            }else{
                JOptionPane.showMessageDialog(this, "No existen mas partidos anteriores"
                                            , "Error: partido 0", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonAnteriorActionPerformed

    private void jButtonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSiguienteActionPerformed
        // TODO add your handling code here:
        if(TODOS==true){
            System.out.println("Actual:"+ACTUAL+" TOTAL:"+TOTAL);
            if(ACTUAL<TOTAL){
                ACTUAL+=1;
                String aux = String.valueOf(ACTUAL);
                obtenerEquiposConfederacion(aux);
            }else{
                JOptionPane.showMessageDialog(this, "No existen mas partidos siguientes"
                                            , "Error: No existen mas partidos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonSiguienteActionPerformed

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
            java.util.logging.Logger.getLogger(InformacionEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformacionEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformacionEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacionEquipos(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla_tarjetas;
    private javax.swing.JButton jButtonAnterior;
    private javax.swing.JButton jButtonSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelE1;
    private javax.swing.JLabel jLabelE2;
    private javax.swing.JLabel jLabelGL;
    private javax.swing.JLabel jLabelGL1;
    private javax.swing.JLabel jLabelGL2;
    private javax.swing.JLabel jLabelGL3;
    private javax.swing.JLabel jLabelGL4;
    private javax.swing.JLabel jLabelGL5;
    private javax.swing.JLabel jLabelGL6;
    private javax.swing.JLabel jLabelResult6;
    private javax.swing.JLabel jLabelResult7;
    private javax.swing.JLabel jLabelResultCA;
    private javax.swing.JLabel jLabelResultES;
    private javax.swing.JLabel jLabelResultET;
    private javax.swing.JLabel jLabelResultFE;
    private javax.swing.JLabel jLabelResultHO;
    private javax.swing.JLabel jLabelResultNP;
    private javax.swing.JLabel jLabelTE;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton regresar_jButton;
    private javax.swing.JTable resultado_consulta_jTable;
    private javax.swing.JTable resultado_consulta_jTableGoles;
    private javax.swing.JTable resultado_consulta_jTablevisita;
    private javax.swing.JLabel resultado_jLabel;
    private javax.swing.JScrollPane resultado_tablaTarjetas;
    private javax.swing.JTable tabla_cambios;
    private javax.swing.JTable tabla_penales;
    // End of variables declaration//GEN-END:variables
}
