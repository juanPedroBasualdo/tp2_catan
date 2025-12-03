package edu.fiuba.algo3.modelo.tablero.terreno.parte;

import edu.fiuba.algo3.modelo.excepciones.CaminoDesconectadoException;
import edu.fiuba.algo3.modelo.excepciones.PosicionInvalidaException;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.Carretera;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.Construccion;

import java.util.ArrayList;
import java.util.List;

public class Arista {
    
    /*-- Atributos --*/

    private final String key;
    private final Vertice vertice1;
    private final Vertice vertice2;
    private Jugador propietario;
    private final List<Arista> adyacentes;

    /*-- Constructor --*/

    public Arista(String key, Vertice v1, Vertice v2) {
        this.key = key;
        this.vertice1 = v1;
        this.vertice2 = v2;
        v1.asignarArista(this);
        v2.asignarArista(this);
        adyacentes = new ArrayList<>();
    }

    /*-- Metodos de creacion --*/

    public void agregarAdyacente(Arista arista) {
        adyacentes.add(arista);
    }

    /*-- Verificaciones --*/

    public boolean estaOcupada() {
        return propietario != null;
    }

    public boolean estaConectada() {
        for(Arista a : adyacentes) {
            if(a.esDe(this.getPropietario())) {
                return true;
            }
        }
        for(Vertice v : getVertices()) {
            if(v.esPropietario(this.getPropietario())) {
                return true;
            }
        }
        return false;
    }

    public boolean esDe(Jugador jugador) {
        return propietario != null && propietario.equals(jugador);
    }

    /*-- Metodos de comportamiento --*/

    public void colocarCamino(Jugador jugador) {
        if (estaOcupada()) {
            throw new PosicionInvalidaException("La arista ya tiene un camino");
        }
        if (!estaConectada()) {
            throw new CaminoDesconectadoException("La arista no esta conectada con una pieza de este jugador");
        }

        Construccion camino = new Carretera(jugador);
        camino.comprarPieza();

        this.propietario = jugador;
    }

    /*-- Getters --*/

    public Vertice vertice1() {
        return this.vertice1;
    }

    public Vertice vertice2() {
        return this.vertice2;
    }

    public Jugador getPropietario() { return propietario; }

    public Vertice[] getVertices() {
        return new Vertice[]{vertice1, vertice2};
    }
}
