package edu.fiuba.algo3.controllers;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import java.util.Objects;

public class ControladorMusica {

    private static ControladorMusica instance;
    private MediaPlayer mediaPlayer;
    public static final double VOLUMEN_INICIAL = 0.4;
    private String cancionActual = "/Musicas/01 Age of Empires II Main Theme.mp3";

    private ControladorMusica() {
        setMusica(cancionActual);
    }

    public void setMusica(String musicaActual){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer= null;
        }

        URL musicasPath = getClass().getResource(musicaActual);
        Media media = new Media(Objects.requireNonNull(musicasPath, "No se encontro el archivo").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(VOLUMEN_INICIAL);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        this.cancionActual = musicaActual;
    }

    public static ControladorMusica getInstance() {
        if (instance == null) {
            instance = new ControladorMusica();
        }
        return instance;
    }

    public void setvolumen(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(Math.max(0, Math.min(1, volume)));
        }
    }

    public double getVolumen() {
        return (mediaPlayer != null) ? mediaPlayer.getVolume() : 0.0;
    }
}