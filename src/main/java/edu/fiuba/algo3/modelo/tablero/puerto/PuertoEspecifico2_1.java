package edu.fiuba.algo3.modelo.tablero.puerto;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import java.util.List;

public class PuertoEspecifico2_1 implements Puerto {
    private final Recurso tipoRecurso;
    public PuertoEspecifico2_1(Recurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    @Override
    public void intercambiar(Jugador jugador, List<Recurso> recursosAEntregar, Recurso recursoARecibir) {
        if (recursosAEntregar.size() != 2 || !recursosAEntregar.stream().allMatch(r -> r == tipoRecurso)) {
            throw new IllegalArgumentException("Para intercambiar con puerto 2:1 entregar 2 recursos del tipo " + tipoRecurso);
        }
        jugador.eliminarRecursos(recursosAEntregar);
        jugador.agregarRecurso(recursoARecibir);
    }
}
