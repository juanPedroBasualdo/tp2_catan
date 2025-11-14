package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.Jugador;

public class Pieza {

    Pieza(Jugador dueño) {}

    public static Pieza crearPieza(PiezaTipo tipo, Jugador dueño) {
        return new Pieza(dueño);
    }
}
