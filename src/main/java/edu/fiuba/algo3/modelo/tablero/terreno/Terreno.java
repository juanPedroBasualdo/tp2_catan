package edu.fiuba.algo3.modelo.tablero.terreno;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tablero.Pieza;
import edu.fiuba.algo3.modelo.tablero.PiezaTipo;
import edu.fiuba.algo3.modelo.tablero.Recurso;

public abstract class Terreno {

    private final int fichaNumero;
    private final Vertice[] vertices = new Vertice[6];
    private final Arista[] aristas = new Arista[6];


    protected Terreno(int fichaNumero) {
        this.fichaNumero = fichaNumero;
    }

    public int getFichaNumero() {
        return fichaNumero;
    }

    public static Terreno crear(TerrenoTipo tipo, int fichaNumero) throws IllegalArgumentException {
        switch (tipo) {
            case BOSQUE:
                return new Bosque(fichaNumero);
            case CAMPO:
                return new Campo(fichaNumero);
            case CERRO:
                return new Cerro(fichaNumero);
            case DESIERTO:
                return new Desierto();
            case MONTANIA:
                return new Montania(fichaNumero);
            case PASTIZAL:
                return new Pastizal(fichaNumero);
            default:
                throw new IllegalArgumentException("Tipo de terreno desconocido: " + tipo);
        }
    }

    public abstract TerrenoTipo getTipo();

    public abstract Recurso getRecurso();

    protected void setVertice(int i, Vertice v) { vertices[i] = v; }
    public void setArista(int i, Arista a) { aristas[i] = a; }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Terreno otro = (Terreno) obj;
        if(this.getFichaNumero() != otro.getFichaNumero()){
            return false;
        }

        if(this.getTipo() != otro.getTipo()) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + fichaNumero + ")";
    }

    public void colocarPoblado(Jugador jugador, int Vertice) {
        if(!vertices[Vertice].estaOcupado()) {
            vertices[Vertice].colocarPieza(Pieza.crearPieza(PiezaTipo.POBLADO, jugador), jugador);
        }
    }


    public boolean tienePobladoDe(Jugador jugador1) {
        return false;
    }

    public boolean tieneLadron() {
        return false;
    }

    public boolean tieneJugadorAdyacente(Jugador jugador) {
        return false;
    }

    public boolean tieneCiudadDe(Jugador jugador) {
        return false;
    }
}

