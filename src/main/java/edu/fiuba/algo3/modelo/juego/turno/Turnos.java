package edu.fiuba.algo3.modelo.juego.turno;

import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Turnos {

    private final Queue<Jugador> turnero;
    private Jugador jugadorActual;
    private int numeroDeTurno;

    public Turnos(Collection<Jugador> listaJugadores) {
        turnero = new ConcurrentLinkedQueue<>();
        turnero.addAll(listaJugadores);
        this.jugadorActual = turnero.peek();
        this.numeroDeTurno = 1;
    }

    public void pasarTurno() {
        Jugador jugadorAnterior = turnero.remove();
        this.jugadorActual = turnero.peek();
        turnero.add(jugadorAnterior);
        this.numeroDeTurno++;
    }

    public Jugador jugadorActual() {
        return this.jugadorActual;
    }

    public int cantidadJugadores() {
        return this.turnero.size();
    }

    public int numeroDeTurno() {
        return this.numeroDeTurno;
    }

    public int verificarPuntajeJugador() {
        return jugadorActual.calcularPuntajeVictoria();
    }

}
