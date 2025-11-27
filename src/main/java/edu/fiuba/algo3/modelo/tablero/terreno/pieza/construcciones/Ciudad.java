package edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.Pieza;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.PiezaTipo;

import java.util.Arrays;

public class Ciudad extends Pieza {

    public Ciudad(Jugador jugador) {
        super(PiezaTipo.CIUDAD,jugador);
        this.puntajeVictoria = 2;
        this.precioConstruccion = Arrays.asList(
                Recurso.MINERAL,
                Recurso.MINERAL,
                Recurso.MINERAL,
                Recurso.CEREAL,
                Recurso.CEREAL
        );
    }
}
