package edu.fiuba.algo3.modelo.tablero.terreno;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tablero.Pieza;
import edu.fiuba.algo3.modelo.tablero.PiezaTipo;

public class Vertice {
    private final List<Arista> aristas = new ArrayList<>();
    private Construccion construccion = new SinConstruccion();

    public void agregarArista(Arista arista) {
        if(!aristas.contains(arista)) {
            aristas.add(arista);
        }
    }

    public void mejorarPoblado(Jugador jugador) {
        if(!esPobladoACiudad(jugador)){
            throw new IllegalStateException("El poblado debe existir y ser del jugador");
        }
        this.pieza = Pieza.crearPieza(PiezaTipo.CIUDAD, jugador);
    }

    public boolean esPobladoACiudad(Jugador jugador) {
        return this.pieza.obtenerTipo() == PiezaTipo.POBLADO && this.propietario == jugador;
    }

    public Jugador getPropietario() {
        return propietario;
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
