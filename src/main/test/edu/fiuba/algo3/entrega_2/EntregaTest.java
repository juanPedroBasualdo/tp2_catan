package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tablero.Coordenada;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EntregaTest {
	
    private Tablero tablero, tableroMock;
    private Jugador jugador1;
	
    @BeforeEach
    void setUp() {
    	tablero = new Tablero();
        tableroMock = mock(Tablero.class);
        jugador1 = new Jugador("Jugador 1");
    }

    @Test
    void test03JugadorMejoraPobladoYRecivePV() {

        // Assign: El Jugador ya fue creado en el setUp()

            // Supone que no cambiaremos el costo de mejorar una ciudad (3 Minerales y 1 Cereal/Trigo)
        List<Recurso> recursosIniciales = Arrays.asList(Recurso.CEREAL, Recurso.CEREAL, Recurso.MINERAL, Recurso.MINERAL, Recurso.MINERAL);
        jugador1.agregarRecursos(recursosIniciales);
        int puntosVictoriaIniciales = jugador1.obtenerPuntaje();

        // Act:
        when(tableroMock.puedeColocarPoblado(jugador1,any(Coordenada.class))).thenReturn(true);
        Coordenada coordenadaPoblado = new Coordenada(2,2,2);
        tableroMock.colocarPoblado(jugador1, coordenadaPoblado);
        tableroMock.mejorarPoblado(jugador1, coordenadaPoblado);
        // TODO: ver como quitar Recursos del Jugador desde Tablero (En teoría debería funcionar para construirPueblo,
        //  charlar sobre donde almacenar precios del juego 19/11 )

        // Assert
        boolean jugadorRecibioPuntajeCorrecto = (jugador1.obtenerPuntaje() - puntosVictoriaIniciales) == 1; // Paso un cambio de 1PV
        boolean jugadorDescontadoDeRecursos = (jugador1.cantidadDeRecursos() == 0); // Se supone que no quedan recursos luego de construir
        assertTrue(jugadorDescontadoDeRecursos && jugadorRecibioPuntajeCorrecto);
    }

    @Test
    void test06ComercioMaritimoTasaEstadar() { // 4 cartas del mismo recurso por 1 carta de cualquier otro recurso.

        //

    }

    @Test
    void test05ComercioMaritimoPuertoEspecifico() { // 2 cartas del recurso indicado en un puerto por 1 carta de cualquier otro recurso.


        //

    }
    @Test
    void test06ComercioMaritimoPuertoGenerico() { // 3 cartas de cualquier recurso por 1 carta de cualquier otro recurso

        //

    }

}
