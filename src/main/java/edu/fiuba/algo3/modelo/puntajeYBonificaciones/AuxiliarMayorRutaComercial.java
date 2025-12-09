package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Arista;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Vertice;

import java.util.*;

public class AuxiliarMayorRutaComercial {

    public int mayorRutaComercial(Collection<Arista> aristasDeJugador, Jugador jugador) {
        int mejor = 0;
        for(Arista a : aristasDeJugador) {
            Set<Arista> visitadas = new HashSet<>();
            mejor = Math.max(mejor, dfs(a, visitadas, jugador));
        }

        return mejor;
    }

    private int dfs(Arista actual, Set<Arista> visitadas, Jugador jugador) {
        visitadas.add(actual);
        int mejor = 1;

        for (Arista ady : actual.getAdyacentes()) {
            if (ady.esDe(jugador) && !visitadas.contains(ady)) {
                Vertice v = verticeCompartido(actual, ady);
                if (!(v == null) && !verticeBloqueado(v, jugador)) {
                    mejor = Math.max(mejor, 1 + dfs(ady, visitadas, jugador));
                }
            }
        }
        visitadas.remove(actual);
        return mejor;
    }

    private Vertice verticeCompartido(Arista a, Arista b) {
        Vertice a1 = a.vertice1();
        Vertice a2 = a.vertice2();
        if (a1 == b.vertice1() || a1 == b.vertice2()) return a1;
        if (a2 == b.vertice1() || a2 == b.vertice2()) return a2;
        return null;
    }

    private boolean verticeBloqueado(Vertice v, Jugador jugador) {
        if (!v.estaOcupado()) {
            return false;
        }
        return !v.esPropietario(jugador);
    }

    public List<Arista> filtrarAristasPara(Collection<Arista> todasLasAristas, Jugador jugador) {
        List<Arista> aristasDeJugador = new ArrayList<>();
        for(Arista a : todasLasAristas) {
            if(a.esDe(jugador)) {
                aristasDeJugador.add(a);
            }
        }

        return aristasDeJugador;
    }
}
