package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.TableroControlador;
import edu.fiuba.algo3.controllers.CatanApp;
import edu.fiuba.algo3.modelo.Observer.Observador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Recurso;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.coordenada.Coordenada;
import edu.fiuba.algo3.modelo.tablero.terreno.parte.Terreno;
import javafx.scene.Node;
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
import javafx.util.Pair;

import java.util.*;

public class TableroVista extends Pane implements Observador {

    private TableroControlador controlador;
    private final Juego juego;
    private final CatanApp app;

    private final double RADIO = 60;

    private final double X_DISTANCIA = RADIO * Math.sqrt(3);
    private final double Y_DISTANCIA = RADIO * 1.5;

    private final double X_INIT = 400;
    private final double Y_INIT = 125;

    private List<Node> nodosTablero = new ArrayList<Node>();

    private List<String[]> iconosConstrucciones = List.of(
            new String[]{"4800FFFF","Iconos/ciudadAzul.png", "Iconos/pobladoAzul.png"},
            new String[]{"FF0000FF","Iconos/ciudadRojo.png", "Iconos/pobladoRojo.png"},
                new String[]{"FFD800FF","Iconos/ciudadAmarillo.png", "Iconos/pobladoAmarillo.png"},
            new String[]{"00FF21FF","Iconos/ciudadVerde.png", "Iconos/pobladoVerde.png"}
    );

    private HashMap<Jugador, String[]> asignacionJugadores;

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

    public TableroVista(CatanApp app, Juego juego, TableroControlador controlador, List<Jugador> listaJugadores) {
        this.app = app;
        this.juego = juego;
        this.controlador = controlador;

        this.asignacionJugadores = new HashMap<>();

        this.setWidth(1366);
        this.setHeight(768);

        int i = 0;
        for ( Jugador j : listaJugadores) {
            asignacionJugadores.put(j,iconosConstrucciones.get(i));
            i++;
        }

        generarTablero(juego.obtenerTablero());

        this.getChildren().addAll(nodosTablero);
    }

    public void generarTablero(Tablero tablero) {

        nodosTablero.clear();

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
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < cantHexagonosFila[i]; j++) {
                double x_actual = columnas[i] + X_DISTANCIA * j;

                Terreno terreno = terrenos.get(index_terreno++);

                Polygon hex = crearHexagono(x_actual,filas[i], terreno);

                List<Double> coordenadas = hex.getPoints();

                añadirBtnHexagono(x_actual,filas[i],i,j);
                añadirBtnVertices(coordenadas,i,j, terreno);
                añadirBtnAristas(coordenadas,i,j, terreno);

                if (tablero.estaLadronEn(new Coordenada(i,j))) {
                   crearImagen("Iconos/ladron.png",x_actual + 30, filas[i] - 10, RADIO/1.6);
                }
            }
        }

        index_terreno = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < cantHexagonosFila[i]; j++) {
                double x_actual = columnas[i] + X_DISTANCIA * j;
                Terreno terreno = terrenos.get(index_terreno++);

                List<Double> coordenadas = calcularVerticesDeHexagonoPuntiagudo(x_actual, filas[i], RADIO);
                renderizarConstrucciones(coordenadas, terreno);
                renderizarCaminos(coordenadas,terreno);
            }
        }

        double radioPuertos = RADIO;


        crearPuerto2_1(Recurso.CEREAL, "Iconos/Puerto2_1_Cereal.png",   columnas[1] + X_DISTANCIA * 0 , filas[0] - Y_DISTANCIA, radioPuertos);
        crearPuerto2_1(Recurso.MINERAL, "Iconos/Puerto2_1_Mineral.png",  columnas[1] + X_DISTANCIA * 2 , filas[0] - Y_DISTANCIA, radioPuertos);
        crearPuerto2_1(Recurso.MADERA, "Iconos/Puerto2_1_Madera.png",   columnas[2] + X_DISTANCIA * 4 , filas[0], radioPuertos);
        crearPuerto2_1(Recurso.LANA, "Iconos/Puerto2_1_Lana.png",     columnas[2] + X_DISTANCIA * 5 , filas[2], radioPuertos);
        crearPuerto2_1(Recurso.ARCILLA, "Iconos/Puerto2_1_Ladrillo.png", columnas[2] - X_DISTANCIA * 0.5 , filas[1], radioPuertos);

        crearPuerto3_1(columnas[4] + X_DISTANCIA * 3 , filas[4], radioPuertos);
        crearPuerto3_1(columnas[1] + X_DISTANCIA * 2, filas[4] + Y_DISTANCIA, radioPuertos);
        crearPuerto3_1(columnas[1] + X_DISTANCIA * 0 , filas[4] + Y_DISTANCIA, radioPuertos);
        crearPuerto3_1(columnas[2] - X_DISTANCIA * 0.5 , filas[3], radioPuertos);
    }

    private void crearPuerto2_1(Recurso recurso, String ruta, double x, double y, double radio) {

        crearImagen(ruta, x, y, radio);

        Button botonPuerto = new Button();
        botonPuerto.setShape(new Circle(radio));
        botonPuerto.setMinSize(radio, radio);
        botonPuerto.setMaxSize(radio, radio);

        botonPuerto.setLayoutX(x - radio / 2);
        botonPuerto.setLayoutY(y - radio / 2);

        botonPuerto.setStyle("-fx-background-color: transparent;");

        botonPuerto.setOnAction(e -> {
            switch (recurso) {
                case MADERA: {
                    System.out.println("Clic en Puerto 2:1 de Madera.");
                    controlador.handleBtnPuerto2_1(Recurso.MADERA);
                    break;
                }
                case CEREAL: {
                    System.out.println("Clic en Puerto 2:1 de Cereal.");
                    controlador.handleBtnPuerto2_1(Recurso.CEREAL);
                    break;
                }
                case MINERAL: {
                    System.out.println("Clic en Puerto 2:1 de Mineral.");
                    controlador.handleBtnPuerto2_1(Recurso.MINERAL);
                    break;
                }
                case LANA: {
                    System.out.println("Clic en Puerto 2:1 de Lana.");
                    controlador.handleBtnPuerto2_1(Recurso.LANA);
                    break;
                }
                case ARCILLA: {
                    System.out.println("Clic en Puerto 2:1 de Ladrillo.");
                    controlador.handleBtnPuerto2_1(Recurso.ARCILLA);
                    break;
                }
            }
        });

        nodosTablero.add(botonPuerto);
    }

    private void crearPuerto3_1(double x, double y, double radio) {
        crearImagen("Iconos/Puerto3_1.png", x, y, radio);

        Button botonPuerto = new Button();
        botonPuerto.setShape(new Circle(radio));
        botonPuerto.setMinSize(radio, radio);
        botonPuerto.setMaxSize(radio, radio);

        botonPuerto.setLayoutX(x - radio / 2);
        botonPuerto.setLayoutY(y - radio / 2);
        botonPuerto.setStyle("-fx-background-color: transparent;");

        botonPuerto.setOnAction(e -> {
            System.out.println("Clic en Puerto 3:1");
            controlador.handleBtnPuerto3_1();
        });

        nodosTablero.add(botonPuerto);
    }

    private void crearImagen(String ruta ,double x, double y, double radio) {

        double imgSize = radio * 0.8;
        Image img = new Image(ruta);
        ImageView imgView = new ImageView(img);

        imgView.setFitWidth(imgSize);
        imgView.setFitHeight(imgSize);

        imgView.setX( x - imgSize / 2);
        imgView.setY( y - imgSize / 2);


        imgView.setMouseTransparent(true);

        nodosTablero.add(imgView);
    }

    private void renderizarConstrucciones(List<Double> coordenadas, Terreno terreno) {

        final double radioConstruccion = RADIO * 0.7;

        for (int i = 0; i < 12; i += 2) {
            double verticeX = coordenadas.get(i);
            double verticeY = coordenadas.get(i + 1);

            int indiceVertice = i/2;

            String construccion = terreno.verticeEn(indiceVertice).obtenerPieza().getClass().getSimpleName();
            Jugador propietario = terreno.verticeEn(indiceVertice).obtenerPieza().getPropietario();

            String iconoPath = null;

            switch(construccion){
                case("Ciudad"):{
                    iconoPath = asignacionJugadores.get(propietario)[1]; // [1] es la ruta a la imagen de Ciudad
                    break;
                }

                case("Poblado"):{
                    iconoPath = asignacionJugadores.get(propietario)[2]; // [2] es la ruta a la imagen de Poblado
                    break;
                }
            }

            if (iconoPath != null) {
                crearImagen(iconoPath, verticeX, verticeY, radioConstruccion);
            }
        }
    }
    private void renderizarCaminos(List<Double> coordenadas, Terreno terreno) {

        final double BUTTON_WIDTH = 25;
        final double BUTTON_HEIGHT = 8;

        for (int i = 0; i < 12; i += 2) {


            Jugador propietario = terreno.aristaEn(i/2).getPropietario();

            if (propietario != null) {


                String colorHex = asignacionJugadores.get(propietario)[0]; // [0] es el código de color HEX/RGBA

                double ax = coordenadas.get(i);
                double ay = coordenadas.get(i + 1);

                double bx = coordenadas.get((i + 2) % 12);
                double by = coordenadas.get((i + 3) % 12);

                double medioX = (ax + bx) / 2;
                double medioY = (ay + by) / 2;

                double dx = bx - ax;
                double dy = by - ay;
                double anguloRad = Math.atan2(dy, dx);
                double anguloDeg = Math.toDegrees(anguloRad);

                Button caminoVisual = new Button();
                caminoVisual.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
                caminoVisual.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);

                if (terreno.aristaEn(i/2).getPropietario() != null) {
                    caminoVisual.setStyle("-fx-background-color: #" + colorHex.substring(0, 6) + "; -fx-background-radius: 0;");
                }else {
                    caminoVisual.setStyle("-fx-background-color: #8B4513; -fx-background-radius: 0;");
                }


                caminoVisual.setLayoutX(medioX - BUTTON_WIDTH / 2);
                caminoVisual.setLayoutY(medioY - BUTTON_HEIGHT / 2);
                caminoVisual.setRotate(anguloDeg);

                nodosTablero.add(caminoVisual);

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

    private void añadirBtnHexagono(double pos_x, double pos_y, double x, double y) {

        double botonSize = RADIO;
        Button botonHexagono = new Button();

        botonHexagono.setShape(new Circle(botonSize));
        botonHexagono.setMinSize(botonSize, botonSize);
        botonHexagono.setMaxSize(botonSize, botonSize);

        botonHexagono.setLayoutX(pos_x - botonSize / 2);
        botonHexagono.setLayoutY(pos_y - botonSize / 2);

        botonHexagono.setStyle("-fx-background-color: transparent;");

        botonHexagono.setOnAction(e -> {
            System.out.println("Botón presionado en la hexagono: (" + y + ", " + x);
        });

        nodosTablero.add(botonHexagono);
    }


    private void añadirBtnVertices(List<Double> listaCoordVertices, int y, int x, Terreno terreno) {

        double botonSize = RADIO / 5;

        for (int i = 0; i < 12; i += 2) {
            double verticeX = listaCoordVertices.get(i);
            double verticeY = listaCoordVertices.get(i + 1);

            Button botonVertice = new Button();

            botonVertice.setShape(new Circle(botonSize / 2));
            botonVertice.setMinSize(botonSize, botonSize);
            botonVertice.setMaxSize(botonSize, botonSize);
            botonVertice.setStyle("-fx-background-color: #A0A0A0; -fx-border-color: black; -fx-border-width: 1px;");

            botonVertice.setLayoutX(verticeX - botonSize / 2);
            botonVertice.setLayoutY(verticeY - botonSize / 2);

            int finalI = i/2;
            botonVertice.setOnAction(e -> {
                System.out.println(y + "," + x + "," + finalI);
                if (this.controlador != null) {
                    this.controlador.handleBtnVertice(y, x, finalI);
                }
            });

            nodosTablero.add(botonVertice);
        }
    }

    private void añadirBtnAristas(List<Double> listaCoordVertices, int y, int x, Terreno terreno) {
        final double BUTTON_WIDTH = 25;
        final double BUTTON_HEIGHT = 8;

        for (int i = 0; i < 12; i += 2) {

            double ax = listaCoordVertices.get(i);
            double ay = listaCoordVertices.get(i + 1);

            double bx = listaCoordVertices.get((i + 2) % 12);
            double by = listaCoordVertices.get((i + 3) % 12);

            double medioX = (ax + bx) / 2;
            double medioY = (ay + by) / 2;

            double dx = bx - ax;
            double dy = by - ay;
            double anguloRad = Math.atan2(dy, dx);
            double anguloDeg = Math.toDegrees(anguloRad);

            Button botonArista = new Button();
            botonArista.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            botonArista.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);

            botonArista.setStyle("-fx-background-color: #8B4513; -fx-background-radius: 0;");

            botonArista.setLayoutX(medioX - BUTTON_WIDTH / 2);
            botonArista.setLayoutY(medioY - BUTTON_HEIGHT / 2);

            botonArista.setRotate(anguloDeg);

            int finalI = i/2;
            botonArista.setOnAction(e -> {
                System.out.println(y + "," + x + "," + finalI);

                controlador.handlerBtnArista(y,x,finalI);
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
            final double imgSize = RADIO * 0.6;
            final double radioRecurso = imgSize / 0.8;

            double offsetX = x - 20;
            double offsetY = y - RADIO / 2 + 10;

            crearImagen(iconoPath, offsetX, offsetY, radioRecurso);
        }

        if (numeroFicha > 0) {
            double circuloRadio = RADIO * 0.3;

            Circle ficha = new Circle(x, y, circuloRadio);
            ficha.setFill(Color.WHITE);
            ficha.setStroke(Color.BLACK);
            ficha.setStrokeWidth(1);

            Label numero = new Label(String.valueOf(numeroFicha));
            numero.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, circuloRadio * 1.4));
            numero.setTextFill(Color.BLACK);

            numero.setTranslateX(x - numero.prefWidth(-1) - 12);
            numero.setTranslateY(y - circuloRadio * 0.9);

            nodosTablero.add(ficha);
            nodosTablero.add(numero);
        }

        return hex;
    }

    @Override
    public void actualizar() {

        this.generarTablero(juego.obtenerTablero());
        this.getChildren().clear();
        this.getChildren().addAll(nodosTablero);

    }
}