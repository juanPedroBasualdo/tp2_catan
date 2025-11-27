package edu.fiuba.algo3.modelo.banca;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.PiezaTipo;
import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Banca {

    private final List<Recurso> listaDeRecursosDisponibles;

    public Banca() {
        this.listaDeRecursosDisponibles = generarRecursosIniciales();
    }

    public static void extraerRecursos(Jugador jugador, List<Recurso> listaDeRecursos) {
        if (jugador.tieneRecursos(listaDeRecursos)) {
            jugador.eliminarRecursos(listaDeRecursos);
        }
    }

    public void intercambioDeTasaEstandar(Jugador jugador, Recurso recursoEntrante, Recurso recursoSaliente) {
        List<Recurso> listaRequiriente = Arrays.asList(recursoEntrante, recursoEntrante,recursoEntrante,recursoEntrante);
        if (jugador.tieneRecursos(listaRequiriente)){
             jugador.eliminarRecursos(listaRequiriente);
             jugador.agregarRecurso(recursoSaliente);
        }
    }

    private List<Recurso> generarRecursosIniciales() {
        List<Recurso> listaDeRecursosDisponibles = new ArrayList<>();
        Recurso[] tiposDeRecursos = Recurso.values();
        for(Recurso recurso : tiposDeRecursos){
            for (int i = 0; i < 5; i++) {
                listaDeRecursosDisponibles.add(recurso);
            }
        }
        return listaDeRecursosDisponibles;
    }


}
