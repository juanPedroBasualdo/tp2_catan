package edu.fiuba.algo3.modelo.tablero.puerto;

import edu.fiuba.algo3.modelo.excepciones.NoTieneRecursosParaIntercambioException;
import edu.fiuba.algo3.modelo.excepciones.PuertoNoAdyacenteException;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Vertice;

import java.util.Arrays;
import java.util.List;

public class PuertoGenerico3_1 implements Puerto {

    /*-- Atributos --*/
    List<Vertice> verticesDePuerto;

    /*-- Constructores --*/
    public PuertoGenerico3_1(List<Vertice> verticesDePuerto) {
        this.verticesDePuerto = verticesDePuerto;
    }

    /*-- Metodo de intercambio --*/
    @Override
    public void intercambiar(Jugador jugador, Recurso recursoACambiar, Recurso recursoARecibir) {
        if(puedeComerciar(jugador)){
            List<Recurso> listaRequiriente = Arrays.asList(recursoACambiar, recursoACambiar ,recursoACambiar);

            if (!jugador.tieneRecursos(listaRequiriente)) {
                throw new NoTieneRecursosParaIntercambioException("El jugador no tiene los recursos necesarios para intercambiar 3:1");
            }

            jugador.eliminarRecursos(listaRequiriente);
            jugador.agregarRecurso(recursoARecibir);
        } else {
            throw new PuertoNoAdyacenteException("El jugador no esta adyacente a un puerto.");
        }
    }

    /*-- Metodo de Verificacion de intercambio valido --*/
    public boolean puedeComerciar(Jugador jugador) {
        for(Vertice v : verticesDePuerto) {
            if(v.esPropietario(jugador)){
                return true;
            }
        }
        return false;
    }

}
