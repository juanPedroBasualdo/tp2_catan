package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public abstract class Jugable extends CartaDesarrollo {

    /*-- Constructor --*/
    public Jugable(Jugador propietario, int turnoObtenido) {
        super(propietario, turnoObtenido);
    }

    /*-- Metodo de comportamiento --*/
    public void jugar(int numeroTurno) {
        if(esJugable(numeroTurno)) {
            this.efecto();
        }
    }

    protected abstract void efecto();
}
