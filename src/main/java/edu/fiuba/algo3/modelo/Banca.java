package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.tablero.PiezaTipo;
import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Banca {

    private final List<Recurso> listaDeRecursosDisponibles;

    public Banca() {
        this.listaDeRecursosDisponibles = generarRecursosIniciales();
    }

    // TODO
    public static int puntajeDe(PiezaTipo piezaTipo) { return 0; }

    // TODO
    public static void otorgarPuntaje(Jugador jugador, int puntaje) {
        jugador.agregarPuntaje(puntaje);
    }

    // TODO
    public static List<Recurso> precioDe(PiezaTipo tipo) { return new ArrayList<>(); }

    public static void extraerRecursos(Jugador jugador, List<Recurso> listaDeRecursos) {
        if (jugador.tieneRecursos(listaDeRecursos)) {
            jugador.eliminarRecursos(listaDeRecursos);
        }
    }

    // TODO Generalizar intercambios de puerto con una Clase Abstracta y polimorfismo
    // TODO Recursos de Banca tienen que ser modificados en vez de crear nuevos RECURSOS

    public static void intercambioDeTasaEstandar(Jugador jugador, Recurso recursoEntrante, Recurso recursoSaliente) {
        List<Recurso> listaRequiriente = Arrays.asList(recursoEntrante, recursoEntrante, recursoEntrante, recursoEntrante);
        if (jugador.tieneRecursos(listaRequiriente)){
             jugador.eliminarRecursos(listaRequiriente);
             jugador.agregarRecursos(Arrays.asList(recursoSaliente));
        }
    }

    public static void intercambioPuertoEspeficico(Jugador jugador, Recurso recursoEntrante, Recurso recursoSaliente) {
        List<Recurso> listaRequiriente = Arrays.asList(recursoEntrante, recursoEntrante);
        if (jugador.tieneRecursos(listaRequiriente)){
            jugador.eliminarRecursos(listaRequiriente);
            jugador.agregarRecursos(Arrays.asList(recursoSaliente));
        }
    }

    public static void intercambioPuertoGenerico(Jugador jugador, Recurso recursoEntrante, Recurso recursoSaliente) {
        List<Recurso> listaRequiriente = Arrays.asList(recursoEntrante, recursoEntrante, recursoEntrante);
        if (jugador.tieneRecursos(listaRequiriente)){
            jugador.eliminarRecursos(listaRequiriente);
            jugador.agregarRecursos(Arrays.asList(recursoSaliente));
        }
    }

    private List<Recurso> generarRecursosIniciales() {
        Recurso[] tiposDeRecursos = Recurso.values();
        for(Recurso recurso : tiposDeRecursos){
            for (int i = 0; i < 5; i++) {
                listaDeRecursosDisponibles.add(recurso);
            }
        }
        return listaDeRecursosDisponibles;
    }


}
