package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class Bonificaciones {
    private final Tablero tablero;

    private int longitudMayorRutaComercial = 0;
    private Jugador jugadorConMayorRutaComercial = null;

    private int mayorCantidadCaballeros = 0;
    private Jugador jugaroConMayorEjercito = null;

    public Bonificaciones(Tablero tablero) { this.tablero = tablero; }

    public int puntajeDe(Jugador jugador) {
        int puntajeBonificaciones = 0;
        if (jugador == jugadorConMayorRutaComercial) { puntajeBonificaciones += 2; }
        if (jugador == jugaroConMayorEjercito) { puntajeBonificaciones += 2; }
        return puntajeBonificaciones;
    }

    /* Llamamos a actualizarBonificaciones cuando un jugador construye un camino, juega una carta de caballero, o cuando construye
    un poblado pues puede bloquear la mayor ruta comercial de otro jugador. */
    public void actualizarBonificaciones() {
        //actualizarMayorRutaComerial();
        //actualizarMayorEjercito();
    }
}
