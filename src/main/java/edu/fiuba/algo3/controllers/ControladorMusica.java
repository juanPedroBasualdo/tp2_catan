package org.example.catandemo;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class ControladorMusica {

    private static ControladorMusica instance;
    private MediaPlayer mediaPlayer;

    public static final double VOLUMEN_INICIAL = 0.1;

    private ControladorMusica() {
        URL resource = getClass().getResource("/Musicas/01 Age of Empires II Main Theme.mp3");
        if (resource != null) {
            Media media = new Media(resource.toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(VOLUMEN_INICIAL);
            mediaPlayer.play();
        }
    }

    public static ControladorMusica getInstance() {
        if (instance == null) {
            instance = new ControladorMusica();
        }
        return instance;
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(Math.max(0, Math.min(1, volume)));
        }
    }

    public double getVolumen() {
        return (mediaPlayer != null) ? mediaPlayer.getVolume() : 0.0;
    }
}