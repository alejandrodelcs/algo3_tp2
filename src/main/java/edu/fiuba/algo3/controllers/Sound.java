package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.exceptions.ErrorIdentifierDoesNotMatchAnyLoadedSong;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Objects;

public class Sound {
    private final HashMap<String, MediaPlayer> soundEffectsFiles = new HashMap<>();
    private MediaPlayer effect;
    private final HashMap<String, MediaPlayer> musicFiles = new HashMap<>();
    private MediaPlayer currentPlayback;
    private final double defaultVolume = 0.6;

    private final SimpleDoubleProperty musicVolumeProperty = new SimpleDoubleProperty(defaultVolume);
    private final SimpleDoubleProperty volumeFx = new SimpleDoubleProperty(defaultVolume);

    private static final Sound singleton = new Sound();

    private Sound(){
    }

    public static Sound get(){
        return singleton;
    }


    public void playFX(String identifier) {
        if (!soundEffectsFiles.containsKey(identifier))
            throw new ErrorIdentifierDoesNotMatchAnyLoadedSong();

        if (effect != null)
            effect.stop();

        effect = soundEffectsFiles.get(identifier);
        effect.play();
    }
    public void playMusic(String identifier) {
        if (!musicFiles.containsKey(identifier))
            throw new ErrorIdentifierDoesNotMatchAnyLoadedSong();

        if (currentPlayback != null)
            currentPlayback.stop();

        currentPlayback = musicFiles.get(identifier);
        currentPlayback.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                currentPlayback.seek(Duration.ZERO);
            }
        });
        currentPlayback.play();
    }


    /*** Llamarse al principio de la ejecucion del programa, o entre escenas
     * para asi no bajar el rendimiento, cargar las canciones que se vayan a usar
     * Toma una direccion del archivo en Resources y un identificador, este ultimo para
     * poder acceder al archivo de sonido luego***/
    public void loadMusic(String direction, String identifier) {
        loadFile(direction, identifier, musicFiles, musicVolumeProperty);
    }

    public void loadSound(String direction, String identifier) {
        loadFile(direction, identifier, soundEffectsFiles, volumeFx);
    }

    private void loadFile(String direction, String identifier, HashMap<String, MediaPlayer> container, SimpleDoubleProperty volume){
        Media media = new Media(Objects.requireNonNull(getClass().getResource("/sound/" + direction).toExternalForm()));
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.volumeProperty().bindBidirectional(volume);
        container.put(identifier, mediaPlayer);
    }

    public void modifyMusicVolume(double value) {
        modifyVolume(value, musicVolumeProperty);
    }
    public void modifyEffectVolume(double value) {
        modifyVolume(value, volumeFx);
    }

    private void modifyVolume(double value, SimpleDoubleProperty volume) {
        if (value >= 0 && value <= 100){
            volume.set(value / 100);
        }
    }
    public void stopMusic() {
        currentPlayback.stop();
    }
    public void muteMusic(boolean mute_music){
        currentPlayback.setMute(mute_music);
    }
    public SimpleDoubleProperty getMusicVolume(){
        return musicVolumeProperty;
    }

    public SimpleDoubleProperty getFXVolume(){
        return volumeFx;
    }

    public boolean musicIsMute() {
        return currentPlayback.isMute();
    }
}
