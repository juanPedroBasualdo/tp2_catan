package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public abstract class CartaDesarrollo {

    protected final Jugador propietario;
    private final int turnoObtenido;

    public CartaDesarrollo(Jugador propietario, int turnoObtenido) {
        this.propietario = propietario;
        this.turnoObtenido = turnoObtenido;
    }

    public boolean esJugable(int numeroTurno) {
        return !(numeroTurno == this.turnoObtenido);
    }
}
