package edu.fiuba.algo3.modelo.tablero.terreno.parte;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.Pieza;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.PiezaTipo;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.terreno.TerrenoTipo;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.Poblado;
import edu.fiuba.algo3.modelo.tablero.terreno.tipo.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class Terreno {

    private final int fichaNumero;
    private final List<Vertice> vertices;
    private final List<Arista> aristas;

    protected Terreno(int fichaNumero) {
        this.fichaNumero = fichaNumero;
        this.vertices = new ArrayList<>();
        this.aristas = new ArrayList<>();
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

    public void agregarVertice(Collection<Vertice> vertices) {
        this.vertices.addAll(vertices);
    }

    public void agregarArista(Collection<Arista> aristas) {
        this.aristas.addAll(aristas);
    }

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

        return this.getTipo() == otro.getTipo();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + fichaNumero + ")";
    }

    public void construirCiudad(Jugador jugador1, int vertice) {
        vertices.get(vertice).mejorarPoblado(jugador1);
    }
    public boolean validarMejoraDePoblado(Jugador jugador1, int vertice) {
        return (vertices.get(vertice).validarDatosMejoraCiudad(jugador1));
    }

    public boolean tienePobladoDe(Jugador jugador1) {
        return false;
    }

    public boolean tieneLadron() {
        return false;
    }

    public boolean puedeColocarPoblado(int indiceVertice) {
        return vertices.get(indiceVertice).esValido();
    }

    public void colocarPoblado(Jugador jugador, int indiceVertice) {
        this.vertices.get(indiceVertice).colocarPieza(new Poblado(jugador));
    }

    public Object tieneCiudadDe(Jugador jugador1) {
        return false;
    }

    public Vertice verticeEn(int vertex) {
        return this.vertices.get(vertex);
    }

    public boolean contieneVertice(Vertice vertice) {
        return this.vertices.contains(vertice);
    }

}

