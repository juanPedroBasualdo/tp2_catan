package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Arista;

import java.util.Collection;
import java.util.List;

public class MayorRutaComercial {

    private final AuxiliarMayorRutaComercial auxiliar;
    private List<Arista> aristas;

    public MayorRutaComercial() {
        this.auxiliar = new AuxiliarMayorRutaComercial();
    }

    public Jugador determinar(Collection<Jugador> jugadores, List<Arista> aristas) {
        Jugador jugadorRutaMasLarga = null;
        int maximaLongitud = 0;
        for(Jugador j: jugadores) {
            int longitudDeJugador = this.calcularCaminoMasLargo(j, aristas);
            if(longitudDeJugador > maximaLongitud) {
                jugadorRutaMasLarga = j;
                maximaLongitud = longitudDeJugador;
            }
        }
        return maximaLongitud >= 5 ? jugadorRutaMasLarga : null;
    }

    private int calcularCaminoMasLargo(Jugador jugador, List<Arista> todasLasAristas) {
        List<Arista> aristasDeJugador = auxiliar.filtrarAristasPara(todasLasAristas, jugador);
        return auxiliar.mayorRutaComercial(aristasDeJugador, jugador);
    }
}
