package edu.fiuba.algo3.modelo.tablero.terreno.tipo;

import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Terreno;
import edu.fiuba.algo3.modelo.tablero.terreno.TerrenoTipo;

public class Pastizal extends Terreno {

    private final Recurso recurso = Recurso.LANA; 

    public Pastizal(int fichaNumero) {
        super(fichaNumero);
    }

    public Recurso getRecurso() {
        return this.recurso;
    }

    @Override
    public TerrenoTipo getTipo() {
        return TerrenoTipo.PASTIZAL;
    }

}
