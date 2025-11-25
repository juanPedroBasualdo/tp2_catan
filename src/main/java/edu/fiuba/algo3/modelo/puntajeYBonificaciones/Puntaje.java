package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Vertice;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.Pieza;

public class Puntaje {
    private final Tablero tablero;
    private final Bonificaciones bonificaciones;

    public Puntaje(Tablero tablero, Bonificaciones bonificaciones) {
        this.bonificaciones = bonificaciones;
        this.tablero = tablero;
    }

    public int calcularPuntaje(Jugador jugador) {
        int puntajeJugador = 0;
        for (Vertice vertice : tablero.getVertices()) {
            Pieza pieza = vertice.obtenerPieza();
                if (pieza != null && pieza.getPropietario() == jugador) {
                    puntajeJugador += pieza.getPuntaje();
                }
        }
        puntajeJugador += jugador.puntajeCartasPV;  // Supongo que cada jugador sabe las cartas PV que tiene y puede hacer el calculo.
        puntajeJugador += bonificaciones.puntajeDe(jugador);
    }
}
