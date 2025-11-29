package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

public class Invencion implements CartaDesarrollo {
    @Override
    public boolean esJugable() {return true; }

    @Override
    public int puntajeCarta() { return 0; }

    public void jugar(Juego juego, Jugador jugador) {
        Recurso recurso1, recurso2;
        // jugador.agregarRecurso(recurso1);
        // jugador.agregarRecurso(recurso2);
    }
}
