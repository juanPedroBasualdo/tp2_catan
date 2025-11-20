package edu.fiuba.algo3.modelo.tablero.terreno.parte;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

public class Arista {
    private final String key;
    private final Vertice vertice1;
    private final Vertice vertice2;
    private Jugador propietario;
    private final List<Arista> adyacentes;


    public Arista(String key, Vertice v1, Vertice v2) {
        this.key = key;
        this.vertice1 = v1;
        this.vertice2 = v2;
        adyacentes = new ArrayList<>();
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

    public Vertice getElOtroVertice(Vertice v) {
        if (v.equals(vertice1)) { return vertice2; }
        if (v.equals(vertice2)) { return vertice1; }
        throw new IllegalArgumentException("El vértice no pertenece a esta arista, esto nunca debería ocurrir.");
    }

}
