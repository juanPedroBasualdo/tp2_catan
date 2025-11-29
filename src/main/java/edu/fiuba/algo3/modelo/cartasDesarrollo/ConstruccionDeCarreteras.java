package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.coordenada.Coordenada;

public class ConstruccionDeCarreteras implements CartaDesarrollo {
    @Override
    public boolean esJugable() {return true; }

    @Override
    public int puntajeCarta() { return 0; }

    @Override
    public void jugar(Juego juego, Jugador jugador) {
        Coordenada coordenada1, coordenada2;
        // juego.construirCamino(coordenada1);
        // juego.construirCamino(coordenada2);
    }
}
