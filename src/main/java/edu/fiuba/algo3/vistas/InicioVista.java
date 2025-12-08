package org.example.catandemo;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;

public class InicioVista {

    private final double RESOLUCION_ANCHO = 1366;
    private final double RESOLUCION_ALTO = 768;
    private final VBox root;
    private final Button botonJugar;
    private final CatanMenuBar menuBar;

    public InicioVista() {
        this.menuBar = new CatanMenuBar();

        String imageUrl = getClass().getResource("/Imagenes/Catan-inicio.png").toExternalForm();
        Image backgroundImage = new Image(imageUrl);
        ImageView backgroundView = new ImageView(backgroundImage);

        backgroundView.setFitWidth(RESOLUCION_ANCHO);
        backgroundView.setFitHeight(RESOLUCION_ALTO);

        botonJugar = new Button("Jugar");
        botonJugar.setStyle("-fx-background-color: #ffcc00; " + "-fx-font-size: 18px; " +
                "-fx-font-weight: bold; " + "-fx-padding: 10 20 10 20;"
        );

        efectoHover(botonJugar);

        StackPane contentPane = new StackPane();
        contentPane.getChildren().addAll(backgroundView, botonJugar);

        StackPane.setAlignment(botonJugar, Pos.CENTER);
        StackPane.setMargin(botonJugar, new Insets(325, 0, 0, 0));

        root = new VBox(this.menuBar, contentPane);
        VBox.setVgrow(contentPane, javafx.scene.layout.Priority.ALWAYS);
    }

    protected void efectoHover(Button button) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.ORANGE);
        shadow.setRadius(15);
        button.setOnMouseEntered(e -> button.setEffect(shadow));
        button.setOnMouseExited(e -> button.setEffect(null));
    }

    public VBox getRoot() {
        return root;
    }

    public Button getBotonJugar() {
        return botonJugar;
    }

    public CatanMenuBar getMenuBar() {
        return menuBar;
    }

}