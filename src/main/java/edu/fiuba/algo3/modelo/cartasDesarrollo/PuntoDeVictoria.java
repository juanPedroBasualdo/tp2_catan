package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.excepciones.CartaDePVNoSePuedeJugar;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public class PuntoDeVictoria implements CartaDesarrollo {
    @Override
    public boolean esJugable() { return false; }

    @Override
    public void jugar(Juego juego, Jugador jugador) {
        throw new CartaDePVNoSePuedeJugar("Las cartas de PV no se pueden jugar.");
    }

    @Override
    public int puntajeCarta() { return 1; }
}
