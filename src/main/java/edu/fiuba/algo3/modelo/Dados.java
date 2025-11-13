package edu.fiuba.algo3.modelo;
import java.util.Random;

public class Dados {
    private Random random;

    public Dados() {
        random = new Random();
    }

    /**
     * Devuelve un número entero entre 0 y 5 (inclusive).
     */
    public int tirar() {
        return random.nextInt(6); // genera un número entre 0 y 5
    }
}

