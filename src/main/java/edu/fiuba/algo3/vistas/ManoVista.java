package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.TableroControlador;
import edu.fiuba.algo3.modelo.Observer.Observador;
import edu.fiuba.algo3.modelo.cartasDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class ManoVista extends HBox implements Observador {
    private final HBox contenedorCartasDeDesarrollo;
    private Jugador jugadorActual;

    TableroControlador controlador;

    public ManoVista() {

        this.setStyle("-fx-background-color: #88BBAA;");
        this.setSpacing(10);
        this.setPadding(new Insets(10, 0, 10, 20));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefWidth(1200);
        this.setPrefHeight(100);

        this.contenedorCartasDeDesarrollo = new HBox(5);
        this.contenedorCartasDeDesarrollo.setAlignment(Pos.CENTER_LEFT);

        Label separador = new Label("CARTAS DESARROLLO:");
        separador.setStyle("-fx-font-weight: bold; -fx-padding: 0 10 0 10;");
        this.getChildren().addAll(separador, contenedorCartasDeDesarrollo);

    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
        renderizarCartasDesarrollo();
    };


    private void renderizarCartasDesarrollo() {
        this.contenedorCartasDeDesarrollo.getChildren().clear();

        for (CartaDesarrollo carta : jugadorActual.cartasDesarrollo()) {
            String nombreTipo = carta.getClass().getSimpleName();

            CartaVista cartaVista = new CartaVista(nombreTipo);

            cartaVista.getClickEnCarta().setOnMouseClicked(e -> {
                System.out.println("Clic en carta de desarrollo: " + nombreTipo);

                controlador.handleBtnCartaDesarrollo(nombreTipo);
            });

            this.contenedorCartasDeDesarrollo.getChildren().add(cartaVista);
        }
    }

    @Override
    public void actualizar() {
        renderizarCartasDesarrollo();
    }

    public void setControlador(TableroControlador tableroControlador) {
        controlador = tableroControlador;
    }
}