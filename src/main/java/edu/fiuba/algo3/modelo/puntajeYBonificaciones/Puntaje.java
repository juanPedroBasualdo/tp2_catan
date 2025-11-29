package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class Puntaje {
    private final Tablero tablero;
    private final Bonificaciones bonificaciones;

    public Puntaje(Tablero tablero, Bonificaciones bonificaciones) {
        this.bonificaciones = bonificaciones;
        this.tablero = tablero;
    }

    public int calcularPuntaje(Jugador jugador) {

        int puntajeJugador = jugador.calcularPuntajeVictoria();
        // puntajeJugador += jugador.puntajeCartasPV;  // Supongo que cada jugador sabe las cartas PV que tiene y puede hacer el calculo.
        return puntajeJugador += bonificaciones.puntajeDe(jugador);
    }
}
