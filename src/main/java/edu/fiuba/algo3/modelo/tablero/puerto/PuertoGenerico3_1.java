package edu.fiuba.algo3.modelo.tablero.puerto;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.coordenada.Coordenada;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuertoGenerico3_1 implements Puerto {

    List<Coordenada> listaCoordenadasPuertos;

    public PuertoGenerico3_1() {
        this.listaCoordenadasPuertos = new ArrayList<Coordenada>();
        
        listaCoordenadasPuertos.add(new Coordenada(0, 0, 0));
        listaCoordenadasPuertos.add(new Coordenada(0, 0, 5));

        listaCoordenadasPuertos.add(new Coordenada(2, 4, 1));
        listaCoordenadasPuertos.add(new Coordenada(2, 4, 2));

        listaCoordenadasPuertos.add(new Coordenada(4, 0, 3));
        listaCoordenadasPuertos.add(new Coordenada(4, 0, 4));

        listaCoordenadasPuertos.add(new Coordenada(4, 1, 2));
        listaCoordenadasPuertos.add(new Coordenada(4, 1, 3));

    }

    // TODO se supone que el puerto tiene que estar en una arista para que Jugador la pueda usar, discutir en llamada

    @Override
    public void intercambiar(Jugador jugador, Recurso recursoACambiar, Recurso recursoARecibir) {

        List<Recurso> listaRequiriente = Arrays.asList(recursoACambiar, recursoACambiar ,recursoACambiar);

        if (!jugador.tieneRecursos(listaRequiriente)) {
            throw new IllegalStateException("El jugador no tiene los recursos necesarios para intercambiar 3:1");
        }

        jugador.eliminarRecursos(listaRequiriente);
        jugador.agregarRecurso(recursoARecibir);

    }

    public List<Coordenada> obtenerCoordenadaPuertoDe() {
        return listaCoordenadasPuertos;
    }
}
