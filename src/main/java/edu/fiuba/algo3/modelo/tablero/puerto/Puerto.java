package edu.fiuba.algo3.modelo.tablero.puerto;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import java.util.List;

public interface Puerto {
    void intercambiar(Jugador jugador, Recurso recursoACambiar, Recurso recursoARecibir);
}
