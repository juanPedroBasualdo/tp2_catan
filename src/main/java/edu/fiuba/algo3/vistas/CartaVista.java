package edu.fiuba.algo3.vistas;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.util.Objects;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class CartaVista extends StackPane {
    private static final double ANCHO = 50;
    private static final double ALTO = 75;
    private final String tipoCarta;

    private final Rectangle clickEnCarta;
    private final ImageView imagenCarta;

    public CartaVista(String tipoCarta) {
        this.tipoCarta = tipoCarta;
        this.imagenCarta = aplicarImagenCarta(tipoCarta);
        this.clickEnCarta = new Rectangle(ANCHO, ALTO);
        this.clickEnCarta.setFill(Color.TRANSPARENT);
        this.clickEnCarta.setStroke(Color.YELLOW);

        this.getChildren().addAll(this.imagenCarta, this.clickEnCarta);
        this.setPrefSize(ANCHO, ALTO);
    }

    private ImageView aplicarImagenCarta(String tipoCarta) {
        String pathImagenCarta = "";

        switch(tipoCarta){
            case "Lana":
                pathImagenCarta = "/Imagenes/Carta_lana.png";
                break;
            case "Mineral":
                pathImagenCarta = "/Imagenes/Carta_arcilla.png";
                break;
            case "Madera":
                pathImagenCarta = "/Imagenes/Carta_madera.png";
                break;
            case "Cereal":
                pathImagenCarta = "/Imagenes/Carta_cereal.png";
                break;
            case "Arcilla":
                pathImagenCarta = "/Imagenes/Carta_arcilla.png";
                break;
            case "Monopolio":
                pathImagenCarta = "/Imagenes/Carta_monopolio.png";
                break;
            case "Abundancia":
                pathImagenCarta = "/Imagenes/Carta_abundancia.png";
                break;
            case "Caballero":
                pathImagenCarta = "/Imagenes/Carta_caballero.png";
                break;
            case "PuntoDeVictoria":
                pathImagenCarta = "/Imagenes/Carta_un_punto_victoria.png";
                break;
            case "ConstruccionDeCarreteras":
                pathImagenCarta = "/Imagenes/Carta_carreteras.png";
                break;
            default:
                pathImagenCarta = "/Imagenes/Carta_desarrollo.png";
        }

        String externalForm = Objects.requireNonNull(getClass().getResource(pathImagenCarta),
                "No se encontro la imagen de la carta").toExternalForm();

        Image imagen = new Image(externalForm);
        ImageView imagenCarta = new ImageView(imagen);
        imagenCarta.setFitWidth(ANCHO);
        imagenCarta.setFitHeight(ALTO);
        imagenCarta.setPreserveRatio(true);

        return imagenCarta;
    }

    public Rectangle getClickEnCarta() {
        return clickEnCarta;
    }

}



