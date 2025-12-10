package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Terreno;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    
    private final double X_INIT = 400;
    private final double Y_INIT = 100;

    private List<Node> nodosTablero = new ArrayList<Node>();
    private final Map<String, Pair<String, String>> TERRENOS_MAP = new HashMap<>() {{
        put("Bosque", new Pair<>("#1aff66", "/Iconos/madera.png"));
        put("Desierto", new Pair<>("#ffffcc", "/Iconos/cactus.png"));
        put("Montania", new Pair<>("#7A7A7A", "/Iconos/mineral.png"));
        put("Campo", new Pair<>("#ffdb4d", "/Iconos/trigo.png"));
        put("Pastizal", new Pair<>("#A0FF40", "/Iconos/lana.png"));
        put("Cerro", new Pair<>("#CC5555", "/Iconos/ladrillo.png"));
        put("Agua", new Pair<>("#7AA0C6", null));
    }};

    private final String BORDE_HEXAGONO_COLOR = "#EDC9AF";

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

        Double[] filas = new Double[] {
                Y_INIT,
                Y_INIT + Y_DISTANCIA * 1,
                Y_INIT + Y_DISTANCIA * 2,
                Y_INIT + Y_DISTANCIA * 3,
                Y_INIT + Y_DISTANCIA * 4
        };

        Double[] columnas = new Double[] {
                X_INIT,
                X_INIT - X_DISTANCIA/2,
                X_INIT - X_DISTANCIA/2 * 2,
                X_INIT - X_DISTANCIA/2,
                X_INIT
        };

        Integer[] cantHexagonosFila = new Integer[]{3, 4, 5, 4, 3};

        int index_terreno = 0;

        for (int i = 0; i < 5; i++) {   // Valor Y del Terreno
            for (int j = 0; j < cantHexagonosFila[i]; j++) {    // Valor X del Terreno
                double x_actual = columnas[i] + X_DISTANCIA * j;

                Terreno terreno = terrenos.get(index_terreno++);

                Polygon hex = crearHexagono(x_actual,filas[i], terreno);

                List<Double> coordenadas = hex.getPoints();

                System.out.println("Vertex: "+ j + "," + i);

                aniadirBtnVertices(coordenadas,i,j, terreno);
                aniadirBtnAristas( coordenadas,i,j, terreno);
            }
        }

    }

    private List<Double> calcularVerticesDeHexagonoPuntiagudo(double cx, double cy, double r) {
        List <Double> coordenadas = new ArrayList<>();
        final  double angulo_rotacion = Math.toRadians(-60);

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


    private void aniadirBtnVertices(List<Double> listaVertices, int y, int x, Terreno terreno) {

        double botonSize = RADIO / 5;

        for (int i = 0; i < 12; i += 2) {
            double verticeX = listaVertices.get(i);
            double verticeY = listaVertices.get(i + 1);

            Button botonVertice = new Button();

            // Figura y Color TODO aplicar icono de terreno si existe con color de jugador
            botonVertice.setShape(new Circle(botonSize / 2));
            botonVertice.setMinSize(botonSize, botonSize);
            botonVertice.setMaxSize(botonSize, botonSize);
            botonVertice.setStyle("-fx-background-color: #A0A0A0; -fx-border-color: black; -fx-border-width: 1px;");

            // Posicionamiento
            botonVertice.setLayoutX(verticeX - botonSize / 2);
            botonVertice.setLayoutY(verticeY - botonSize / 2);

            // Opcional: Asignar un controlador de eventos (por ejemplo, para construir un asentamiento)
            int finalI = i/2;
            botonVertice.setOnAction(e -> {
                System.out.println("Botón presionado en la coordenada: (" + y + ", " + x + ") en pos: " + finalI);

            });

            nodosTablero.add(botonVertice);
        }
    }

    private void aniadirBtnAristas(List<Double> listaVertices, int y, int x, Terreno terreno) {
        final double BUTTON_WIDTH = 25;
        final double BUTTON_HEIGHT = 8;

        for (int i = 0; i < 12; i += 2) {

            double ax = listaVertices.get(i);
            double ay = listaVertices.get(i + 1);

            double bx = listaVertices.get((i + 2) % 12);
            double by = listaVertices.get((i + 3) % 12);

            double medioX = (ax + bx) / 2;
            double medioY = (ay + by) / 2;

            double dx = bx - ax;
            double dy = by - ay;
            double anguloRad = Math.atan2(dy, dx);
            double anguloDeg = Math.toDegrees(anguloRad);

            Button botonArista = new Button();
            botonArista.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            botonArista.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);

            // TODO cambiar con color de cada jugador!!
            botonArista.setStyle("-fx-background-color: #8B4513; -fx-background-radius: 0;"); // Marrón para simular un camino

            botonArista.setLayoutX(medioX - BUTTON_WIDTH / 2);
            botonArista.setLayoutY(medioY - BUTTON_HEIGHT / 2);

            // Aplicamos la rotación
            botonArista.setRotate(anguloDeg);

            int finalI = i/2;
            botonArista.setOnAction(e -> {
                System.out.println("Camino presionado en la arista de: (" + y + ", " + x + ") en pos:" + finalI);
            });

            nodosTablero.add(botonArista);
        }
    }


    private Polygon crearHexagono(double x, double y, Terreno terreno) {

        String tipoTerreno = terreno.getClass().getSimpleName();

        String colorHex = TERRENOS_MAP.get(tipoTerreno).getKey();
        String iconoPath = TERRENOS_MAP.get(tipoTerreno).getValue();
        int numeroFicha = terreno.getFichaNumero();
        
        
        Polygon hex = new Polygon();
        List<Double> coordenadas = calcularVerticesDeHexagonoPuntiagudo(x,y,RADIO);
        hex.getPoints().addAll(coordenadas);

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

        return hex;
    }

    public static void main(String[] args) {
        launch(args);
    }

}