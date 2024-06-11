/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.it.map.b.adventures;

import di.uniba.it.map.b.adventure.gui.EngineGUI;
import di.uniba.it.map.b.adventure.parser.ParserOutput;
import di.uniba.it.map.b.adventure.type.AdvObject;
import di.uniba.it.map.b.adventure.type.Command;
import di.uniba.it.map.b.adventure.type.Room;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
public abstract class GameDescription {

    //lista che contiene item di tipo 'Room' utilizzata per memorizzare le stanze del gioco
    private final List<Room> rooms = new ArrayList<>();

    //lista che contiene item di tipo 'Command' utilizzata per memorizzare i comandi del gioco
    private final List<Command> commands = new ArrayList<>();

    //lista che contiene item di tipo 'AdvObject' utilizzata per memorizzare gli elementi dell'inventario
    private final List<AdvObject> inventory = new ArrayList<>();

    //lista che contiene tutti gli oggetti presenti nel gioco
    private final List<AdvObject> objects = new ArrayList<>();

    //variabile che indica la stanza attuale
    private Room currentRoom;

    //variabile che consente di verificare se il gioco è stato caricato o meno
    public static boolean loadedGame = false;

    //metodo che restituisce la lista contenente tutte le stanze del gioco
    public List<Room> getRooms() {
        return rooms;
    }

    //metodo che restituisce la lista contenente tutti i comandi del gioco
    public List<Command> getCommands() {
        return commands;
    }

    //metodo che restituisce la lista contenente tutti gli elementi presenti nell'inventario
    public List<AdvObject> getInventory() {
        return inventory;
    }

    //metodo che restituisce la lista contenente tutti gli oggetti presenti nel gioco
    public List<AdvObject> getObjects() {
        return objects;
    }

    //metodo che restituisce il valore della variabile 'currentRoom'
    public Room getCurrentRoom() {
        return currentRoom;
    }

    //metodo che permette di modificare il valore della variabile 'currentRoom'
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    //metodo che permette di stampare a video l'introduzione del gioco
    public abstract void Introduction();

    //metodo che permette di gestire l'inizializzazione del gioco;
    public abstract void startGame();

    //metodo che permette di gestire la successiva mossa nel gioco, riceve in input un item di tipo ParserOutput che contiene
    // le informazioni analizzate dal parser
    public abstract void nextMove(ParserOutput p, EngineGUI frame);

    //metodo che permette di stampre a video il finale del gioco
    public abstract void Conclusion();
}
