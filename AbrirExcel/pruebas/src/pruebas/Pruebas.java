/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author gerald
 */
public class Pruebas {
    public Pruebas(File filename){
        
        
    }
    
    public static ArrayList<ArrayList<String>> obtenerArchivo(String nombreArchivo){
        
        File file = new File(nombreArchivo+".xlsx");
        
        ArrayList<ArrayList<String>> datosJugadores = new ArrayList();
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            
            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            
            XSSFSheet hssfSheet = workBook.getSheetAt(0);

            Iterator rowIterator = hssfSheet.rowIterator();

            while(rowIterator.hasNext()){
            	XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                
                Iterator iterator = hssfRow.cellIterator();
                
                ArrayList<String> celda = new ArrayList();
                
                while(iterator.hasNext()){
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    //System.out.print(hssfCell.toString()+"\t");
                    celda.add(hssfCell.toString());
                    
                    
                }
                //System.out.println("");
                datosJugadores.add(celda);
            }
            
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return datosJugadores;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<ArrayList<String>> archivo = obtenerArchivo("prueba");
        
        for(int i = 0; i < archivo.size(); i++){
            for(int j = 0; j < archivo.get(0).size(); j++){
                System.out.print(archivo.get(i).get(j)+"\t");
            }
            System.out.println("");
        }
       
    }
    
}
