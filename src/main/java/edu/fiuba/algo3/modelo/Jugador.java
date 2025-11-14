package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Jugador {

    private final String nombre;
    private final List<Recurso> recursos;
    private final Random random = new Random();

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.recursos = new ArrayList<>();
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void agregarRecursos(List<Recurso> nuevos) {
        recursos.addAll(nuevos);
    }

    public void agregarRecurso(Recurso recurso) {
        recursos.add(recurso);
    }

    public boolean tieneRecurso(Recurso recursoBuscado) {
        return recursos.contains(recursoBuscado);
    }

    public int cantidadDeRecursos() {
        return recursos.size();
    }

    public void descartarPorLadron() {
        int cantADescartar = recursos.size() / 2;
        for (int i = 0; i < cantADescartar; i++) {
            if (!recursos.isEmpty()) {
                int idx = random.nextInt(recursos.size());
                recursos.remove(idx);
            }
        }
    }

    public Recurso robarCartaAleatoria(Jugador objetivo) {
        if (objetivo.recursos.isEmpty()) return null;
        int idx = random.nextInt(objetivo.recursos.size());
        Recurso robado = objetivo.recursos.remove(idx);
        this.recursos.add(robado);
        return robado;
    }

    public void eliminarRecurso(Recurso recurso) {
        recursos.remove(recurso);
    }

    public List<Recurso> obtenerRecursos() {return recursos;};

}
