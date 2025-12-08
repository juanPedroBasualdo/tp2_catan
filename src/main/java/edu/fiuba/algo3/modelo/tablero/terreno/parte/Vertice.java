package edu.fiuba.algo3.modelo.tablero.terreno.parte;

import edu.fiuba.algo3.modelo.excepciones.JugadorInvalidoException;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.*;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

    /*-- Atributos --*/

    private Productor construccion;  // Puede ser un Poblado o una Ciudad
    private final int indice;
    private final List<Vertice> adyacentes;
    private final List<Arista> aristasAdyacentes = new ArrayList<>();

    /*-- Constructores --*/

    public Vertice(int indice) {
        this.indice = indice;
        this.construccion = new Vacio();
        adyacentes = new ArrayList<>();
    }

    /*-- Metodos de creacion --*/

    public void asignarAdyacente(Vertice vertice) {
        adyacentes.add(vertice);
    }

    /*-- Verificaciones --*/

    protected boolean esValido() {
        return this.construccion.esValido();
    }

    public boolean tieneIndice(int indice) {
        return this.indice == indice;
    }

    public boolean estaOcupado() {
        return construccion.esNoVacio();
    }

    public boolean esPropietario(Jugador jugador) {
        return construccion.tienePropietario(jugador);
    }

    private void verificarDisponibilidad() {
        if (this.estaOcupado()) {
            throw new IllegalStateException("El vértice ya está ocupado");
        }
        if(!this.esValido()) {
            throw new IllegalStateException("No se puede colocar en este Vertice");
        }
    }

    /*-- Metodos de comportamiento --*/

    public void colocarPoblado(Productor poblado) {
        this.verificarDisponibilidad();
        // Esto verifica que el jugador contenga los recursos antes de agregar la Pieza al vertice y extrae los recursos
        poblado.comprarPieza();

        this.construccion = poblado;
        this.invalidarAdyacentes();

    }

    public void posicionarPoblado(Jugador jugador, Poblado poblado) {
        this.verificarDisponibilidad();
        this.construccion = poblado;
        jugador.agregarConstruccion(poblado);

        this.invalidarAdyacentes();
    }

    private void asignarPieza(Productor pieza) {
        this.construccion = pieza;
    }

    private void invalidarAdyacentes() {
        for(Vertice ady : adyacentes) {
            ady.asignarPieza(new Invalido());
        }
    }

    public boolean validarDatosMejoraCiudad(Jugador jugador1) {
        return construccion.validarMejoraPoblado(jugador1);
    }

    public void mejorarPoblado(Jugador jugador1) {
        if (!validarDatosMejoraCiudad(jugador1)) {
           throw new JugadorInvalidoException();
        }
        Productor ciudad = new Ciudad(jugador1);
        ciudad.comprarPieza();
        jugador1.removerConstruccion(this.construccion);
        this.construccion = ciudad;

        this.invalidarAdyacentes();
    }

    public void producir(Recurso recurso) {
        if(this.construccion.esValido() && this.construccion.esNoVacio()) {
            this.construccion.producir(recurso);
        }
    }

    /*-- Getters --*/

    public Construccion obtenerPieza() {
        return construccion;
    }

    public void asignarArista(Arista arista) {
        aristasAdyacentes.add(arista);
    }

    public List<Arista> getAristas() {
        return aristasAdyacentes;
    }

    public boolean bloqueaMayorRutaComercial(Jugador jugador) {
        if (!estaOcupado()) return false;         // estaOcupado() == false → return false
        return !construccion.tienePropietario(jugador);
        /* estaOcupado() == true, pieza.tienePropietario(jugador) == true, return !true → false
        estaOcupado() == true, pieza.tienePropietario(jugador) == false, return !false → true */
    }
}
