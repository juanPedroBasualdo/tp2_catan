package edu.fiuba.algo3.modelo.tablero.puerto;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import java.util.List;

public interface Puerto {
    void intercambiar(Jugador jugador, List<Recurso> recursosAEntregar, Recurso recursoARecibir);
}
