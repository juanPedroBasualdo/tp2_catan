package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.juego.turno.Turnos;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class TurnosTest {

    Turnos turnero;
    List<Jugador> listaJugadores;

    @BeforeEach
    void setUp() {
        listaJugadores = new ArrayList<>();
        for(int i = 1 ; i <= 4 ; i++){
            listaJugadores.add(new Jugador("jugador" + i));
        }
        turnero = new Turnos(listaJugadores);
    }

    @Test
    void test01ElJugadorActualDelTurnoRecienCreadoEsCorrecto(){
        // Arrange
        Jugador jugador = listaJugadores.get(0);

        // Act
        Jugador actual = turnero.jugadorActual();

        // Assert
        Assertions.assertEquals(jugador, actual);
        Assertions.assertEquals("jugador1", actual.obtenerNombre());
    }

    @Test
    void test02PasarElTurnoDebeCambiarCorrectamenteElJugadorActual(){
        // Arrange
        Jugador anterior = turnero.jugadorActual();
        Jugador jugador1 = listaJugadores.get(0);
        Jugador jugador2 = listaJugadores.get(1);

        // Act
        turnero.pasarTurno();
        Jugador actual = turnero.jugadorActual();

        // Assert
        Assertions.assertEquals(jugador1, anterior);
        Assertions.assertEquals(jugador2, actual);
        Assertions.assertNotEquals(jugador1, jugador2);
        Assertions.assertNotEquals(anterior, actual);

    }

    @Test
    void test03CantidadDeJugadoresNoCambiaAlPasarElTurno() {
        // Arrange
        int cantidadDeJugadoresPreCambio = turnero.cantidadJugadores();

        // Act
        turnero.pasarTurno();
        int cantidadDeJugadoresPostCambio = turnero.cantidadJugadores();

        // Assert
        Assertions.assertEquals(cantidadDeJugadoresPreCambio, cantidadDeJugadoresPostCambio);
        Assertions.assertEquals(4, cantidadDeJugadoresPreCambio);
        Assertions.assertEquals(4, cantidadDeJugadoresPostCambio);
    }

    @Test
    void test04NumeroDeTurnoDebeCambiarAlPasarElTurno() {
        // Arrange
        int numeroDeTurnoPreCambio = turnero.numeroDeTurno();

        // Act
        turnero.pasarTurno();
        int numeroDeTurnoPostCambio = turnero.numeroDeTurno();

        // Assert
        Assertions.assertNotEquals(numeroDeTurnoPreCambio,numeroDeTurnoPostCambio);
        Assertions.assertEquals(1,numeroDeTurnoPreCambio);
        Assertions.assertEquals(2,numeroDeTurnoPostCambio);
    }

    @Test
    void test05ElTurnoDebeVolverAlJugadorOriginalLuegoDeUnCiclo() {
        // Arrange
        Jugador primerJugador = turnero.jugadorActual();
        int numeroTurnoPrimerJugador = turnero.numeroDeTurno();
        int cicloCompleto = turnero.cantidadJugadores();

        // Act
        turnero.pasarTurno(); // Jugador2
        turnero.pasarTurno(); // Jugador3
        turnero.pasarTurno(); // Jugador4
        turnero.pasarTurno(); // Jugador1 de nuevo
        int numeroTurnoActual = turnero.numeroDeTurno();

        // Assert
        Assertions.assertEquals(numeroTurnoPrimerJugador + cicloCompleto, numeroTurnoActual);
        Assertions.assertEquals(primerJugador, turnero.jugadorActual());
        Assertions.assertEquals("jugador1", primerJugador.obtenerNombre());
    }
}
