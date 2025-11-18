package edu.fiuba.algo3.modelo.tablero.terreno;

import edu.fiuba.algo3.modelo.tablero.Recurso;

public class Bosque extends Terreno{

    private final Recurso recurso = Recurso.MADERA;

    public Bosque(int fichaNumero) {
        super(fichaNumero);
    }

    public Recurso getRecurso() {
        return this.recurso;
    }

    @Override
    public TerrenoTipo getTipo() {
        return TerrenoTipo.BOSQUE;
    }

    

}
