package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.TableroVista;
import javafx.application.Platform;
import javafx.scene.control.Slider;

public class TableroControlador {

    private final TableroVista vista;
    private final CatanApp app;

    private static final String PATH_MUSICA_1 = "/Musicas/01 Age of Empires II Main Theme.mp3";
    private static  final String PATH_MUSICA_2 = "/Musicas/02 Maps of the World.mp3";
    private static  final String PATH_MUSICA_3 = "/Musicas/13 Tazer.mp3";

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
        vista.getBotonComerciarJugador().setOnAction(e -> handleComerciarJugadorClick());
        vista.getBotonComerciarBanca().setOnAction(e -> handleComerciarBancaClick());
        vista.getBotonComerciarPuerto2a1().setOnAction(e -> handleComerciarPuerto2a1Click());
        vista.getBotonComerciarPuerto3a1().setOnAction(e -> handleComerciarPuerto3a1Click());
        vista.getBotonPasar().setOnAction(e -> handlePasarClick());
        vista.getBotonComprarCartaDesarrollo().setOnAction(e -> handleComprarCartaDesarrolloClick());
        setupMenuElegirMusicaHandler();
    }

    private void handleTirarDadosClick() {
        System.out.println("Tiro dados");
    }

    private void handleConstruirClick() {
        System.out.println("Construyo");
    }

    private void handleComerciarJugadorClick() {
        System.out.println("Comercio con otro jugador");
    }

    private void handleComerciarBancaClick() {
        System.out.println("Comercio con la banca");
    }

    private void handleComerciarPuerto2a1Click() {
        System.out.println("Comercio con puerto 2:1");
    }

    private void handleComerciarPuerto3a1Click() {
        System.out.println("Comercio con puerto 3:1");
    }

    private void handlePasarClick() {
        System.out.println("Paso el turno");
    }

    private void handleComprarCartaDesarrolloClick() { System.out.println("Compro carta de desarrollo");}


    private void setupMenuHandlers() {
        vista.getItemCreditos().setOnAction(e -> app.mostrarCreditos());
        vista.getItemReglas().setOnAction(e -> app.mostrarReglas());
        vista.getItemCerrarApp().setOnAction(e -> {Platform.exit();System.exit(0);});
    }

    private void setupVolumeControl() {
        ControladorMusica musica = ControladorMusica.getInstance();
        Slider barraVolumen = vista.getBarraVolumen();

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