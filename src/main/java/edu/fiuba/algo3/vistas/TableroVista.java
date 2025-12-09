package edu.fiuba.algo3.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class TableroVista {

    private final BorderPane root;

    private final MenuItem itemCreditos;
    private final MenuItem itemReglas;
    private final MenuItem itemCerrarApp;
    private final Slider barraVolumen;
    private final MenuItem itemElegirMusica;

    private final Button botonConstruir;
    private final Button botonComerciar;
    private final Button botonPasar;
    private final Button botonTirarDados;

    public TableroVista() {
        itemCreditos = new MenuItem("Creditos");
        itemReglas = new MenuItem("Reglas");
        Menu menuAcercaDe = new Menu("Acerca De", null, itemCreditos, itemReglas);

        itemCerrarApp = new MenuItem("Cerrar Aplicación");
        Menu menuSalir = new Menu("Salir", null, itemCerrarApp);

        double VOLUMEN_INICIAL = 0.1;
        barraVolumen = new Slider(0.0, 1.0, VOLUMEN_INICIAL);
        barraVolumen.setPrefWidth(150);

        HBox hboxSonido = new HBox(10, new Label("Volumen:"), barraVolumen);
        hboxSonido.setAlignment(Pos.CENTER);
        hboxSonido.setPadding(new Insets(0, 5, 0, 5));

        CustomMenuItem customItemVolumen = new CustomMenuItem(hboxSonido);
        customItemVolumen.setHideOnClick(false);

        itemElegirMusica = new MenuItem("Elegir Música");
        Menu menuSonido = new Menu("Sonido", null, itemElegirMusica, customItemVolumen);

        MenuBar menuBar = new MenuBar(menuAcercaDe, menuSonido, menuSalir);

        //Contenedor del Tablero
        VBox tableroContainer = new VBox();
        tableroContainer.setAlignment(Pos.CENTER);
        tableroContainer.setStyle("-fx-background-color: #2b78a9;");

        Label labelTablero = new Label("TABLERO");
        labelTablero.setStyle("-fx-font-size: 72px; -fx-text-fill: white;");
        tableroContainer.getChildren().add(labelTablero);

        botonConstruir = new Button("Construir");
        botonPasar = new Button("Pasar");

        Image tradeIcon = new Image(getClass().getResource("/Imagenes/Boton_comercio.png").toExternalForm());
        ImageView tradeView = new ImageView(tradeIcon);

        tradeView.setFitWidth(30);
        tradeView.setFitHeight(30);

        botonComerciar = new Button();
        botonComerciar.setGraphic(tradeView);

        final double buttonSize = 50;
        botonConstruir.setPrefWidth(150);
        botonPasar.setPrefWidth(150);

        botonComerciar.setPrefWidth(buttonSize);
        botonComerciar.setPrefHeight(buttonSize);
        botonComerciar.setMaxWidth(buttonSize);
        botonComerciar.setMaxHeight(buttonSize);

        Tooltip.install(botonComerciar, new Tooltip("Comerciar"));
        VBox contenedorAcciones = new VBox(5, botonConstruir, botonComerciar, botonPasar);
        contenedorAcciones.setAlignment(Pos.CENTER_LEFT);

        Label labelMano = new Label("Mano");
        labelMano.setFont(new Font(24));
        labelMano.setStyle("-fx-text-fill: white;");
        labelMano.setMinWidth(800);

        HBox bottomBar = new HBox(10, labelMano, contenedorAcciones);
        bottomBar.setAlignment(Pos.CENTER_LEFT);
        bottomBar.setStyle("-fx-background-color: #333333; -fx-padding: 10;");

        VBox pvBox = new VBox(5);
        pvBox.setPrefWidth(330);
        pvBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 5;");

        HBox pvHeader = new HBox();
        pvHeader.setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1 0; -fx-padding: 2 0 5 0;");
        Label labelJugadores = new Label("Jugadores");
        labelJugadores.setStyle("-fx-font-weight: bold;");
        HBox.setHgrow(labelJugadores, Priority.ALWAYS);
        pvHeader.getChildren().addAll(labelJugadores, new Label("PV") {{setStyle("-fx-font-weight: bold;");}});

        pvBox.getChildren().addAll(
                pvHeader,
                new Label("Jugador 1: [PV]"),
                new Label("Jugador 2: [PV]"),
                new Label("Jugador 3: [PV]"),
                new Label("Jugador 4: [PV]")
        );

        // Contenedor Dados y Botón
        botonTirarDados = new Button("Tirar");
        VBox contenedorDados = new VBox(10, new Label("Dados") {{setStyle("-fx-font-weight: bold;");}}, botonTirarDados);
        contenedorDados.setAlignment(Pos.TOP_CENTER);
        contenedorDados.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10;");
        contenedorDados.setPrefWidth(100);

        // Contenedor Banca
        VBox contenedorBanca = new VBox(10, new Label("BANCA") {{setStyle("-fx-font-weight: bold;");}});
        contenedorBanca.setAlignment(Pos.TOP_CENTER);
        contenedorBanca.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10;");
        HBox.setHgrow(contenedorBanca, Priority.ALWAYS);
        contenedorBanca.setPrefHeight(150);

        HBox contenedorBancaDados = new HBox(10, contenedorBanca, contenedorDados);
        contenedorBancaDados.setPrefWidth(330);

        VBox rightBar = new VBox(15, pvBox, contenedorBancaDados);
        rightBar.setAlignment(Pos.TOP_CENTER);
        rightBar.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 10;");
        rightBar.setPrefWidth(350);

        root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(tableroContainer);
        root.setBottom(bottomBar);
        root.setRight(rightBar);
        root.setPrefWidth(1366);
        root.setPrefHeight(768);
        root.setStyle("-fx-background-color: #f0f0f0;");

    }

    public BorderPane getRoot() {
        return root;
    }

    // Getters del Menú
    public MenuItem getItemCreditos() {
        return itemCreditos; }

    public MenuItem getItemReglas() {
        return itemReglas; }

    public MenuItem getItemCerrarApp() {
        return itemCerrarApp;
    }

    public MenuItem getItemElegirMusica() {
        return itemElegirMusica;
    }

    public Slider getBarraVolumen() {
        return barraVolumen;
    }

    // Getters botones
    public Button getBotonTirarDados() {
        return botonTirarDados;
    }

    public Button getBotonConstruir() {
        return botonConstruir;
    }

    public Button getBotonComerciar() {
        return botonComerciar;
    }

    public Button getBotonPasar() {
        return botonPasar;
    }
}
