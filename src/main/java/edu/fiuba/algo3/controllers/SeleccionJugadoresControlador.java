package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.SeleccionJugadoresVista;
import javafx.application.Platform;
import javafx.scene.control.Slider;

public class SeleccionJugadoresControlador {

    private final SeleccionJugadoresVista vista;
    private final CatanApp app;

    public SeleccionJugadoresControlador(SeleccionJugadoresVista vista, CatanApp app) {
        this.vista = vista;
        this.app = app;
        setupEventHandlers();
        setupMenuHandlers();
        setupVolumeControl();
    }

    private void setupEventHandlers() {
        vista.getBotonJugadores3().setOnAction(event -> onStartGameClick(3));
        vista.getBotonJugadores4().setOnAction(event -> onStartGameClick(4));
        vista.getBotonJugadores5().setOnAction(event -> onStartGameClick(5));
        vista.getBotonAtras().setOnAction(event -> onBotonAtrasClick());
    }

    private void onStartGameClick(int numJugadores) {
        app.mostrarPantallaJuego(numJugadores);
    }

    private void onBotonAtrasClick() {
        app.mostrarPantallaInicio();
    }

    private void setupMenuHandlers() {
        CatanMenuBar menuBar = vista.getMenuBar();

        menuBar.getBarraMenuCreditos().setOnAction(e -> {
            app.mostrarCreditos();
        });

        menuBar.getBarraMenuReglas().setOnAction(e -> {
            app.mostrarReglas();
        });

        menuBar.getBarraMenuSalir().setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    private void setupVolumeControl() {
        ControladorMusica musica = ControladorMusica.getInstance();

        Slider barraVolumen = vista.getMenuBar().getBarraVolumen();

        barraVolumen.setValue(musica.getVolumen());

        barraVolumen.valueProperty().addListener((obs, oldValue, newValue) -> {
            musica.setvolumen(newValue.doubleValue());
        });
    }
}