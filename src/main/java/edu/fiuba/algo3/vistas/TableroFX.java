package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Terreno;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

public class TableroFX extends Application {

    private final double RADIO = 60;

    private final double X_DISTANCIA = RADIO * Math.sqrt(3);
    private final double Y_DISTANCIA = RADIO * 1.5;
    
    private final double X_INIT = 250;
    private final double Y_INIT = 330;


    private List<Node> nodosTablero = new ArrayList<Node>();
    private final Map<String, Pair<String, String>> TERRENOS_MAP = new HashMap<>() {{
        put("Bosque", new Pair<>("#1aff66", "file:./src/main/java/icons/madera.png"));
        put("Desierto", new Pair<>("#ffffcc", "file:./src/main/java/icons/cactus.png"));
        put("Montania", new Pair<>("#7A7A7A", "file:./src/main/java/icons/mineral.png"));
        put("Campo", new Pair<>("#ffdb4d", "file:./src/main/java/icons/trigo.png"));
        put("Pastizal", new Pair<>("#A0FF40", "file:./src/main/java/icons/lana.png"));
        put("Cerro", new Pair<>("#CC5555", "file:./src/main/java/icons/ladrillo.png"));
        put("Agua", new Pair<>("#7AA0C6", null));
    }};

    private final String BORDE_HEXAGONO_COLOR = "#EDC9AF";


    private final Random random = new Random();

    @Override
    public void start(Stage stage) throws IOException {

        generarTablero(new Tablero());
        Pane root = new Pane();
        root.getChildren().addAll(nodosTablero);

        Scene escena = new Scene(root, 1366,768);

        stage.setTitle("TestHexagono");
        stage.setScene(escena);
        stage.show();
    }

    public void generarTablero(Tablero tablero) {

        List<Terreno> terrenos = tablero.getTerrenos();

        double x_1 = X_INIT + X_DISTANCIA/2;
        double x_2 = x_1 + X_DISTANCIA/2;

        double y_1 = Y_INIT + Y_DISTANCIA;
        double y_2 = y_1 + Y_DISTANCIA;

        double y_3 = Y_INIT - Y_DISTANCIA;
        double y_4 = y_3 - Y_DISTANCIA;


        for (int i = 0; i < 3 ; i++) {
            crearHexagono(x_2,y_4, terrenos.get(i));
        }

        for (int i = 3; i < 7 ; i++) {
            crearHexagono(x_1,y_3, terrenos.get(i));
        }

        for (int i = 7; i < 12 ; i++) {
            crearHexagono(X_INIT, Y_INIT, terrenos.get(i));
        }

        for (int i = 12; i < 16 ; i++) {
            crearHexagono(x_1, y_1, terrenos.get(i));
        }

        for (int i = 16; i < 19 ; i++) {
            crearHexagono(x_2, y_2, terrenos.get(i));
        }

    }



    private List<Double> calcularVerticesDeHexagonoPuntiagudo(double cx, double cy, double r) {
        List <Double> coordenadas = new ArrayList<>();
        final  double angulo_rotacion = Math.toRadians(60);
        double angulo_inicial = Math.PI / 2;

        for (int i = 0; i < 6; i++) {
            double angulo = angulo_inicial + i * angulo_rotacion;
            double x = cx + r * Math.cos(angulo);
            double y = cy - r * Math.sin(angulo);

            coordenadas.add(x);
            coordenadas.add(y);
        }
        return coordenadas;
    }


    private void crearHexagono(double x, double y, Terreno terreno) {

        String tipoTerreno = String.valueOf(terreno.getClass());
        String colorHex = TERRENOS_MAP.get(tipoTerreno).getKey();
        String iconoPath = TERRENOS_MAP.get(tipoTerreno).getValue();
        int numeroFicha = terreno.getFichaNumero();
        
        
        Polygon hex = new Polygon();
        hex.getPoints().addAll(calcularVerticesDeHexagonoPuntiagudo(x,y,RADIO));

        hex.setFill(Color.web(colorHex));
        hex.setStroke(Color.web(BORDE_HEXAGONO_COLOR));
        hex.setStrokeWidth(5);

        nodosTablero.add(hex);

        if (iconoPath != null) {
            Image img = new Image(iconoPath);
            ImageView imagen = new ImageView(img);

            double imgSize = RADIO * 0.6;
            imagen.setFitWidth(imgSize);
            imagen.setFitHeight(imgSize);

            // Posicionamiento en el centro superior del hexágono
            imagen.setX(x - imgSize / 2 - 20);
            imagen.setY(y - RADIO / 2 - imgSize / 2 + 10); // RADIO/2 es aprox el centro superior

            nodosTablero.add(imagen);
        }

        if (numeroFicha > 0) {
            double circuloRadio = RADIO * 0.3; // Radio del círculo de la ficha

            // Círculo blanco para la ficha
            Circle ficha = new Circle(x, y, circuloRadio);
            ficha.setFill(Color.WHITE);
            ficha.setStroke(Color.BLACK);
            ficha.setStrokeWidth(1);

            // Etiqueta para el número
            Label numero = new Label(String.valueOf(numeroFicha));
            numero.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, circuloRadio * 1.4));
            numero.setTextFill(Color.BLACK);

            // Centro el Label sobre el círculo
            numero.setTranslateX(x - numero.prefWidth(-1) - 12);
            // Ajusto verticalmente para centrar el texto. Se resta la mitad de la altura de la fuente.
            numero.setTranslateY(y - circuloRadio * 0.9);

            nodosTablero.add(ficha);
            nodosTablero.add(numero);
        }
    }
}