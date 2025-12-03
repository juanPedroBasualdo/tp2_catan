package edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.Arrays;
import java.util.List;

public class Carretera extends Construccion {

    private final List<Recurso> precioConstruccion = Arrays.asList(
            Recurso.MADERA,
            Recurso.ARCILLA
    );

    public Carretera(Jugador jugador) {
        super(jugador);
    }

    @Override
    public List<Recurso> obtenerPrecioPieza() {
        return precioConstruccion;
    }

    @Override
    public int puntosDeVictoria() {
        return 0;
    }

}
