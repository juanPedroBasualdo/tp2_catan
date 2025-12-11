package edu.fiuba.algo3.vistas;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class ManoVista extends HBox {

    public ManoVista() {

        this.setStyle("-fx-background-color: #88BBAA;");
        this.setSpacing(10);
        this.setPadding(new Insets(10, 0, 10, 20));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefWidth(1200);
        this.setPrefHeight(100);

    }
}