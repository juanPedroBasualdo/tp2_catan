package edu.fiuba.algo3.modelo.tablero.puerto;

import edu.fiuba.algo3.modelo.excepciones.PuertoNoAdyacenteException;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

public interface Puerto {

    /**
     * Intercambia cierta cantidad de recursos segun corresponda con un jugador adyacente al puerto.
     * @param jugador Jugador adyacente al puerto con quien intercambiar.
     * @param recursoACambiar Recurso que ofrece el jugador.
     * @param recursoARecibir Recurso a recibir el jugador.
     * @throws PuertoNoAdyacenteException Si el jugador no tiene un poblado adyacente al puerto.
     */
    void intercambiar(Jugador jugador, Recurso recursoACambiar, Recurso recursoARecibir);
}
