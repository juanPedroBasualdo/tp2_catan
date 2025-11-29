package edu.fiuba.algo3.modelo.tablero.terreno.parte;

import edu.fiuba.algo3.modelo.excepciones.JugadorInvalidoException;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.Construccion;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.Invalido;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.Vacio;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private Construccion construccion;  // Puede ser un Poblado o una Ciudad
    private final int indice;
    private final List<Vertice> adyacentes;
    private final List<Arista> aristasAdyacentes = new ArrayList<>();

    public Vertice(int indice) {
        this.indice = indice;
        this.construccion = new Vacio();
        adyacentes = new ArrayList<>();
    }

    protected boolean esValido() {
        return this.construccion.valido();
    }

    public boolean tieneIndice(int indice) {
        return this.indice == indice;
    }

    public boolean estaOcupado() {
        return !construccion.estaVacio();
    }

    public void asignarAdyacente(Vertice vertice) {
        adyacentes.add(vertice);
    }

    public void colocarPieza(Construccion pieza) {
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


    private void asignarPieza(Construccion pieza) {
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
        Construccion ciudad = new Ciudad(jugador1);
        ciudad.comprarPieza();
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
}
