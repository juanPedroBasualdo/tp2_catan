package edu.fiuba.algo3.modelo.tablero;

import java.util.*;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tablero.terreno.Arista;
import edu.fiuba.algo3.modelo.tablero.terreno.Terreno;
import edu.fiuba.algo3.modelo.tablero.terreno.TerrenoTipo;
import edu.fiuba.algo3.modelo.tablero.terreno.Vertice;

public class Tablero {
    private final List<Terreno> terrenos = new ArrayList<>(19);
    private final List<Vertice> vertices = new ArrayList<>(54);
    private final List<Arista> aristas = new ArrayList<>(72);

    public Tablero() {
        Random random = new Random();
        generarTerrenos(random);
    }

    public Tablero(long seed) {
        Random random = new Random(seed);
        generarTerrenos(random);
        generarVertices();
        asignarVerticesATerrenos(terrenos);
        generarAristas();
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

        for (TerrenoTipo tipo : terrenosTipos) {
            if (tipo == TerrenoTipo.DESIERTO) {
                terrenos.add(Terreno.crear(tipo, 0));
            } else {
                terrenos.add(Terreno.crear(tipo, fichas.remove(0)));
            }
        }
    }

    private void generarVertices() {
        for (int i = 0; i < 54; i++) {
            vertices.add(new Vertice());
        }
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

    private void asignarVerticesATerrenos(List<Terreno> terrenos) {
        for (int i = 0; i < terrenos.size(); i++) {
            Terreno terreno = terrenos.get(i);
            for (int vertice : indiceVerticesDeCadaTerreno[i]) {
                Vertice v = vertices.get(vertice);
                terreno.agregarVertice(v);
            }
        }
    }

    private static final int[][] verticesDeCadaArista = {
            {0,1}, {1,2}, {2,3}, {3,4}, {4,5}, {5,0},                   // terreno 0
            {6,7}, {7,8}, {8,9}, {9,2}, {2,1}, {1,6},                   // terreno 1
            {10,11}, {11,12}, {12,13}, {13,8}, {8,7}, {7,10},           // terreno 2
            {4,3}, {3,14}, {14,15}, {15,16}, {16,17}, {17,4},           // terreno 3
            {2,9}, {9,18}, {18,19}, {19,14}, {14,3}, {3,2},             // terreno 4
            {8,13}, {13,20}, {20,21}, {21,18}, {18,9}, {9,8},           // terreno 5
            {12,22}, {22,23}, {23,24}, {24,20}, {20,13}, {13,12},       // terreno 6
            {16,15}, {15,25}, {25,26}, {26,27}, {27,28}, {28,16},       // terreno 7
            {14,19}, {19,29}, {29,30}, {30,25}, {25,15}, {15,14},       // terreno 8
            {18,21}, {21,31}, {31,32}, {32,29}, {29,19}, {19,18},       // terreno 9
            {20,24}, {24,33}, {33,34}, {34,31}, {31,21}, {21,20},       // terreno 10
            {23,35}, {35,36}, {36,37}, {37,33}, {33,24}, {24,23},       // terreno 11
            {25,30}, {30,38}, {38,39}, {39,40}, {40,26}, {26,25},       // terreno 12
            {29,32}, {32,41}, {41,42}, {42,38}, {38,30}, {30,29},       // terreno 13
            {31,34}, {34,43}, {43,44}, {44,41}, {41,32}, {32,31},       // terreno 14
            {33,37}, {37,45}, {45,46}, {46,43}, {43,34}, {34,33},       // terreno 15
            {38,42}, {42,47}, {47,48}, {48,49}, {49,39}, {39,38},       // terreno 16
            {41,44}, {44,50}, {50,51}, {51,47}, {47,42}, {42,41},       // terreno 17
            {43,46}, {46,52}, {52,53}, {53,50}, {50,44}, {44,43},       // terreno 18
    };

    private final Map<String, Arista> aristasExistentes = new HashMap<>();

    private void generarAristas() {
        for (int[] par : verticesDeCadaArista) {
            int v1 = Math.min(par[0], par[1]);      // v1 siempre es el menor del par
            int v2 = Math.max(par[0], par[1]);      // v2 siempre es el mayor del par
            String key = v1 + "-" + v2;             // clave única ej: {1,2} → "1-2"
            Arista arista = aristasExistentes.get(key);
            if (arista == null) {                   // para no repetir aristas
                Vertice vertice1 = vertices.get(v1);
                Vertice vertice2 = vertices.get(v2);
                arista = new Arista(vertice1, vertice2);
                vertice1.agregarArista(arista);
                vertice2.agregarArista(arista);
                aristas.add(arista);
                aristasExistentes.put(key, arista);
            }
        }
    }

    public List<Terreno> getTerrenos() {
        return terrenos;
    }

    /* TO DO ADAPTAR A NUEVA IMPLEMENTACION DEL TABLERO
    public static Map<Recurso, Long> producirRecursos(int tirar, List<Terreno> terreno) {
        List<Terreno> produccion = new ArrayList<>();
        for(Terreno t : terreno) {
            if(!t.tieneLadron()) {
                produccion.add(t);
            }
        }
        return produccion.stream().map(Terreno::getRecurso).collect(java.util.stream.Collectors.groupingBy(t -> t, java.util.stream.Collectors.counting()));
    }

    public void colocarPoblado(Jugador jugador, Coordenada coordenada) {
        if(this.puedeColocarPoblado(jugador, coordenada)) {
            terrenos[coordenada.getX()][coordenada.getY()].colocarPoblado(jugador, coordenada.getZ());
        }
    }

    public void mejorarPoblado(Jugador jugador, Coordenada coordenada) {
        if(this.existePuebloDeJugador(jugador, coordenada)) {
            terrenos[coordenada.getX()][coordenada.getY()].colocarCiudad(jugador, coordenada.getZ());
        }
    }

    public boolean existePuebloDeJugador(Jugador jugador1, Coordenada coordenada) {
        return false;
    }

    public boolean puedeColocarPoblado(Jugador jugador1, Coordenada coordenada) {
        return false;
    }

    public List<Terreno> getHexagonosAdyacentes(Coordenada coordenada) {
        return new ArrayList<Terreno>();
    }

    public void otorgarRecursosIniciales(Jugador jugador, Coordenada coordenada) {
        List<Terreno> adyacentes = this.getHexagonosAdyacentes(coordenada);

        for (Terreno terreno : adyacentes) {
            jugador.agregarRecurso(terreno.getRecurso());
        }
    }
    */
}
