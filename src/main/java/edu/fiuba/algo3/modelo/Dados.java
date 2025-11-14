package edu.fiuba.algo3.modelo;
import java.util.Random;

public class Dados {
    private Random random;

    public Dados() {
        random = new Random();
    }

    public int tirar(){
        return (this.tirarDado() + this.tirarDado());
    }

    /**
     * Devuelve un número entero entre 1 y 6 (inclusive).
     */
    public int tirarDado() {
        return (random.nextInt(6) + 1); // genera un número entre 1 y 6
    }
}

