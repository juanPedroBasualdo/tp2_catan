package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.Arrays;

public class Invencion extends CartaDesarrollo implements Jugable {

    private Recurso recurso1;
    private Recurso recurso2;

    public Invencion(Jugador propietario, int turnoObtenido) {
        super(propietario, turnoObtenido);
    }

    public Invencion(Jugador propietario, int turnoObtenido, Recurso recurso1, Recurso recurso2) {
        this(propietario, turnoObtenido);
        this.recurso1 = recurso1;
        this.recurso2 = recurso2;
    }

    public void jugar(int numeroTurno, Recurso recurso1, Recurso recurso2){
        this.elegirRecursos(recurso1, recurso2);
        this.jugar(numeroTurno);
    }

    @Override
    protected void efecto() {
        this.propietario.agregarRecursos(Arrays.asList(this.recurso1, this.recurso2));
    }

    private void elegirRecursos(Recurso recurso1, Recurso recurso2) {
        this.recurso1 = recurso1;
        this.recurso2 = recurso2;
    }
}

