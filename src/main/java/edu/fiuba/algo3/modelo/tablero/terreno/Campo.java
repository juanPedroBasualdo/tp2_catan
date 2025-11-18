package edu.fiuba.algo3.modelo.tablero.terreno;

import edu.fiuba.algo3.modelo.tablero.Recurso;

public class Campo extends Terreno{

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
