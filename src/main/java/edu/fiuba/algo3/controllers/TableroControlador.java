package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.juego.turno.FaseTurno;
import edu.fiuba.algo3.vistas.JuegoVista;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.coordenada.Coordenada;
import javafx.application.Platform;
import javafx.scene.control.Slider;

public class TableroControlador {

    private final JuegoVista vista;
    private final CatanApp app;
    private final Juego juego;
    private AccionesJuego accion = AccionesJuego.ESPERARACCION;

    private static final String PATH_MUSICA_1 = "/Musicas/age_of_empires_main_theme.mp3";
    private static  final String PATH_MUSICA_2 = "/Musicas/02 Maps of the World.mp3";
    private static  final String PATH_MUSICA_3 = "/Musicas/13 Tazer.mp3";

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
        // wsetupVolumeControl();
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
        int fichaActual = juego.tirarDados();
        juego.otorgarRecursos(fichaActual);
        juego.cambiarFase(fichaActual);
    }

    private void handleConstruirClick() {
        System.out.println("Construyo");

        System.out.println("FASE: "+ juego.obtenerFase());
        accion = AccionesJuego.CONSTRUIR;
    }

    public void handleBtnVertice(int y, int x, int z) {

        try{
            Coordenada coordVert = new Coordenada(y,x,z);
            Tablero tablero = juego.obtenerTablero();
            String construccion = tablero.getTerreno(coordVert).verticeEn(z).obtenerPieza().getClass().getSimpleName();
            switch(accion){
                case CONSTRUIR:
                    switch(construccion){
                        case ("Vacio"): {
                            switch (juego.obtenerFase()){
                                case INICIANDO1:{
                                    juego.posicionarPoblado(coordVert);
                                    break;
                                }
                                case INICIANDO2:
                                    juego.posicionarPoblado(coordVert);
                                    juego.otorgarRecursosIniciales(coordVert);
                                    System.out.println(juego.jugadorActual().obtenerRecursos());
                                    break;
                                case TURNOJUGADOR: {
                                    juego.construirPoblado(coordVert);
                                    break;
                                }
                                default:{
                                    System.out.println(juego.obtenerFase() + "NO SE PUEDE CONSTRUIR");
                                }
                            }
                            break;
                        }
                        case ("Poblado"): {

                            if (juego.obtenerFase() == FaseTurno.TURNOJUGADOR){
                                juego.mejorarACiudad(coordVert);
                            }else {
                                System.out.println("NO SE PUEDE MEJORAR DURANTE" + juego.obtenerFase());
                            }

                            break;
                        }
                        case ("Ciudad"): {
                            System.out.println("No se puede mejorar una CIUDAD");
                            break;
                        }

                    }
                    juego.notificarObservadores();
                    accion = AccionesJuego.ESPERARACCION;
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void handlerBtnArista(int y, int x, int z) {
        try{
            Coordenada coordArista = new Coordenada(y,x,z);

            switch(accion){
                case CONSTRUIR:
                    if (juego.obtenerFase() == FaseTurno.INICIANDO1 || juego.obtenerFase() == FaseTurno.INICIANDO2) {
                        juego.posicionarCamino(coordArista);
                        this.juego.cambiarFase(0);
                        this.juego.pasarTurno();
                    }
                    if (juego.obtenerFase() == FaseTurno.TURNOJUGADOR){
                        juego.construirCamino(coordArista);
                    }
                    juego.notificarObservadores();
                    accion = AccionesJuego.ESPERARACCION;
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            System.out.println(e);
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
        if (juego.obtenerFase() == FaseTurno.TURNOJUGADOR){
            System.out.println("Paso el turno");
            juego.pasarTurno();
            juego.cambiarFase(0);
            juego.notificarObservadores();
        }else {
            System.out.println("Estas en la fase inicial");
        }
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