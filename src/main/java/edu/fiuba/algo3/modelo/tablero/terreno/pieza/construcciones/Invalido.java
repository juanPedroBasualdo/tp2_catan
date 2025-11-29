package edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones;

import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.List;

public class Invalido extends Construccion{

    public Invalido() {
        super(null);
    }

    @Override
    public List<Recurso> obtenerPrecioPieza() {
        return null;
    }

    @Override
    public int puntosDeVictoria() {
        return 0;
    }
}
