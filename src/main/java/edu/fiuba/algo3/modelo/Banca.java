package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.tablero.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Banca {

    List<Recurso> recursosEnBanca;

    public Banca() {
        this.recursosEnBanca = crearRecursosInicialesDelTablero();
    }

    private List<Recurso> crearRecursosInicialesDelTablero() {
        List<Recurso> listaDeRecursos = new ArrayList<Recurso>();
        Recurso[] listaTiposRecursos = Recurso.values();
        for (Recurso recurso : listaTiposRecursos) {
            for (int j = 0; j < 5; j++) {
                listaDeRecursos.add(recurso);
            }
        }

        return listaDeRecursos;
    }

    // TODO: Discutir si acá irían la logica del cobro de las construcciones (tiene sentido si son devueltas a la banca)

}
