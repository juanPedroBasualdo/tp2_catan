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
    
    private final double X_INIT = 350;
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

        final double X_STEP = X_DISTANCIA;

        double x_1 = X_INIT + X_DISTANCIA/2;
        double x_2 = X_INIT + X_DISTANCIA;

        double y_1 = Y_INIT + Y_DISTANCIA;
        double y_2 = Y_INIT + Y_DISTANCIA * 2;
        double y_3 = Y_INIT - Y_DISTANCIA;
        double y_4 = Y_INIT - Y_DISTANCIA * 2;


        int index = 0; // Use a single index to track the current terrain tile

        double row1_start_x = X_INIT - X_STEP;
        for (int j = 0; j < 3 ; j++) {
            double current_x = row1_start_x + j * X_STEP;
            crearHexagono(current_x, y_4, terrenos.get(index++));
        }

        double row2_start_x = X_INIT - X_STEP * 1.5;
        for (int j = 0; j < 4 ; j++) {
            double current_x = row2_start_x + j * X_STEP;
            crearHexagono(current_x, y_3, terrenos.get(index++));
        }

        double row3_start_x = X_INIT - X_STEP * 2;
        for (int j = 0; j < 5 ; j++) {
            double current_x = row3_start_x + j * X_STEP;
            crearHexagono(current_x, Y_INIT, terrenos.get(index++));
        }

        double row4_start_x = X_INIT - X_STEP * 1.5;
        for (int j = 0; j < 4 ; j++) {
            double current_x = row4_start_x + j * X_STEP;
            crearHexagono(current_x, y_1, terrenos.get(index++));
        }

        double row5_start_x = X_INIT - X_STEP;
        for (int j = 0; j < 3 ; j++) {
            double current_x = row5_start_x + j * X_STEP;
            crearHexagono(current_x, y_2, terrenos.get(index++));
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

        String tipoTerreno = String.valueOf(terreno.getClass().getSimpleName());

        System.out.println(tipoTerreno);
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

    public static void main(String[] args) {
        launch(args);
    }

}