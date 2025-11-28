package edu.fiuba.algo3.entrega_3;

import java.util.*;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class JuegoTest {

    private Jugador jugador1;
    private Jugador jugador2;
    private Juego juego;


    @BeforeEach
    void setUp() {
        jugador1 = new Jugador("Jugador 1");
        jugador2 = new Jugador("Jugador 2");
        List<Jugador> listaJugadores = Arrays.asList(jugador1,jugador2);
        juego = new Juego(listaJugadores);
    }

    @Test
    void test01IntercambioEntreJugadoresValido() {
        // Arrange
        List<Recurso> recursosJ1 = Arrays.asList(Recurso.LANA,Recurso.LANA,Recurso.MADERA);
        List<Recurso> recursosJ2 = Arrays.asList(Recurso.MINERAL,Recurso.MINERAL);

        jugador1.agregarRecursos(recursosJ1);
        jugador2.agregarRecursos(recursosJ2);

        // Act
        juego.intercambioEntreJugadores(jugador1,jugador2,recursosJ1,recursosJ2);

        // Assert
        List<Recurso> recursosActuales1 = jugador1.obtenerRecursos();
        List<Recurso> recursosActuales2 = jugador2.obtenerRecursos();

        Assert.assertEquals(recursosJ2, recursosActuales1);
        Assert.assertEquals(recursosJ1, recursosActuales2);
    }

}
