package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.Collection;

public class Bonificaciones {
    private final MayorRutaComercial determinadorMayorRutaComercial;
    private final MayorEjercito determinadorMayorEjercito;

    private Jugador jugadorConMayorRutaComercial = null;
    private Jugador jugadorConMayorEjercito = null;

    public Bonificaciones(Collection<Jugador> jugadores, Tablero tablero) {
        this.determinadorMayorRutaComercial = new MayorRutaComercial(tablero, jugadores);
        this.determinadorMayorEjercito = new MayorEjercito(jugadores);
    }

    public void actualizarBonificaciones() {
        jugadorConMayorRutaComercial = determinadorMayorRutaComercial.determinar();
        jugadorConMayorEjercito = determinadorMayorEjercito.determinar();
    }

    public int puntajeDe(Jugador jugador) {
        int puntos = 0;
        if (jugador == jugadorConMayorRutaComercial) puntos += 2;
        if (jugador == jugadorConMayorEjercito) puntos += 2;
        return puntos;
    }
}
