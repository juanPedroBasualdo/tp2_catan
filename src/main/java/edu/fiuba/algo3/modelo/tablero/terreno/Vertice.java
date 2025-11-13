package edu.fiuba.algo3.modelo.tablero.terreno;

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

    public Jugador getPropietario() {
        return propietario;
    }

    public Pieza getPieza() {
        return pieza;
    }
}
