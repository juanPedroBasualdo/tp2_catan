package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.Collection;

public class MayorRutaComercial {
    private final Tablero tablero;
    private final Collection<Jugador> jugadores;

    public MayorRutaComercial(Tablero tablero, Collection<Jugador> jugadores) {
        this.tablero = tablero;
        this.jugadores = jugadores;
    }

    public Jugador determinar() {
        Jugador jugadorConRutaComercialMasLarga = null;
        int maxLargo = 4;   // mínimo para ganar tarjeta (Camino más largo = 5)

        for (Jugador jugador : jugadores) {
            int largo = calcularCaminoMasLargo(jugador);
            if (largo > maxLargo) {
                maxLargo = largo;
                jugadorConRutaComercialMasLarga = jugador;
            }
        }
        return jugadorConRutaComercialMasLarga;
    }

    private int calcularCaminoMasLargo(Jugador jugador) {
        return new DFSRutaMasLarga(tablero).calcularPara(jugador);
    }
}
