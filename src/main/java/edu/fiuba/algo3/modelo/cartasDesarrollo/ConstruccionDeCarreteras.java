package edu.fiuba.algo3.modelo.cartasDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.coordenada.Coordenada;

public class ConstruccionDeCarreteras extends Jugable {

    /*-- Atributos --*/

    private Coordenada coordenada1;
    private Coordenada coordenada2;

    /*-- Constructores--*/

    public ConstruccionDeCarreteras(Jugador propietario, int turnoObtenido, Coordenada c1, Coordenada c2) {
        this(propietario, turnoObtenido);
        this.coordenada1 = c1;
        this.coordenada2 = c2;
    }

    public ConstruccionDeCarreteras(Jugador propietario, int turnoObtenido) {
        super(propietario, turnoObtenido);
    }

    /*-- Metodos de comportamiento --*/

    public void jugar(Jugador jugador, int numeroTurno, Coordenada c1, Coordenada c2) {
        this.elegirCoordenadas(c1, c2);
        this.jugar(numeroTurno);
    }

    @Override
    protected void efecto() { // TODO
    }

    /*-- Setter --*/

    private void elegirCoordenadas(Coordenada coordenada1, Coordenada coordenada2) {
        this.coordenada1 = coordenada1;
        this.coordenada2 = coordenada2;
    }
}
