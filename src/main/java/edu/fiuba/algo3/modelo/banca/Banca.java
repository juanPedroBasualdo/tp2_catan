package edu.fiuba.algo3.modelo.banca;

import edu.fiuba.algo3.modelo.banca.mazo.Mazo;
import edu.fiuba.algo3.modelo.cartasDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.Arrays;
import java.util.List;

public class Banca {

    /*-- Atributos --*/

    private final Mazo mazoDeDesarrollo;
    private final List<Recurso> precioCartasDesarrollo = Arrays.asList(
        Recurso.MINERAL, Recurso.LANA, Recurso.CEREAL
    );

    /*-- Constructor --*/

    public Banca() {
        this.mazoDeDesarrollo = new Mazo();
    }

    /*-- Auxiliar --*/

    public static void extraerRecursos(Jugador jugador, List<Recurso> listaDeRecursos) {
        if (jugador.tieneRecursos(listaDeRecursos)) {
            jugador.eliminarRecursos(listaDeRecursos);
        } else {
            throw new RecursosInsuficientesException("El jugador no tiene suficientes recursos para hacer esta operacion.");
        }
    }

    /*-- Metodos de intercambio --*/

    public void intercambioDeTasaEstandar(Jugador jugador, Recurso recursoEntrante, Recurso recursoSaliente) {
        List<Recurso> listaRequiriente = Arrays.asList(recursoEntrante, recursoEntrante,recursoEntrante,recursoEntrante);
        Banca.extraerRecursos(jugador, listaRequiriente);
        jugador.agregarRecurso(recursoSaliente);
    }

    public static void intercambioDeTasaEstandarEstatico(Jugador jugador, Recurso recursoEntrante, Recurso recursoSaliente) {
        List<Recurso> listaRequiriente = Arrays.asList(recursoEntrante, recursoEntrante,recursoEntrante,recursoEntrante);
        Banca.extraerRecursos(jugador, listaRequiriente);
        jugador.agregarRecurso(recursoSaliente);
    }

    /**
     * Metodo para intercambio entre dos jugadores una vez aceptada la oferta y teniendo ambas listas de recursos
     * @param jugador1 El jugador que ofrece listaRecursosJ1 a jugador2 por listaRecursosJ2
     * @param jugador2 El jugador que acepta listaRecursosJ1 por listaRecursosJ2
     * @param listaRecursosJ1 Lista de recursos oferta
     * @param listaRecursosJ2 Lista de recursos demanda
     */
    public void intercambioEntreJugadores(Jugador jugador1, Jugador jugador2, List<Recurso> listaRecursosJ1, List<Recurso> listaRecursosJ2) {
        if(!(jugador1.tieneRecursos(listaRecursosJ1)) || !(jugador2.tieneRecursos(listaRecursosJ2))) {
            throw new RecursosInsuficientesException("No se tienen recursos necesarios para el intercambio.");
        }

        jugador1.eliminarRecursos(listaRecursosJ1);
        jugador1.agregarRecursos(listaRecursosJ2);

        jugador2.eliminarRecursos(listaRecursosJ2);
        jugador2.agregarRecursos(listaRecursosJ1);
    }

    /*-- Metodo de mazo --*/

    public void venderCartaDesarrollo(Jugador jugador, int numeroTurno) {
        Banca.extraerRecursos(jugador, this.precioCartasDesarrollo);
        CartaDesarrollo carta = this.mazoDeDesarrollo.robarCartaDesarrollo(jugador, numeroTurno);
        jugador.agregarCarta(carta);
    }


}
