package edu.fiuba.algo3.modelo.tablero.terreno;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private final List<Arista> aristas = new ArrayList<>();
    private Construccion construccion = new SinConstruccion();

    public void agregarArista(Arista arista) {
        if(!aristas.contains(arista)) {
            aristas.add(arista);
        }
    }

    public List<Arista> getAristas() {
        return List.copyOf(aristas);
    }

    public Construccion getConstruccion() {
        return construccion;
    }

    public boolean tieneConstruccion() {
        return !construccion.estaVacio();
    }

    public void construirPoblado(Construccion nuevoPoblado) {   // la construccion sabe a que jugador le pertenece
        if(this.tieneConstruccion()) {
            throw new IllegalStateException("Ya hay un poblado o una ciudad en este vértice.");
        }
        construccion = nuevoPoblado;
    }

    public void construirCiudad(Construccion nuevaCiudad) {
        if(!construccion.esPoblado()) {
            throw new IllegalStateException("No hay un poblado en este vértice para mejorar a ciudad.");
        }
        construccion = nuevaCiudad;
    }
}
