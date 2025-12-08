package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.Collection;

public class MayorEjercito {

    public Jugador determinar(Collection<Jugador> jugadores) {
        Jugador candidato = null;
        int actualMayorCantidadCaballerosJugados = 0;
        for (Jugador jugador : jugadores) {
            int caballerosJugados = jugador.getCaballerosJugados();
            if (caballerosJugados > actualMayorCantidadCaballerosJugados) {
                actualMayorCantidadCaballerosJugados = caballerosJugados;
                candidato = jugador;
            }
        }
        return actualMayorCantidadCaballerosJugados >= 3 ? candidato : null;
    }
}
