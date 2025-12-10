package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.Observer.Observable;
import edu.fiuba.algo3.modelo.banca.Banca;
import edu.fiuba.algo3.modelo.cartasDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.cartasDesarrollo.Jugable;
import edu.fiuba.algo3.modelo.cartasDesarrollo.PuntoDeVictoria;
import edu.fiuba.algo3.modelo.excepciones.CartaNoEsJugableException;
import edu.fiuba.algo3.modelo.puntajeYBonificaciones.Puntaje;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.Construccion;
import edu.fiuba.algo3.modelo.tablero.terreno.pieza.construcciones.Productor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jugador extends Observable {

    /*-- Atributos --*/

    private final String nombre;
    private final Random random = new Random();
    private List<Construccion> construcciones;
    private final List<Recurso> recursos;
    private List<CartaDesarrollo> cartasDesarrollo;
    private int cantidadDeCaballerosJugados; // TODO Delegable

    /*-- Constructores --*/

    public Jugador(String nombre) {
        super(); // Inicializa la lista de observadores
        this.nombre = nombre;
        this.recursos = new ArrayList<>();
        this.construcciones = new ArrayList<>();
        this.cartasDesarrollo = new ArrayList<CartaDesarrollo>();
        this.cantidadDeCaballerosJugados = 0;
    }

    /*-- Metodos de Construcciones --*/

    public void agregarConstruccion(Construccion construccion) {
        construcciones.add(construccion);
        this.notificarObservadores();
    }

    public void removerConstruccion(Productor construccion) {
        this.construcciones.remove(construccion);
        this.notificarObservadores();
    }

    /*-- Metodos de recurso --*/

    public void agregarRecurso(Recurso recurso) {
        this.recursos.add(recurso);
        this.notificarObservadores();
    }

    public void agregarRecursos(List<Recurso> nuevos) {
        this.recursos.addAll(nuevos);
        this.notificarObservadores();
    }

    public Recurso eliminarRecurso(int index) {
        Recurso eliminado = recursos.remove(index);
        this.notificarObservadores();
        return eliminado;
    }

    public Recurso eliminarRecurso(Recurso recurso) {
        int index = recursos.indexOf(recurso);
        Recurso eliminado = recursos.remove(index);
        this.notificarObservadores();
        return eliminado;
    }

    public void eliminarRecursos(List<Recurso> listaDeRecursos) {
        for (Recurso r : listaDeRecursos) {
            if (!recursos.remove(r)) {
                throw new IllegalStateException("Recurso a eliminar de Jugador no existe.");
            }
        }
        this.notificarObservadores();
    }

    public boolean tieneRecurso(Recurso recursoBuscado) {
        return this.recursos.contains(recursoBuscado);
    }

    public int cantidadDeRecursos() {
        return this.recursos.size();
    }

    public boolean tieneRecursos(List<Recurso> listaDeRecursos) {
        List<Recurso> copiaRecursos = new ArrayList<>(this.recursos);
        for (Recurso r : listaDeRecursos) {
            if (!copiaRecursos.remove(r)) {
                return false;
            }
        }
        return true;
    }

    /*-- Metodos de Carta --*/

    public void jugarCarta(Jugable carta, int numeroTurno) {
        Jugable cartaAJugar = tieneCartasJugablesDeTipo(carta, numeroTurno);
        if(cartaAJugar != null) {
            carta.jugar(numeroTurno);
            this.cartasDesarrollo.remove(cartaAJugar);
            this.notificarObservadores();
        } else {
            throw new CartaNoEsJugableException("No se puede jugar esta carta " + carta.getClass() + ". [Turno: " + numeroTurno + "]");
        }
    }

    public void agregarCarta(CartaDesarrollo carta) {
        this.cartasDesarrollo.add(carta);
        this.notificarObservadores();
    }

    /*-- Auxiliares de Carta --*/

    public List<Recurso> extraerTotalidadDe(Recurso recurso) {
        List<Recurso> extraidos = new ArrayList<>();
        while (this.recursos.contains(recurso)) {
            this.recursos.remove(recurso);
            extraidos.add(recurso);
        }
        return extraidos;
    }

    private List<Jugable> filtrarCartasJugablesDeTipo(Jugable carta) {
        List<Jugable> cartasFiltradas = new ArrayList<>();
        for(CartaDesarrollo c : cartasDesarrollo) {
            if(c.getClass().equals(carta.getClass())) {
                cartasFiltradas.add((Jugable) c);
            }
        }
        return cartasFiltradas;
    }

    protected Jugable tieneCartasJugablesDeTipo(CartaDesarrollo carta, int numeroTurno) {
        if(!carta.esJugable()) {
            return null;
        }
        List<Jugable> listaDeCartasDeTipo = this.filtrarCartasJugablesDeTipo((Jugable) carta);
        if(listaDeCartasDeTipo.isEmpty()) {
            return null;
        }
        for(Jugable c : listaDeCartasDeTipo) {
            if(c.puedeJugarse(numeroTurno)) {
                return c;
            }
        }
        return null;
    }

    /*-- Auxiliares de Puntaje --*/

    public int calcularPuntajeVictoria() {
        return Puntaje.calcularPuntajeJugador(this);
    }

    public int calcularPuntajeVictoria(int puntajeBonificacion) {
        return Puntaje.calcularPuntajeJugador(this, puntajeBonificacion);
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

    public int puntajeConstrucciones() {
        int res = 0;
        for(Construccion c : construcciones) {
            res += c.puntosDeVictoria();
        }
        return res;
    }

    public void incrementarCaballerosJugados() { this.cantidadDeCaballerosJugados += 1; }

    /*-- Metodos de intercambio --*/

    public void intercambiarConTasaEstandar(Recurso recursoEntrante, Recurso recursoSaliente) {
        Banca.intercambioDeTasaEstandarEstatico(this, recursoEntrante, recursoSaliente);
        this.notificarObservadores(); // TODO yo creo que si amerita actualizar luego de intercambiar
    }

    /*-- Auxiliares de Ladron --*/

    public boolean debeDescartar() {
        return this.recursos.size() >= 7;
    }

    public void descartarPorLadron() {
        if(this.debeDescartar()) {
            int cantADescartar = (recursos.size() / 2);
            for (int i = 0; i <= cantADescartar; i++) {
                if (!recursos.isEmpty()) {
                    int idx = random.nextInt(recursos.size());
                    recursos.remove(idx);
                }
            }
            this.notificarObservadores();
        }
    }

    public Recurso robarRecursoAleatorio(Jugador objetivo) {
        if (objetivo.recursos.isEmpty()){ return null; }
        int idx = random.nextInt(objetivo.recursos.size());
        Recurso robado = objetivo.eliminarRecurso(idx);
        this.recursos.add(robado);
        this.notificarObservadores();
        return robado;
    }

    /*-- Getters --*/

    public String obtenerNombre() {
        return nombre;
    }

    public List<Recurso> obtenerRecursos() {
        return recursos;
    }

    public int getCaballerosJugados() {
        return cantidadDeCaballerosJugados;
    }

}
