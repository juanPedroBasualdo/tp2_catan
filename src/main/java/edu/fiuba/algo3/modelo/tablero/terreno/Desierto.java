package edu.fiuba.algo3.modelo.tablero.terreno;

public class Desierto extends Terreno{

    Desierto(){
        super(0);
    }
    
    @Override
    public TerrenoTipo getTipo() {
        return TerrenoTipo.DESIERTO;
    }

}
