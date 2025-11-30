package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public class PuntoDeVictoria extends CartaDesarrollo implements OtorgaPuntajes {

    public PuntoDeVictoria(Jugador propietario, int turnoObtenido) {
        super(propietario, turnoObtenido);
    }

    @Override
    public boolean esJugable(int numeroTurno) {
        return false;
    }


    @Override
    protected void efecto() {
        /* Dejo vacio el efecto por no poder hacer herencia multiple.
         * Ya que en el caso de poder, Jugable seria una clase abstracta y no una interfaz. */
    }

    @Override
    public int puntajeCarta() {
        return 1;
    }


}
