package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CatanMenuBar;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;

import java.util.Objects;

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

    public static class BarraBotonesVista extends VBox {

        private final Button botonConstruir;
        private final Button botonComerciarJugador;
        private final Button botonComerciarBanca;
        private final Button botonComerciarPuerto2a1;
        private final Button botonComerciarPuerto3a1;
        private final Button botonPasar;

        private static final String PATH_BOTON_CONSTRUIR = "/Imagenes/Construir.png";
        private static final String PATH_BOTON_PASAR = "/Imagenes/PasarTurno.png";
        private static final String PATH_BOTON_COMERCIAR_JUGADOR = "/Imagenes/Comercio.jpg";
        private static final String PATH_BOTON_COMERCIAR_BANCA = "/Imagenes/Banco.jpg";
        private static final String PATH_BOTON_COMERCIAR_PUERTO_2 = "/Imagenes/Puerto2_1.jpg";
        private static final String PATH_BOTON_COMERCIAR_PUERTO_3 = "/Imagenes/Puerto3.1.jpg";

        private static final double TAMANIO_BOTON = 65;

        public BarraBotonesVista() {
            botonConstruir = crearBoton(PATH_BOTON_CONSTRUIR, "Construir");
            botonPasar = crearBoton(PATH_BOTON_PASAR, "Pasar Turno");
            botonComerciarJugador = crearBoton(PATH_BOTON_COMERCIAR_JUGADOR, "Comerciar con Jugador");
            botonComerciarBanca = crearBoton(PATH_BOTON_COMERCIAR_BANCA, "Comerciar con Banca");
            botonComerciarPuerto2a1 = crearBoton(PATH_BOTON_COMERCIAR_PUERTO_2, "Comerciar con puerto 2:1");
            botonComerciarPuerto3a1 = crearBoton(PATH_BOTON_COMERCIAR_PUERTO_3, "Comerciar con puerto 3:1");

            HBox botones = new HBox(7);
            botones.getChildren().addAll(botonConstruir, botonComerciarJugador, botonComerciarBanca,
                    botonComerciarPuerto2a1, botonComerciarPuerto3a1, botonPasar);
            botones.setAlignment(Pos.CENTER_RIGHT);

            this.setSpacing(5);
            this.getChildren().addAll(botones);
            this.setAlignment(Pos.CENTER_RIGHT);
        }

        private Button crearBoton(String pathIcono, String textoTooltip) {
            Image icono = new Image(Objects.requireNonNull(getClass().getResource(pathIcono)).toExternalForm());

            ImageView vistaIcono = new ImageView(icono);
            vistaIcono.setFitWidth(TAMANIO_BOTON);
            vistaIcono.setFitHeight(TAMANIO_BOTON);

            Button boton = new Button();
            boton.setGraphic(vistaIcono);

            boton.setPrefWidth(TAMANIO_BOTON);
            boton.setPrefHeight(TAMANIO_BOTON);
            boton.setStyle("-fx-padding: 0;");

            Tooltip.install(boton, new Tooltip(textoTooltip));

            return boton;
        }

        public Button getBotonConstruir() {
            return botonConstruir;
        }

        public Button getBotonComerciarJugador() {
            return botonComerciarJugador;
        }

        public Button getBotonComerciarBanca() {
            return botonComerciarBanca;
        }

        public Button getBotonComerciarPuerto2a1() {
            return botonComerciarPuerto2a1;
        }

        public Button getBotonComerciarPuerto3a1() {
            return botonComerciarPuerto3a1;
        }

        public Button getBotonPasar() {
            return botonPasar;
        }
    }
}