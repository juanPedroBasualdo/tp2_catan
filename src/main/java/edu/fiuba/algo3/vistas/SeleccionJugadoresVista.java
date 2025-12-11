package edu.fiuba.algo3.vistas; // Asegúrate de usar el paquete correcto

import edu.fiuba.algo3.controllers.CatanMenuBar;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SeleccionJugadoresVista {

    private final CatanMenuBar menuBar;
    private final VBox root;

    private final double RESOLUCION_ANCHO = 1366;
    private final double RESOLUCION_ALTO = 768;

    private final Button botonJugadores3;
    private final Button botonJugadores4;
    private final Button botonAtras;

    public SeleccionJugadoresVista() {

        this.menuBar = new CatanMenuBar();

        String imageUrl = getClass().getResource("/Imagenes/Catan-inicio.png").toExternalForm();
        Image backgroundImage = new Image(imageUrl);
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(RESOLUCION_ANCHO);
        backgroundView.setFitHeight(RESOLUCION_ALTO);

        String buttonStyle = "-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #ffcc00; -fx-padding: 5 20 5 20;";

        botonJugadores3 = new Button("3 Jugadores");
        botonJugadores3.setStyle(buttonStyle);

        botonJugadores4 = new Button("4 Jugadores");
        botonJugadores4.setStyle(buttonStyle);

        addButtonHoverEffect(botonJugadores3);
        addButtonHoverEffect(botonJugadores4);

        Label labelTitulo = new Label("Catan");
        labelTitulo.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: black;");

        VBox contenedorBotones = new VBox(10, botonJugadores3, botonJugadores4);
        contenedorBotones.setAlignment(Pos.CENTER);

        VBox contenedorCentral = new VBox(15, labelTitulo, contenedorBotones);
        contenedorCentral.setAlignment(Pos.CENTER);

        botonAtras = new Button("Atrás");
        botonAtras.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #ffcc00; -fx-padding: 5 20 5 20;");

        addButtonHoverEffect(botonAtras);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(RESOLUCION_ANCHO);
        anchorPane.setPrefHeight(RESOLUCION_ALTO);
        anchorPane.getChildren().addAll(backgroundView, contenedorCentral, botonAtras);

        AnchorPane.setTopAnchor(contenedorCentral, 250.0);
        AnchorPane.setLeftAnchor(contenedorCentral, 0.0);
        AnchorPane.setRightAnchor(contenedorCentral, 0.0);

        AnchorPane.setLeftAnchor(botonAtras, 30.0);
        AnchorPane.setBottomAnchor(botonAtras, 40.0);

        root = new VBox(this.menuBar, anchorPane);
        root.setPrefWidth(RESOLUCION_ANCHO);
        root.setPrefHeight(RESOLUCION_ALTO);
        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
    }

    protected void addButtonHoverEffect(Button button) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.ORANGE);
        shadow.setRadius(15);
        button.setOnMouseEntered(e -> button.setEffect(shadow));
        button.setOnMouseExited(e -> button.setEffect(null));
    }

    public VBox getRoot() {
        return root;
    }

    public Button getBotonJugadores3() {
        return botonJugadores3;
    }

    public Button getBotonJugadores4() {
        return botonJugadores4;
    }

    public Button getBotonAtras() {
        return botonAtras;
    }

    public CatanMenuBar getMenuBar() {
        return menuBar;
    }

    public MenuItem getItemMusica1() {
        return menuBar.getItemMusica1();
    }

    public MenuItem getItemMusica2() {
        return menuBar.getItemMusica2();
    }
    public MenuItem getItemMusica3() {
        return menuBar.getItemMusica3();
    }

}