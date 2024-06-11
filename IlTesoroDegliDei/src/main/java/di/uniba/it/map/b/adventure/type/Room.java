/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.it.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */

/*
/ Classe che descrive le caratteristiche delle stanze presenti nel gioco
 */
public class Room {

    private int id = 0;
    private int floor;

    private String name;
    private String description;
    private String look;
    private String hint;

    private boolean locked = false;

    private Room south = null;
    private Room north = null;
    private Room east = null;
    private Room west = null;

    private String pathMiniMap = "/img/MiniMap_err.png";
    private String pathView = "/img/View_err.png";

    private final List<AdvObject> objects = new ArrayList<>();

    //costruttore con parametro 'id'
    public Room(int id) {
        this.id = id;
    }

    //costruttore con parametri id, floor e description
    public Room(int id, int floor, String name, String description) {
        this.id = id;
        this.floor = floor;
        this.name = name;
        this.description = description;
    }

    //metodo che restituisce il valore della variabile 'id'
    public int getId() {
        return id;
    }

    //metodo che restituisce il valore della variabie 'floor'
    public int getFloor() {
        return floor;
    }

    //metodo che restituisce il valore della variabile 'name'
    public String getName() {
        return name;
    }

    //metodo che permette di modificare il valore della variabile 'nome'
    public void setName(String name) {
        this.name = name;
    }

    //metodo che restituisce il valore della variabile 'description'
    public String getDescription() {
        return description;
    }

    //metodo che permette di modifcare il valore della variabile 'description'
    public void setDescription(String description) {
        this.description = description;
    }

    //metodo che restituisce il valore della variabile 'look'
    public String getLook() {
        return look;
    }

    //metodo che permette di modificare il valore della variabile 'look'
    public void setLook(String look) {
        this.look = look;
    }

    //metodo che restituisce il valore della variabile 'hint'
    public String getHint() {
        return hint;
    }

    //metodo che permette di modificare il valore della variabile 'hint'
    public void setHint(String hint) {
        this.hint = hint;
    }

    //metodo che restituisce il valore della variabile 'locked'
    public boolean isLocked() {
        return locked;
    }

    //metodo che permette di modificare il valore della variabile 'locked'
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    //metodo che restituisce il valore della variabile 'south'
    public Room getSouth() {
        return south;
    }

    //metodo che permette di modificare il valore della variabile 'south'
    public void setSouth(Room south) {
        this.south = south;
    }

    //metodo che restituisce il valore della variabile 'north'
    public Room getNorth() {
        return north;
    }

    //metodo che permette di modificare il valore della variabile 'north'
    public void setNorth(Room north) {
        this.north = north;
    }

    //metodo che restituisce il valore della variabile 'east'
    public Room getEast() {
        return east;
    }

    //metodo che permette di modificare il valore della variabile 'east'
    public void setEast(Room east) {
        this.east = east;
    }

    //metodo che restituisce il valore della variabile 'west'
    public Room getWest() {
        return west;
    }

    //metodo che permette di modificare il valore della variabile 'west'
    public void setWest(Room west) {
        this.west = west;
    }

    //metodo che restituisce il valore della variabile 'pathMiniMap'
    public String getPathMiniMap() {
        return pathMiniMap;
    }

    //metodo che permette di modificare il valore della variabile 'pathMiniMap'
    public void setPathMiniMap(String pathMiniMap) {
        this.pathMiniMap = pathMiniMap;
    }

    //metodo che restituisce il valore della variabile 'pathView'
    public String getPathView() {
        return pathView;
    }

    //metodo 
    public void setPathView(String pathView) {
        this.pathView = pathView;
    }

    //metodo che restituisce il valore della variabile 'objects'
    public List<AdvObject> getObjects() {
        return objects;
    }
}
