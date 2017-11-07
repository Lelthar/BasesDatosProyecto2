# -*- coding: utf-8 -*-
import random as r

def cargar_lista(nombre_archivo):
    file = open(nombre_archivo,encoding="utf8")
    lista = []
    while(file.readline()):
        lista.append(file.readline().strip("\n"))

    file.close()
    return lista

def agregarCeros(numero):
    resultado = ""
    largo = 4 - len(numero)
    for i in range(largo):
        resultado += "0"
    resultado += numero
    return resultado

def crear_insercion_jugadores(cantidad, codigo_equipo, pasaporte):
    lista_nombres = cargar_lista("nombres.txt")
    lista_apellidos1 = cargar_lista("apellidos.txt")
    lista_apellidos2 = cargar_lista("apellidos.txt")

    largoNombres = len(lista_nombres)
    largoApellidos = len(lista_apellidos1)
    

    lista_puestos = ["PORTERO","DEFENSA","MEDIOCAMPISTA","DELANTERO"]

    for i in range(cantidad):
        puesto = ""
        if(i == 0):
            puesto = lista_puestos[0]
        elif(0 < i and i < 5):
            puesto = lista_puestos[1]
        elif(4 < i and i < 8):
            puesto = lista_puestos[2]
        elif(7 < i and i < 11):
            puesto = lista_puestos[3]
        else:
            puesto = lista_puestos[r.randrange(len(lista_puestos))]
        
        consulta = "INSERT INTO jugador VALUES("+ "1"+agregarCeros(str(i+pasaporte))+", '"+lista_nombres[r.randrange(largoNombres)]+"', '"+lista_apellidos1[r.randrange(largoApellidos)]+"', '"+lista_apellidos2[r.randrange(largoApellidos)]+"', '"+str(r.randrange(1963,1998))+"-"+str(r.randrange(1,13))+"-"+str(r.randrange(1,29))+"',"+ str(i+1)+", '"+puesto+"','"+str(codigo_equipo)+"');"
        print(consulta)
        

crear_insercion_jugadores(23,2,0)


