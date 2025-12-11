package edu.fiuba.algo3.modelo.tablero.coordenada;

import java.util.ArrayList;
import java.util.List;

public class Coordenada {

    private final int x, y, v;

    public Coordenada(int offsetX, int offsetY, int vertex) {
        this.x = offsetX;
        this.y = offsetY;
        this.v = vertex;
    }

    public Coordenada(int offsetX, int offsetY) {
        this(offsetX, offsetY, 0);
    }

    public List<Coordenada> obtenerDireccionesAdyacentes(int maxY) {
                Coordenada[] direccionesCoordenadas = {
                new Coordenada(-1,0),
                new Coordenada(0,1),
                new Coordenada(1,0),
                new Coordenada(1,-1),
                new Coordenada(0,-1),
                new Coordenada(-1,-1),
        };
        List<Coordenada> direcciones = new ArrayList<>(List.of(direccionesCoordenadas));
        direcciones.removeIf(c -> !this.verificarDentroDeRango(c, maxY));
        return direcciones;
    }

    public Coordenada aplicarDireccion(Coordenada direccion) {
        return new Coordenada(this.x + direccion.x(), this.y + direccion.y());
    }


    public boolean verificarDentroDeRango(Coordenada direccion, int maxY) {
        if(this.x + direccion.x() == 5) {
            return false;
        }
        if(this.x + direccion.x() == -1) {
            return false;
        }
        if(this.y + direccion.y() == -1) {
            return false;
        }
        return (((this.y + direccion.y())) != maxY);
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public int vertex() {
        return this.v;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + this.x + "," + this.y + "," + this.v + ")";
    }
}
