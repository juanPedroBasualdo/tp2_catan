package org.example.catandemo;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CreditosVista {

    private final VBox root;

    public CreditosVista() {
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setStyle("-fx-padding: 20;");
        root.setPrefWidth(600);
        root.setPrefHeight(400);

        Label titulo = new Label("Trabajo Practico 2 - Catan");
        titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");

        Label tituloIntegrantes = new Label("Integrantes");
        tituloIntegrantes.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");

        Label listaIntegrantes = new Label("Santiago Bassani\n" + "Juan Pedro Basualdo\n" + "Fernando Guo\n" +
                        "Ignacio Daniel Lopez\n\n"
        );
        listaIntegrantes.setWrapText(true);
        listaIntegrantes.setAlignment(Pos.CENTER);
        listaIntegrantes.setFont(new Font(14.0));

        Label tituloCorrectores = new Label("Correctores");
        tituloCorrectores.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");

        // --- Lista de Correctores ---
        Label listaCorrectores = new Label("Bruno Grassano\n" + "Joaquin Pandolfi\n"
        );
        listaCorrectores.setWrapText(true);
        listaCorrectores.setAlignment(Pos.CENTER);
        listaCorrectores.setFont(new Font(14.0));

        root.getChildren().addAll(
                titulo,
                tituloIntegrantes,
                listaIntegrantes,
                tituloCorrectores,
                listaCorrectores
        );
    }

    public VBox getRoot() {
        return root;
    }
}