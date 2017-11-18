
CREATE TABLE confederacion (
    codigo_confederacion CHAR(10) CHECK (codigo_confederacion = 'CONCACAF' OR 
                                         codigo_confederacion = 'UEFA' OR
                                         codigo_confederacion = 'CONMEBOL' OR
                                         codigo_confederacion = 'CAF' OR
                                         codigo_confederacion = 'AFC' OR
                                         codigo_confederacion = 'OFC') NOT NULL,
    nombre_confederacion CHAR(60) CHECK (nombre_confederacion = 'CONFEDERACIÓN CENTROAMERICANA Y DEL CARIBE DE FÚTBOL' OR
                                         nombre_confederacion = 'UNIÓN EUROPEA DE FÚTBOL ASOCIADO' OR
                                         nombre_confederacion = 'CONFEDERACIÓN SUDAMERICANA DE FÚTBOL' OR
                                         nombre_confederacion = 'CONFEDERACIÓN DE FÚTBOL DE ÁFRICA' OR
                                         nombre_confederacion = 'CONFEDERACIÓN DE FÚTBOL DE ASIA' OR
                                         nombre_confederacion = 'CONFEDERACIÓN DE FÚTBOL DE OCEANÍA') NOT NULL,
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
    /* UNA CONFEDERACION TIENE N EQUIPOS AFILIADOS => RELACIÓN ESTÁ_AFILIADO_A */
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
    /* UN EQUIPO TIENE N JUGADORES => RELACIÓN TIENE */
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
    /* UN EQUIPO TIENE UN ENTRENADOR => RELACIÓN ES_ENTRENADO_POR */
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
    tipo_asistente CHAR(20) CHECK (tipo_asistente = 'TÉCNICO' OR
                                   tipo_asistente = 'PREPARADOR FÍSICO' OR
                                   tipo_asistente = 'MÉDICO' OR
                                   tipo_asistente = 'PSICÓLOGO' OR
                                   tipo_asistente = 'NUTRICIONISTA' OR
                                   tipo_asistente = 'ADMINISTRATIVO' OR
                                   tipo_asistente = 'DELEGADO') NOT NULL,
    numero_entrenador INTEGER NOT NULL,
    PRIMARY KEY (numero_pasaporte),
    /* UN ENTRENADOR TIENE N ASISTENTES => RELACIÓN ES_AYUDADO_POR */
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
    /* UN EQUPO TIENE N FEDERATIVOS => RELACIÓN ES_DIRIGIDO_POR */
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
/* SI EN EL PARTIDO SE TIRARON PENALE, VA A EXISTIR UNA TABLA DE PENALES CON EL NÚMERO DE PARTIDO RELACIONADO */
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
/* UN PARTIDO ES SUPERVISADO POR N ÁRBITROS */
CREATE TABLE arbitros_por_partido (
	numero_partido INTEGER NOT NULL,
	numero_pasaporte_arbitro INTEGER NOT NULL,
	rol CHAR(20) CHECK (rol = 'ARBITRO CENTRAL' OR
	                    rol = 'LÍNEA' OR
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
/* LAS TABLAS A CONTINUACIÓN CONTIENEN LAS PLANTILLAS Y LOS CAMBIOS QUE UN EQUIPO REGISTRA EN UN DETERMINADO PARTIDO.
 * LAS TABLAS SON SIMILARES, CONTIENEN CASI LOS MISMOS ATRIBUTOS.
 * SE REGISTRA EL NÚMERO DEL PARTIDO, Y EL PASAPORTE DEL JUGADOR QUE REPRESENTA A ESE EQUIPO COMO TITULAR.
 * EN LA TABLA DE CAMBIOS SE REGISTRA EL MINUTO DEL CAMBIO DEL JUGADOR.
 * PARA LOS CAPITANES SE MANEJA UNA TABLA APARTE QUE CONTIENE A LOS CAPITANES QUE JUEGAN EN UN PARTIDO, ESTA TABLA SE CREA PORQUE EN UN PARTIDO
 * PUEDE HABER MÁS DE UN CAPITÁN.
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

CREATE TABLE cuerpo_tecnico_equipo_local (				/* CUERPO_TÉCNICO_EQUIPO_VISITA */
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
/* LAS TABLAS A CONTINUACIÓN CONTIENEN LAS PLANTILLAS Y LOS CAMBIOS QUE UN EQUIPO REGISTRA EN UN DETERMINADO PARTIDO.
 * LAS TABLAS SON SIMILARES, CONTIENEN CASI LOS MISMOS ATRIBUTOS.
 * SE REGISTRA EL NÚMERO DEL PARTIDO, Y EL PASAPORTE DEL JUGADOR QUE REPRESENTA A ESE EQUIPO COMO TITULAR.
 * EN LA TABLA DE CAMBIOS SE REGISTRA EL MINUTO DEL CAMBIO DEL JUGADOR.
 * PARA LOS CAPITANES SE MANEJA UNA TABLA APARTE QUE CONTIENE A LOS CAPITANES QUE JUEGAN EN UN PARTIDO, ESTA TABLA SE CREA PORQUE EN UN PARTIDO
 * PUEDE HABER MÁS DE UN CAPITÁN.
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

CREATE TABLE cuerpo_tecnico_equipo_visita (				/* CUERPO_TÉCNICO_EQUIPO_VISITA */
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
	numero_pasaporte_jugador INTEGER NOT NULL,
	codigo_equipo CHAR(3) NOT NULL,
	minuto_cambio INTEGER NOT NULL,
	PRIMARY KEY (numero_partido, numero_pasaporte_jugador),
	FOREIGN KEY (numero_partido) REFERENCES partido,
	FOREIGN KEY (codigo_equipo) REFERENCES equipo,
	FOREIGN KEY (numero_pasaporte_jugador) REFERENCES jugador
)



/**************************************************************************************************************************************************/
/***** GOLES Y TARJETAS ***************************************************************************************************************************/
/**************************************************************************************************************************************************/

/* EN EL MODELO SE MANEJAN LOS GOLES GLOBALMENTE, ESTA TABLA CONTIENE TODOS LOS GOLES REGISTRADOS EN EL MUNDIAL.
 * SE REGISTRA EL PARTIDO EN EL QUE FUE REGISTRADO EL GOL, EL JUGADOR QUE LO ANOTÓ Y EL MINUTO EN EL QUE SE DIÓ.
 * EL MINUTO FORMA PARTE DE LA LLAVE PRIMARIA PORQUE UN JUGADOR PUEDE ANOTAR MÁS DE UN GOL EN UN PARTIDO, ESTO PARA DIFERENCIARLOS.
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
 * SE REGISTRA EL PARTIDO EN EL QUE FUE REGISTRADA LA TARJETA, EL JUGADOR QUE LA OBTUVO Y EL MINUTO EN EL QUE SE DIÓ, ADEMÁS DEL TIPO.
 * EL MINUTO FORMA PARTE DE LA LLAVE PRIMARIA PORQUE UN JUGADOR PUEDE RECIBIR MÁS DE UNA TARJETA EN UN PARTIDO, ESTO PARA DIFERENCIARLOS.
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
