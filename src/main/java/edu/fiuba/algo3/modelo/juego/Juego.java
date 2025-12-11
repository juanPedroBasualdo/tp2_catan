package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Observer.Observador;
import edu.fiuba.algo3.modelo.cartasDesarrollo.Jugable;
import edu.fiuba.algo3.modelo.juego.turno.FaseTurno;
import edu.fiuba.algo3.modelo.juego.turno.FasesTurno;
import edu.fiuba.algo3.modelo.juego.turno.Turnos;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.puntajeYBonificaciones.Bonificaciones;
import edu.fiuba.algo3.modelo.randomizados.Dados;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.banca.*;
import edu.fiuba.algo3.modelo.tablero.coordenada.Coordenada;

import java.util.Collection;
import java.util.List;

public class Juego {

    /*-- Atributos --*/
    private final Turnos turnero;
    private final Tablero tablero;
    private final Banca banca;
    private final Dados dados;
    private final Bonificaciones bonificaciones;
    private final FasesTurno flujo;

    /*-- Constructores --*/

    public Juego(Collection<Jugador> listaJugadores) {
        turnero = new Turnos(listaJugadores);
        tablero = new Tablero();
        banca = new Banca();
        dados = new Dados();
        bonificaciones = new Bonificaciones(listaJugadores);
        flujo = new FasesTurno(listaJugadores.size());
    }

    /*-- Getter --*/

    public Collection<Jugador> listaDeJugadores() {
        return this.turnero.jugadores();
    }

    public Tablero obtenerTablero() {
        return this.tablero;
    }

    /*-- Metodos de fachada --*/

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

    public void posicionarPoblado(Coordenada coordenada) { tablero.posicionarPoblado(turnero.jugadorActual(), coordenada);}

    public void posicionarCamino(Coordenada coordenada) { tablero.posicionarCamino(turnero.jugadorActual(), coordenada);}

    public void otorgarRecursos(int fichaNumero) {
        tablero.producirRecursos(fichaNumero);
    }

    public void otorgarRecursosIniciales(Coordenada coordenada) {
        tablero.otorgarRecursosIniciales(this.turnero.jugadorActual(), coordenada);
    }

    public void intercambioTasaEspecifica(Recurso recursoACambiar, Recurso recursoARecibir) {
        tablero.intercambiarConPuertoEspecifico(turnero.jugadorActual(), recursoACambiar, recursoARecibir);
    }

    public void intercambioTasaGenerica(Recurso recursoACambiar, Recurso recursoARecibir) {
        tablero.intercambiarConPuertoGenerico(turnero.jugadorActual(), recursoACambiar, recursoARecibir);
    }

    public void moverLadron(Coordenada coordenada) {
        this.tablero.moverLadron(coordenada);
    }

    public void descartarRecursos() {
        Jugador jugadorDeTurno = this.turnero.jugadorActual();
        jugadorDeTurno.descartarPorLadron();
        do {
            this.turnero.siguienteJugador();
            Jugador actual = this.turnero.jugadorActual();
            actual.descartarPorLadron();
        } while(jugadorDeTurno != this.turnero.jugadorActual());
    }

    public void robarCarta(Jugador jugadorVictima) {
        this.tablero.robarCarta(this.turnero.jugadorActual(), jugadorVictima);
    }

    public boolean estaLadron(Coordenada coordenada) {
        return this.tablero.estaLadronEn(coordenada);
    }

    public void intercambioTasaEstandar(Recurso recursoACambiar, Recurso recursoARecibir) {
        banca.intercambioDeTasaEstandar(turnero.jugadorActual(), recursoACambiar, recursoARecibir);
    }

    public void comprarCartaDesarrollo() {
        banca.venderCartaDesarrollo(turnero.jugadorActual(), turnero.numeroDeTurno());
    }

    public void intercambioEntreJugadores(Jugador jugador1, Jugador jugador2, List<Recurso> listaRecursosJ1, List<Recurso> listaRecursosJ2) {
        this.banca.intercambioEntreJugadores(jugador1, jugador2, listaRecursosJ1, listaRecursosJ2);
    }

    public void jugarCarta(Jugable carta) {
        this.turnero.jugadorActual().jugarCarta(carta, this.turnero.numeroDeTurno());
    }

    public void pasarTurno() {
        turnero.pasarTurno();
    }

    public Jugador jugadorActual() {
        return this.turnero.jugadorActual();
    }

    public int obtenerPuntaje() {
        return turnero.verificarPuntajeJugador(this.bonificaciones.puntajeDe(this.turnero.jugadorActual()));
    }

    public void actualizarBonificaciones() {
        this.bonificaciones.actualizarBonificaciones(this.tablero.getAristas());
    }

    public void cambiarFase(int numeroDado) {
        this.flujo.cambiarFase(numeroDado);
    }

    public void cambiarFase(FaseTurno fase) {
        this.flujo.cambiarFase(fase);
    }

    public FaseTurno obtenerFase() {
        return this.flujo.obtenerFase();
    }

    // Metodos observer:

    public void notificarObservadores() {
        this.turnero.notificarObservadores();
        this.tablero.notificarObservadores();
        this.bonificaciones.notificarObservadores();
    }

    public void agregarObserversDeJugador(Observador observador) {
        this.turnero.agregarObservador(observador);
    }

    public void agregarObserversDeTablero(Observador observador) {
        this.tablero.agregarObservador(observador);
    }

    public void agregarObserverDeBonificaciones(Observador observador) {
        this.bonificaciones.agregarObservador(observador);
    }

}
