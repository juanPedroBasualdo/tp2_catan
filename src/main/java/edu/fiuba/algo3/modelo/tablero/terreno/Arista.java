package edu.fiuba.algo3.modelo.tablero.terreno;

public class Arista {
    private final Vertice a;
    private final Vertice b;
    private Carretera carretera = new SinCarretera();

    public Arista(Vertice v1, Vertice v2) {
        this.a = v1;
        this.b = v2;
    }

    public Carretera getCarretera() {
        return carretera;
    }

    public Vertice getElOtroVertice(Vertice v) {
        if (v == a) return b;
        if (v == b) return a;
        throw new IllegalArgumentException("El vértice no pertenece a esta arista, esto nunca debería ocurrir.");
    }

    public boolean estaLibre() {
        return !carretera.hayCarretera();
    }

    public boolean tieneCarretera() {
        return carretera.estaLibre();
    }

    public void construirCarretera(Carretera carretera) {
        if (!estaLibre()) {
            throw new IllegalStateException("Ya hay una carretera en esta arista, no podes construir otra sobre la existente.");
        }
        this.carretera = new Carretera(jugador);
    }
}
