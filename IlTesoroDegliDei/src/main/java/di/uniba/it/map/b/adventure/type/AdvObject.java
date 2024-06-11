/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.it.map.b.adventure.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
public class AdvObject {

    private final int id; //variabile che indica l'id associato ad un item

    private String name; //variabile che indica il nome associato ad un item
    private String description;//variabile che indica la descrizione associato ad un item
    private String text; //variabile che contiene alcune caratteristiche dell'item

    private Set<String> alias; //insieme che contiene tutti i sinonimi di ciascun item

    private boolean openable = false; //variabile che indica se un item è apribile
    private boolean pickupable = false; //variabile che indica se un item può essere raccolto
    private boolean pushable = false; //variabile che indica se un item può essere spinto
    private boolean open = true; //variabile che indica se un item è aperto
    private boolean usable = false; //variabile che indica se un item può essere usato
    private boolean pushed = false; //variabile che indica se un item è stato premuto dall'utente
    private boolean taken = false; //variabile che indica se un item è stato già raccolto o meno
    private boolean hidden = false; //variabile che indica se un item risulta nascosto o meno
    private boolean lookable = false; //variabile che indica se un item è ispezionabile o meno
    private boolean readable = false; //variabile che indica se un item può essere letto o meno

    private int contained = 0; //variabile che indica se un item è contenuto in un altro item
    private int order = 0; //variabile che viene sfruttata per settare l'ordine in cui l'utente deve interagire con elementi per risolvere l'enigma

    //costruttore con parametro id
    public AdvObject(int id) {
        this.id = id;
    }

    //costruttore con parametri id e nome
    public AdvObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //costruttore con parametri id, nome e descrizione
    public AdvObject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //costruttore con parametri id, nome, descrizione e alias
    public AdvObject(int id, String name, String description, Set<String> alias) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    //metodo che restituisce il valore della variabile 'id'
    public int getId() {
        return id;
    }

    //metodo che restituisce il valore della variabile 'name'
    public String getName() {
        return name;
    }

    //metodo che modifica il valore della variabile 'name'
    public void setName(String name) {
        this.name = name;
    }

    //metodo che restituisce il valore della variabile 'description'
    public String getDescription() {
        return description;
    }

    //metodo che modifica il valore della variabile 'description'
    public void setDescritpion(String description) {
        this.description = description;
    }

    //metodo che restituisce il valore della variabile 'look'
    public String getText() {
        return text;
    }

    //metodo che permette di modificare il valore della variabile 'look'
    public void setText(String text) {
        this.text = text;
    }

    //metodo che restituisce il valore della variabile 'alias'
    public Set<String> getAlias() {
        return alias;
    }

    //metodo che modifica il valore della variabile 'alias'
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    //metodo che modifica il valore della variabile 'alias'
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    //metodo che restituisce il valore della variabile 'openable'
    public boolean isOpenable() {
        return openable;
    }

    //metodo che permette di modificare il valore della variabile 'openable'
    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    //metodo che restituisce il valore della variabile 'pickupable'
    public boolean isPickupable() {
        return pickupable;
    }

    //metodo che permette di modificare il valore della variabile 'pickupable'
    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
    }

    //metodo che restituisce il valore della variabile 'pushable'
    public boolean isPushable() {
        return pushable;
    }

    //metodo che permette di modificare il valore della variabile 'pushable'
    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }

    //metodo che restituisce il valore della variabile 'open'
    public boolean isOpen() {
        return open;
    }

    //metodo che permette di modficare il valore della variabile 'open'
    public void setOpen(boolean open) {
        this.open = open;
    }

    //metodo che restituisce il valore della variabile 'usable'
    public boolean isUsable() {
        return usable;
    }

    //metodo che permette di modifica il valore della variabile 'usable'
    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    //metodo che restituisce il valore della variabile 'pushed'
    public boolean isPushed() {
        return pushed;
    }

    //metodo che permette di modificare il valore della variabile 'pushed'
    public void setPushed(boolean pushed) {
        this.pushed = pushed;
    }

    //metodo che restituisce il valore della variabile 'taken'
    public boolean isTaken() {
        return taken;
    }

    //metodo che permette di modificare il valore della variabile 'taken'
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    //metodo che restituisce il valore della variabile 'hidden'
    public boolean isHidden() {
        return hidden;
    }

    //metodo che permette di modificare il valore della variabile 'hidden'
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    //metodo che restituisce il valore della variabile 'lookable'
    public boolean isLookable() {
        return lookable;
    }

    //metodo che permette di modificare il valore della variabile 'lookable'
    public void setLookable(boolean lookable) {
        this.lookable = lookable;
    }

    //metodo che restituisce il valore della variabile 'readable'
    public boolean isReadable() {
        return readable;
    }

    //metodo che permette di modificare il valore della variabile 'readable'
    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    //metodo che restituisce il valore della variabile 'contained'
    public int getContained() {
        return contained;
    }

    //metodo che permette di modificare il valore della variabile 'contained'
    public void setContained(int contained) {
        this.contained = contained;
    }

    //metodo che restituisce il valore della variabile 'order'
    public int getOrder() {
        return order;
    }

    //metodo che permette di modificare il valore della variabile 'order'
    public void setOrder(int order) {
        this.order = order;
    }
}
