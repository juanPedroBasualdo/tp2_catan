package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CatanMenuBar;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;

public class JuegoVista {

    private final BorderPane root;
    private final CatanMenuBar menuBar;
    private final BarraDerechaVista barraDerecha;
    private final BarraBotonesVista botoneraVista;
    private final ManoVista manoVista;

    private final VBox tableroContainer;

    public JuegoVista() {
        this.menuBar = new CatanMenuBar();
        this.barraDerecha = new BarraDerechaVista();
        this.botoneraVista = new BarraBotonesVista();
        this.manoVista = new ManoVista();

        this.tableroContainer = new VBox();
        tableroContainer.setAlignment(Pos.CENTER);
        tableroContainer.setStyle("-fx-background-color: #2b78a9;");

        HBox bottomBar = new HBox(10, manoVista, botoneraVista);
        bottomBar.setAlignment(Pos.CENTER_LEFT);
        bottomBar.setStyle("-fx-background-color: #333333; -fx-padding: 10;");

        root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(tableroContainer);
        root.setBottom(bottomBar);
        root.setRight(barraDerecha);
        root.setPrefWidth(1366);
        root.setPrefHeight(768);
        root.setStyle("-fx-background-color: #f0f0f0;");
    }

    public void setTablero(TableroVista tablero) {
        this.tableroContainer.getChildren().clear();
        this.tableroContainer.getChildren().add(tablero);
    }

    public BorderPane getRoot() {
        return root;
    }

    public MenuItem getItemCreditos() { return menuBar.getBarraMenuCreditos(); }

    public MenuItem getItemReglas() { return menuBar.getBarraMenuReglas(); }

    public MenuItem getItemCerrarApp() { return menuBar.getBarraMenuSalir(); }

    public Slider getBarraVolumen() { return menuBar.getBarraVolumen(); }

    public MenuItem getItemElegirMusica() { return menuBar.getItemElegirMusica(); }

    public Button getBotonTirarDados() {
        return barraDerecha.getBotonTirarDados();
    }

    public Button getBotonConstruir() {
        return botoneraVista.getBotonConstruir();
    }

    public Button getBotonComerciarJugador() {
        return botoneraVista.getBotonComerciarJugador();
    }

    public Button getBotonComerciarBanca() {
        return botoneraVista.getBotonComerciarBanca();
    }

    public Button getBotonComerciarPuerto2a1() {
        return botoneraVista.getBotonComerciarPuerto2a1();
    }

    public Button getBotonComerciarPuerto3a1() {
        return botoneraVista.getBotonComerciarPuerto3a1();
    }

    public Button getBotonPasar() {
        return botoneraVista.getBotonPasar();
    }
}
