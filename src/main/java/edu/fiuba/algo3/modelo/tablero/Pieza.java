package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.Jugador;

public class Pieza {

    private Jugador propietario;
    private PiezaTipo tipo;
    private int puntaje;

    Pieza(Jugador propietario) {}

    public Pieza(PiezaTipo tipo) {
        this.tipo = tipo;
    }

    public static Pieza crearPieza(PiezaTipo tipo, Jugador propietario) {
        return new Pieza(tipo);
    }

    public boolean valido() {
        return this.tipo != PiezaTipo.INVALIDO;
    }
}
