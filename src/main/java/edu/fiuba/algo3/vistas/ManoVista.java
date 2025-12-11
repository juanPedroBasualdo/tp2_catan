package edu.fiuba.algo3.vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class ManoVista extends HBox {
    private final HBox contenedorCartasDeDesarrollo;

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
}