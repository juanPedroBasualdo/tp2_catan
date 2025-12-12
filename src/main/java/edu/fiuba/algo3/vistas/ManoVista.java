package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.TableroControlador;
import edu.fiuba.algo3.modelo.Observer.Observador;
import edu.fiuba.algo3.modelo.cartasDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso; // Asegúrate de importar el enum Recurso
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

import java.util.List;
import java.util.Map;

public class ManoVista extends HBox implements Observador {

    private final HBox contenedorCartasDeDesarrollo;
    private final HBox contenedorCartasDeRecurso; // Nuevo contenedor para recursos
    private Jugador jugadorActual;

    TableroControlador controlador;

    public ManoVista() {

        this.setStyle("-fx-background-color: #88BBAA;");
        this.setSpacing(10);
        this.setPadding(new Insets(10, 0, 10, 20));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefWidth(1200);
        this.setPrefHeight(100);

        // Contenedor de Cartas de Desarrollo
        this.contenedorCartasDeDesarrollo = new HBox(5);
        this.contenedorCartasDeDesarrollo.setAlignment(Pos.CENTER_LEFT);

        // Contenedor de Cartas de Recurso
        this.contenedorCartasDeRecurso = new HBox(5); // Espaciado de 5 entre cartas de recurso
        this.contenedorCartasDeRecurso.setAlignment(Pos.CENTER_LEFT);

        // Etiquetas y adición de contenedores al HBox principal
        Label separadorDesarrollo = new Label("CARTAS DESARROLLO:");
        separadorDesarrollo.setStyle("-fx-font-weight: bold; -fx-padding: 0 10 0 10;");

        Label separadorRecurso = new Label("CARTAS RECURSO:");
        separadorRecurso.setStyle("-fx-font-weight: bold; -fx-padding: 0 0 0 10;");

        this.getChildren().addAll(
                separadorDesarrollo,
                contenedorCartasDeDesarrollo,
                separadorRecurso,
                contenedorCartasDeRecurso
        );
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
        actualizarVistas();
    };

    // Método principal de renderizado que combina ambos tipos de cartas
    private void actualizarVistas() {
        if (jugadorActual != null) {
            renderizarCartasDesarrollo();
            renderizarCartasRecurso();
        }
    }


    private void renderizarCartasRecurso() {
        this.contenedorCartasDeRecurso.getChildren().clear();

        List<Recurso> recursosDelJugador = jugadorActual.obtenerRecursos();

        for (Recurso recurso : recursosDelJugador) {

            String nombreRecurso = recurso.toString();
            System.out.println(nombreRecurso);
            CartaVista cartaVista = new CartaVista(nombreRecurso);

            cartaVista.getClickEnCarta().setOnMouseClicked(e -> {
                System.out.println("Clic en carta de recurso: " + nombreRecurso);
                controlador.handleBtnCartaRecurso(nombreRecurso);
            });

            this.contenedorCartasDeRecurso.getChildren().add(cartaVista);
        }
    }


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
        actualizarVistas();
    }

    public void setControlador(TableroControlador tableroControlador) {
        controlador = tableroControlador;
    }
}