package edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.List;

public abstract class Productor extends Construccion{

    protected Productor(Jugador propietario) {
        super(propietario);
    }

    @Override
    public List<Recurso> obtenerPrecioPieza() {
        return List.of();
    }

    @Override
    public int puntosDeVictoria() {
        return 0;
    }

    public abstract void producir(Recurso recurso);
}
