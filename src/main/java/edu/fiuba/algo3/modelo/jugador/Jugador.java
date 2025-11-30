package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.banca.Banca;
import edu.fiuba.algo3.modelo.cartasDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.cartasDesarrollo.PuntoDeVictoria;
import edu.fiuba.algo3.modelo.puntajeYBonificaciones.Puntaje;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.Construccion;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.Productor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Jugador {

    private final String nombre;
    private final List<Recurso> recursos;
    private List<CartaDesarrollo> cartasDesarrollo;
    private final Random random = new Random();
    private List<Construccion> construcciones;
    private int cantidadDeCaballerosJugados;

    // TODO hacer el puntaje con una clase de Puntaje
    private Integer puntaje;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.recursos = new ArrayList<>();
        this.construcciones = new ArrayList<>();
        this.cartasDesarrollo = new ArrayList<CartaDesarrollo>();
        this.puntaje = 0;
        this.cantidadDeCaballerosJugados = 0;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void agregarRecursos(List<Recurso> nuevos) {
        this.recursos.addAll(nuevos);
    }

    public void agregarRecurso(Recurso recurso) {
        this.recursos.add(recurso);
    }

    public boolean tieneRecurso(Recurso recursoBuscado) {
        return this.recursos.contains(recursoBuscado);
    }

    public int cantidadDeRecursos() {
        return this.recursos.size();
    }

    public void descartarPorLadron() {
        int cantADescartar = (recursos.size() / 2);
        for (int i = 0; i <= cantADescartar; i++) {
            if (!recursos.isEmpty()) {
                int idx = random.nextInt(recursos.size());
                recursos.remove(idx);
            }
        }
    }

    public Recurso robarCartaAleatoria(Jugador objetivo) {
        if (objetivo.recursos.isEmpty()){ return null; }
        int idx = random.nextInt(objetivo.recursos.size());
        Recurso robado = objetivo.eliminarRecurso(idx);
        this.recursos.add(robado);
        return robado;
    }

    public Recurso eliminarRecurso(int index) {
        return recursos.remove(index);
    }

    public Recurso eliminarRecurso(Recurso recurso) {
        int index = recursos.indexOf(recurso);
        return recursos.remove(index);
    }

    public List<Recurso> obtenerRecursos() {return recursos;};

    public boolean tieneRecursos(List<Recurso> listaDeRecursos) {
        List<Recurso> copiaRecursos = new ArrayList<>(this.recursos);
        for (Recurso r : listaDeRecursos) {
            if (!copiaRecursos.remove(r)) {
                return false;
            }
        }
        return true;
    }

    public void eliminarRecursos(List<Recurso> listaDeRecursos) {
        for (Recurso r : listaDeRecursos) {
            if (!recursos.remove(r)) {
                throw new IllegalStateException("Recurso a eliminar de Jugador no existe.");
            }
        }
    }

    public int calcularPuntajeVictoria() {
        return Puntaje.calcularPuntajeJugador(this);
    }

    public int puntajeCartasPV() {
        List<PuntoDeVictoria> cartasPV = this.filtrarCartasDePuntos();
        int puntosCartasPV = 0;
        for (PuntoDeVictoria carta : cartasPV) {
            puntosCartasPV += carta.puntajeCarta();
        }
        return puntosCartasPV;
    }

    private List<PuntoDeVictoria> filtrarCartasDePuntos() {
        List<PuntoDeVictoria> listaCartasPV = new ArrayList<>();
        for(CartaDesarrollo c : this.cartasDesarrollo) {
            if(c.getClass().equals(PuntoDeVictoria.class)){
                listaCartasPV.add((PuntoDeVictoria) c);
            }
        }
        return listaCartasPV;

    }

    public void intercambiarConTasaEstandar(Recurso recursoEntrante, Recurso recursoSaliente) {
        Banca.intercambioDeTasaEstandarEstatico(this, recursoEntrante, recursoSaliente);
    }

    public void agregarPieza(Construccion pieza) {
        construcciones.add(pieza);
    }

    public int getCaballerosJugados() {
        return cantidadDeCaballerosJugados;
    }

    public void incrementarCaballerosJugados() { this.cantidadDeCaballerosJugados += 1; }

    public List<Recurso> extraerTotalidadDe(Recurso recurso) {
        List<Recurso> extraidos = new ArrayList<>();
        while (this.recursos.contains(recurso)) {
            this.recursos.remove(recurso);
            extraidos.add(recurso);
        }
        return extraidos;
    }

    public int puntajeConstrucciones() {
        int res = 0;
        for(Construccion c : construcciones) {
            res += c.puntosDeVictoria();
        }
        return res;
    }

    public void removerConstruccion(Productor construccion) {
        if(this.construcciones.contains(construccion)) {
            this.construcciones.remove(construccion);
        }
    }
}
