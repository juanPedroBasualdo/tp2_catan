package edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

public class NoProductor extends Productor{

    protected NoProductor(Jugador propietario) {
        super(propietario);
    }

    @Override
    public void producir(Recurso recurso) {}
}
