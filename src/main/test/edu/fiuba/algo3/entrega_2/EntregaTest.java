package edu.fiuba.algo3.entrega_2;

import java.util.Arrays;

import edu.fiuba.algo3.modelo.Banca;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tablero.Coordenada;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EntregaTest {
	/*
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

    @Test
    void test07intercambioInteriorDeberiaRealizarseSiElReceptorAcepta() {
        Jugador emisor = new Jugador("1");
        Jugador receptor = mock(Jugador.class);

        // el receptor acepta
        when(receptor.aceptarIntercambio(any())).thenReturn(true);

        // Mock de manos (si las tienes como clase aparte)
        Mano manoEmisor = mock(Mano.class);
        Mano manoReceptor = mock(Mano.class);
        emisor.setMano(manoEmisor);
        when(receptor.getMano()).thenReturn(manoReceptor);

        Intercambio intercambio = new Intercambio(
                new Recursos(Recurso.MADERA),
                new Recursos(Recurso.ARCILLA)
        );

        boolean resultado = emisor.intercambiarCon(receptor, intercambio);

        assertTrue(resultado);
        verify(manoEmisor).remover(intercambio.getOfrecido());
        verify(manoReceptor).agregar(intercambio.getOfrecido());
        verify(manoReceptor).remover(intercambio.getSolicitado());
        verify(manoEmisor).agregar(intercambio.getSolicitado());
    }

    @Test
    void test08intercambioInteriorNoDeberiaRealizarseSiElReceptorRechaza() {
        Jugador emisor = new Jugador("A");
        Jugador receptor = mock(Jugador.class);

        when(receptor.aceptarIntercambio(any())).thenReturn(false);

        Intercambio intercambio = mock(Intercambio.class);

        boolean resultado = emisor.intercambiarCon(receptor, intercambio);

        assertFalse(resultado);


        verify(receptor, never()).getMano();
    }

    @Test
    void test09comprarCartaDeDesarrolloDeberiaConsumirRecursosYAgregarAlMazoOculto() {
        Jugador jugador = new Jugador("A");

        Mano mano = mock(Mano.class);
        jugador.setMano(mano);

        MazoDesarrollo mazo = mock(MazoDesarrollo.class);
        jugador.setMazoDesarrollo(mazo);

        CartaDesarrollo carta = mock(CartaDesarrollo.class);
        Banca banca = mock(Banca.class);

        // La banca entrega una carta al azar
        when(banca.entregarCartaDesarrollo()).thenReturn(carta);

        jugador.comprarCartaDesarrollo(banca);

        // Verificar consumo de recursos
        verify(mano).remover(new Recursos(0,1,1,1,0)); 
        // costos típicos: oveja, trigo, piedra (ajustá según tu modelo)

        // verificar que fue al mazo oculto
        verify(mazo).agregarACartasOcultas(carta);
    }

    @Test
    void test00cartaCompradaNoPuedeUsarseEnElMismoTurno() {
        Jugador jugador = new Jugador("A");

        CartaDesarrollo carta = new CartaDesarrollo();
        carta.marcarCompradaEnTurno(10);

        // Turno actual también 10 → no válida para jugar
        assertThrows(
                CartaNoJugableEsteTurnoException.class,
                () -> jugador.jugarCarta(carta, 10)
        );
    }



	 */

}
