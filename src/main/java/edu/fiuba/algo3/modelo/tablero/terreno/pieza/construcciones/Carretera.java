package edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.Pieza;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.PiezaTipo;

import java.util.Arrays;

public class Carretera extends Pieza {

    public Carretera(Jugador jugador) {
        super(PiezaTipo.CARRETERA,jugador);
        this.puntajeVictoria = 0;
        this.precioConstruccion = Arrays.asList(
                Recurso.MADERA,
                Recurso.ARCILLA
        );
    }
}
