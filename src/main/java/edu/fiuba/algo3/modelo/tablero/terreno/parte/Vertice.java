package edu.fiuba.algo3.modelo.tablero.terreno.parte;

import edu.fiuba.algo3.modelo.excepciones.JugadorInvalidoException;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.*;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private Productor construccion;  // Puede ser un Poblado o una Ciudad
    private final int indice;
    private final List<Vertice> adyacentes;
    private final List<Arista> aristasAdyacentes = new ArrayList<>();

    public Vertice(int indice) {
        this.indice = indice;
        this.construccion = new Vacio();
        adyacentes = new ArrayList<>();
    }

    protected boolean esValido() {
        return this.construccion.esValido();
    }

    public boolean tieneIndice(int indice) {
        return this.indice == indice;
    }

    public boolean estaOcupado() {
        return construccion.esNoVacio();
    }

    public void asignarAdyacente(Vertice vertice) {
        adyacentes.add(vertice);
    }

    public void colocarPieza(Productor pieza) {
        if (estaOcupado()) {
            throw new IllegalStateException("El vértice ya está ocupado");
        }
        if(!esValido()) {
            throw new IllegalStateException("No se puede colocar en este Vertice");
        }

        // Esto verifica que el jugador contenga los recursos antes de agregar la Pieza al vertice y extrae los recursos
        pieza.comprarPieza();

        this.construccion = pieza;
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

        // Creamos la pieza ciudad y la compramos de Banca para reemplazar el Poblado
        Productor ciudad = new Ciudad(jugador1);
        ciudad.comprarPieza();
        jugador1.removerConstruccion(this.construccion);
        this.construccion = ciudad;

        this.invalidarAdyacentes();
    }

    public Construccion obtenerPieza() { return construccion;}

    public boolean esPropietario(Jugador jugador) {
        return construccion.tienePropietario(jugador);
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

    public void producir(Recurso recurso) {
        if(this.construccion.esValido() && this.construccion.esNoVacio()) {
            this.construccion.producir(recurso);
        }
    }
}
