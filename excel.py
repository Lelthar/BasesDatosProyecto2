# Ejemplo de creacion de hoja Excel
import xlwt
from datetime import datetime
import numpy as np
try:
    from StringIO import StringIO
except ImportError:
    from io import StringIO

def procesar_linea(linea):
    largo = len(linea)
    i=0;
    arreglo=[]
    string = ""
    for i in range(largo):
        if(linea[i]==','):
            arreglo.append(string)
            string=""
        elif(linea[i]!=" "):
             string+=linea[i]
    arreglo.append(string)
    return arreglo

#Recibe la cantidad de lineas a leer
def leer_archivo(cantidad):
    # archivo-entrada.py
    datos = []
    f = open ('jugadores.txt','r')
    a = 0;
    while(a<cantidad):
        dato = f.readline()
        dato = dato[27:]
        dato = dato.replace("TO_DATE('","")
        dato = dato.replace("'YYYY-MM-DD'),","")
        dato = dato.replace(");\n","")
        dato = dato.replace("'","")
        dato = dato.replace(");","")
        res=procesar_linea(dato)
        datos.append(res)
        a+=1
    f.close()
    print(len(datos))
    return datos

#cantidad de lineas a escribir
def crear_excel(cantidad):
    style0 = xlwt.easyxf('font: name Times New Roman, colour black, bold on')
    style1 = xlwt.easyxf('',num_format_str='DD-MMM-YY')
    wb = xlwt.Workbook()
    ws = wb.add_sheet('Jugadores',cell_overwrite_ok=True)
    datos = leer_archivo(735)
    largo = len(datos)
    for i in range(largo):
        linea = datos[i]
        ws.write(i, 0, linea[0], style0)
        ws.write(i, 1, linea[1], style0)
        ws.write(i, 2, linea[2], style0)
        ws.write(i, 3, linea[3], style0)
        ws.write(i, 4, linea[4], style0)
        ws.write(i, 5, linea[5], style0)
        ws.write(i, 6, linea[6], style0)
        ws.write(i, 7, linea[7], style0)
    wb.save('Jugadores.xls')
    print("Archivo creado")

#crear_excel(735)

