package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.Collection;

public class MayorEjercito {
    private final Collection<Jugador> jugadores;

    public MayorEjercito(Collection<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Jugador determinar() {
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
