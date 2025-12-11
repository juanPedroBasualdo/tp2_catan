package edu.fiuba.algo3.modelo.juego.turno;

public class FasesTurno {

    private FaseTurno fase;
    private int cantidadTurnosIniciales;
    private final int cantidadJugadores;

    public FasesTurno(int cantidadJugadores) {
        cantidadTurnosIniciales = 0;
        this.cantidadJugadores = cantidadJugadores;
        fase = FaseTurno.INICIANDO;
    }

    public void cambiarFase(int numeroDado) {
        if(this.fase == FaseTurno.INICIANDO && !(this.cantidadTurnosIniciales == (cantidadJugadores * 2))) {
            this.cantidadTurnosIniciales++;
        }
        if(this.fase == FaseTurno.INICIANDO && this.cantidadTurnosIniciales == (cantidadJugadores * 2)) {
            this.fase = FaseTurno.TIRARDADOS;
        }
        if(this.fase == FaseTurno.TIRARDADOS) {
            if(numeroDado == 7) {
                this.fase = FaseTurno.ROBAR;
            } else {
                this.fase = FaseTurno.TURNOJUGADOR;
            }
        }
    }

    public FaseTurno obtenerFase() {
        return this.fase;
    }

    public void cambiarFase(FaseTurno fase) {
        this.fase = fase;
    }

}
