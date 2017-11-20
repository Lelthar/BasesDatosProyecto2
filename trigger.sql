

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


    CREATE INDEX TABLA_JUGADORES ON JUGADOR (NUMERO_PASAPORTE ASC, CODIGO_EQUIPO ASC);

    CREATE INDEX TABLA_PARTIDOS ON PARTIDO (NUMERO_PARTIDO ASC);
