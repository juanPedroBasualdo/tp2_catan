package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public class PuntoDeVictoria extends CartaDesarrollo implements OtorgaPuntajes {

    /*-- Metodo de comportamiento --*/
    public PuntoDeVictoria(Jugador propietario, int turnoObtenido) {
        super(propietario, turnoObtenido);
    }

    @Override
    public boolean esJugable() {
        return false;
    }

    @Override
    public int puntajeCarta() {
        return 1;
    }


}
