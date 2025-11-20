package edu.fiuba.algo3.modelo.tablero;

import java.util.*;

import edu.fiuba.algo3.modelo.Banca;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tablero.terreno.Terreno;
import edu.fiuba.algo3.modelo.tablero.terreno.TerrenoTipo;

public class Tablero {
    private final Terreno[][] terrenos = new Terreno[5][];

    public Tablero() {
        this.armarFormaDeTablero();
        Random random = new Random();
        generarTerrenos(random);
    }

    public Tablero(long seed) {
        this.armarFormaDeTablero();
        Random random = new Random(seed);
        generarTerrenos(random);
    }

    public static Map<Recurso, Long> producirRecursos(int tirar, List<Terreno> terreno) {
        List<Terreno> produccion = new ArrayList<>();
        for(Terreno t : terreno) {
            if(!t.tieneLadron()) {
                produccion.add(t);
            }
        }
        return produccion.stream().map(Terreno::getRecurso).collect(java.util.stream.Collectors.groupingBy(t -> t, java.util.stream.Collectors.counting()));
    }

    private void generarTerrenos(Random random) {
        List<TerrenoTipo> terrenosTipos = new ArrayList<>(List.of(
            TerrenoTipo.CAMPO, TerrenoTipo.CAMPO, TerrenoTipo.CAMPO, TerrenoTipo.CAMPO,
            TerrenoTipo.PASTIZAL, TerrenoTipo.PASTIZAL, TerrenoTipo.PASTIZAL, TerrenoTipo.PASTIZAL,
            TerrenoTipo.BOSQUE, TerrenoTipo.BOSQUE, TerrenoTipo.BOSQUE, TerrenoTipo.BOSQUE,
            TerrenoTipo.CERRO, TerrenoTipo.CERRO, TerrenoTipo.CERRO,
            TerrenoTipo.MONTANIA, TerrenoTipo.MONTANIA, TerrenoTipo.MONTANIA,
            TerrenoTipo.DESIERTO
        ));

        Collections.shuffle(terrenosTipos, random);

        List<Integer> fichas = new ArrayList<>(List.of(2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12));
        Collections.shuffle(fichas, random);

        
        for(int i = 0 ; i < terrenos.length; i++) {
            for(int j = 0 ; j < terrenos[i].length ; j++) {
                if(terrenosTipos.get(0) == TerrenoTipo.DESIERTO) {
                    terrenos[i][j] = Terreno.crear(terrenosTipos.remove(0), 0);
                } else {
                    terrenos[i][j] = Terreno.crear(terrenosTipos.remove(0), fichas.remove(0)); 
                }            
            }
        }

    }

    public void armarFormaDeTablero() {
        terrenos[0] = new Terreno[3];
        terrenos[4] = new Terreno[3];

        terrenos[1] = new Terreno[4];
        terrenos[3] = new Terreno[4];

        terrenos[2] = new Terreno[5];
    }

    public List<Terreno> getTerrenos() {
        List<Terreno> listaTerrenos = new ArrayList<>();
        for(int i = 0 ; i < terrenos.length ; i++) {
            for(int j = 0 ; j < terrenos[i].length ; j++){
                listaTerrenos.add(terrenos[i][j]);
            }
        }
        return listaTerrenos;
    }

    public void colocarPoblado(Jugador jugador, Coordenada coordenada) {
        if(this.puedeColocarPoblado(jugador, coordenada)) {
            terrenos[coordenada.getX()][coordenada.getY()].colocarPoblado(jugador, coordenada.getZ());

            /*
            TODO Hacer la logica para aplicar recursos y puntaje con polimorfismo
            Banca.extraerRecursos(jugador1, Banca.precioDe(PiezaTipo.POBLADO));
            Banca.otorgarPuntaje(jugador1, Banca.puntajeDe(PiezaTipo.POBLADO));
            */

            // Temporal!!
            List<Recurso> listaPrecioRecursosPoblado = Arrays.asList(Recurso.MADERA,Recurso.ARCILLA,Recurso.LANA,Recurso.CEREAL);
            Banca.extraerRecursos(jugador, listaPrecioRecursosPoblado);
            Banca.otorgarPuntaje(jugador,1);
        }
    }

    public void mejorarPoblado(Jugador jugador, Coordenada coordenadaPoblado) {
        if(jugador.tieneRecursos(Banca.precioDe(PiezaTipo.CIUDAD)) && puedeMejorarPoblado(jugador, coordenadaPoblado)) {
            terrenos[coordenadaPoblado.getX()][coordenadaPoblado.getY()].construirCiudad(jugador, coordenadaPoblado.getZ());

            /*
            TODO Hacer la logica para aplicar recursos y puntaje con polimorfismo
            Banca.extraerRecursos(jugador, Banca.precioDe(PiezaTipo.CIUDAD));
            Banca.otorgarPuntaje(jugador, Banca.puntajeDe(PiezaTipo.CIUDAD));
            */

            List<Recurso> listaPrecioRecursosCiudad = Arrays.asList(Recurso.MINERAL,Recurso.MINERAL,Recurso.MINERAL,Recurso.CEREAL,Recurso.CEREAL);
            Banca.extraerRecursos(jugador, listaPrecioRecursosCiudad);
            Banca.otorgarPuntaje(jugador,2);
        }
    }

    public boolean puedeMejorarPoblado(Jugador jugador1, Coordenada coordenada) {
        return terrenos[coordenada.getX()][coordenada.getY()].validarMejoraDePoblado(jugador1, coordenada.getZ());
    }

    public boolean puedeColocarPoblado(Jugador jugador1, Coordenada coordenada) {
        return false;
    }


    // TODO para tests mientras decidimos en la forma definitiva de Tablero
    public boolean esCoordenadaPuerto(Coordenada coordenada) { return false; } ;


    public List<Terreno> getHexagonosAdyacentes(Coordenada coordenada) {
        return new ArrayList<Terreno>();
    }

    public void otorgarRecursosIniciales(Jugador jugador, Coordenada coordenada) {
        List<Terreno> adyacentes = this.getHexagonosAdyacentes(coordenada);

        for (Terreno terreno : adyacentes) {
            jugador.agregarRecurso(terreno.getRecurso());
        }
    }

}
