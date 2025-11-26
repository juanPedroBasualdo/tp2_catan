package edu.fiuba.algo3.modelo.tablero.terreno.parte;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.Pieza;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.PiezaTipo;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private Pieza pieza;  // Puede ser un Poblado o una Ciudad
    private final int indice;
    private final List<Vertice> adyacentes;

    public Vertice(int indice) {
        this.indice = indice;
        this.pieza = new Pieza(PiezaTipo.VACIO);
        adyacentes = new ArrayList<>();
    }

    protected boolean esValido() {
        return this.pieza.valido();
    }

    public boolean tieneIndice(int indice) {
        return this.indice == indice;
    }

    public boolean estaOcupado() {
        return !pieza.estaVacio();
    }

    public void asignarAdyacente(Vertice vertice) {
        adyacentes.add(vertice);
    }

    public void colocarPieza(Pieza pieza) {
        if (estaOcupado()) {
            throw new IllegalStateException("El vértice ya está ocupado");
        }
        if(!esValido()) {
            throw new IllegalStateException("No se puede colocar en este Vertice");
        }
        this.pieza = pieza;
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

    public boolean validarDatosMejoraCiudad(Jugador jugador1) {
        return pieza.validarMejoraPoblado(jugador1);
    }

    public void mejorarPoblado(Jugador jugador1) {
        if (!validarDatosMejoraCiudad(jugador1)) {
           // TODO Hacer la excepcion para cuando no es valido
        }
        this.pieza = new Pieza(PiezaTipo.CIUDAD, jugador1);
        this.invalidarAdyacentes();
    }

    public Pieza obtenerPieza() { return pieza ;}

    public boolean tienePropietario(Jugador jugador) {
        return pieza.tienePropietario(jugador);
    }

}
