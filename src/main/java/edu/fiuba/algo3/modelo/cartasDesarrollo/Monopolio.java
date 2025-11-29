package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.banca.Banca;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Monopolio implements CartaDesarrollo {
    private final Collection<Jugador> jugadores;
    private List<Recurso> recursosExtraidos;

    public Monopolio(Collection<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.recursosExtraidos = new ArrayList<>();
    }

    @Override
    public boolean esJugable() {return true; }

    @Override
    public void jugar(Juego juego, Jugador jugador, Recurso recursoElegido) {
        recursosExtraidos.clear();

        for (Jugador j : jugadores) {
            if (j == jugador) continue;
            List<Recurso> delJugador = j.extraerTotalidadDe(recursoElegido);
            recursosExtraidos.addAll(delJugador);
        }

        for (Recurso r : recursosExtraidos) {
            jugador.agregarRecurso(r);
        }
    }

    @Override
    public int puntajeCarta() { return 0; }

    @Override
    public void jugar(Juego juego, Jugador jugador) { }
}
