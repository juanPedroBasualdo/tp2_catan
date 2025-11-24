package edu.fiuba.algo3.modelo.tablero.terreno.pieza;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public class Pieza {

    private Jugador propietario;
    private PiezaTipo tipo;
    private int puntaje;

    Pieza(Jugador propietario) {}

    public Pieza(PiezaTipo tipo, Jugador jugador) {
        this.tipo = tipo;
        this.propietario = jugador;
    }

    public static Pieza crearPieza(PiezaTipo tipo, Jugador propietario) {
        return new Pieza(tipo, propietario);
    }

    public boolean validarMejoraPoblado(Jugador jugador1) {
        return tipo == (PiezaTipo.POBLADO) && propietario == (jugador1);
    }
    public boolean valido() {
        return this.tipo != PiezaTipo.INVALIDO;
    }
}
