package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.coordenada.Coordenada;

public class Caballero extends Jugable {

    Tablero tablero;
    Coordenada coordenada;

    /*-- Constructor --*/

    public Caballero(Jugador propietario, int turnoObtenido) {
        super(propietario, turnoObtenido);
    }

    public Caballero(Jugador propietario, Tablero tablero) {
        this(propietario, 1);
        this.tablero = tablero;
    }

    /*-- Metodo de  comportamiento --*/

    @Override
    protected void efecto() {
        this.tablero.moverLadron(coordenada);
    }
}
