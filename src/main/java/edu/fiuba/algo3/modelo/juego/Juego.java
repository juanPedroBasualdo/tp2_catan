package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.juego.turno.Turnos;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.randomizados.Dados;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.banca.*;
import edu.fiuba.algo3.modelo.tablero.coordenada.Coordenada;

import java.util.Collection;
import java.util.List;

public class Juego {

    private final Turnos turnero;
    private final Tablero tablero;
    private final Banca banca;
    private final Dados dados;

    public Juego(Collection<Jugador> listaJugadores) {
        turnero = new Turnos(listaJugadores);
        tablero = new Tablero();
        banca = new Banca();
        dados = new Dados();
    }

    public int tirarDados() {
       return dados.tirar();
    }

    public void construirCamino(Coordenada coordenada) {
        tablero.colocarCarretera(turnero.jugadorActual(), coordenada);
    }

    public void construirPoblado(Coordenada coordenada) {
        tablero.colocarPoblado(turnero.jugadorActual(), coordenada);
    }

    public void mejorarACiudad(Coordenada coordenada) {
        tablero.mejorarPoblado(turnero.jugadorActual(), coordenada);
    }

    public void intercambioTasaEspecifica(Recurso recursoACambiar, Recurso recursoARecibir) {
        tablero.intercambiarConPuertoEspecifico(turnero.jugadorActual(), recursoACambiar, recursoARecibir);
    }

    public void intercambioTasaGenerica(Recurso recursoACambiar, Recurso recursoARecibir) {
        tablero.intercambiarConPuertoGenerico(turnero.jugadorActual(), recursoACambiar, recursoARecibir);
    }

    public void intercambioTasaEstandar(Recurso recursoACambiar, Recurso recursoARecibir) {
        banca.intercambioDeTasaEstandar(turnero.jugadorActual(), recursoACambiar, recursoARecibir);
    }

    public void comprarCartaDesarrollo() {
        // TODO
    }

    public void jugadorCartaDesarrollo() {}

    public void intercambioEntreJugadores(Jugador jugador1, Jugador jugador2, List<Recurso> listaRecursosJ1, List<Recurso> listaRecursosJ2) {

        if(!(jugador1.tieneRecursos(listaRecursosJ1) && jugador2.tieneRecursos(listaRecursosJ2))){
            throw new RecursosInsuficientesException();
        }

        jugador1.eliminarRecursos(listaRecursosJ1);
        jugador1.agregarRecursos(listaRecursosJ2);

        jugador2.eliminarRecursos(listaRecursosJ2);
        jugador2.agregarRecursos(listaRecursosJ1);

    }

    public void pasarTurno() {
        turnero.pasarTurno();
    }

    public void obtenerPuntaje() {
        turnero.verificarPuntajeJugador();
    }

}
