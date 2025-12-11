package edu.fiuba.algo3.vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public class BarraDerechaVista extends VBox {

    private final Button botonTirarDados;
    private final Label labelResultadoDados;
    private final Label labelJugadorActual;
    private final Label labelInfo;

    private static final String PATH_ICONO_DADO = "/Imagenes/Dados.png";
    private static final double TAMANIO_DADO = 70;

    public BarraDerechaVista() {
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
                new Label("Jugador 1: PV"),
                new Label("Jugador 2: PV"),
                new Label("Jugador 3: PV"),
                new Label("Jugador 4: PV")
        );

        botonTirarDados = crearBotonDado(PATH_ICONO_DADO, "Tirar dados");
        labelResultadoDados = new Label("-");
        labelResultadoDados.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        VBox contenedorDados = new VBox(10, new Label("Dados") {{setStyle("-fx-font-weight: bold;");}}, botonTirarDados, labelResultadoDados);
        contenedorDados.setAlignment(Pos.TOP_CENTER);
        contenedorDados.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10;");

        labelJugadorActual = new Label("Turno del jugador: Jugador 1");
        labelJugadorActual.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        labelInfo = new Label("");
        labelInfo.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        VBox contenedorJugadorActual = new VBox(10, labelJugadorActual);
        contenedorJugadorActual.setAlignment(Pos.TOP_CENTER);
        contenedorJugadorActual.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10;");
        HBox.setHgrow(contenedorJugadorActual, Priority.ALWAYS);
        contenedorJugadorActual.setPrefHeight(150);

        HBox contenedorBancaDados = new HBox(10, contenedorJugadorActual, contenedorDados);
        contenedorBancaDados.setPrefWidth(330);

        this.getChildren().addAll(pvBox, contenedorBancaDados);
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 10;");
        this.setPrefWidth(350);
        this.setSpacing(15);
    }
    private Button crearBotonDado(String pathIcono, String textoTooltip) {

        Image icono = new Image(Objects.requireNonNull(getClass().getResource(pathIcono)).toExternalForm());

        ImageView vistaIcono = new ImageView(icono);

        vistaIcono.setFitWidth(TAMANIO_DADO);
        vistaIcono.setFitHeight(TAMANIO_DADO);

        Button boton = new Button();
        boton.setGraphic(vistaIcono);

        boton.setPrefSize(TAMANIO_DADO, TAMANIO_DADO);
        boton.setMaxSize(TAMANIO_DADO, TAMANIO_DADO);
        boton.setStyle("-fx-padding: 0;");

        Tooltip.install(boton, new Tooltip(textoTooltip));

        return boton;
    }

    public Button getBotonTirarDados() {
        return botonTirarDados;
    }

    public Label getLabelResultadoDados() {return  labelResultadoDados;}

    public Label getLabelJugadorActual() {return  labelJugadorActual;}
}
