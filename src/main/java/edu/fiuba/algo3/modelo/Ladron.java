package edu.fiuba.algo3.modelo;
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

    public boolean moverA(Terreno nuevoTerreno) {
        if (nuevoTerreno == null) return false;
        this.posicionActual = nuevoTerreno;
        return true;
    }

    public Recurso robarCartaAleatoria(Jugador jugadorRobado, Jugador jugadorRobador) {
        if (jugadorRobado == null || jugadorRobador == null) return null;
        if (jugadorRobado.cantidadDeRecursos() == 0) return null;

        var recursosRobado = jugadorRobado.obtenerRecursos();
        int idx = random.nextInt(recursosRobado.size());
        Recurso recursoRobado = recursosRobado.get(idx);

        // Efectúa el robo
        jugadorRobado.eliminarRecurso(recursoRobado);
        jugadorRobador.agregarRecurso(recursoRobado);

        return recursoRobado;
    }
}
