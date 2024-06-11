/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.it.map.b.adventures;

import di.uniba.it.map.b.adventure.database.Database;
import di.uniba.it.map.b.adventure.gui.EngineGUI;
import di.uniba.it.map.b.adventure.gui.StartMenu;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
public class Utils extends Thread {

    //variabile booleana che indica quando il timer è scaduto
    private static boolean timeOver = false;

    //variabile che indica se il thred è in esecuzione o meno
    public static boolean isRunning = true;

    //variabile che indica la durata in secondi
    private final int seconds;

    //variabile che indica la visualizzazione di game over
    private static boolean displayGameOver = true;

    //variabile che indica il tempo rimanente
    private static int remainingTime; //5 minuti in secondi       

    //variabile di tipo Object utilizzata per la sincronizzazione di porzioni di codice
    private static final Object LOCK = new Object();

    //costruttore con parametro 'seconds'
    public Utils(final int seconds) {
        this.seconds = seconds;
    }

    //metodo che legge il contenuto di un file passato come parametro e lo restituisce come stringa
    public static String readFile(String nomeFile) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            File file = new File(nomeFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                stringBuilder.append(line).append("\n");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Errore durante l'apertura del file: " + e.getMessage());
        }
        return stringBuilder.toString();
    }

    //metodo che carica i dati presenti su un file passato come parametro all'interno di un set di String e lo restituisce
    public static Set<String> loadFileListInSet(File file) throws IOException {
        Set<String> set = new HashSet<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Errore durante l'apertura del file: " + e.getMessage());
        }

        while (reader.ready()) {
            set.add(reader.readLine().trim().toLowerCase());
        }
        reader.close();
        return set;
    }

    //metodo che restituisce le singole parole presenti all'interno di una stringa passata come parametro
    //escludendo le stopwords
    public static List<String> parseString(String string, Set<String> stopwords) {
        List<String> tokens = new ArrayList<>();
        String[] split = string.toLowerCase().split("\\s+");
        for (String t : split) {
            if (!stopwords.contains(t)) {
                tokens.add(t);
            }
        }
        return tokens;
    }

    @Override
    public void run() {
        String time;
        remainingTime = seconds;
        try {
            for (int i = seconds; i >= 0; i--) {
                time = Integer.toString(i);
                if (!timeOver) {
                    EngineGUI.displayTime(time);
                } else {
                    EngineGUI.returnToMenu(time);
                }
                if (!getDisplayGameOver()) {
                    isRunning = false;
                    break;
                }
                remainingTime--;
                Thread.sleep(1000); // Pausa di 1 secondo
            }
            if (isRunning) {
                if (!timeOver) {
                    timeOver = true;
                    String gameOverText = " Il tempo ha disposizione è terminato! Non sei riuscito a portare \n"
                            + "a termine la tua missione ed ora sei sprofondato insieme alla piramide.\n"
                            + "In un altra vita, forse riuscirai in questa ardua impresa!";
                    EngineGUI.setTextDelay(gameOverText);
                    EngineGUI.disableInputArea();
                    Database.deletePlayerData();
                    EngineGUI.restartTimer();

                } else {
                    timeOver = false;
                    EngineGUI.closeWindow();
                    new StartMenu().setVisible(true);
                }
            }
        } catch (InterruptedException e) {
            // Il thread è stato interrotto
        }
    }

    //metodo che restituisce il tempo rimanente
    public static int getRemainingTime() {
        return remainingTime;
    }

    public static boolean getDisplayGameOver() {
        synchronized (LOCK) {
            return displayGameOver;
        }
    }

    public static void setDisplayGameOver(boolean value) {
        synchronized (LOCK) {
            displayGameOver = value;
        }
    }

}
