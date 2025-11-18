package edu.fiuba.algo3.modelo.tablero.terreno;

import edu.fiuba.algo3.modelo.Jugador;

public class Arista {
    private Vertice verticeA;
    private Vertice verticeB;
    private Jugador propietario;

    public Arista(Vertice a, Vertice b) {
        this.verticeA = a;
        this.verticeB = b;
    }

    public boolean estaOcupada() {
        return propietario != null;
    }

    public void colocarCamino(Jugador jugador) {
        if (estaOcupada()) {
            throw new IllegalStateException("La arista ya tiene un camino");
        }
        this.propietario = jugador;
    }

}
