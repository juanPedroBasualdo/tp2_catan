package edu.fiuba.algo3.modelo.tablero.terreno.tipo;

import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Terreno;
import edu.fiuba.algo3.modelo.tablero.terreno.TerrenoTipo;

public class Campo extends Terreno {

    private final Recurso recurso = Recurso.LANA;

    public Campo(int fichaNumero) {
        super(fichaNumero);
    }

    public Recurso getRecurso() {
        return recurso;
    }

    @Override
    public TerrenoTipo getTipo() {
        return TerrenoTipo.CAMPO;
    }
    
}
