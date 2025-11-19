package edu.fiuba.algo3.modelo.tablero.terreno;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tablero.Pieza;
import edu.fiuba.algo3.modelo.tablero.PiezaTipo;

public class Vertice {
    private Pieza pieza;  // Puede ser un Poblado o una Ciudad
    private Jugador propietario;

    public boolean estaOcupado() {
        return pieza != null;
    }

    public void colocarPieza(Pieza pieza, Jugador jugador) {
        if (estaOcupado()) {
            throw new IllegalStateException("El vértice ya está ocupado");
        }
        this.pieza = pieza;
        this.propietario = jugador;
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

    public Pieza getPieza() {
        return pieza;
    }
}
