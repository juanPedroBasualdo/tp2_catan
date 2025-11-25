package edu.fiuba.algo3.modelo.tablero.puerto;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import java.util.List;

public class PuertoGenerico3_1 implements Puerto {
    @Override
    public void intercambiar(Jugador jugador, List<Recurso> recursosAEntregar, Recurso recursoARecibir) {
        if (recursosAEntregar.size() != 3) {
            throw new IllegalArgumentException("Puerto generico exige 3 recursos para intercambiar.");
        }
        if (!jugador.tieneRecursos(recursosAEntregar)) {
            throw new IllegalStateException("El jugador no tiene los recursos necesarios para intercambiar 3:1");
        }
        jugador.eliminarRecursos(recursosAEntregar);
        jugador.agregarRecurso(recursoARecibir);
    }
}
