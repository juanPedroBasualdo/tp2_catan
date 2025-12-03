package edu.fiuba.algo3.modelo.puntajeYBonificaciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public class Puntaje {

    public static int calcularPuntajeJugador(Jugador jugador) {
        int resultado = 0;
        resultado += jugador.puntajeCartasPV();
        resultado += jugador.puntajeConstrucciones();
        return resultado;
    }

}
