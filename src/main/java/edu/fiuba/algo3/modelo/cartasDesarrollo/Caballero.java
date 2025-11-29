package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public class Caballero implements CartaDesarrollo {
    @Override
    public boolean esJugable() { return true; }

    @Override
    public void jugar(Juego juego, Jugador jugador) {
        jugador.incrementarCaballerosJugados();
        // juego.moverLadron(); TODO
    }

    @Override
    public int puntajeCarta() { return 0; }
}
