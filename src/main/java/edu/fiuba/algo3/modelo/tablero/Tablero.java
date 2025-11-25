package edu.fiuba.algo3.modelo.tablero;

import java.util.*;

import edu.fiuba.algo3.modelo.banca.Banca;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.coordenada.Coordenada;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Arista;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Terreno;
import edu.fiuba.algo3.modelo.tablero.terreno.TerrenoTipo;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Vertice;

public class Tablero {
    private final Terreno[][] terrenos = new Terreno[5][];
    private final List<Vertice> vertices = new ArrayList<>();
    private final Map<String, Arista> aristas = new HashMap<>();


    public Tablero() {
        this(new Random());
    }

    public Tablero(long seed) {
        this(new Random(seed));
    }

    public Tablero(Random random) {
        this.armarFormaDeTablero();
        this.generarTerrenos(random);
        this.asignarVertices();
        this.asignarAristas();
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

    private static final int[][] indiceVerticesDeCadaTerreno = {
            {0, 1, 2, 3, 4, 5},         // terreno 0
            {6, 7, 8, 9, 2, 1},         // terreno 1
            {10, 11, 12, 13, 8, 7},     // terreno 2
            {4, 3, 14, 15, 16, 17},     // terreno 3
            {2, 9, 18, 19, 14, 3},      // terreno 4
            {8, 13, 20, 21, 18, 9},     // terreno 5
            {12, 22, 23, 24, 20, 13},   // terreno 6
            {16, 15, 25, 26, 27, 28},   // terreno 7
            {14, 19, 29, 30, 25, 15},   // terreno 8
            {18, 21, 31, 32, 29, 19},   // terreno 9
            {20, 24, 33, 34, 31, 21},   // terreno 10
            {23, 35, 36, 37, 33, 24},   // terreno 11
            {25, 30, 38, 39, 40, 26},   // terreno 12
            {29, 32, 41, 42, 38, 30},   // terreno 13
            {31, 34, 43, 44, 41, 32},   // terreno 14
            {33, 37, 45, 46, 43, 34},   // terreno 15
            {38, 42, 47, 48, 49, 39},   // terreno 16
            {41, 44, 50, 51, 47, 42},   // terreno 17
            {43, 46, 52, 53, 50, 44}    // terreno 18
    };

    public List<Vertice> generarListadoVertices(int indiceTablero) {
        List<Vertice> verticesTerreno = new ArrayList<>();
        for(int i = 0 ; i < indiceVerticesDeCadaTerreno[indiceTablero].length ; i++) {
            Vertice nuevoVertice = new Vertice(indiceVerticesDeCadaTerreno[indiceTablero][i]);
            verticesTerreno.add(nuevoVertice);
            this.vertices.add(nuevoVertice);
        }
        return verticesTerreno;
    }

    public List<Terreno> getTerrenos() {
        List<Terreno> listaTerrenos = new ArrayList<>();
        for (Terreno[] terreno : terrenos) {
            listaTerrenos.addAll(Arrays.asList(terreno));
        }
        return listaTerrenos;
    }

    private void asignarVertices() {
        List<Terreno> terrenosComoLista = this.getTerrenos();
        for(int i = 0 ; i < terrenosComoLista.size() ; i++) {
            Terreno t = terrenosComoLista.get(i);
            t.agregarVertice(this.generarListadoVertices(i));
        }
    }

    private static final int[][] verticesDeCadaArista = new int[][]{
            {0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 0},                   // terreno 0
            {6, 7}, {7, 8}, {8, 9}, {9, 2}, {2, 1}, {1, 6},                   // terreno 1
            {10, 11}, {11, 12}, {12, 13}, {13, 8}, {8, 7}, {7, 10},           // terreno 2
            {4, 3}, {3, 14}, {14, 15}, {15, 16}, {16, 17}, {17, 4},           // terreno 3
            {2, 9}, {9, 18}, {18, 19}, {19, 14}, {14, 3}, {3, 2},             // terreno 4
            {8, 13}, {13, 20}, {20, 21}, {21, 18}, {18, 9}, {9, 8},           // terreno 5
            {12, 22}, {22, 23}, {23, 24}, {24, 20}, {20, 13}, {13, 12},       // terreno 6
            {16, 15}, {15, 25}, {25, 26}, {26, 27}, {27, 28}, {28, 16},       // terreno 7
            {14, 19}, {19, 29}, {29, 30}, {30, 25}, {25, 15}, {15, 14},       // terreno 8
            {18, 21}, {21, 31}, {31, 32}, {32, 29}, {29, 19}, {19, 18},       // terreno 9
            {20, 24}, {24, 33}, {33, 34}, {34, 31}, {31, 21}, {21, 20},       // terreno 10
            {23, 35}, {35, 36}, {36, 37}, {37, 33}, {33, 24}, {24, 23},       // terreno 11
            {25, 30}, {30, 38}, {38, 39}, {39, 40}, {40, 26}, {26, 25},       // terreno 12
            {29, 32}, {32, 41}, {41, 42}, {42, 38}, {38, 30}, {30, 29},       // terreno 13
            {31, 34}, {34, 43}, {43, 44}, {44, 41}, {41, 32}, {32, 31},       // terreno 14
            {33, 37}, {37, 45}, {45, 46}, {46, 43}, {43, 34}, {34, 33},       // terreno 15
            {38, 42}, {42, 47}, {47, 48}, {48, 49}, {49, 39}, {39, 38},       // terreno 16
            {41, 44}, {44, 50}, {50, 51}, {51, 47}, {47, 42}, {42, 41},       // terreno 17
            {43, 46}, {46, 52}, {52, 53}, {53, 50}, {50, 44}, {44, 43},       // terreno 18
    };

    private void asignarAristas() {
        List<Terreno> terrenosComoLista = this.getTerrenos();
        for(int i = 0 ; i < terrenosComoLista.size(); i++) {
            terrenosComoLista.get(i).agregarArista(this.asignarAristas(i));
        }
    }

    private List<Arista> asignarAristas(int indiceTerreno) {
        List<Arista> aristasDeTerreno = new ArrayList<>();
        for(int i = (indiceTerreno * 6) ; i < ((indiceTerreno + 1) * 6) ; i++) {
            int[] par = verticesDeCadaArista[i];
            int v1 = Math.min(par[0], par[1]);      // v1 siempre es el menor del par
            int v2 = Math.max(par[0], par[1]);      // v2 siempre es el mayor del par
            String key = v1 + "-" + v2;             // clave única ej: {1,2} → "1-2"
            Arista arista = aristas.get(key);
            if (arista == null) {
                Vertice vertice1 = vertices.get(v1);
                Vertice vertice2 = vertices.get(v2);
                arista = new Arista(key, vertice1, vertice2);
                vertice1.asignarAdyacente(vertice2);
                vertice2.asignarAdyacente(vertice1);
                aristas.put(key, arista);
            }
            aristasDeTerreno.add(arista);
        }
        return aristasDeTerreno;
    }

    public static Map<Recurso, Long> producirRecursos(int numero, List<Terreno> terreno) {
        List<Terreno> produccion = new ArrayList<>();
        for(Terreno t : terreno) {
            if(!t.tieneLadron()) {
                produccion.add(t);
            }
        }
        return produccion.stream().map(Terreno::getRecurso).collect(java.util.stream.Collectors.groupingBy(t -> t, java.util.stream.Collectors.counting()));
    }

    // TODO ver si es apropiado usar banca en Tablero

    public void mejorarPoblado(Jugador jugador, Coordenada coordenadaPoblado) {
        if (this.puedeMejorarPoblado(jugador,coordenadaPoblado)) {

            terrenos[coordenadaPoblado.x()][coordenadaPoblado.y()].construirCiudad(jugador, coordenadaPoblado.vertex());

            List<Recurso> listaRecursosCiudad = Arrays.asList(Recurso.CEREAL, Recurso.CEREAL, Recurso.MINERAL, Recurso.MINERAL, Recurso.MINERAL);
            Banca.extraerRecursos(jugador,listaRecursosCiudad);
            Banca.otorgarPuntaje(jugador, 2);
        }
    }

    public void colocarPoblado(Jugador jugador, Coordenada coordenada) {
        if(this.puedeColocarPoblado(jugador, coordenada)) {
            terrenos[coordenada.x()][coordenada.y()].colocarPoblado(jugador, coordenada.vertex());

            List<Recurso> listaRecursosPoblado = Arrays.asList(Recurso.MADERA, Recurso.ARCILLA, Recurso.LANA, Recurso.CEREAL);
            Banca.extraerRecursos(jugador, listaRecursosPoblado);
            Banca.otorgarPuntaje(jugador,1);
        }

    }

    public boolean puedeColocarPoblado(Jugador jugador1, Coordenada coordenada) {
        return this.terrenos[coordenada.x()][coordenada.y()].puedeColocarPoblado(coordenada.vertex());
    }

    public boolean puedeMejorarPoblado(Jugador jugador1, Coordenada coordenada) {
        return this.terrenos[coordenada.x()][coordenada.y()].validarMejoraDePoblado(jugador1,coordenada.vertex());
    }

    public List<Terreno> getTerrenosAdyacentes(Coordenada coordenada) {
        List<Coordenada> direcciones = coordenada.obtenerDireccionesAdyacentes();
        List<Terreno> adyacentes = new ArrayList<>();
        Vertice vertice = this.getTerreno(coordenada).verticeEn(coordenada.vertex());
        for(Coordenada c : direcciones) {
            Terreno t = this.getTerreno(coordenada.aplicarDireccion(c));
            if(t.contieneVertice(vertice)) {
                adyacentes.add(t);
            }
        }
        return adyacentes;
    }

    public Terreno getTerreno(Coordenada coordenada) {
        return terrenos[coordenada.x()][coordenada.y()];
    }

    public void otorgarRecursosIniciales(Jugador jugador, Coordenada coordenada) {
        List<Terreno> adyacentes = this.getTerrenosAdyacentes(coordenada);

        for (Terreno terreno : adyacentes) {
            jugador.agregarRecurso(terreno.getRecurso());
        }
    }

    public List<Vertice> getVertices() {
        return Collections.unmodifiableList(vertices);
    }
}
