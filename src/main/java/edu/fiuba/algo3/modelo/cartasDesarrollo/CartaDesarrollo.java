package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

public interface CartaDesarrollo {
    boolean esJugable();
    void jugar(Juego juego, Jugador jugador);
    default void jugar(Juego juego, Jugador jugador, Recurso recurso) { }   // Monopolio tiene que saber el recurso a extraer de otros jugadores
    int puntajeCarta();
}
