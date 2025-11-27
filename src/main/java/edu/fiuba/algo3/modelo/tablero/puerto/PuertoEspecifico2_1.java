package edu.fiuba.algo3.modelo.tablero.puerto;

import edu.fiuba.algo3.modelo.excepciones.NoTieneRecursosParaIntercambioException;
import edu.fiuba.algo3.modelo.excepciones.PuertoNoAdyacenteException;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Vertice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PuertoEspecifico2_1 implements Puerto {

    private final HashMap<Recurso, List<Vertice>> verticesDePuerto;

    // TODO se supone que el puerto tiene que estar en una arista para que Jugador la pueda usar, discutir en llamada

    public PuertoEspecifico2_1(HashMap<Recurso, List<Vertice>> verticesDePuerto) {
        this.verticesDePuerto = verticesDePuerto;
    }

    @Override
    public void intercambiar(Jugador jugador, Recurso recursoACambiar, Recurso recursoARecibir) {
        if(puedeIntercambiar(jugador, recursoACambiar)) {
            List<Recurso> listaRequiriente = Arrays.asList(recursoACambiar, recursoACambiar);

            if (!jugador.tieneRecursos(listaRequiriente)) {
                throw new NoTieneRecursosParaIntercambioException("El jugador no tiene los recursos necesarios para intercambiar 2:1");
            }

            jugador.eliminarRecursos(listaRequiriente);
            jugador.agregarRecurso(recursoARecibir);
        } else {
            throw new PuertoNoAdyacenteException("El jugador no se encuentra adyacente al puerto");
        }
    }

    public boolean puedeIntercambiar(Jugador jugador, Recurso recurso) {
        List<Vertice> verticesDelIntercambio = this.obtenerVerticesPuertoDe(recurso);
        for(Vertice v : verticesDelIntercambio) {
            if(v.esPropietario(jugador)){
                return true;
            }
        }
        return false;
    }

    public List<Vertice> obtenerVerticesPuertoDe(Recurso recurso) {
        return verticesDePuerto.get(recurso);
    }

}
