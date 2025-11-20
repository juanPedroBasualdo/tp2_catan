package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.Jugador;

public class Pieza {

    private final Jugador dueño;
    private final PiezaTipo tipo;

    Pieza(Jugador dueño, PiezaTipo tipo) {
        this.dueño = dueño;
        this.tipo = tipo;
    }

    public static Pieza crearPieza(PiezaTipo tipo, Jugador dueño) {
        return new Pieza(dueño, tipo);
    }


    public PiezaTipo obtenerTipo() {
        return tipo;
    }

    public Jugador getPropietario() {
        return dueño;
    }
}
