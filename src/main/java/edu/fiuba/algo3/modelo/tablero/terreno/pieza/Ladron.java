package edu.fiuba.algo3.modelo.tablero.terreno.pieza;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Terreno;

import java.util.Random;

public class Ladron {
    
    private Terreno posicionActual;
    private final Random random = new Random();

    public Ladron() {
        this.posicionActual = null; // TODO ver como poner como Desierto.class();
    }

    public Ladron(Terreno posicionActualInicial) {
        this.posicionActual = posicionActualInicial;
    }

    public Terreno obtenerPosicionActual() {
        return posicionActual;
    }

    public void moverA(Terreno nuevoTerreno) {
        this.posicionActual = nuevoTerreno;
    }

    public Recurso robarCartaAleatoria(Jugador jugadorRobado, Jugador jugadorRobador) {
        if (jugadorRobado == null || jugadorRobador == null) { return null; }
        if (jugadorRobado.cantidadDeRecursos() == 0) {return null;}

        int idx = random.nextInt(jugadorRobado.obtenerRecursos().size());

        // Efectúa el robo
        Recurso recursoRobado = jugadorRobado.eliminarRecurso(idx);
        jugadorRobador.agregarRecurso(recursoRobado);

        return recursoRobado;
    }
}
