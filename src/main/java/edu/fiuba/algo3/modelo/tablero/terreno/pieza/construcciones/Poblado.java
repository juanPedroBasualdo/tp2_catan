package edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.Arrays;
import java.util.List;

public class Poblado extends Construccion {

    private final List<Recurso> precioConstruccion = Arrays.asList(
      Recurso.MADERA,
      Recurso.LANA,
      Recurso.ARCILLA,
      Recurso.CEREAL
    );

    public Poblado(Jugador propietario) {
        super(propietario);
    }

    @Override
    public List<Recurso> obtenerPrecioPieza() {
        return precioConstruccion;
    }

    @Override
    public int puntosDeVictoria() {
        return 1;
    }
}
