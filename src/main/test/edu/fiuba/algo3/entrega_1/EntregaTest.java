package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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


    	List<Integer> fichas = terA.stream().filter(h -> !(h.getTipo() == TerrenoTipo.DESIERTO)).map(Terreno::getFichaNumero).sorted().toList();

    	List<Integer> esperado = List.of(2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12);
    	assertEquals(esperado, fichas, "Las fichas de número deben coincidir con la configuración estándar de Catán");

    	boolean iguales = true;
    	for (int i = 0; i < terA.size(); i++) {
    	    if (terA.get(i).getTipo() != terB.get(i).getTipo() ||
    	        terA.get(i).getFichaNumero() != terB.get(i).getFichaNumero()) {
    	        iguales = false;
    	        break;
    	    }
    	}
    	assertFalse(iguales, "Dos tableros generados con seeds distintas deben ser diferentes");
    }
    
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
        when(tableroMock.getHexagonosAdyacentes(new Coordenada(2,2,2))).thenReturn(adyacentes);

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

    void test04LanzamientoDeDadosGeneraNumeroValido() {
        int resultado = dados.lanzar();

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

        when(dados.lanzar()).thenReturn(8);

        Map<Jugador, List<Recurso>> produccion = Produccion.producirRecursos(dados.lanzar(), List.of(bosque));

        assertEquals(1, produccion.get(jugador1).size(),
            "Un poblado produce 1 recurso adyacente al número lanzado");
        assertEquals(Recurso.MADERA, produccion.get(jugador1).get(0));
    }
    
    @Test
    void test06TerrenoBajoLadronNoProduceRecursos() {
        Terreno terreno = mock(Bosque.class);
        when(terreno.getFichaNumero()).thenReturn(8);
        when(terreno.tienePobladoDe(jugador1)).thenReturn(true);
        when(terreno.tieneLadron()).thenReturn(true);

        when(dadosMock.lanzar()).thenReturn(8);

        Map<Jugador, List<Recurso>> produccion = Tablero.producirRecursos(dados.lanzar(), List.of(terreno));

        assertTrue(produccion.getOrDefault(jugador1, List.of()).isEmpty(),
            "El hexágono con el ladrón no debe producir recursos");
    }
    
    @Test
    void test07JugadorDescartaLaMitadAlLanzar7() {
        jugador1.agregarRecursos(Arrays.asList(
            Recurso.MADERA, Recurso.MADERA, Recurso.CEREAL,
            Recurso.LANA, Recurso.MINERAL, Recurso.MADERA, Recurso.LANA
        ));

        when(dadosMock.lanzar()).thenReturn(7);

        int antes = jugador1.cantidadDeRecursos();
        jugador1.descartarPorLadron();

        int esperado = antes / 2;
        assertEquals(esperado, jugador1.cantidadDeRecursos(),
            "El jugador debe descartar la mitad de sus cartas (redondeando hacia abajo)");
    }

    @Test
    void test08MoverLadronYRobarCartaAleatoria() {
        Terreno origen = mock(Desierto.class);
        Terreno destino = mock(Bosque.class);

        jugador2.agregarRecursos(List.of(Recurso.LANA, Recurso.MADERA));

        when(destino.tieneJugadorAdyacente(jugador2)).thenReturn(true);
        when(ladron.moverA(destino)).thenReturn(true);

        ladron.moverA(destino);
        Recurso robado = ladron.robarCartaAleatoria(jugador2, jugador1);

        assertNotNull(robado, "Debe robarse una carta al mover el ladrón a un hexágono adyacente a otro jugador");
        assertTrue(jugador1.tieneRecurso(robado), "El jugador activo debe recibir la carta robada");
        assertFalse(jugador2.tieneRecurso(robado), "El jugador afectado debe perder la carta robada");
    }
    
}
