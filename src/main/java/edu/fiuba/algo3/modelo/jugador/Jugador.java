package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.banca.Banca;
import edu.fiuba.algo3.modelo.tablero.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jugador {

    private final String nombre;
    private final List<Recurso> recursos;
    private final Random random = new Random();

    // TODO hacer el puntaje con una clase de Puntaje
    private Integer puntaje;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.recursos = new ArrayList<>();
        this.puntaje = 0;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void agregarRecursos(List<Recurso> nuevos) {
        this.recursos.addAll(nuevos);
    }

    public void agregarRecurso(Recurso recurso) {
        this.recursos.add(recurso);
    }

    public boolean tieneRecurso(Recurso recursoBuscado) {
        return this.recursos.contains(recursoBuscado);
    }

    public int cantidadDeRecursos() {
        return this.recursos.size();
    }

    public void descartarPorLadron() {
        int cantADescartar = (recursos.size() / 2);
        for (int i = 0; i <= cantADescartar; i++) {
            if (!recursos.isEmpty()) {
                int idx = random.nextInt(recursos.size());
                recursos.remove(idx);
            }
        }
    }

    public Recurso robarCartaAleatoria(Jugador objetivo) {
        if (objetivo.recursos.isEmpty()){ return null; }
        int idx = random.nextInt(objetivo.recursos.size());
        Recurso robado = objetivo.eliminarRecurso(idx);
        this.recursos.add(robado);
        return robado;
    }

    public Recurso eliminarRecurso(int index) {
        return recursos.remove(index);
    }

    public Recurso eliminarRecurso(Recurso recurso) {
        int index = recursos.indexOf(recurso);
        return recursos.remove(index);
    }

    public List<Recurso> obtenerRecursos() {return recursos;};

    public boolean tieneRecursos(List<Recurso> listaDeRecursos) {
        List<Recurso> copiaRecursos = new ArrayList<>(this.recursos);
        for (Recurso r : listaDeRecursos) {
            if (!copiaRecursos.remove(r)) {
                return false;
            }
        }
        return true;
    }

    public void eliminarRecursos(List<Recurso> listaDeRecursos) {
        for (Recurso r : listaDeRecursos) {
            if (!recursos.remove(r)) {
                throw new IllegalStateException("Recurso a eliminar de Jugador no existe.");
            }
        }
    }

    public int obtenerPuntaje() {
        return puntaje;
    }

    // TODO Generalizar un método para recibír cierta cantidad de recursos de jugador por 1 recurso obtenido
    // TODO crear Clases de Puerto para usar polimorfismo
    public void intercambiarConTasaEstandar(Recurso recursoEntrante, Recurso recursoSaliente) {
        Banca.intercambioDeTasaEstandar(this, recursoEntrante, recursoSaliente);
    }

    public void intercambiarConPuertoEspecifico(Recurso recursoEntrante, Recurso recursoSaliente) {
        Banca.intercambioPuertoEspeficico(this, recursoEntrante,recursoSaliente);
    }

    public void intercambiarConPuertoGenerico(Recurso recursoEntrante, Recurso recursoSaliente) {
        Banca.intercambioPuertoGenerico(this,recursoEntrante,recursoSaliente);
    }

    public void agregarPuntaje(int puntaje) {
        this.puntaje += puntaje;
    }
}
