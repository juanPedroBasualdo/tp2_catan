package edu.fiuba.algo3.modelo.banca;

import edu.fiuba.algo3.modelo.banca.mazo.Mazo;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.Arrays;
import java.util.List;

public class Banca {

    private final Mazo mazoDeDesarrollo;
    private final List<Recurso> precioCartasDesarrollo = Arrays.asList(
        Recurso.MINERAL, Recurso.LANA, Recurso.CEREAL
    );


    public Banca() {
        this.mazoDeDesarrollo = new Mazo();
    }

    public static void extraerRecursos(Jugador jugador, List<Recurso> listaDeRecursos) {
        if (jugador.tieneRecursos(listaDeRecursos)) {
            jugador.eliminarRecursos(listaDeRecursos);
        } else {
            throw new RecursosInsuficientesException("El jugador no tiene suficientes recursos para hacer esta operacion.");
        }
    }

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

    public void venderCartaDesarrollo(Jugador jugador, int numeroTurno) {
        Banca.extraerRecursos(jugador, this.precioCartasDesarrollo);
        this.mazoDeDesarrollo.robarCartaDesarrollo(jugador, numeroTurno);
    }


}
