package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.SeleccionJugadoresVista;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import edu.fiuba.algo3.vistas.IngresarNombresVista;
import edu.fiuba.algo3.controllers.IngresarNombresControlador;

public class SeleccionJugadoresControlador {

    private final SeleccionJugadoresVista vista;
    private final CatanApp app;

    private static final String PATH_MUSICA_1 = "/Musicas/age_of_empires_main_theme.mp3";
    private static  final String PATH_MUSICA_2 = "/Musicas/02 Maps of the World.mp3";
    private static  final String PATH_MUSICA_3 = "/Musicas/13 Tazer.mp3";

    public SeleccionJugadoresControlador(SeleccionJugadoresVista vista, CatanApp app) {
        this.vista = vista;
        this.app = app;
        setupEventHandlers();
        setupMenuHandlers();
        setupVolumeControl();
        setupMenuElegirMusicaHandler();
    }

    private void setupEventHandlers() {
        vista.getBotonJugadores3().setOnAction(event -> onObtenerNombresClick(3));
        vista.getBotonJugadores4().setOnAction(event -> onObtenerNombresClick(4));
        vista.getBotonAtras().setOnAction(event -> onBotonAtrasClick());
    }

    private void onObtenerNombresClick(int cantJugadores) {
        app.mostrarIngresarNombres(cantJugadores);
    }

    //private void onStartGameClick(int numJugadores) {
        //app.mostrarPantallaJuego(numJugadores);
   // }

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

    private void setupMenuElegirMusicaHandler() {
        vista.getItemMusica1().setOnAction(e ->
                ControladorMusica.getInstance().setMusica(PATH_MUSICA_1));
        vista.getItemMusica2().setOnAction(e ->
                ControladorMusica.getInstance().setMusica(PATH_MUSICA_2));
        vista.getItemMusica3().setOnAction(e ->
                ControladorMusica.getInstance().setMusica(PATH_MUSICA_3));
    }
}