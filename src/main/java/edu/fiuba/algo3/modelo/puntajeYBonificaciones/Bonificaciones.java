package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.Observer.Observable;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Arista;

import java.util.Collection;
import java.util.List;

public class Bonificaciones extends Observable {

    /*-- Atributos --*/

    private final Collection<Jugador> jugadoresEnPartida;
    private final MayorRutaComercial determinadorMayorRutaComercial;
    private final MayorEjercito determinadorMayorEjercito;
    private Jugador jugadorConMayorRutaComercial = null;
    private Jugador jugadorConMayorEjercito = null;

    /*-- Constructores --*/

    public Bonificaciones(Collection<Jugador> jugadores) {
        this.jugadoresEnPartida = jugadores;
        this.determinadorMayorRutaComercial = new MayorRutaComercial();
        this.determinadorMayorEjercito = new MayorEjercito();
    }

    /*-- Metodos de comportamiento --*/

    public void actualizarBonificaciones(Collection<Arista> aristas) {
        // Se almacenan los jugadores actuales con mayor ejercito y mayor ruta comercial para ver si hubo cambios
        Jugador anteriorRuta = this.jugadorConMayorRutaComercial;
        Jugador anteriorEjercito = this.jugadorConMayorEjercito;

        this.jugadorConMayorRutaComercial = determinadorMayorRutaComercial.determinar(this.jugadoresEnPartida, aristas);
        this.jugadorConMayorEjercito = determinadorMayorEjercito.determinar(this.jugadoresEnPartida);

        // Notificación condicional: Solo si hubo un cambio en los jugadores con mayor ejercito y mayor ruta comercial
        if (anteriorRuta != this.jugadorConMayorRutaComercial || anteriorEjercito != this.jugadorConMayorEjercito) {
            this.notificarObservadores();
        }
    }

    public int puntajeDe(Jugador jugador) {
        int puntos = 0;
        if (jugador == jugadorConMayorRutaComercial) puntos += 2;
        if (jugador == jugadorConMayorEjercito) puntos += 2;
        return puntos;
    }
}
