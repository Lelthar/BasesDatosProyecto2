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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jeanca
 */
public class VentanaTablaGeneralPosiciones extends javax.swing.JFrame {

    /**
     * Creates new form EquiposConfederacion
     */
    public VentanaTablaGeneralPosiciones() {
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
            System.out.println("1");
            /*Conexion.Conexion.realizarConsulta("drop table tablafinal");
            Conexion.Conexion.realizarConsulta("exec borrar('tablasemifinal')");
            Conexion.Conexion.realizarConsulta("exec borrar('tablatemporal1')");
            Conexion.Conexion.realizarConsulta("exec borrar('tablacuartosfinal')");
            Conexion.Conexion.realizarConsulta("exec borrar('tablatemporal2')");
            Conexion.Conexion.realizarConsulta("exec borrar('tablaoctavosfinal')");
            Conexion.Conexion.realizarConsulta("exec borrar('tablatemporal3')");
            Conexion.Conexion.realizarConsulta("exec borrar('tablafasegrupos')");
            Conexion.Conexion.realizarConsulta("exec borrar('tabla_goles_equipos1')");
            Conexion.Conexion.realizarConsulta("exec borrar('tabla_goles_equipos2')");
            Conexion.Conexion.realizarConsulta("exec borrar('tabla_goles_equipos')");
            Conexion.Conexion.realizarConsulta("exec borrar('tabla_goles_equipos_completo')");*/
          
            Conexion.Conexion.realizarConsulta("CREATE TABLE tablafinal AS select equipo.codigo_equipo, equipo.nombre_pais, count(*) as total_goles from partido inner join goles_por_partido on (partido.numero_partido = goles_por_partido.numero_partido) inner join equipo on(goles_por_partido.codigo_equipo = equipo.codigo_equipo) where partido.fase = 'FINAL' group by equipo.codigo_equipo, equipo.nombre_pais order by count(*) desc");
            
            Conexion.Conexion.realizarConsulta("CREATE TABLE tablasemifinal AS select equipo.codigo_equipo, equipo.nombre_pais, count(*) as total_goles from partido inner join goles_por_partido on (partido.numero_partido = goles_por_partido.numero_partido) inner join equipo on(goles_por_partido.codigo_equipo = equipo.codigo_equipo) where partido.fase = 'TERCER LUGAR' group by equipo.codigo_equipo, equipo.nombre_pais order by count(*) desc");
            
            Conexion.Conexion.realizarConsulta("CREATE TABLE tablatemporal1 AS select * from tablafinal UNION select * from tablasemifinal");
            
            Conexion.Conexion.realizarConsulta("CREATE TABLE tablacuartosfinal AS select equipo.codigo_equipo, equipo.nombre_pais, count(*) as total_goles from partido inner join goles_por_partido on (partido.numero_partido = goles_por_partido.numero_partido) inner join equipo on(goles_por_partido.codigo_equipo = equipo.codigo_equipo) where partido.fase = 'CUARTOS DE FINAL' AND not exists (select * from tablatemporal1 where equipo.codigo_equipo = tablatemporal1.codigo_equipo) group by equipo.codigo_equipo, equipo.nombre_pais order by total_goles desc");
         
            Conexion.Conexion.realizarConsulta("CREATE TABLE tablatemporal2 AS select * from tablafinal UNION select * from tablasemifinal UNION select * from tablacuartosfinal");
            
            Conexion.Conexion.realizarConsulta("CREATE TABLE tablaoctavosfinal AS select equipo.codigo_equipo, equipo.nombre_pais, count(*) as total_goles from partido inner join goles_por_partido on (partido.numero_partido = goles_por_partido.numero_partido) inner join equipo on(goles_por_partido.codigo_equipo = equipo.codigo_equipo) where partido.fase = 'OCTAVOS DE FINAL' AND not exists (select * from tablatemporal2 where equipo.codigo_equipo = tablatemporal2.codigo_equipo) group by equipo.codigo_equipo, equipo.nombre_pais order by total_goles desc");
          
            Conexion.Conexion.realizarConsulta("CREATE TABLE tablatemporal3 AS select * from tablafinal UNION select * from tablasemifinal UNION select * from tablacuartosfinal UNION select * from tablaoctavosfinal");
          
            Conexion.Conexion.realizarConsulta("CREATE TABLE tablafasegrupos AS select equipo.codigo_equipo, equipo.nombre_pais, count(*) as total_goles from partido inner join goles_por_partido on (partido.numero_partido = goles_por_partido.numero_partido) inner join equipo on(goles_por_partido.codigo_equipo = equipo.codigo_equipo) where partido.fase = 'FASE DE GRUPOS' AND not exists (select * from tablatemporal3 where equipo.codigo_equipo = tablatemporal3.codigo_equipo ) group by equipo.codigo_equipo, equipo.nombre_pais order by total_goles desc");
            Conexion.Conexion.realizarConsulta("CREATE TABLE tabla_goles_equipos1 AS SELECT equipo.codigo_equipo, equipo.nombre_pais, partido.numero_partido, count(*)as total_goles_partido FROM equipo inner join capitan_local  on (equipo.codigo_equipo = capitan_local.codigo_equipo) inner join partido on (capitan_local.numero_partido = PARTIDO.NUMERO_PARTIDO) inner join goles_por_partido on (PARTIDO.numero_partido = goles_por_partido.numero_partido) WHERE equipo.codigo_equipo = goles_por_partido.codigo_equipo GROUP BY equipo.codigo_equipo, equipo.nombre_pais,partido.numero_partido order by total_goles_partido desc");
            Conexion.Conexion.realizarConsulta("CREATE TABLE tabla_goles_equipos2 AS SELECT equipo.codigo_equipo, equipo.nombre_pais, partido.numero_partido, count(*)as total_goles_partido FROM equipo inner join capitan_visita  on (equipo.codigo_equipo = capitan_visita.codigo_equipo) inner join partido on (capitan_visita.numero_partido = PARTIDO.NUMERO_PARTIDO) inner join goles_por_partido on (PARTIDO.numero_partido = goles_por_partido.numero_partido) WHERE equipo.codigo_equipo = goles_por_partido.codigo_equipo GROUP BY equipo.codigo_equipo, equipo.nombre_pais,partido.numero_partido order by total_goles_partido desc");
            Conexion.Conexion.realizarConsulta("CREATE TABLE tabla_goles_equipos AS select * from tabla_goles_equipos1 union select * from tabla_goles_equipos2");
            Conexion.Conexion.realizarConsulta("CREATE TABLE tabla_goles_equipos_completo AS select tabla_goles_equipos.codigo_equipo, tabla_goles_equipos.nombre_pais, sum(tabla_goles_equipos.total_goles_partido) as goles_total from tabla_goles_equipos group by tabla_goles_equipos.codigo_equipo, tabla_goles_equipos.nombre_pais");
            
            
            ResultSet resultSetFinal =  Conexion.Conexion.realizarConsulta("select * from tablafinal");
            ResultSetMetaData resultSetMetaDataFinal = resultSetFinal.getMetaData();
            ArrayList<ArrayList<String>> listaFinal = new ArrayList<>();
            int numColumnas = resultSetMetaDataFinal.getColumnCount();

            while(resultSetFinal.next()){
                ArrayList<String> vector = new ArrayList<>();
                for (int i = 1; i <= numColumnas; i++) {
                    vector.add(resultSetFinal.getString(i));
                    
                }
                listaFinal.add(vector);
            }
            
            ResultSet resultSetSemiFinal =  Conexion.Conexion.realizarConsulta("select * from tablasemifinal");
            ResultSetMetaData resultSetMetaDataSemiFinal = resultSetSemiFinal.getMetaData();
            ArrayList<ArrayList<String>> listaSemiFinal = new ArrayList<>();
            int numColumnasSemiFinal = resultSetMetaDataSemiFinal.getColumnCount();

            while(resultSetSemiFinal.next()){
                ArrayList<String> vector = new ArrayList<>();
                for (int i = 1; i <= numColumnasSemiFinal; i++) {
                    vector.add(resultSetSemiFinal.getString(i));
                    
                }
                listaSemiFinal.add(vector);
            }
            
            ResultSet resultSetCuartosFinal =  Conexion.Conexion.realizarConsulta("select * from tablacuartosfinal");
            ResultSetMetaData resultSetMetaDataCuartosFinal = resultSetCuartosFinal.getMetaData();
            ArrayList<ArrayList<String>> listaCuartosFinal = new ArrayList<>();
            int numColumnasCuartosFinal = resultSetMetaDataCuartosFinal.getColumnCount();

            while(resultSetCuartosFinal.next()){
                ArrayList<String> vector = new ArrayList<>();
                for (int i = 1; i <= numColumnasCuartosFinal; i++) {
                    vector.add(resultSetCuartosFinal.getString(i));
                    
                }
                listaCuartosFinal.add(vector);
            }
            
            ResultSet resultSetOctavosFinal =  Conexion.Conexion.realizarConsulta("select * from tablaoctavosfinal");
            ResultSetMetaData resultSetMetaDataOctavosFinal = resultSetOctavosFinal.getMetaData();
            ArrayList<ArrayList<String>> listaOctavosFinal = new ArrayList<>();
            int numColumnasOctavosFinal = resultSetMetaDataOctavosFinal.getColumnCount();

            while(resultSetOctavosFinal.next()){
                ArrayList<String> vector = new ArrayList<>();
                for (int i = 1; i <= numColumnasOctavosFinal; i++) {
                    vector.add(resultSetOctavosFinal.getString(i));
                    
                }
                listaOctavosFinal.add(vector);
            }
            
            ResultSet resultSetFaseGrupos =  Conexion.Conexion.realizarConsulta("select * from tablafasegrupos");
            ResultSetMetaData resultSetMetaDataFaseGrupos = resultSetFaseGrupos.getMetaData();
            ArrayList<ArrayList<String>> listaFaseGrupos  = new ArrayList<>();
            int numColumnasFaseGrupos = resultSetMetaDataFaseGrupos.getColumnCount();

            while(resultSetFaseGrupos.next()){
                ArrayList<String> vector = new ArrayList<>();
                for (int i = 1; i <= numColumnasFaseGrupos; i++) {
                    vector.add(resultSetFaseGrupos.getString(i));
                    
                }
                listaFaseGrupos.add(vector);
            }
            
            ResultSet resultSetGolesEquipos =  Conexion.Conexion.realizarConsulta("select * from tabla_goles_equipos_completo");
            ResultSetMetaData resultSetMetaDataGolesEquipos = resultSetGolesEquipos.getMetaData();
            ArrayList<ArrayList<String>> listaGolesEquipos = new ArrayList<>();
            int numColumnasGolesEquipos = resultSetMetaDataGolesEquipos.getColumnCount();

            while(resultSetGolesEquipos.next()){
                ArrayList<String> vector = new ArrayList<>();
                for (int i = 1; i <= numColumnasGolesEquipos; i++) {
                    vector.add(resultSetGolesEquipos.getString(i));
                    
                }
                listaGolesEquipos.add(vector);
            }
            
            ResultSet resultSetGolesEquiposPartidos =  Conexion.Conexion.realizarConsulta("select * from tabla_goles_equipos");
            ResultSetMetaData resultSetMetaDataGolesEquiposPartidos = resultSetGolesEquiposPartidos.getMetaData();
            ArrayList<ArrayList<String>> listaGolesEquiposPartidos = new ArrayList<>();
            int numColumnasGolesEquiposPartidos = resultSetMetaDataGolesEquiposPartidos.getColumnCount();

            while(resultSetGolesEquiposPartidos.next()){
                ArrayList<String> vector = new ArrayList<>();
                for (int i = 1; i <= numColumnasGolesEquiposPartidos; i++) {
                    vector.add(resultSetGolesEquiposPartidos.getString(i));
                    
                }
                listaGolesEquiposPartidos.add(vector);
            }
            ArrayList<ArrayList<String>> listaGolesEquiposEncontra = new ArrayList<>();
            
            for(int i = 0; i < listaGolesEquiposPartidos.size(); i++){
                ArrayList<String> vector = new ArrayList<>();
                vector.add(listaGolesEquiposPartidos.get(i).get(0));
                vector.add(listaGolesEquiposPartidos.get(i).get(1));
                vector.add(listaGolesEquiposPartidos.get(i).get(2));
                for(int j = 0; j < listaGolesEquiposPartidos.size(); j++){
                    if(listaGolesEquiposPartidos.get(j).get(2).equals(listaGolesEquiposPartidos.get(i).get(2)) && !listaGolesEquiposPartidos.get(j).get(0).equals(listaGolesEquiposPartidos.get(i).get(0))){
                        vector.add(listaGolesEquiposPartidos.get(j).get(3));
                    }
                }
                listaGolesEquiposEncontra.add(vector);
                
            }
            ArrayList<ArrayList<String>> listaRespaldoContra = listaGolesEquiposEncontra;
            ArrayList<ArrayList<String>> listaTotalGolesEncontra = new ArrayList<>();
            int largo1 = listaGolesEquiposEncontra.size();
            int largo2 = largo1;
            for(int i = 0; i < largo1; i++){
                ArrayList<String> vector = new ArrayList<>();
                vector.add(listaGolesEquiposEncontra.get(i).get(0));
                vector.add(listaGolesEquiposEncontra.get(i).get(1));
                int numero = 0;
                
                numero += Integer.parseInt(listaGolesEquiposEncontra.get(i).get(3));
                for(int j = 0; j < largo2; j++){
                    if(listaGolesEquiposEncontra.get(i).get(0).equals(listaGolesEquiposEncontra.get(j).get(0)) && !listaGolesEquiposEncontra.get(i).get(2).equals(listaGolesEquiposEncontra.get(j).get(2))){
                        numero += Integer.parseInt(listaGolesEquiposEncontra.get(j).get(3));
                        listaGolesEquiposEncontra.remove(j);
                        j--;
                        largo1--;
                        largo2--;
                    }
                }
                vector.add(Integer.toString(numero));
                listaTotalGolesEncontra.add(vector);
            }
            
            ArrayList<ArrayList<String>> listaTotalGPE = new ArrayList<>();
            int largo3 = listaGolesEquipos.size();
            int largo4 = listaRespaldoContra.size();
            for(int i = 0; i < largo3; i++){
                ArrayList<String> vector = new ArrayList<>();
                vector.add(listaGolesEquipos.get(i).get(0));
                vector.add(listaGolesEquipos.get(i).get(1));
                int ganes = 0;
                int derrotas = 0;
                int empates = 0;
                
                for(int j = 0; j < largo4; j++){
                    if(listaGolesEquipos.get(i).get(0).equals(listaRespaldoContra.get(j).get(0))){
                        if(Integer.parseInt(listaGolesEquiposPartidos.get(j).get(3))> Integer.parseInt(listaRespaldoContra.get(j).get(3))){
                            ganes++;
                        }else if(Integer.parseInt(listaGolesEquiposPartidos.get(j).get(3)) < Integer.parseInt(listaRespaldoContra.get(j).get(3))){
                            derrotas++;
                        }else{
                            empates++;
                        }
                    }
                }
                vector.add(Integer.toString(ganes));
                vector.add(Integer.toString(derrotas));
                vector.add(Integer.toString(empates));
                
                listaTotalGPE.add(vector);
            }
            
            for(int i = 0; i < listaTotalGPE.size(); i++){
                System.out.print(listaTotalGPE.get(i).get(0));
                System.out.print(listaTotalGPE.get(i).get(1));
                System.out.print(listaTotalGPE.get(i).get(2));
                System.out.print(listaTotalGPE.get(i).get(3));
                System.out.print(listaTotalGPE.get(i).get(4));
            }
            
            DefaultTableModel tableModel = (DefaultTableModel) resultado_consulta_jTable.getModel();
            tableModel.setRowCount(0);
            int posiciones = 1;
            tableModel.addColumn("Posicion");
            tableModel.addColumn("Equipo");
            tableModel.addColumn("Puntos");
            tableModel.addColumn("PJ");
            tableModel.addColumn("PG");
            tableModel.addColumn("PE");
            tableModel.addColumn("PP");
            tableModel.addColumn("GF");
            tableModel.addColumn("GC");
            tableModel.addColumn("Dif");
            
            String[] finalistas = { "Finales" };
            tableModel.addRow(finalistas);
            for(int i = 0; i < listaFinal.size(); i++){
                Vector vector = new Vector();
                vector.add(posiciones);
                vector.add(listaFinal.get(i).get(1));
                int totalPartidos = 0;
                int partidosGanados = 0;
                int partidosPerdidos = 0;
                int partidosEmpatados = 0;
                for(int j = 0; j <listaTotalGPE.size(); j++){
                    if(listaTotalGPE.get(j).get(0).endsWith(listaFinal.get(i).get(0))){
                        totalPartidos = (Integer.parseInt(listaTotalGPE.get(j).get(2))+Integer.parseInt(listaTotalGPE.get(j).get(3))+Integer.parseInt(listaTotalGPE.get(j).get(4)));
                        partidosGanados = Integer.parseInt(listaTotalGPE.get(j).get(2));
                        partidosPerdidos = Integer.parseInt(listaTotalGPE.get(j).get(3));
                        partidosEmpatados = Integer.parseInt(listaTotalGPE.get(j).get(4));
                        break;
                    }
                }
                vector.add(partidosGanados*3+partidosEmpatados);
                vector.add(totalPartidos);
                vector.add(partidosGanados);
                vector.add(partidosEmpatados);
                vector.add(partidosPerdidos);
                
                int golesFavor = 0;
                int golesContra = 0;
                int golesDif = 0;
                
                for(int j = 0; j < listaGolesEquipos.size(); j++){
                    if(listaGolesEquipos.get(j).get(0).endsWith(listaFinal.get(i).get(0))){
                        golesFavor = Integer.parseInt(listaGolesEquipos.get(j).get(2));
                        break;
                    }
                }
                
                for(int j = 0; j < listaTotalGolesEncontra.size(); j++){
                    if(listaTotalGolesEncontra.get(j).get(0).endsWith(listaFinal.get(i).get(0))){
                        golesFavor = Integer.parseInt(listaTotalGolesEncontra.get(j).get(2));
                        break;
                    }
                }
                
                golesDif = golesFavor - golesContra;
                
                vector.add(golesFavor);
                vector.add(golesContra);
                vector.add(golesDif);
                
                tableModel.addRow(vector);
                posiciones++;
            }
            
            String[] seminales = { "Semifinales" };
            tableModel.addRow(seminales);
            for(int i = 0; i < listaSemiFinal.size(); i++){
                Vector vector = new Vector();
                vector.add(posiciones);
                vector.add(listaSemiFinal.get(i).get(1));
                int totalPartidos = 0;
                int partidosGanados = 0;
                int partidosPerdidos = 0;
                int partidosEmpatados = 0;
                for(int j = 0; j <listaTotalGPE.size(); j++){
                    if(listaTotalGPE.get(j).get(0).endsWith(listaSemiFinal.get(i).get(0))){
                        totalPartidos = (Integer.parseInt(listaTotalGPE.get(j).get(2))+Integer.parseInt(listaTotalGPE.get(j).get(3))+Integer.parseInt(listaTotalGPE.get(j).get(4)));
                        partidosGanados = Integer.parseInt(listaTotalGPE.get(j).get(2));
                        partidosPerdidos = Integer.parseInt(listaTotalGPE.get(j).get(3));
                        partidosEmpatados = Integer.parseInt(listaTotalGPE.get(j).get(4));
                        break;
                    }
                }
                vector.add(partidosGanados*3+partidosEmpatados);
                vector.add(totalPartidos);
                vector.add(partidosGanados);
                vector.add(partidosEmpatados);
                vector.add(partidosPerdidos);
                
                int golesFavor = 0;
                int golesContra = 0;
                int golesDif = 0;
                
                for(int j = 0; j < listaGolesEquipos.size(); j++){
                    if(listaGolesEquipos.get(j).get(0).endsWith(listaSemiFinal.get(i).get(0))){
                        golesFavor = Integer.parseInt(listaGolesEquipos.get(j).get(2));
                        break;
                    }
                }
                
                for(int j = 0; j < listaTotalGolesEncontra.size(); j++){
                    if(listaTotalGolesEncontra.get(j).get(0).endsWith(listaSemiFinal.get(i).get(0))){
                        golesFavor = Integer.parseInt(listaTotalGolesEncontra.get(j).get(2));
                        break;
                    }
                }
                
                golesDif = golesFavor - golesContra;
                
                vector.add(golesFavor);
                vector.add(golesContra);
                vector.add(golesDif);
                
                tableModel.addRow(vector);
                posiciones++;
            }
            
            String[] cuartos = { "Cuartos de final" };
            tableModel.addRow(cuartos);
            for(int i = 0; i < listaCuartosFinal.size(); i++){
                Vector vector = new Vector();
                vector.add(posiciones);
                vector.add(listaCuartosFinal.get(i).get(1));
                int totalPartidos = 0;
                int partidosGanados = 0;
                int partidosPerdidos = 0;
                int partidosEmpatados = 0;
                for(int j = 0; j <listaTotalGPE.size(); j++){
                    if(listaTotalGPE.get(j).get(0).endsWith(listaCuartosFinal.get(i).get(0))){
                        totalPartidos = (Integer.parseInt(listaTotalGPE.get(j).get(2))+Integer.parseInt(listaTotalGPE.get(j).get(3))+Integer.parseInt(listaTotalGPE.get(j).get(4)));
                        partidosGanados = Integer.parseInt(listaTotalGPE.get(j).get(2));
                        partidosPerdidos = Integer.parseInt(listaTotalGPE.get(j).get(3));
                        partidosEmpatados = Integer.parseInt(listaTotalGPE.get(j).get(4));
                        break;
                    }
                }
                vector.add(partidosGanados*3+partidosEmpatados);
                vector.add(totalPartidos);
                vector.add(partidosGanados);
                vector.add(partidosEmpatados);
                vector.add(partidosPerdidos);
                
                int golesFavor = 0;
                int golesContra = 0;
                int golesDif = 0;
                
                for(int j = 0; j < listaGolesEquipos.size(); j++){
                    if(listaGolesEquipos.get(j).get(0).endsWith(listaCuartosFinal.get(i).get(0))){
                        golesFavor = Integer.parseInt(listaGolesEquipos.get(j).get(2));
                        break;
                    }
                }
                
                for(int j = 0; j < listaTotalGolesEncontra.size(); j++){
                    if(listaTotalGolesEncontra.get(j).get(0).endsWith(listaCuartosFinal.get(i).get(0))){
                        golesFavor = Integer.parseInt(listaTotalGolesEncontra.get(j).get(2));
                        break;
                    }
                }
                
                golesDif = golesFavor - golesContra;
                
                vector.add(golesFavor);
                vector.add(golesContra);
                vector.add(golesDif);
                
                tableModel.addRow(vector);
                
                posiciones++;
            }
            
            String[] octavos = { "Octavos de final" };
            tableModel.addRow(octavos);
            for(int i = 0; i < listaOctavosFinal.size(); i++){
                Vector vector = new Vector();
                vector.add(posiciones);
                vector.add(listaOctavosFinal.get(i).get(1));
                int totalPartidos = 0;
                int partidosGanados = 0;
                int partidosPerdidos = 0;
                int partidosEmpatados = 0;
                for(int j = 0; j <listaTotalGPE.size(); j++){
                    if(listaTotalGPE.get(j).get(0).endsWith(listaOctavosFinal.get(i).get(0))){
                        totalPartidos = (Integer.parseInt(listaTotalGPE.get(j).get(2))+Integer.parseInt(listaTotalGPE.get(j).get(3))+Integer.parseInt(listaTotalGPE.get(j).get(4)));
                        partidosGanados = Integer.parseInt(listaTotalGPE.get(j).get(2));
                        partidosPerdidos = Integer.parseInt(listaTotalGPE.get(j).get(3));
                        partidosEmpatados = Integer.parseInt(listaTotalGPE.get(j).get(4));
                        break;
                    }
                }
                vector.add(partidosGanados*3+partidosEmpatados);
                vector.add(totalPartidos);
                vector.add(partidosGanados);
                vector.add(partidosEmpatados);
                vector.add(partidosPerdidos);
                
                int golesFavor = 0;
                int golesContra = 0;
                int golesDif = 0;
                
                for(int j = 0; j < listaGolesEquipos.size(); j++){
                    if(listaGolesEquipos.get(j).get(0).endsWith(listaOctavosFinal.get(i).get(0))){
                        golesFavor = Integer.parseInt(listaGolesEquipos.get(j).get(2));
                        break;
                    }
                }
                
                for(int j = 0; j < listaTotalGolesEncontra.size(); j++){
                    if(listaTotalGolesEncontra.get(j).get(0).endsWith(listaOctavosFinal.get(i).get(0))){
                        golesFavor = Integer.parseInt(listaTotalGolesEncontra.get(j).get(2));
                        break;
                    }
                }
                
                golesDif = golesFavor - golesContra;
                
                vector.add(golesFavor);
                vector.add(golesContra);
                vector.add(golesDif);
                
                tableModel.addRow(vector);
                posiciones++;
            }
            
            String[] faseGrupos = { "Fase de grupos" };
            tableModel.addRow(faseGrupos);
            for(int i = 0; i < listaFaseGrupos.size(); i++){
                Vector vector = new Vector();
                vector.add(posiciones);
                vector.add(listaFaseGrupos.get(i).get(1));
                int totalPartidos = 0;
                int partidosGanados = 0;
                int partidosPerdidos = 0;
                int partidosEmpatados = 0;
                for(int j = 0; j <listaTotalGPE.size(); j++){
                    if(listaTotalGPE.get(j).get(0).endsWith(listaFaseGrupos.get(i).get(0))){
                        totalPartidos = (Integer.parseInt(listaTotalGPE.get(j).get(2))+Integer.parseInt(listaTotalGPE.get(j).get(3))+Integer.parseInt(listaTotalGPE.get(j).get(4)));
                        partidosGanados = Integer.parseInt(listaTotalGPE.get(j).get(2));
                        partidosPerdidos = Integer.parseInt(listaTotalGPE.get(j).get(3));
                        partidosEmpatados = Integer.parseInt(listaTotalGPE.get(j).get(4));
                        break;
                    }
                }
                vector.add(partidosGanados*3+partidosEmpatados);
                vector.add(totalPartidos);
                vector.add(partidosGanados);
                vector.add(partidosEmpatados);
                vector.add(partidosPerdidos);
                
                int golesFavor = 0;
                int golesContra = 0;
                int golesDif = 0;
                
                for(int j = 0; j < listaGolesEquipos.size(); j++){
                    if(listaGolesEquipos.get(j).get(0).endsWith(listaFaseGrupos.get(i).get(0))){
                        golesFavor = Integer.parseInt(listaGolesEquipos.get(j).get(2));
                        break;
                    }
                }
                
                for(int j = 0; j < listaTotalGolesEncontra.size(); j++){
                    if(listaTotalGolesEncontra.get(j).get(0).endsWith(listaFaseGrupos.get(i).get(0))){
                        golesFavor = Integer.parseInt(listaTotalGolesEncontra.get(j).get(2));
                        break;
                    }
                }
                
                golesDif = golesFavor - golesContra;
                
                vector.add(golesFavor);
                vector.add(golesContra);
                vector.add(golesDif);
                
                tableModel.addRow(vector);
                
                posiciones++;
            }
            
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
        resultado_jLabel.setText("Tabla de goleadores del campeonato:");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(regresar_jButton)
                .addGap(132, 132, 132))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultado_jLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(resultado_jLabel)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(VentanaTablaGeneralPosiciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaTablaGeneralPosiciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaTablaGeneralPosiciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaTablaGeneralPosiciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new VentanaTablaGeneralPosiciones().setVisible(true);
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
