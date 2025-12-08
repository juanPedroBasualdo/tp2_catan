package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Arista;

import java.util.Collection;
import java.util.List;

public class Bonificaciones {

    /*-- Atributos --*/

    private final Collection<Jugador> jugadoresEnPartida;
    private final MayorRutaComercial determinadorMayorRutaComercial;
    private final MayorEjercito determinadorMayorEjercito;
    private Jugador jugadorConMayorRutaComercial = null;
    private Jugador jugadorConMayorEjercito = null;

    /*-- Constructores --*/

    public Bonificaciones(Collection<Jugador> jugadores) {
        this.jugadoresEnPartida = jugadores;
        this.determinadorMayorRutaComercial = new MayorRutaComercial();
        this.determinadorMayorEjercito = new MayorEjercito();
    }

    /*-- Metodos de comportamiento --*/

    public void actualizarBonificaciones(List<Arista> aristas) {
        jugadorConMayorRutaComercial = determinadorMayorRutaComercial.determinar(jugadoresEnPartida, aristas);
        jugadorConMayorEjercito = determinadorMayorEjercito.determinar(this.jugadoresEnPartida);
    }

    public int puntajeDe(Jugador jugador) {
        int puntos = 0;
        if (jugador == jugadorConMayorRutaComercial) puntos += 2;
        if (jugador == jugadorConMayorEjercito) puntos += 2;
        return puntos;
    }
}
