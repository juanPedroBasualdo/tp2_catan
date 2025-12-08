package org.example.catandemo;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class InicioControlador {

    private final InicioVista vista;
    private final CatanApp app;

    public InicioControlador(InicioVista vista, CatanApp app) {
        this.vista = vista;
        this.app = app;
        setupEventHandlers();
        setupMenuHandlers();
        setupVolumeControl();
    }

    private void setupEventHandlers() {
        Button botonJugar = vista.getBotonJugar();
        botonJugar.setOnAction(event -> handleBotonJugarClick());
    }

    private void handleBotonJugarClick() {
        app.mostrarPantallaSeleccion();
    }

    private void setupMenuHandlers() {
        CatanMenuBar menuBar = vista.getMenuBar();

        menuBar.getBarraMenuCreditos().setOnAction(e -> {
            app.mostrarCreditos();
        });

        menuBar.getBarraMenuSalir().setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        menuBar.getBarraMenuReglas().setOnAction(e -> {
            app.mostrarReglas();
        });
    }

    private void setupVolumeControl() {
        ControladorMusica musica = ControladorMusica.getInstance();

        Slider barraVolumen = vista.getMenuBar().getBarraVolumen();

        barraVolumen.setValue(musica.getVolumen());

        barraVolumen.valueProperty().addListener((obs, oldValue, newValue) -> {
            musica.setVolume(newValue.doubleValue());
        });
    }
}
