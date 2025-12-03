package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Monopolio extends Jugable {

    /*-- Atributos --*/

    private Collection<Jugador> jugadores;
    private Recurso recursoElegido;

    /*-- Constructores --*/

    public Monopolio(Jugador propietario, int numeroTurno) {
        super(propietario, numeroTurno);
    }

    public Monopolio(Jugador propietario, int numeroTurno, Collection<Jugador> jugadores, Recurso recurso) {
        this(propietario, numeroTurno);
        this.jugadores = jugadores;
        this.recursoElegido = recurso;
    }

    /*-- Metodos de comportamiento --*/

    public void jugar(int numeroTurno, Recurso recurso) {
        this.elegirRecurso(recurso);
        this.jugar(numeroTurno);
    }

    @Override
    protected void efecto() {
        List<Recurso> monopolio = new ArrayList<>();
        for(Jugador j : jugadores) {
            List<Recurso> obtenido = j.extraerTotalidadDe(this.recursoElegido);
            monopolio.addAll(obtenido);
        }
        this.propietario.agregarRecursos(monopolio);
    }

    /*-- Setter --*/

    public void elegirRecurso(Recurso recurso) {
        this.recursoElegido = recurso;
    }
}
