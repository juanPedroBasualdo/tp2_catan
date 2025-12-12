package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.IngresarNombresVista;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.util.List;
import java.util.stream.Collectors;

public class IngresarNombresControlador {

    private final IngresarNombresVista vista;
    private final CatanApp app;
    private final int cantJugadores;
    public static final int CANT_MAXIMA_JUGADORES = 10;
    public IngresarNombresControlador(CatanApp app, int cantJugadores, IngresarNombresVista vista) {
        this.app = app;
        this.cantJugadores = cantJugadores;
        this.vista = vista;
        setupEventHandlerJugar();
    }

    private void setupEventHandlerJugar() {
        vista.getBotonJugar().setOnAction(e -> handleJugarclick());
    }

    private void handleJugarclick(){
        List<String> nombreJugadores = vista.getNombreJugadores().stream()
                .map(TextField::getText).collect(Collectors.toList());
        if(esNombreValido(nombreJugadores)){
            vista.getStage().close();

            app.mostrarPantallaJuego(cantJugadores,nombreJugadores);
        }else {
           mostrarError("Ingrese todos los nombres de los jugadores");
        }
    }

    private boolean esNombreValido(List<String> nombreJugadores){
        return nombreJugadores.stream().noneMatch(name -> name == null ||
                name.trim().isEmpty() ||
                name.trim().length() > CANT_MAXIMA_JUGADORES);
    }

    private void mostrarError(String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(vista.getStage());
        alert.setTitle("Ingresar nombre de los jugadores");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
