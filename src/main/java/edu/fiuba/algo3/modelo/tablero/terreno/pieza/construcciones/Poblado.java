package edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.Pieza;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.PiezaTipo;

import java.util.Arrays;
import java.util.List;

public class Poblado extends Pieza {

    public Poblado(Jugador jugador) {
        super(PiezaTipo.POBLADO,jugador);
        this.puntajeVictoria = 1;
        this.precioConstruccion = Arrays.asList(
                Recurso.MADERA,
                Recurso.ARCILLA,
                Recurso.LANA,
                Recurso.CEREAL
        );
    }
}
