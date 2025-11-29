package edu.fiuba.algo3.modelo.tablero.terreno.tipo;

import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Terreno;
import edu.fiuba.algo3.modelo.tablero.terreno.TerrenoTipo;

public class Desierto extends Terreno {

    public Desierto(){
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

    @Override
    public void producir() {}

}
