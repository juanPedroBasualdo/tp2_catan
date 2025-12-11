package edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones;

import edu.fiuba.algo3.modelo.banca.Banca;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.List;

public abstract class Construccion {

    private final Jugador propietario;

    protected Construccion(Jugador propietario) {
        this.propietario = propietario;
    }

    public void comprarPieza() {
        if (!propietario.tieneRecursos(this.obtenerPrecioPieza())){
            throw new RecursosInsuficientesException("El jugador no tiene recursos suficientes para comprar la pieza. ");
        }
        propietario.agregarConstruccion(this);
        Banca.extraerRecursos(propietario, this.obtenerPrecioPieza());
    }

    abstract public List<Recurso> obtenerPrecioPieza();

    abstract public int puntosDeVictoria();

    public Jugador getPropietario() { return propietario; }

    public boolean tienePropietario(Jugador jugador) {
        return jugador.equals(propietario);
    }

    public boolean esNoVacio() {
        return !this.getClass().equals(Vacio.class);
    }

    public boolean esValido() {
        return !this.getClass().equals(Invalido.class);
    }

    public boolean validarMejoraPoblado(Jugador jugador1) {
        return this.getClass().equals(Poblado.class) && propietario == (jugador1);
    }
}
