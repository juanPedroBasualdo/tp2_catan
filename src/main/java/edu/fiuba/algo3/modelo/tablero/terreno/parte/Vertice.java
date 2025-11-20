package edu.fiuba.algo3.modelo.tablero.terreno.parte;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tablero.Pieza;
import edu.fiuba.algo3.modelo.tablero.PiezaTipo;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private Pieza pieza;  // Puede ser un Poblado o una Ciudad
    private Jugador propietario;
    private final int indice;
    private List<Vertice> adyacentes;

    public Vertice(int indice) {
        this.indice = indice;
        adyacentes = new ArrayList<>();
    }

    protected boolean esValido() {
        if(pieza == null) {
            return true;
        }
        return this.pieza.valido();
    }

    public boolean tieneIndice(int indice) {
        return this.indice == indice;
    }

    public boolean estaOcupado() {
        return pieza != null;
    }

    public void asignarAdyacente(Vertice vertice) {
        adyacentes.add(vertice);
    }

    public void colocarPieza(Pieza pieza, Jugador jugador) {
        if (estaOcupado()) {
            throw new IllegalStateException("El vértice ya está ocupado");
        }
        if(!esValido()) {
            throw new IllegalStateException("No se puede colocar en este Vertice");
        }
        this.pieza = pieza;
        this.propietario = jugador;
        this.invalidarAdyacentes();
    }

    private void asignarPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    private void invalidarAdyacentes() {
        for(Vertice ady : adyacentes) {
            ady.asignarPieza(new Pieza(PiezaTipo.INVALIDO));
        }
    }
}
