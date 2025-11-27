package edu.fiuba.algo3.modelo.tablero.terreno.pieza;

import edu.fiuba.algo3.modelo.banca.Banca;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.List;

public class Pieza {

    private Jugador propietario;
    private PiezaTipo tipo;

    protected int puntajeVictoria;
    protected List<Recurso> precioConstruccion;

    public Pieza(PiezaTipo tipo) { this.tipo = tipo; }

    public Pieza(PiezaTipo tipo, Jugador jugador) {
        this.tipo = tipo;
        this.propietario = jugador;
    }

    public void comprarPieza() {
        if (!propietario.tieneRecursos(this.obtenerPrecioPieza())){
            throw new RecursosInsuficientesException();
        }
        propietario.agregarPieza(this);
        Banca.extraerRecursos(propietario, this.obtenerPrecioPieza());
    }

    /**
     * Este metodo toma una lista de Piezas y devuelve su puntaje de victoria total
     * @param listaPiezas una lista de piezas de un jugador
     * @return el puntaje de victoria
     */
    public static int calcularPuntaje(List<Pieza> listaPiezas) {
        int sum = 0;
        for (Pieza pieza : listaPiezas) {
            sum += pieza.obtenerPuntajeVictoria();
        }
        return sum;
    }

    public int obtenerPuntajeVictoria() { return puntajeVictoria; };

    protected List<Recurso> obtenerPrecioPieza() {
        return precioConstruccion;
    }

    public boolean validarMejoraPoblado(Jugador jugador1) {
        return tipo == (PiezaTipo.POBLADO) && propietario == (jugador1);
    }
    public boolean valido() {
        return this.tipo != PiezaTipo.INVALIDO;
    }

    public Jugador getPropietario() { return propietario; }

    public boolean tienePropietario(Jugador jugador) {
        return jugador == propietario;
    }

    public boolean estaVacio() {
        return this.tipo == PiezaTipo.VACIO;
    }

}
