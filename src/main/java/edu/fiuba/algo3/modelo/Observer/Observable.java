package edu.fiuba.algo3.modelo.Observer;
import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        this.observadores.add(observador);
    }

    public void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.actualizar();
        }
    }
}