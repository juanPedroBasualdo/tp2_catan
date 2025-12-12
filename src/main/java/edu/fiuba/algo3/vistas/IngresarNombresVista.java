package edu.fiuba.algo3.vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.geometry.Insets;


public class IngresarNombresVista {
    private final Stage stage;
    private final VBox root;
    private final Button jugar;
    private final List<TextField> nombreJugadores;

    public IngresarNombresVista(int cantJugadores, Stage stage) {
        this.stage = new Stage();
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.initOwner(stage);
        this.stage.setResizable(false);
        this.stage.setTitle("Ingresar Nombres de Jugadores");
        this.stage.setAlwaysOnTop(true);
        this.nombreJugadores = new ArrayList<>();

        this.root = new VBox(20);
        this.root.setPadding(new Insets(20,20,20,20));
        this.root.setAlignment(Pos.CENTER);


        Label descripcion = new Label("Ingrese el nombre de los jugadores");
        descripcion.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        this.root.getChildren().add(descripcion);

        for (int i = 0; i < cantJugadores; i++) {
            TextField nombreJugador = new TextField();
            nombreJugador.setPromptText("Nombre del jugador");
            nombreJugador.setMaxHeight(350);
            this.nombreJugadores.add(nombreJugador);
            this.root.getChildren().add(nombreJugador);
        }

        this.jugar = new Button("Iniciar Partida");
        this.jugar.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #ffcc00; -fx-padding: 5 20 5 20;");
        this.root.getChildren().add(jugar);

        Scene scene = new Scene(root, 400, 100 + cantJugadores * 40);
        this.stage.setScene(scene);
    }
        public void mostrar(){
            stage.showAndWait();
        }

        public Button getBotonJugar(){
            return jugar;
        }

        public List<TextField> getNombreJugadores(){
            return nombreJugadores;
        }
        public Stage getStage(){
            return stage;
        }

    }

