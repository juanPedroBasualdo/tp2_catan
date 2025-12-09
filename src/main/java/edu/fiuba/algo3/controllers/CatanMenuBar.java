package edu.fiuba.algo3.controllers;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class CatanMenuBar extends MenuBar {

    private final MenuItem barraMenuCreditos;
    private final MenuItem barraMenuReglas;
    private final MenuItem barraMenuSalir;
    private final Slider barraVolumen;

    public CatanMenuBar() {
        Menu menuJuego = new Menu("Juego");
        barraMenuSalir = new MenuItem("Salir del juego");
        menuJuego.getItems().addAll(new SeparatorMenuItem(), barraMenuSalir);

        Menu menuAcercaDe = new Menu("Acerca De");
        barraMenuCreditos = new MenuItem("Créditos");
        barraMenuReglas = new MenuItem("Reglas");
        menuAcercaDe.getItems().addAll(barraMenuCreditos, barraMenuReglas);

        Menu menuSonido = new Menu("Sonido");
        barraVolumen = new Slider(0.0, 1.0, ControladorMusica.VOLUMEN_INICIAL);
        barraVolumen.setPrefWidth(120);

        HBox contenedorBarraVolumen = new HBox(5, new Label("Volumen:"), barraVolumen);
        contenedorBarraVolumen.setAlignment(Pos.CENTER_LEFT);
        contenedorBarraVolumen.setPadding(new Insets(3, 10, 3, 10));

        CustomMenuItem itemVolumen = new CustomMenuItem(contenedorBarraVolumen);
        itemVolumen.setHideOnClick(false);

        MenuItem itemElegirMusica = new MenuItem("Elegir Música");
        menuSonido.getItems().addAll(itemVolumen, itemElegirMusica);

        this.getMenus().addAll(menuAcercaDe, menuSonido, menuJuego);
    }

    public MenuItem getBarraMenuCreditos() {
        return barraMenuCreditos;
    }

    public MenuItem getBarraMenuReglas() {
        return barraMenuReglas;
    }

    public MenuItem getBarraMenuSalir() {
        return barraMenuSalir;
    }

    public Slider getBarraVolumen() {
        return barraVolumen;
    }
}