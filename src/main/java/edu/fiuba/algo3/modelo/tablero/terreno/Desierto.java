package edu.fiuba.algo3.modelo.tablero.terreno;

import edu.fiuba.algo3.modelo.tablero.Recurso;

public class Desierto extends Terreno{

    Desierto(){
        super(0);
    }
    
    @Override
    public TerrenoTipo getTipo() {
        return TerrenoTipo.DESIERTO;
    }

    @Override
    public Recurso getRecurso() {
        return null;
    }

}
