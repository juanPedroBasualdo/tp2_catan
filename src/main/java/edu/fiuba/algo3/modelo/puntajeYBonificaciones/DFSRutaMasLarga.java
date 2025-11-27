package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Arista;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Vertice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFSRutaMasLarga {
    private final Tablero tablero;

    public DFSRutaMasLarga(Tablero tablero) {
        this.tablero = tablero;
    }

    public int calcularPara(Jugador jugador) {
        int max = 0;

        // Tomamos solo las aristas del jugador

        return 0;
/*      TODO arreglar esto despues.
        List<Arista> caminos = tablero.getAristas().stream().filter(a -> a.esDe(jugador)).toList();


        // DFS desde cada arista como inicio posible
        for (Arista arista : caminos) {
            Set<Arista> visitadas = new HashSet<>();
            int largo = dfs(jugador, arista, visitadas);
            if (largo > max) max = largo;
        }
        return max;
*/
    }

    private int dfs(Jugador jugador, Arista actual, Set<Arista> visitadas) {
        visitadas.add(actual);

        int mejor = 0;
        for (Arista siguiente : obtenerContinuaciones(jugador, actual)) {
            if (!visitadas.contains(siguiente)) {
                int res = dfs(jugador, siguiente, visitadas);
                if (res > mejor) mejor = res;
            }
        }
        visitadas.remove(actual);
        return 1 + mejor;
    }

    private List<Arista> obtenerContinuaciones(Jugador jugador, Arista arista) {
        List<Arista> continuaciones = new ArrayList<>();

        for (Vertice v : arista.getVertices()) {
            // Si el vértice está bloqueado, corta el camino
            if (v.bloqueaMayorRutaComercial(jugador)) continue;

            for (Arista ady : v.getAristas()) {
                if (ady.esDe(jugador)) {
                    continuaciones.add(ady);
                }
            }
        }
        return continuaciones;
    }
}
