package org.example.catandemo;

import javafx.application.Platform;
import javafx.scene.control.Slider;

public class TableroControlador {

    private final TableroVista vista;
    private final CatanApp app;

    public TableroControlador(TableroVista vista, CatanApp app) {
        this.vista = vista;
        this.app = app;
        setupEventHandlers();
        setupMenuHandlers();
        setupVolumeControl();
    }

    private void setupEventHandlers() {
        vista.getBotonTirarDados().setOnAction(e -> handleTirarDadosClick());
        vista.getBotonConstruir().setOnAction(e -> handleConstruirClick());
        vista.getBotonComerciar().setOnAction(e -> handleComerciarClick());
        vista.getBotonPasar().setOnAction(e -> handlePasarClick());
    }

    private void handleTirarDadosClick() {
        System.out.println("Tiro dados");
    }

    private void handleConstruirClick() {
        System.out.println("Construyo");
    }

    private void handleComerciarClick() {
        System.out.println("Comercio");
    }

    private void handlePasarClick() {
        System.out.println("Paso el turno");
    }

    private void setupMenuHandlers() {
        vista.getItemCreditos().setOnAction(e -> app.mostrarCreditos());
        vista.getItemReglas().setOnAction(e -> app.mostrarReglas());
        vista.getItemCerrarApp().setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    private void setupVolumeControl() {
        ControladorMusica musica = ControladorMusica.getInstance();
        Slider barraVolumen = vista.getBarraVolumen();

        barraVolumen.setValue(musica.getVolumen());

        barraVolumen.valueProperty().addListener((obs, oldValue, newValue) -> {
            musica.setVolume(newValue.doubleValue());
        });
    }
}
