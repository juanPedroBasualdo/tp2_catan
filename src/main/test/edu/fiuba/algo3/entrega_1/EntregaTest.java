package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.*;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.tablero.terreno.*;
import edu.fiuba.algo3.modelo.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class EntregaTest {
	
    private Tablero tablero, tableroMock;
    private Dados dados, dadosMock;
    private Ladron ladron;
    private Jugador jugador1;
    private Jugador jugador2;
	
    @BeforeEach
    void setUp() {
    	tablero = new Tablero();
        tableroMock = mock(Tablero.class);
        dados = new Dados();
        dadosMock = mock(Dados.class);
        ladron = mock(Ladron.class);
        jugador1 = new Jugador("Jugador 1");
        jugador2 = new Jugador("Jugador 2");
    }
    
    @Test
    void test01AsignacionAleatoriaDeHexagonosYFichas() {
    	 Tablero tableroA = new Tablero(1234L);
    	 Tablero tableroB = new Tablero(5678L);

    	List<Terreno> terA = tableroA.getTerrenos();
    	List<Terreno> terB = tableroB.getTerrenos();

    	 
    	assertEquals(19, terA.size(), "Debe haber exactamente 19 hexágonos");
    	assertEquals(19, terB.size(), "Debe haber exactamente 19 hexágonos");

    	Map<TerrenoTipo, Long> conteo = terA.stream().map(Terreno::getTipo)
                .collect(java.util.stream.Collectors.groupingBy(t -> t, java.util.stream.Collectors.counting()));

    	assertEquals(4, conteo.getOrDefault(TerrenoTipo.CAMPO, 0L), "Debe haber 4 campos");
    	assertEquals(4, conteo.getOrDefault(TerrenoTipo.PASTIZAL, 0L), "Debe haber 4 pastizales");
    	assertEquals(4, conteo.getOrDefault(TerrenoTipo.BOSQUE, 0L), "Debe haber 4 bosques");
    	assertEquals(3, conteo.getOrDefault(TerrenoTipo.CERRO, 0L), "Debe haber 3 cerros");
    	assertEquals(3, conteo.getOrDefault(TerrenoTipo.MONTANIA, 0L), "Debe haber 3 montañas");
    	assertEquals(1, conteo.getOrDefault(TerrenoTipo.DESIERTO, 0L), "Debe haber 1 desierto");


    	List<Integer> fichas = terA.stream().filter(h -> !(h.getTipo() == TerrenoTipo.DESIERTO)).map(Terreno::getFichaNumero).sorted().collect(java.util.stream.Collectors.toList());;

    	List<Integer> esperado = List.of(2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12);
    	assertEquals(esperado, fichas, "Las fichas de número deben coincidir con la configuración estándar de Catán");

    	boolean iguales = true;
        // Iteramos por cada Terreno en los Tableros para verificar si existe uno que sea diferente
    	for (int i = 0; i < terA.size(); i++) {
    	    if (terA.get(i).getTipo() != terB.get(i).getTipo() ||
    	        terA.get(i).getFichaNumero() != terB.get(i).getFichaNumero()) {
    	        iguales = false;
    	        break;
    	    }
    	}
    	assertFalse(iguales, "Dos tableros generados con seeds distintas deben ser diferentes");
    }

    @Test
    void test02ReglaDeDistanciaEntrePobladosIniciales() {
    	tablero.colocarPoblado(jugador1, new Coordenada(0,0,0));

        boolean permitido = tablero.puedeColocarPoblado(jugador1, new Coordenada(0,0,1));
        assertFalse(permitido, 
        		"No debería poder colocarse un poblado a menos de 2 caminos de otro");
    }
    
    @Test
    void test03RecursosInicialesDelSegundoPoblado() {
        // Mocks de hexágonos adyacentes al segundo poblado
        Terreno h1 = mock(Bosque.class);
        Terreno h2 = mock(Campo.class);
        Terreno h3 = mock(Pastizal.class);

        when(h1.getRecurso()).thenReturn(Recurso.MADERA);
        when(h2.getRecurso()).thenReturn(Recurso.CEREAL);
        when(h3.getRecurso()).thenReturn(Recurso.LANA);

        List<Terreno> adyacentes = List.of(h1, h2, h3);
        when(tableroMock.getHexagonosAdyacentes(any(Coordenada.class))).thenReturn(adyacentes);
        doCallRealMethod()
                .when(tableroMock)
                .otorgarRecursosIniciales(any(Jugador.class), any(Coordenada.class));
        // Se omite la colocacion del primer poblado
        
        tableroMock.colocarPoblado(jugador1, new Coordenada(2,2,2)); // Segundo poblado

        // Se otorgan los recursos iniciales
        tableroMock.otorgarRecursosIniciales(jugador1, new Coordenada(2,2,2));

        assertTrue(jugador1.tieneRecurso(Recurso.MADERA));
        assertTrue(jugador1.tieneRecurso(Recurso.CEREAL));
        assertTrue(jugador1.tieneRecurso(Recurso.LANA));

        assertEquals(3, jugador1.cantidadDeRecursos(),
            "El jugador debe recibir 1 recurso por cada Terreno adyacente al segundo poblado");
    }

    @Test
    void test04LanzamientoDeDadosGeneraNumeroValido() {
        int resultado = dados.tirar();

        assertTrue(resultado >= 2 && resultado <= 12,
            "El número de los dados debe estar entre 2 y 12");
    }
    
    @Test
    void test05ProduccionCorrectaDeRecursos() {
        Terreno bosque = mock(Bosque.class);
        when(bosque.getFichaNumero()).thenReturn(8);
        when(bosque.tieneCiudadDe(jugador1)).thenReturn(false);
        when(bosque.tienePobladoDe(jugador1)).thenReturn(true);
        when(bosque.getRecurso()).thenReturn(Recurso.MADERA);

        when(dadosMock.tirar()).thenReturn(8);

        Map<Recurso, Long> produccion = Tablero.producirRecursos(dadosMock.tirar(), List.of(bosque));

        assertEquals(1, List.of(produccion.get(Recurso.MADERA)).size(),
            "Un poblado produce 1 recurso adyacente al número lanzado");
    }

    @Test
    void test06TerrenoBajoLadronNoProduceRecursos() {
        Terreno terreno = mock(Bosque.class);
        when(terreno.getFichaNumero()).thenReturn(8);
        when(terreno.tienePobladoDe(jugador1)).thenReturn(true);
        when(terreno.tieneLadron()).thenReturn(true);

        when(dadosMock.tirar()).thenReturn(8);

        Map<Recurso, Long> produccion = Tablero.producirRecursos(dados.tirar(), List.of(terreno));

        assertTrue(produccion.isEmpty(),
            "El hexágono con el ladrón no debe producir recursos");
    }
    
    @Test
    void test07JugadorDescartaLaMitadAltirar7() {
        jugador1.agregarRecursos(Arrays.asList(
            Recurso.MADERA, Recurso.MADERA, Recurso.CEREAL,
            Recurso.LANA, Recurso.MINERAL, Recurso.MADERA, Recurso.LANA
        ));

        when(dadosMock.tirar()).thenReturn(7);

        int antes = jugador1.cantidadDeRecursos();
        jugador1.descartarPorLadron();

        int esperado = antes / 2;
        assertEquals(esperado, jugador1.cantidadDeRecursos(),
            "El jugador debe descartar la mitad de sus cartas (redondeando hacia abajo)");
    }

    @Test
    void test08MoverLadronYRobarCartaAleatoria() throws Exception {
        // --- Arrange ---
        Ladron ladron = new Ladron();

        Jugador jugadorRobador = new Jugador("Jugador 1");   // jugador activo
        Jugador jugadorRobado = new Jugador("Jugador 2");
        jugadorRobado.agregarRecursos(List.of(Recurso.LANA, Recurso.MADERA));

        Field randomField = Ladron.class.getDeclaredField("random");
        randomField.setAccessible(true);
        randomField.set(ladron, new Random(0)); // semilla fija

        Terreno destino = mock(Terreno.class);

        // --- Act ---
        ladron.moverA(destino);
        Recurso robado = ladron.robarCartaAleatoria(jugadorRobado, jugadorRobador);

        // --- Assert ---
        assertNotNull(robado, "Debe devolver un recurso robado");
        assertEquals(1, jugadorRobado.cantidadDeRecursos(),
                "El jugador robado debe perder exactamente 1 recurso");
        assertEquals(1, jugadorRobador.cantidadDeRecursos(),
                "El jugador robador debe ganar exactamente 1 recurso");
        assertFalse(jugadorRobado.tieneRecurso(robado),
                "El jugador robado ya no debe tener el recurso robado");
        assertTrue(jugadorRobador.tieneRecurso(robado),
                "El jugador robador debe tener el recurso robado");
    }


}
