package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.coordenada.Coordenada;
import edu.fiuba.algo3.vistas.JuegoVista;
import javafx.application.Platform;
import javafx.scene.control.Slider;

public class TableroControlador {

    private final JuegoVista vista;
    private final CatanApp app;
    private final Juego juego;
    private AccionesJuego accion = AccionesJuego.ESPERARACCION;

    private enum AccionesJuego {
        INICIARJUEGO,
        CONSTRUIR,
        COMERCIAR21,
        COMERCIAR31,
        COMERCIARJUGADOR,
        COMERCIARBANCA,
        TERMINARTURNO,
        TIRARDADOS,
        ESPERARACCION
    }

    public TableroControlador(JuegoVista vista, CatanApp app, Juego juego) {
        this.vista = vista;
        this.app = app;
        this.juego = juego;

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
        vista.getItemElegirMusica().setOnAction(e -> handleElegirMusicaClick());
    }

    private void handleTirarDadosClick() {
        System.out.println("Tiro dados");
    }

    private void handleConstruirClick() {
        System.out.println("Construyo");
        accion = AccionesJuego.CONSTRUIR;
    }

    public void handleBtnVertice(int y, int x, int z) {

        try{
            Coordenada coordVert = new Coordenada(x,y,z);
            Tablero tablero = juego.obtenerTablero();
            String construccion = tablero.getTerreno(coordVert).verticeEn(z).obtenerPieza().getClass().getSimpleName();
            switch(accion){

                case CONSTRUIR:
                    switch(construccion){
                        case ("Vacio"): {
                            juego.posicionarPoblado(coordVert);
                            break;
                        }
                        case ("Poblado"): {
                            juego.mejorarACiudad(coordVert);
                            break;
                        }
                        case ("Ciudad"): {
                            System.out.println("No se puede mejorar una CIUDAD");
                            break;
                        }
                    }
                    accion = AccionesJuego.ESPERARACCION;
                    break;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


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

    private void handleElegirMusicaClick() {
        System.out.println("Abrir ventana de selección de música.");
    }


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
}