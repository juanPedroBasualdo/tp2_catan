package edu.fiuba.algo3.modelo.tablero.terreno;

import edu.fiuba.algo3.modelo.tablero.Recurso;

public class Pastizal extends Terreno{

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
