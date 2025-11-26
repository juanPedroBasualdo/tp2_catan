package edu.fiuba.algo3.modelo.tablero.puerto;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.coordenada.Coordenada;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Arista;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PuertoEspecifico2_1 implements Puerto {

    private final HashMap<Recurso, List<Coordenada>> listasDeVertices;

    // TODO se supone que el puerto tiene que estar en una arista para que Jugador la pueda usar, discutir en llamada

    public PuertoEspecifico2_1() {
        this.listasDeVertices = new HashMap<Recurso, List<Coordenada>>();

        listasDeVertices.put(Recurso.CEREAL, Arrays.asList(
                new Coordenada(0, 1, 0),
                new Coordenada(0, 1, 1)
        ));

        listasDeVertices.put(Recurso.MADERA, Arrays.asList(
                new Coordenada(1, 0, 5),
                new Coordenada(1, 0, 4)
        ));

        listasDeVertices.put(Recurso.MINERAL, Arrays.asList(
                new Coordenada(1, 3, 0),
                new Coordenada(1, 3, 1)
        ));

        listasDeVertices.put(Recurso.ARCILLA, Arrays.asList(
                new Coordenada(3, 0, 5),
                new Coordenada(3, 0, 4)
        ));

        listasDeVertices.put(Recurso.LANA, Arrays.asList(
                new Coordenada(3, 3, 2),
                new Coordenada(3, 3, 3)
        ));
    }

    @Override
    public void intercambiar(Jugador jugador, Recurso recursoACambiar, Recurso recursoARecibir) {

        List<Recurso> listaRequiriente = Arrays.asList(recursoACambiar, recursoACambiar);

        if (!jugador.tieneRecursos(listaRequiriente)) {
            throw new IllegalStateException("El jugador no tiene los recursos necesarios para intercambiar 2:1");
        }

        jugador.eliminarRecursos(listaRequiriente);
        jugador.agregarRecurso(recursoARecibir);
    }

    public List<Coordenada> obtenerCoordenadaPuertoDe(Recurso recurso) {
        return listasDeVertices.get(recurso);
    }

}
