package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.juego.turno.Turnos;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.randomizados.Dados;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.banca.*;
import edu.fiuba.algo3.modelo.tablero.coordenada.Coordenada;

import java.util.Collection;

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

    public void construirPoblado(Coordenada coordenada) {
        tablero.colocarPoblado(turnero.jugadorActual(), coordenada);
    }

    public void mejorarACiudad(Coordenada coordenada) {
        tablero.mejorarPoblado(turnero.jugadorActual(), coordenada);
    }

    public void construirCamino(Coordenada coordenada) {
        // TODO
    }
    
    public void pasarTurno() {
        turnero.pasarTurno();
    }

    public void obtenerPuntaje() {
        turnero.verificarPuntajeJugador();
    }

    public void intercambioTasaEstandar(Recurso recursoACambiar, Recurso recursoARecibir) {
        banca.intercambioDeTasaEstandar(turnero.jugadorActual(), recursoACambiar, recursoARecibir);
    }




}
