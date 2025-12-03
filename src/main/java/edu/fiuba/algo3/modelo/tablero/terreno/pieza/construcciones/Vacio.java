package edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones;

import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.List;

public class Vacio extends NoProductor  {

    public Vacio() {
        super(null);
    }

    @Override
    public List<Recurso> obtenerPrecioPieza() {
        return null;
    }

}
