package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.juego.turno.Turnos;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.banca.*;

import java.util.Collection;

public class Juego {

    private Turnos turnero;
    private Tablero tablero;
    private Banca banca;

    public Juego(Collection<Jugador> listaJugadores) {
        turnero = new Turnos(listaJugadores);
        tablero = new Tablero();
    }

}
