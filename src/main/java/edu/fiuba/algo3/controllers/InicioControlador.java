package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.InicioVista;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class InicioControlador {

    private final InicioVista vista;
    private final CatanApp app;

    private static final String PATH_MUSICA_1 = "/Musicas/01 Age of Empires II Main Theme.mp3";
    private static  final String PATH_MUSICA_2 = "/Musicas/02 Maps of the World.mp3";
    private static  final String PATH_MUSICA_3 = "/Musicas/13 Tazer.mp3";

    public InicioControlador(InicioVista vista, CatanApp app) {
        this.vista = vista;
        this.app = app;
        setupEventHandlers();
        setupMenuHandlers();
        setupVolumeControl();
        setupMenuElegirMusicaHandler();
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
            musica.setvolumen(newValue.doubleValue());
        });
    }

    private void setupMenuElegirMusicaHandler() {
        vista.getItemMusica1().setOnAction(e ->
                ControladorMusica.getInstance().setMusica(PATH_MUSICA_1));
        vista.getItemMusica2().setOnAction(e ->
                ControladorMusica.getInstance().setMusica(PATH_MUSICA_2));
        vista.getItemMusica3().setOnAction(e ->
                ControladorMusica.getInstance().setMusica(PATH_MUSICA_3));
    }
}
