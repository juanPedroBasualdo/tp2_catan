package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.CreditosVista;
import edu.fiuba.algo3.vistas.InicioVista;
import edu.fiuba.algo3.vistas.SeleccionJugadoresVista;
import edu.fiuba.algo3.vistas.JuegoVista;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class CatanApp extends Application {

    private Stage escena;
    private final double RESOLUCION_ANCHO = 1280;
    private final double RESOLUCION_ALTO = 680;
    private static final String URL_REGLAS_CATAN = "https://deviramericas.com/wp-content/uploads/2016/12/Catan-Plus-reglas.pdf";

    @Override
    public void start(Stage stage) {
        this.escena = stage;
        // ControladorMusica.getInstance();
        mostrarPantallaInicio();
        escena.setTitle("Catan");
        escena.setResizable(false);
        escena.show();
    }

    public void mostrarPantallaInicio() {

        InicioVista vista = new InicioVista();
        InicioControlador controlador = new InicioControlador(vista, this);

        Scene scene = new Scene(vista.getRoot(), RESOLUCION_ANCHO, RESOLUCION_ALTO);
        escena.setScene(scene);
        escena.setTitle("Catan");
    }

    public void mostrarPantallaSeleccion() {

        SeleccionJugadoresVista vista = new SeleccionJugadoresVista();

        SeleccionJugadoresControlador controlador = new SeleccionJugadoresControlador(vista, this);

        Scene scene = new Scene(vista.getRoot(), RESOLUCION_ANCHO, RESOLUCION_ALTO);
        escena.setScene(scene);
        escena.setTitle("Catan");
    }

    public void mostrarPantallaJuego(int numJugadores, List<String> nombreJugadores) {

        List<Jugador> listaJugadores = new ArrayList<Jugador>();

        for (String nombre : nombreJugadores) {
            listaJugadores.add(new Jugador(nombre));
        }

        Juego juego = new Juego(listaJugadores);
        JuegoVista vistaPrincipal = new JuegoVista(listaJugadores);

        TableroControlador tableroControlador = new TableroControlador(vistaPrincipal, this, juego);

        vistaPrincipal.getManoVista().setJugadorActual(juego.jugadorActual());
        vistaPrincipal.getManoVista().setControlador(tableroControlador);

        TableroVista tableroVista = new TableroVista(this, juego, tableroControlador, listaJugadores);


        juego.agregarObserversDeJugador(tableroVista);
        juego.agregarObserversDeJugador(vistaPrincipal.getManoVista());
        juego.agregarObserversDeTablero(tableroVista);

        vistaPrincipal.setTablero(tableroVista);

        Scene scene = new Scene(vistaPrincipal.getRoot(), RESOLUCION_ANCHO, RESOLUCION_ALTO);
        escena.setScene(scene);
        escena.setTitle("Catan");

    }
    public void mostrarCreditos () {

        CreditosVista creditosVista = new CreditosVista();
        Scene scene = new Scene(creditosVista.getRoot());

        Stage creditosStage = new Stage();
        creditosStage.setTitle("Créditos");
        creditosStage.setScene(scene);

        creditosStage.initModality(Modality.APPLICATION_MODAL);
        creditosStage.initOwner(escena);
        creditosStage.setResizable(false);
        creditosStage.show();
    }

    public void mostrarReglas () {
        try {
            Desktop.getDesktop().browse(new URI(URL_REGLAS_CATAN));
        } catch (Exception e) {
        }
    }

    public void mostrarIngresarNombres ( int cantJugadores){
        IngresarNombresVista nombresVista = new IngresarNombresVista(cantJugadores, escena);
        new IngresarNombresControlador(this, cantJugadores, nombresVista);
        nombresVista.mostrar();
    }

    public static void main (String[]args){
        launch(args);
    }
}