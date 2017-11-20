
CREATE TABLE confederacion (
    codigo_confederacion CHAR(10) CHECK (codigo_confederacion = 'CONCACAF' OR 
                                         codigo_confederacion = 'UEFA' OR
                                         codigo_confederacion = 'CONMEBOL' OR
                                         codigo_confederacion = 'CAF' OR
                                         codigo_confederacion = 'AFC' OR
                                         codigo_confederacion = 'OFC') NOT NULL,
    nombre_confederacion CHAR(60) CHECK (nombre_confederacion = 'CONFEDERACI�N CENTROAMERICANA Y DEL CARIBE DE F�TBOL' OR
                                         nombre_confederacion = 'UNI�N EUROPEA DE F�TBOL ASOCIADO' OR
                                         nombre_confederacion = 'CONFEDERACI�N SUDAMERICANA DE F�TBOL' OR
                                         nombre_confederacion = 'CONFEDERACI�N DE F�TBOL DE �FRICA' OR
                                         nombre_confederacion = 'CONFEDERACI�N DE F�TBOL DE ASIA' OR
                                         nombre_confederacion = 'CONFEDERACI�N DE F�TBOL DE OCEAN�A') NOT NULL,
    PRIMARY KEY (codigo_confederacion)
)

CREATE TABLE equipo (
    codigo_equipo CHAR(3) NOT NULL,
    nombre_pais CHAR(40) NOT NULL,
    grupo_inicio_campeonato CHAR(1) CHECK (grupo_inicio_campeonato = 'A' OR
                                           grupo_inicio_campeonato = 'B' OR
                                           grupo_inicio_campeonato = 'C' OR
                                           grupo_inicio_campeonato = 'D' OR
                                           grupo_inicio_campeonato = 'E' OR
                                           grupo_inicio_campeonato = 'F' OR
                                           grupo_inicio_campeonato = 'G' OR
                                           grupo_inicio_campeonato = 'H') NOT NULL,
    afiliado_confederacion CHAR(10) NOT NULL,
    PRIMARY KEY (codigo_equipo),
    /* UNA CONFEDERACION TIENE N EQUIPOS AFILIADOS => RELACI�N EST�_AFILIADO_A */
    FOREIGN KEY (afiliado_confederacion) REFERENCES confederacion
)

CREATE TABLE jugador (
    numero_pasaporte INTEGER NOT NULL,
    nombre CHAR(15) NOT NULL,
    apellido1 CHAR(15) NOT NULL,
    apellido2 CHAR(15) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    numero_camiseta INTEGER CHECK (0 < numero_camiseta AND numero_camiseta < 100) NOT NULL,
    puesto CHAR(15) CHECK (puesto = 'PORTERO' OR 
                           puesto = 'DEFENSA' OR 
                           puesto = 'MEDIOCAMPISTA' OR 
                           puesto = 'DELANTERO') NOT NULL, 
    codigo_equipo CHAR(3) NOT NULL,
    PRIMARY KEY (numero_pasaporte),
    /* UN EQUIPO TIENE N JUGADORES => RELACI�N TIENE */
    FOREIGN KEY (codigo_equipo) REFERENCES equipo
)

CREATE TABLE entrenador (
    numero_pasaporte INTEGER NOT NULL,
    nombre CHAR(15) NOT NULL,
    apellido1 CHAR(15) NOT NULL,
    apellido2 CHAR(15) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    pais_nacimiento CHAR(20) NOT NULL,
    fecha_inicio_puesto DATE NOT NULL,
    equipo CHAR (3) NOT NULL,
    PRIMARY KEY (numero_pasaporte),
    /* UN EQUIPO TIENE UN ENTRENADOR => RELACI�N ES_ENTRENADO_POR */
    FOREIGN KEY (equipo) REFERENCES equipo
)

CREATE TABLE asistente (
    numero_pasaporte INTEGER NOT NULL,
    nombre CHAR(15) NOT NULL,
    apellido1 CHAR(15) NOT NULL,
    apellido2 CHAR(15) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    pais_nacimiento CHAR(20) NOT NULL,
    fecha_inicio_puesto DATE NOT NULL,
    tipo_asistente CHAR(20) CHECK (tipo_asistente = 'T�CNICO' OR
                                   tipo_asistente = 'PREPARADOR F�SICO' OR
                                   tipo_asistente = 'M�DICO' OR
                                   tipo_asistente = 'PSIC�LOGO' OR
                                   tipo_asistente = 'NUTRICIONISTA' OR
                                   tipo_asistente = 'ADMINISTRATIVO' OR
                                   tipo_asistente = 'DELEGADO') NOT NULL,
    numero_entrenador INTEGER NOT NULL,
    PRIMARY KEY (numero_pasaporte),
    /* UN ENTRENADOR TIENE N ASISTENTES => RELACI�N ES_AYUDADO_POR */
    FOREIGN KEY (numero_entrenador) REFERENCES entrenador
)

CREATE TABLE federativo (
    numero_pasaporte INTEGER NOT NULL,
    nombre CHAR(15) NOT NULL,
    apellido1 CHAR(15) NOT NULL,
    apellido2 CHAR(15) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    pais_nacimiento CHAR(20) NOT NULL,
    fecha_inicio_puesto DATE NOT NULL,
    puesto CHAR(20) CHECK (puesto = 'PRESIDENTE' OR
                           puesto = 'VICEPRESIDENTE' OR
                           puesto = 'SECRETARIO' OR
                           puesto = 'TESORERO' OR
                           puesto = 'FISCAL' OR
                           puesto = 'VOCAL') NOT NULL,
    codigo_equipo CHAR(3) NOT NULL,
    PRIMARY KEY (numero_pasaporte),
    /* UN EQUPO TIENE N FEDERATIVOS => RELACI�N ES_DIRIGIDO_POR */
    FOREIGN KEY (codigo_equipo) REFERENCES equipo
)

CREATE TABLE arbitro (
    numero_pasaporte INTEGER NOT NULL,
    nombre CHAR(15) NOT NULL,
    apellido1 CHAR(15) NOT NULL,
    apellido2 CHAR(15) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    pais_nacimiento CHAR(20) NOT NULL,
    fecha_inicio_arbitraje DATE NOT NULL,
    PRIMARY KEY (numero_pasaporte)
)

CREATE TABLE sede (
    nombre_sede CHAR(40) NOT NULL,
    nombre_estadio CHAR(40) NOT NULL,
    capacidad_estadio INTEGER CHECK (25000 <= capacidad_estadio AND capacidad_estadio <= 150000),
    link_estadio VARCHAR(200) NOT NULL,
    PRIMARY KEY (nombre_sede, nombre_estadio)
)

/* UN PARTIDO SE CELEBRA EN UNA SEDE Y ESTADIO */
/* UNA SEDE CELEBRA N PARTIDOS */
/* SI EN EL PARTIDO SE TIRARON PENALE, VA A EXISTIR UNA TABLA DE PENALES CON EL N�MERO DE PARTIDO RELACIONADO */
CREATE TABLE partido (
	numero_partido INTEGER NOT NULL,
	fase CHAR(20) CHECK (fase = 'FASE DE GRUPOS' OR
		                fase = 'OCTAVOS DE FINAL' OR
		                fase = 'CUARTOS DE FINAL' OR
		                fase = 'SEMI FINALES' OR
		                fase = 'TERCER LUGAR' OR
		                fase = 'FINAL') NOT NULL, 
    fecha_partido DATE NOT NULL,
    hora_partido INTEGER NOT NULL,
    cantidad_aficionados INTEGER NOT NULL,
    min_extra_primer_tiempo INTEGER NOT NULL,
    min_extra_segundo_tiempo INTEGER NOT NULL,
	nombre_sede CHAR(40) NOT NULL,
	nombre_estadio CHAR(40) NOT NULL,
	tiempos_extra CHAR(2) CHECK (tiempos_extra = 'SI' OR
	                             tiempos_extra = 'NO') NOT NULL,
    PRIMARY KEY (numero_partido),
	FOREIGN KEY (nombre_sede, nombre_estadio) REFERENCES sede(nombre_sede,nombre_estadio)
)

/* TABLA QUE CONTIENE AL CUERPO ARBITRAL POR PARTIDO */
/* UN PARTIDO ES SUPERVISADO POR N �RBITROS */
CREATE TABLE arbitros_por_partido (
	numero_partido INTEGER NOT NULL,
	numero_pasaporte_arbitro INTEGER NOT NULL,
	rol CHAR(20) CHECK (rol = 'ARBITRO CENTRAL' OR
	                    rol = 'L�NEA' OR
					    rol = 'CUARTO ARBITRO' OR
					    rol = 'QUINTO ARBITRO') NOT NULL,
	PRIMARY KEY (numero_partido, numero_pasaporte_arbitro),
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (numero_pasaporte_arbitro) REFERENCES arbitro
)

/**************************************************************************************************************************************************/
/***** PLANTILLAS_POR_PARTIDO *********************************************************************************************************************/
/**************************************************************************************************************************************************/

/*____ TABLAS RELACIONADAS A EQUIPO1 O EQUIPO LOCAL ______________________________________________________________________________________________*/
/* LAS TABLAS A CONTINUACI�N CONTIENEN LAS PLANTILLAS Y LOS CAMBIOS QUE UN EQUIPO REGISTRA EN UN DETERMINADO PARTIDO.
 * LAS TABLAS SON SIMILARES, CONTIENEN CASI LOS MISMOS ATRIBUTOS.
 * SE REGISTRA EL N�MERO DEL PARTIDO, Y EL PASAPORTE DEL JUGADOR QUE REPRESENTA A ESE EQUIPO COMO TITULAR.
 * EN LA TABLA DE CAMBIOS SE REGISTRA EL MINUTO DEL CAMBIO DEL JUGADOR.
 * PARA LOS CAPITANES SE MANEJA UNA TABLA APARTE QUE CONTIENE A LOS CAPITANES QUE JUEGAN EN UN PARTIDO, ESTA TABLA SE CREA PORQUE EN UN PARTIDO
 * PUEDE HABER M�S DE UN CAPIT�N.
 */
CREATE TABLE capitan_local (							/* CAPITANES_POR_PARTIDO_EQUIPO_LOCAL */
	numero_partido INTEGER NOT NULL,
	codigo_equipo CHAR(3) NOT NULL,
	numero_pasaporte_jugador INTEGER NOT NULL,
	minuto_inicio_capitan INTEGER NOT NULL,
	minuto_fin_capitan INTEGER NOT NULL,
	PRIMARY KEY (numero_partido, numero_pasaporte_jugador, minuto_inicio_capitan),
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (numero_pasaporte_jugador) REFERENCES jugador
)

CREATE TABLE cuerpo_tecnico_equipo_local (				/* CUERPO_T�CNICO_EQUIPO_VISITA */
	numero_partido INTEGER NOT NULL,
	numero_pasaporte_tecnico INTEGER NOT NULL,
	numero_pasaporte_asistente1 INTEGER NOT NULL,
	numero_pasaporte_asistente2 INTEGER NOT NULL,
	numero_pasaporte_delegado INTEGER NOT NULL,
	PRIMARY KEY (numero_partido, numero_pasaporte_tecnico),
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (numero_pasaporte_tecnico) REFERENCES entrenador,
	FOREIGN KEY (numero_pasaporte_asistente1) REFERENCES asistente,
	FOREIGN KEY (numero_pasaporte_asistente2) REFERENCES asistente,
	FOREIGN KEY (numero_pasaporte_delegado) REFERENCES federativo
)
CREATE TABLE pantilla_titular_equipo_local (			/* TITULARES_POR_PARTIDO_EQUIPO_LOCAL */
	numero_partido INTEGER NOT NULL,
	codigo_equipo CHAR(3) NOT NULL,
	numero_pasaporte_jugador INTEGER NOT NULL,
	PRIMARY KEY (numero_partido, numero_pasaporte_jugador),
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (codigo_equipo) REFERENCES equipo,
	FOREIGN KEY (numero_pasaporte_jugador) REFERENCES jugador
)

CREATE TABLE cambios_equipo_local (						/* CAMBIOS_POR_PARTIDO_EQUIPO_LOCAL */
	numero_partido INTEGER NOT NULL,
	numero_pasaporte_jugador_entra INTEGER NOT NULL,
    	numero_pasaporte_jugador_sale INTEGER NOT NULL,
	codigo_equipo CHAR(3) NOT NULL,
	minuto_cambio INTEGER NOT NULL,
	PRIMARY KEY (numero_partido, numero_pasaporte_jugador_entra,numero_pasaporte_jugador_sale),
	FOREIGN KEY (codigo_equipo) REFERENCES equipo,
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (numero_pasaporte_jugador_entra) REFERENCES jugador,
    FOREIGN KEY (numero_pasaporte_jugador_sale) REFERENCES jugador
)

/*____ TABLAS RELACIONADAS A EQUIPO2 O EQUIPO VISITA _____________________________________________________________________________________________*/
/* LAS TABLAS A CONTINUACI�N CONTIENEN LAS PLANTILLAS Y LOS CAMBIOS QUE UN EQUIPO REGISTRA EN UN DETERMINADO PARTIDO.
 * LAS TABLAS SON SIMILARES, CONTIENEN CASI LOS MISMOS ATRIBUTOS.
 * SE REGISTRA EL N�MERO DEL PARTIDO, Y EL PASAPORTE DEL JUGADOR QUE REPRESENTA A ESE EQUIPO COMO TITULAR.
 * EN LA TABLA DE CAMBIOS SE REGISTRA EL MINUTO DEL CAMBIO DEL JUGADOR.
 * PARA LOS CAPITANES SE MANEJA UNA TABLA APARTE QUE CONTIENE A LOS CAPITANES QUE JUEGAN EN UN PARTIDO, ESTA TABLA SE CREA PORQUE EN UN PARTIDO
 * PUEDE HABER M�S DE UN CAPIT�N.
 */ 
CREATE TABLE capitan_visita (							/* CAPITANES_POR_PARTIDO_EQUIPO_VISITA */
	numero_partido INTEGER NOT NULL,
	codigo_equipo CHAR(3) NOT NULL,
	numero_pasaporte_jugador INTEGER NOT NULL,
	minuto_inicio_capitan INTEGER NOT NULL,
	minuto_fin_capitan INTEGER NOT NULL,
	PRIMARY KEY (numero_partido, numero_pasaporte_jugador, minuto_inicio_capitan),
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (numero_pasaporte_jugador) REFERENCES jugador
)

CREATE TABLE cuerpo_tecnico_equipo_visita (				/* CUERPO_T�CNICO_EQUIPO_VISITA */
	numero_partido INTEGER NOT NULL,
	numero_pasaporte_tecnico INTEGER NOT NULL,
	numero_pasaporte_asistente1 INTEGER NOT NULL,
	numero_pasaporte_asistente2 INTEGER NOT NULL,
	numero_pasaporte_delegado INTEGER NOT NULL,
	PRIMARY KEY (numero_partido, numero_pasaporte_tecnico, numero_pasaporte_asistente1, numero_pasaporte_asistente2, numero_pasaporte_delegado),
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (numero_pasaporte_tecnico) REFERENCES entrenador,
	FOREIGN KEY (numero_pasaporte_asistente1) REFERENCES asistente,
	FOREIGN KEY (numero_pasaporte_asistente2) REFERENCES asistente,
	FOREIGN KEY (numero_pasaporte_delegado) REFERENCES federativo
)

CREATE TABLE pantilla_titular_equipo_visita (			/* TITULARES_POR_PARTIDO_EQUIPO_VISITA */
	numero_partido INTEGER NOT NULL,
	codigo_equipo CHAR(3) NOT NULL,
	numero_pasaporte_jugador INTEGER NOT NULL,
	PRIMARY KEY (numero_partido, codigo_equipo, numero_pasaporte_jugador),
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (codigo_equipo) REFERENCES equipo,
	FOREIGN KEY (numero_pasaporte_jugador) REFERENCES jugador
)

CREATE TABLE cambios_equipo_visita (					/* CAMBIOS_POR_PARTIDO_EQUIPO_VISITA */
	numero_partido INTEGER NOT NULL,
	numero_pasaporte_jugador_entra INTEGER NOT NULL,
    numero_pasaporte_jugador_sale INTEGER NOT NULL,
	codigo_equipo CHAR(3) NOT NULL,
	minuto_cambio INTEGER NOT NULL,
	PRIMARY KEY (numero_partido, numero_pasaporte_jugador_entra,numero_pasaporte_jugador_sale),
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (codigo_equipo) REFERENCES equipo,
	FOREIGN KEY (numero_pasaporte_jugador_entra) REFERENCES jugador,
    FOREIGN KEY (numero_pasaporte_jugador_sale) REFERENCES jugador
)



/**************************************************************************************************************************************************/
/***** GOLES Y TARJETAS ***************************************************************************************************************************/
/**************************************************************************************************************************************************/

/* EN EL MODELO SE MANEJAN LOS GOLES GLOBALMENTE, ESTA TABLA CONTIENE TODOS LOS GOLES REGISTRADOS EN EL MUNDIAL.
 * SE REGISTRA EL PARTIDO EN EL QUE FUE REGISTRADO EL GOL, EL JUGADOR QUE LO ANOT� Y EL MINUTO EN EL QUE SE DI�.
 * EL MINUTO FORMA PARTE DE LA LLAVE PRIMARIA PORQUE UN JUGADOR PUEDE ANOTAR M�S DE UN GOL EN UN PARTIDO, ESTO PARA DIFERENCIARLOS.
 */ 
CREATE TABLE goles_por_partido (
	numero_partido INTEGER NOT NULL,
	numero_pasaporte_jugador INTEGER NOT NULL,
	codigo_equipo CHAR(3) NOT NULL,				/* SI EL JUGADOR NO PERTENECE AL EQUIPO REGISTRADO SE DEBE A AUTOGOL */
	minuto_gol INTEGER NOT NULL,
	PRIMARY KEY (numero_partido, codigo_equipo, numero_pasaporte_jugador, minuto_gol),
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (codigo_equipo) REFERENCES equipo,
	FOREIGN KEY (numero_pasaporte_jugador) REFERENCES jugador
)

/* EN EL MODELO SE MANEJAN LASTARJETAS GLOBALMENTE, ESTA TABLA CONTIENE TODAS LAS TARJETAS (AMARILLAS/ROJAS) REGISTRADAS EN EL MUNDIAL.
 * SE REGISTRA EL PARTIDO EN EL QUE FUE REGISTRADA LA TARJETA, EL JUGADOR QUE LA OBTUVO Y EL MINUTO EN EL QUE SE DI�, ADEM�S DEL TIPO.
 * EL MINUTO FORMA PARTE DE LA LLAVE PRIMARIA PORQUE UN JUGADOR PUEDE RECIBIR M�S DE UNA TARJETA EN UN PARTIDO, ESTO PARA DIFERENCIARLOS.
 */
 
CREATE TABLE tarjetas_por_partido (
	numero_partido INTEGER NOT NULL,
	numero_pasaporte_jugador INTEGER NOT NULL,
	minuto_tarjeta INTEGER NOT NULL,
	tipo_tarjeta CHAR(10) CHECK (tipo_tarjeta = 'AMARILLA' OR
	                             tipo_tarjeta = 'ROJA'),
	PRIMARY KEY (numero_partido, numero_pasaporte_jugador, minuto_tarjeta),
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (numero_pasaporte_jugador) REFERENCES jugador
)


/* SE REGISTRA LA TANDA DE PENALES, SI EXISTE, DE UN PARTIDO */
/* NO REQUIERE ACTUALIZAR LA TABLA DE GOLES POR PARTIDO, YA QUE ESTA CONTIENE EL PARTIDO Y EL JUGADOR QUE ANOTA */
CREATE TABLE penales_por_partido (
	numero_partido INTEGER NOT NULL,
	numero_penal INTEGER NOT NULL,
	jugador_que_cobra INTEGER NOT NULL,
	anotado CHAR(2) CHECK (anotado = 'SI' OR
	                       anotado = 'NO') NOT NULL,
	PRIMARY KEY (numero_partido, numero_penal, jugador_que_cobra),
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (jugador_que_cobra) REFERENCES jugador
)

--Este procedure sirve para borrar una tabla que le pongan en los parametros
create or replace procedure borrar (tabla IN varchar2) as
    table_or_view_not_exist exception;
    pragma exception_init(table_or_view_not_exist, -942);
    attempted_ddl_on_in_use_GTT exception;
    pragma exception_init(attempted_ddl_on_in_use_GTT, -14452);
begin
    execute immediate 'TRUNCATE TABLE '||tabla;
    execute immediate 'DROP TABLE '||tabla;

    exception 
        when table_or_view_not_exist then
            dbms_output.put_line('Table t did not exist at time of drop. Continuing....');

        when attempted_ddl_on_in_use_GTT then
            dbms_output.put_line('Help!!!! Someone is keeping from doing my job!');
            dbms_output.put_line('Please rescue me');
            raise;
end borrar;

-- Obtiene el link de google maps de una sede --
create procedure link_sede (input_nombre_sede VARCHAR) as
begin
    select link_estadio from sede where nombre_sede = input_nombre_sede;
end;

/* VISTAS DEL PROGRAMA (2) EN TOTAL */
CREATE VIEW vista_crud_equipo
AS (SELECT * FROM equipo);


/*TRIGGER DEL PROGRAMA*/
CREATE VIEW vista_crud_partido
AS (SELECT * FROM partido);
SELECT * FROM vista_crud_equipo;

CREATE OR REPLACE TRIGGER ELIMINAR_JUGADOR_CAMBIO_LOCAL
AFTER INSERT ON CAMBIOS_EQUIPO_LOCAL
BEGIN
    DELETE FROM pantilla_titular_equipo_local
        WHERE numero_pasaporte_jugador=new.numero_pasaporte_jugador;

    INSERT INTO pantilla_titular_equipo_local
        values(new.numero_partido,new.numero_pasaporte_jugador_entra,new.codigo_equipo);
END;


CREATE OR REPLACE TRIGGER ELIMINAR_JUGADOR_CAMBIO_VISITA
AFTER INSERT ON CAMBIOS_EQUIPO_VISITA
BEGIN
    DELETE FROM pantilla_titular_equipo_visita
        WHERE numero_pasaporte_jugador=new.numero_pasaporte_jugador;

    INSERT INTO pantilla_titular_equipo_visita
        values(new.numero_partido,new.numero_pasaporte_jugador_entra,new.codigo_equipo);
END;

/*INDICES DEL PROGRAMA*/
CREATE INDEX TABLA_JUGADORES ON JUGADOR (NUMERO_PASAPORTE ASC, CODIGO_EQUIPO ASC);

CREATE INDEX TABLA_JUGADORES ON PARTIDO (NUMERO_PARTIDO ASC, FASE DESC);


/*Este codigo sirve para borrar las tablas generadas por la consulta de tabla general de posiciones*/
exec borrar('tablafinal');
exec borrar('tablasemifinal');
exec borrar('tablatemporal1');
exec borrar('tablacuartosfinal');
exec borrar('tablatemporal2');
exec borrar('tablaoctavosfinal');
exec borrar('tablatemporal3');
exec borrar('tablafasegrupos');
exec borrar('tabla_goles_equipos1');
exec borrar('tabla_goles_equipos2');
exec borrar('tabla_goles_equipos');
exec borrar('tabla_goles_equipos_completo');

/*Funciones que una sirve para ver el total de puntos de un equipo y el otro para ver la diferencia de goles*/
CREATE FUNCTION puntos_equipo(partidos_ganados IN NUMBER,partidos_empatados IN NUMBER) 
   RETURN NUMBER 
   IS 
   BEGIN  
      RETURN (partidos_ganados*3+partidos_empatados); 
    END;
/

CREATE FUNCTION diferencia_goles(goles_favor IN NUMBER,goles_contra IN NUMBER) 
   RETURN NUMBER 
   IS 
   BEGIN  
      RETURN(goles_favor - goles_contra); 
    END;
/