package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.tablero.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jugador {

    private final String nombre;
    private final List<Recurso> recursos;
    private final Random random = new Random();
    private final Integer puntosVictoria;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.recursos = new ArrayList<>();
        this.puntosVictoria = 0;
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

    public List<Recurso> obtenerRecursos() { return recursos; };

    public int obtenerPuntaje() { return puntosVictoria; };

}
