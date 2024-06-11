/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.it.map.b.adventure.gui;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
public class Sound {

    //attributo utilizzato per riprodurre i suoni
    private static Clip clip;

    //metodo che riproduce un suono a partire dal path indicato
    public static void playEffect(String path) {
        try {
            File soundPath = new File(path);
            if (soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
        } catch (Exception ex) {
            System.out.println("Errore nella riproduzione del suono.");
        }
    }

    //metodo che avvia la riproduzione della musica di sottofondo
    public static void startMusic() {
        try {
            File musicPath = new File("./src/main/resources/Sounds/MenuMusic.wav");

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);

                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Errore, file non trovato.");
            }
        } catch (HeadlessException | IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            System.out.println("Errore, impossibile riprodurre la musica." + ex);
        }
    }

    public static void stopMusic() {
        clip.stop();
        clip.close();
    }
}
