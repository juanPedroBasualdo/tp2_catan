package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BarraDerechaVista extends VBox {

    private final Button botonTirarDados;
    private final Label labelResultadoDados;
    private final Label labelJugadorActual;
    private final Label labelInfo;
    private final List<Label> cantPV;

    private final Button botonComerciarCereal;
    private final Button botonComerciarLana;
    private final Button botonComerciarMineral;
    private final Button botonComerciarMadera;
    private final Button botonComerciarArcilla;

    private static final String PATH_IMAGEN_DADO = "/Imagenes/Dados.png";
    private static final String PATH_IMAGEN_CEREAL  = "/Imagenes/Carta_cereal.png";
    private static final String PATH_IMAGEN_LANA  = "/Imagenes/Carta_lana.png";
    private static final String PATH_IMAGEN_MINERAL  = "/Imagenes/Carta_mineral.png";
    private static final String PATH_IMAGEN_MADERA = "/Imagenes/Carta_madera.png";
    private static final String PATH_IMAGEN_ARCILLA  = "/Imagenes/Carta_arcilla.png";
    private static final double TAMANIO_DADO = 70;

    public BarraDerechaVista(List<Jugador> nombreJugadores) {
        this.cantPV =new ArrayList<>();

        List<String> nombresJugadores = nombreJugadores.stream()
                .map(Jugador::obtenerNombre)
                .collect(Collectors.toList());

        VBox pvBox = new VBox(5);
        pvBox.setPrefWidth(330);
        pvBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 5;");

        HBox pvHeader = new HBox();
        pvHeader.setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1 0; -fx-padding: 2 0 5 0;");
        Label labelJugadores = new Label("Jugadores");
        labelJugadores.setStyle("-fx-font-weight: bold;");
        HBox.setHgrow(labelJugadores, Priority.ALWAYS);
        pvHeader.getChildren().addAll(labelJugadores, new Label("Puntos de Victoria")
        {{setStyle("-fx-font-weight: bold;");}});



        pvBox.getChildren().addAll(pvHeader);
        for (Jugador j: nombreJugadores) {

            Label etiquetaJugadorPV = new Label(j.obtenerNombre() + ": " + j.calcularPuntajeVictoria() + "PV");


            this.cantPV.add(etiquetaJugadorPV);
            pvBox.getChildren().add(etiquetaJugadorPV);
        }

        botonComerciarCereal = crearBotones(PATH_IMAGEN_CEREAL, "Comerciar cereal");
        botonComerciarLana = crearBotones(PATH_IMAGEN_LANA, "Comerciar lana");
        botonComerciarMineral = crearBotones(PATH_IMAGEN_MINERAL, "Comerciar mineral");
        botonComerciarMadera = crearBotones(PATH_IMAGEN_MADERA, "Comerciar madera");
        botonComerciarArcilla = crearBotones(PATH_IMAGEN_ARCILLA, "Comerciar arcilla");


        HBox contenedorBotonesComercio = new HBox(10);
        contenedorBotonesComercio.setAlignment(Pos.CENTER);
        contenedorBotonesComercio.getChildren().addAll(
                botonComerciarCereal, botonComerciarLana,botonComerciarMineral,
                botonComerciarMadera, botonComerciarArcilla);
        contenedorBotonesComercio.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10;");

        botonTirarDados = crearBotones(PATH_IMAGEN_DADO, "Tirar dados");
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

        this.getChildren().addAll(pvBox, contenedorBancaDados,contenedorBotonesComercio);
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 10;");
        this.setPrefWidth(350);
        this.setSpacing(15);
    }
    private Button crearBotones(String pathIcono, String textoTooltip) {

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

    public void actualizarPuntosVictoria(int indiceJugador, int nuevosPV) {
        if (indiceJugador >= 0 && indiceJugador < cantPV.size()) {

            String nombreActual = cantPV.get(indiceJugador).getText().split(":")[0];

            cantPV.get(indiceJugador).setText(nombreActual + ": " + nuevosPV + " PV");
        }
    }

    public Button getBotonTirarDados() {
        return botonTirarDados;
    }

    public Label getLabelResultadoDados() {return  labelResultadoDados;}

    public Label getLabelJugadorActual() {return  labelJugadorActual;}

    public Button getBotonComerciarCereal() {
        return botonComerciarCereal;
    }

    public Button getBotonComerciarLana() {
        return botonComerciarLana;
    }

    public Button getBotonComerciarMineral() {
        return botonComerciarMineral;
    }

    public Button getBotonComerciarMadera() {
        return botonComerciarMadera;
    }

    public Button getBotonComerciarArcilla() {
        return botonComerciarArcilla;
    }
}
