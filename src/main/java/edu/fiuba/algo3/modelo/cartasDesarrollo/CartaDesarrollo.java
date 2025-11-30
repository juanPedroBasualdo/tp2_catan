package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public abstract class CartaDesarrollo {

    /*-- Atributos --*/

    protected final Jugador propietario;
    private final int turnoObtenido;

    /*-- Constructores --*/

    public CartaDesarrollo(Jugador propietario, int turnoObtenido) {
        this.propietario = propietario;
        this.turnoObtenido = turnoObtenido;
    }

    /*-- Metodo de comportamiento --*/

    public boolean esJugable(int numeroTurno) {
        return !(numeroTurno == this.turnoObtenido);
    }
}
