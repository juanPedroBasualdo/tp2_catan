package edu.fiuba.algo3.modelo.randomizados;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MezcladorDeObjetos<E> {

    private final Random random;

    public MezcladorDeObjetos(Random random) {
        this.random = random;
    }

    public MezcladorDeObjetos(long seed) {
        this(new Random(seed));
    }

    public MezcladorDeObjetos() {
        this(new Random());
    }

    public void mezclar(List<E> coleccionOrdenada) {
        Collections.shuffle(coleccionOrdenada, this.random);
    }
}
