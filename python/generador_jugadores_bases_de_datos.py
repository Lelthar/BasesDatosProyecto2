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
    jugadores_file = open("insercion_jugadores.txt",'a')

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
        
        consulta = "INSERT INTO jugador VALUES("+ "1"+agregarCeros(str(i+pasaporte))+", '"+lista_nombres[r.randrange(largoNombres)]+"', '"+lista_apellidos1[r.randrange(largoApellidos)]+"', '"+lista_apellidos2[r.randrange(largoApellidos)]+"', '"+str(r.randrange(1983,1998))+"-"+str(r.randrange(1,13))+"-"+str(r.randrange(1,29))+"',"+ str(i+1)+", '"+puesto+"','"+str(codigo_equipo)+"');"
        jugadores_file.write(consulta+"\n")
        print(consulta)
        
    jugadores_file.close()



#INSERT INTO confederacion VALUES(pasaporte, 'nombre', 'apellido1', 'apellido2', 'fechaNacimiento', 'pais_nacimiento', 'fecha_inicio', 'equipo_numerod');

def crear_insercion_entrenadores(codigo_equipo, pasaporte):
    entrenadores_file = open("insercion_entrenadores.txt",'a')

    lista_nombres = cargar_lista("nombres.txt")
    lista_apellidos1 = cargar_lista("apellidos.txt")
    lista_apellidos2 = cargar_lista("apellidos.txt")
    lista_paises = cargar_lista("paises.txt")

    largoNombres = len(lista_nombres)
    largoApellidos = len(lista_apellidos1)
    largoPaises = len(lista_paises)

    consulta = "INSERT INTO entrenador VALUES("+ "2"+agregarCeros(str(pasaporte))+", '"+lista_nombres[r.randrange(largoNombres)]+"', '"+lista_apellidos1[r.randrange(largoApellidos)]+"', '"+lista_apellidos2[r.randrange(largoApellidos)]+"', '"+str(r.randrange(1963,1983))+"-"+str(r.randrange(1,13))+"-"+str(r.randrange(1,29))+", '"+lista_paises[r.randrange(largoPaises)]+"','"+str(r.randrange(2008,2018))+"-"+str(r.randrange(1,13))+"-"+str(r.randrange(1,29))+"','"+str(codigo_equipo)+"');"
    entrenadores_file.write(consulta+"\n")
    print(consulta)
        
    entrenadores_file.close()




def crear_insercion_asistentes(numero_entrenador, pasaporte):
    asistentes_file = open("insercion_asistentes.txt",'a')

    lista_nombres = cargar_lista("nombres.txt")
    lista_apellidos1 = cargar_lista("apellidos.txt")
    lista_apellidos2 = cargar_lista("apellidos.txt")
    lista_paises = cargar_lista("paises.txt")

    largoNombres = len(lista_nombres)
    largoApellidos = len(lista_apellidos1)
    largoPaises = len(lista_paises)
    

    lista_puestos = ["TÉCNICO","PREPARADOR FÍSICO","MÉDICO","PSICÓLOGO","NUTRICIONISTA","ADMINISTRATIVO","DELEGADO"]

    for i in range(7):
        puesto = ""
        if(i == 0):
            puesto = lista_puestos[0]
        elif(i == 1):
            puesto = lista_puestos[1]
        elif(i == 2):
            puesto = lista_puestos[2]
        elif(i == 3):
            puesto = lista_puestos[3]
        elif(i == 4):
            puesto = lista_puestos[4]
        elif(i == 5):
            puesto = lista_puestos[5]
        elif(i == 6):
            puesto = lista_puestos[6]
        
        
        consulta = "INSERT INTO asistente VALUES("+ "3"+agregarCeros(str(i+pasaporte))+", '"+lista_nombres[r.randrange(largoNombres)]+"', '"+lista_apellidos1[r.randrange(largoApellidos)]+"', '"+lista_apellidos2[r.randrange(largoApellidos)]+"', '"+str(r.randrange(1963,1983))+"-"+str(r.randrange(1,13))+"-"+str(r.randrange(1,29))+"', '"+lista_paises[r.randrange(largoPaises)]+"', '"+str(r.randrange(2008,2018))+"-"+str(r.randrange(1,13))+"-"+str(r.randrange(1,29))+"' , '"+puesto+"', "+str(numero_entrenador)+");"
        asistentes_file.write(consulta+"\n")
        print(consulta)
        
    asistentes_file.close()



def crear_insercion_federativos(numero_equipo, pasaporte):
    federativo_file = open("insercion_federativos.txt",'a')

    lista_nombres = cargar_lista("nombres.txt")
    lista_apellidos1 = cargar_lista("apellidos.txt")
    lista_apellidos2 = cargar_lista("apellidos.txt")
    lista_paises = cargar_lista("paises.txt")

    largoNombres = len(lista_nombres)
    largoApellidos = len(lista_apellidos1)
    largoPaises = len(lista_paises)
    

    lista_puestos = ["PRESIDENTE","VICEPRESIDENTE","SECRETARIO","TESORERO","FISCAL","VOCAL"]

    for i in range(6):
        puesto = ""
        if(i == 0):
            puesto = lista_puestos[0]
        elif(i == 1):
            puesto = lista_puestos[1]
        elif(i == 2):
            puesto = lista_puestos[2]
        elif(i == 3):
            puesto = lista_puestos[3]
        elif(i == 4):
            puesto = lista_puestos[4]
        elif(i == 5):
            puesto = lista_puestos[5]
        
        
        consulta = "INSERT INTO federativo VALUES("+ "4"+agregarCeros(str(i+pasaporte))+", '"+lista_nombres[r.randrange(largoNombres)]+"', '"+lista_apellidos1[r.randrange(largoApellidos)]+"', '"+lista_apellidos2[r.randrange(largoApellidos)]+"', '"+str(r.randrange(1963,1983))+"-"+str(r.randrange(1,13))+"-"+str(r.randrange(1,29))+"', '"+lista_paises[r.randrange(largoPaises)]+"', '"+str(r.randrange(2008,2018))+"-"+str(r.randrange(1,13))+"-"+str(r.randrange(1,29))+"' , '"+puesto+"', "+str(numero_equipo)+");"
        federativo_file.write(consulta+"\n")
        print(consulta)
        
    federativo_file.close()

def generar_equipos(numero):
    equipos_file = open("insercion_equipos.txt",'a')

    lista_confederaciones = ["CONCACAF","UEFA","CONMEBOL","CAF","AFC","OFC"]
    lista_equipos = cargar_lista("equipos.txt")

    largo_confederaciones = len(lista_confederaciones)
    largo_equipos = len(lista_equipos)

    pasaporte_jugadores = 0
    pasaporte_entrenador = 0
    pasaporte_asistente = 0
    pasaporte_federativos = 0

    #INSERT INTO equipo VALUES('codigo', 'nombre_pais', 'grupo', 'confederacion');
    for i in range(numero):
        grupo = ""
        if(0<=i and i<4):
            grupo = "A"
        elif(4<=i and i<8):
            grupo = "B"
        elif(8<=i and i<12):
            grupo = "C"
        elif(12<=i and i<16):
            grupo = "D"
        elif(16<=i and i<20):
            grupo = "E"
        elif(20<=i and i<24):
            grupo = "F"
        elif(24<=i and i<28):
            grupo = "G"
        elif(28<=i and i<32):
            grupo = "H"

        valor_aleatorio = r.randrange(largo_equipos)

        consulta = "INSERT INTO equipo VALUES('"+str(i)+"', '"+lista_equipos[valor_aleatorio]+"', '"+grupo+"', '"+lista_confederaciones[r.randrange(largo_confederaciones)]+"');"

        equipos_file.write(consulta+"\n")

        lista_equipos.pop(valor_aleatorio)
        largo_equipos -= 1
        crear_insercion_jugadores(23,i,pasaporte_jugadores)

        pasaporte_jugadores += 23

        crear_insercion_entrenadores(i,pasaporte_entrenador)

        pasaporte_entrenador += 1

        crear_insercion_asistentes(int("2"+agregarCeros(str(pasaporte_entrenador))),pasaporte_asistente)

        pasaporte_asistente += 7

        crear_insercion_federativos(i,pasaporte_federativos)

        pasaporte_federativos += 6

    equipos_file.close()

generar_equipos(2)