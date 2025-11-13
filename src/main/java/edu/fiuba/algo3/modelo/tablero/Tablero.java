package edu.fiuba.algo3.modelo.tablero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
            for(int j = 0 ; j < terrenos[i].length ; i++){
                listaTerrenos.add(terrenos[i][j]); 
            }
        }
        return listaTerrenos;
    }

}
