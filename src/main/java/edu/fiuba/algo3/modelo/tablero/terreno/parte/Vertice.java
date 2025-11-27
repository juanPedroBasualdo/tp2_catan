package edu.fiuba.algo3.modelo.tablero.terreno.parte;

import edu.fiuba.algo3.modelo.excepciones.JugadorInvalidoException;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.Pieza;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.PiezaTipo;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.Ciudad;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private Pieza pieza;  // Puede ser un Poblado o una Ciudad
    private final int indice;
    private final List<Vertice> adyacentes;
    private final List<Arista> aristasAdyacentes = new ArrayList<>();

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

        // Esto verifica que el jugador contenga los recursos antes de agregar la Pieza al vertice y extrae los recursos
        pieza.comprarPieza();

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
           throw new JugadorInvalidoException();
        }

        // Creamos la pieza ciudad y la compramos de Banca para reemplazar el Poblado
        Pieza ciudad = new Ciudad(jugador1);
        ciudad.comprarPieza();
        this.pieza = ciudad;

        this.invalidarAdyacentes();
    }

    public Pieza obtenerPieza() { return pieza ;}

    public boolean esPropietario(Jugador jugador) {
        return pieza.tienePropietario(jugador);
    }

    public void asignarArista(Arista arista) {
        aristasAdyacentes.add(arista);
    }

    public List<Arista> getAristas() {
        return aristasAdyacentes;
    }

    public boolean bloqueaMayorRutaComercial(Jugador jugador) {
        if (!estaOcupado()) return false;         // estaOcupado() == false → return false
        return !pieza.tienePropietario(jugador);
        /* estaOcupado() == true, pieza.tienePropietario(jugador) == true, return !true → false
        estaOcupado() == true, pieza.tienePropietario(jugador) == false, return !false → true */
    }
}
