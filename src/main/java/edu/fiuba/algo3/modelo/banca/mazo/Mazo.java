package edu.fiuba.algo3.modelo.banca.mazo;

import edu.fiuba.algo3.modelo.cartasDesarrollo.*;
import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.Random;

public class Mazo {

    /*-- Atributos --*/

    private final Random random;

    /*-- Constructores --*/

    public Mazo(Random random) {
        this.random = random;
    }

    public Mazo(Long seed) {
        this(new Random(seed));
    }

    public Mazo() {
        this(new Random());
    }

    /*-- Metodo de creacion de Cartas --*/

    public CartaDesarrollo robarCartaDesarrollo(Jugador jugador, int numeroTurno) {
        int eleccion = (random.nextInt(25) + 1);
        if(eleccion <= 14) {
            return new Caballero(jugador, numeroTurno);
        }
        if(eleccion < 20) {
           eleccion = (random.nextInt(3) + 1);
           switch(eleccion) {
               case 1:
                   return new Monopolio(jugador, numeroTurno);
               case 2:
                   return new ConstruccionDeCarreteras(jugador, numeroTurno);
               default:
                   return new Invencion(jugador, numeroTurno);
           }
        } else {
            return new PuntoDeVictoria(jugador, numeroTurno);
        }
    }
}
