package edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;


import java.util.Arrays;
import java.util.List;

public class Ciudad extends Productor {

    private final List<Recurso> precioConstruccion = Arrays.asList(
    Recurso.MINERAL,
    Recurso.MINERAL,
    Recurso.MINERAL,
    Recurso.CEREAL,
    Recurso.CEREAL
        );

    public Ciudad(Jugador jugador) {
        super(jugador);
    }

    @Override
    public List<Recurso> obtenerPrecioPieza() {
        return precioConstruccion;
    }

    @Override
    public int puntosDeVictoria() {
        return 2;
    }

    @Override
    public void producir(Recurso recurso) {
        List<Recurso> recursosProducidos = Arrays.asList(recurso, recurso);
        this.getPropietario().agregarRecursos(recursosProducidos);
    }

}
